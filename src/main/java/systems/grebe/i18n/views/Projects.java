package systems.grebe.i18n.views;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.TabSheetVariant;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import systems.grebe.i18n.components.ExpertDialog;
import systems.grebe.i18n.components.floating.UnsavedChanges;
import systems.grebe.i18n.database.i18n.*;
import systems.grebe.i18n.util.ConversionUtil;
import systems.grebe.i18n.util.DataProviderUtil;
import systems.grebe.i18n.util.SpringUtil;

import java.util.Set;

@PageTitle("Projects")
@Route(value = "/projects")
@Menu(order = 1, title = "Projects", icon = "CHART_GRID")
public class Projects extends Composite<SplitLayout> {

	private final Binder<Project> binder = new Binder<>(Project.class);
	private Grid<Project> grid;

	public Projects(ProjectRepository projectRepository) {
		this.grid = initProjectGrid(projectRepository);
		TabSheet tabSheet = initTabSheet();
		tabSheet.add("Details", initDetails());
		tabSheet.add("Versions", initVersionHistory());
	}

	/**
	 * Initializes and configures a {@code Grid} component for displaying and managing {@code Project} entities.
	 * The grid includes columns, a data provider, selection handling, and a context menu for creating and
	 * deleting projects. The grid is contained in a vertically scrolling container.
	 *
	 * @param projectRepository the {@code ProjectRepository} instance used to fetch and persist {@code Project} entities
	 * @return a {@code Grid<Project>} instance configured for project display and management
	 */
	private Grid<Project> initProjectGrid(ProjectRepository projectRepository) {
		getContent().setSizeFull();
		Scroller scroller = new Scroller();

		Grid<Project> grid = new Grid<>(Project.class, false);
		grid.addColumn(Project::getName).setHeader("Name");
		grid.setDataProvider(DataProviderUtil.createForRepository(projectRepository));
		grid.setHeightFull();
		grid.setSelectionMode(Grid.SelectionMode.SINGLE);
		GridContextMenu<Project> contextMenu = grid.addContextMenu();
		contextMenu.addItem(" New", event -> {
			binder.setBean(new Project());
		}).addComponentAsFirst(VaadinIcon.PLUS.create());
		contextMenu.addItem(" Delete", event -> {
			Project p = grid.asSingleSelect().getValue();
			if(p == null) {
				return;
			}
			projectRepository.delete(p);
			this.grid.getDataProvider().refreshAll();
		}).addComponentAsFirst(VaadinIcon.TRASH.create());
		grid.addSelectionListener(event -> binder.setBean(grid.asSingleSelect().getValue()));
		scroller.setScrollDirection(Scroller.ScrollDirection.VERTICAL);
		scroller.setContent(grid);
		getContent().addToPrimary(scroller);
		return grid;
	}

	/**
	 * Initializes and configures a TabSheet component for the application.
	 * The TabSheet is styled using the LUMO_TABS_CENTERED theme variant
	 * and added to the secondary layout of the current content container.
	 *
	 * @return a {@code TabSheet} instance configured with application-wide settings.
	 */
	private TabSheet initTabSheet() {
		TabSheet tabSheet = new TabSheet();
		tabSheet.addThemeVariants(TabSheetVariant.LUMO_TABS_CENTERED);
		getContent().addToSecondary(tabSheet);
		return tabSheet;
	}

