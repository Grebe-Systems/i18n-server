package systems.grebe.i18n.components;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.BeforeLeaveObserver;
import com.vaadin.flow.router.Layout;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.menu.MenuConfiguration;
import com.vaadin.flow.server.menu.MenuEntry;
import com.vaadin.flow.theme.lumo.LumoUtility;
import systems.grebe.i18n.components.floating.UnsavedChanges;
import java.util.List;

@Layout
public class CustomAppLayout extends AppLayout implements BeforeLeaveObserver {

	private H1 viewTitle;
	private UnsavedChanges unsavedChanges = new UnsavedChanges();

	public CustomAppLayout() {
		setPrimarySection(Section.DRAWER);
		addDrawerContent();
		addHeaderContent();
	}

	private void addHeaderContent() {
		DrawerToggle toggle = new DrawerToggle();
		toggle.setAriaLabel("Menu toggle");

		viewTitle = new H1();
		viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
		addToNavbar(true, toggle, viewTitle);
	}

	private void addDrawerContent() {
		Header header = new Header();

		Scroller scroller = new Scroller(createNavigation());

		addToDrawer(header, scroller, createFooter());
	}

	private SideNav createNavigation() {
		SideNav nav = new SideNav();
		List<MenuEntry> menuEntries = MenuConfiguration.getMenuEntries();
		menuEntries.forEach(entry -> {
			if (entry.icon() != null) {
				nav.addItem(new SideNavItem(entry.title(), entry.path(), VaadinIcon.valueOf(entry.icon()).create()));
			} else {
				nav.addItem(new SideNavItem(entry.title(), entry.path()));
			}
		});
		return nav;
	}

	private Footer createFooter() {
		Footer layout = new Footer();

		return layout;
	}


	@Override
	protected void onAttach(AttachEvent attachEvent) {
		super.onAttach(attachEvent);
		ComponentUtil.addListener(UI.getCurrent(), UnsavedChanges.OpenChangesEvent.class, e -> unsavedChanges.open());
		unsavedChanges.setSaveListener(e -> {
			ComponentUtil.fireEvent(UI.getCurrent(), new UnsavedChanges.ChangesSavedEvent(this, false));
			unsavedChanges.close();
		});
		unsavedChanges.setRevertListener(e -> {
			ComponentUtil.fireEvent(UI.getCurrent(), new UnsavedChanges.ChangesRevertedEvent(this, false));
			unsavedChanges.close();
		});
		unsavedChanges.addCloseChangesListener(e -> unsavedChanges.close());
		systems.grebe.i18n.util.ComponentUtil.listenGlobally(VaadinSession.getCurrent(), UnsavedChanges.CloseChangesEvent.class, e -> unsavedChanges.close());
	}


	@Override
	protected void afterNavigation() {
		super.afterNavigation();
		viewTitle.setText(getCurrentPageTitle());
	}

	private String getCurrentPageTitle() {
		return MenuConfiguration.getPageHeader(getContent()).orElse("");
	}


	@Override
	public void beforeLeave(BeforeLeaveEvent beforeLeaveEvent) {
		// Close changes if there are any?
		BeforeLeaveEvent.ContinueNavigationAction action = beforeLeaveEvent.postpone();
		unsavedChanges.close();
		action.proceed();
	}
}