	/**
	 * Initializes and configures a FormLayout for editing and displaying project details.
	 * The layout includes fields for the project's name, description, and a version switcher
	 * for managing the active version of the project. The details form is fully responsive
	 * and dynamically updates the project's data bindings. It also integrates listeners to
	 * handle unsaved changes and save events.
	 *
	 * @return a {@code FormLayout} instance configured with project detail fields and their bindings.
	 */
	private FormLayout initDetails() {
		FormLayout details = new FormLayout();
		details.setSizeFull();

		TextField nameComponent = new TextField();
		nameComponent.setWidthFull();
		nameComponent.setRequiredIndicatorVisible(true);
		nameComponent.setRequired(true);
		binder.forField(nameComponent).bind(Project::getName, Project::setName);

		TextArea descriptionComponent = new TextArea();
		descriptionComponent.setWidthFull();
		descriptionComponent.setMinRows(10);
		binder.forField(descriptionComponent).bind(Project::getDescription, Project::setDescription);

		FormLayout.FormItem name = details.addFormItem(nameComponent, "Name");
		FormLayout.FormItem versionSwitcher = details.addFormItem(initVersionSwitcher(), "Active Version");
		FormLayout.FormItem locales = details.addFormItem(initLocales(), "Locales");
		FormLayout.FormItem description = details.addFormItem(descriptionComponent, "Description");
		details.setColspan(name, 1);
		details.setColspan(versionSwitcher, 1);
		details.setColspan(locales, 2);
		details.setColspan(description, 2);

		ComponentUtil.addListener(UI.getCurrent(), UnsavedChanges.ChangesSavedEvent.class, e -> {
			if(binder.getBean() != null) {
				SpringUtil.getContext().getBean(ProjectRepository.class).save(binder.getBean());
				this.grid.getDataProvider().refreshAll();
			}
		});

		binder.addValueChangeListener(event -> {
			ComponentUtil.fireEvent(UI.getCurrent(), new UnsavedChanges.OpenChangesEvent(this, false));
		});
		return details;
	}


	/**
	 * Initializes a ComboBox for selecting and managing the active version of a project.
	 * The ComboBox is dynamically updated based on the project selected in the grid
	 * and provides functionality to handle version activation and persistence.
	 *
	 * @return A ComboBox configured for managing the active version of a project.
	 */
	private ComboBox<ProjectVersion> initVersionSwitcher() {
		ComboBox<ProjectVersion> activeVersion = new ComboBox<>();
		activeVersion.setItemLabelGenerator(ProjectVersion::getVersionName);
		activeVersion.setWidthFull();
		activeVersion.setRequiredIndicatorVisible(true);
		activeVersion.setRequired(true);
		activeVersion.addValueChangeListener(event -> {
			ProjectVersion pv = event.getValue();
			if(pv == null || binder.getBean() == null) {
				return;
			}
			if(binder.getBean().getLatestVersion() == null) {
				pv.setActive(true);
				SpringUtil.getContext().getBean(ProjectVersionRepository.class).save(pv);
				return;
			}
			if(binder.getBean().getLatestVersion().equals(pv)) {
				return;
			}
			binder.getBean().getLatestVersion().setActive(false);
			pv.setActive(true);
			SpringUtil.getContext().getBean(ProjectVersionRepository.class).save(pv);
		});
		activeVersion.setEnabled(false);
		this.grid.addSelectionListener(event -> {
			Project p = this.grid.asSingleSelect().getValue();
			if(p == null) {
				activeVersion.setEnabled(false);
				activeVersion.setItems();
				return;
			}
			activeVersion.setEnabled(true);
			Set<ProjectVersion> versions = p.getProjectVersions();
			activeVersion.setItems(versions);
			activeVersion.setValue(p.getLatestVersion());

		});
		return activeVersion;
	}

	/**
	 * Initializes and configures a version history layout using a vertical layout and a grid.
	 * The version history displays a list of {@code ProjectVersion} entities with their
	 * attributes such as active status, major, minor, and patch versions.
	 * <p>
	 * It includes the following features:
	 * - A grid for displaying version information.
	 * - Read-only checkboxes to indicate if a version is active.
	 * - Context menu options for creating a new version.
	 * - Dynamic updates of grid items based on the selected {@code Project}.
	 *
	 * @return a {@code VerticalLayout} component configured to display version history.
	 */
	private VerticalLayout initVersionHistory() {
		VerticalLayout versionHistory = new VerticalLayout();
		versionHistory.setPadding(false);
		versionHistory.setMargin(false);
		versionHistory.setSizeFull();

		Grid<ProjectVersion> grid = new Grid<>(ProjectVersion.class, false);
		grid.addComponentColumn(projectVersion -> {
			Boolean value = projectVersion.getActive();
			if(value == null) {
				value = false;
			}
			Checkbox checkbox = new Checkbox(value);
			checkbox.setEnabled(false);
			return checkbox;
		}).setHeader("Active");
		grid.addColumn(ProjectVersion::getMajor).setHeader("Major");
		grid.addColumn(ProjectVersion::getMinor).setHeader("Minor");
		grid.addColumn(ProjectVersion::getPatch).setHeader("Patch");
		grid.setEnabled(false);
		versionHistory.setSizeFull();
		versionHistory.add(grid);
		this.grid.addSelectionListener(event -> {
			Project p = this.grid.asSingleSelect().getValue();
			if(p == null) {
				// Need to unset all data from Version-History
				grid.setEnabled(false);
				grid.setItems();
				return;
			}
			grid.setEnabled(true);
			Set<ProjectVersion> versions = p.getProjectVersions();
			grid.setItems(versions);
		});

		GridContextMenu<ProjectVersion> contextMenu = grid.addContextMenu();
		contextMenu.addItem(" New", event -> {
			createNewProjectVersion().open();
		}).addComponentAsFirst(VaadinIcon.PLUS.create());

		return versionHistory;
	}



	/**
	 * Creates a new {@link ExpertDialog} instance configured for handling {@link ProjectVersion} entities.
	 * The dialog is preconfigured with a form layout for collecting and binding version details,
	 * including major, minor, and patch numbers. Upon successful submission, the new project version
	 * is associated with the current project and saved to the database.
	 *
	 * @return an {@link ExpertDialog} for creating and managing a new {@link ProjectVersion}.
	 */
	private ExpertDialog<ProjectVersion> createNewProjectVersion() {
		Binder<ProjectVersion> versionBinder = new Binder<>(ProjectVersion.class);
		versionBinder.setBean(new ProjectVersion());
		ExpertDialog<ProjectVersion> dialog = new ExpertDialog<>(versionBinder, ProjectVersion.class);
		dialog.addExpertDialogStatusListener(statusEvent -> {
			ExpertDialog.ExpertDialogStatus status = statusEvent.getStatus();
			if(ExpertDialog.ExpertDialogStatus.SUCCESS.equals(status)) {
				versionBinder.getBean().setProject(binder.getBean());
				SpringUtil.getContext().getBean(ProjectVersionRepository.class).save(versionBinder.getBean());
			}
		});
		dialog.setHeaderTitle("New Version");
		NumberField major = new NumberField();
		major.setMin(0);
		major.setRequiredIndicatorVisible(true);
		major.setRequired(true);
		NumberField minor = new NumberField();
		minor.setMin(0);
		minor.setRequiredIndicatorVisible(true);
		minor.setRequired(true);
		NumberField patch = new NumberField();
		patch.setMin(0);
		patch.setRequiredIndicatorVisible(true);
		patch.setRequired(true);
		dialog.getLayout().addFormItem(major, "Major");
		dialog.getLayout().addFormItem(minor, "Minor");
		dialog.getLayout().addFormItem(patch, "Patch");
		versionBinder.forField(major).withConverter(ConversionUtil.doubleToLongConverter).bind(ProjectVersion::getMajor, ProjectVersion::setMajor);
		versionBinder.forField(minor).withConverter(ConversionUtil.doubleToLongConverter).bind(ProjectVersion::getMinor, ProjectVersion::setMinor);
		versionBinder.forField(patch).withConverter(ConversionUtil.doubleToLongConverter).bind(ProjectVersion::getPatch, ProjectVersion::setPatch);
		return dialog;
	}

	private MultiSelectComboBox<Locale> initLocales() {
		MultiSelectComboBox<Locale> locales = new MultiSelectComboBox<>();
		locales.setWidthFull();
		locales.setAllowCustomValue(false);
		locales.setEnabled(false);
		this.grid.addSelectionListener(event -> {
			Project p = this.grid.asSingleSelect().getValue();
			if(p == null) {
				locales.setItems();
				locales.setValue();
				locales.setEnabled(false);
				return;
			}
			locales.setEnabled(true);
			locales.setItems(SpringUtil.getContext().getBean(LocaleRepository.class).findAll());
			locales.setValue(p.getLocales());
		});
		locales.addValueChangeListener(event -> {
			Project p = this.grid.asSingleSelect().getValue();
			if(p == null) {
				return;
			}
			p.getLocales().clear();
			p.getLocales().addAll(event.getValue());
			ComponentUtil.fireEvent(UI.getCurrent(), new UnsavedChanges.OpenChangesEvent(this, false));
		});
		return locales;
	}


}
