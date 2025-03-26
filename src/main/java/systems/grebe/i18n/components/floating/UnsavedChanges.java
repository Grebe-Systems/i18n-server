package systems.grebe.i18n.components.floating;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.BeforeLeaveObserver;
import com.vaadin.flow.shared.Registration;

public class UnsavedChanges extends HorizontalLayout implements BeforeLeaveObserver {

	private final Button saveButton;
	private final Button revertButton;

	private Registration saveRegistration;
	private Registration revertRegistration;


	public UnsavedChanges() {
		saveButton = new Button("Save");
		saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
		saveButton.getStyle().set("border-top-left-radius", "0px");
		saveButton.getStyle().set("border-bottom-left-radius", "0px");
		revertButton = new Button("Cancel");
		revertButton.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
		revertButton.getStyle().set("border-top-right-radius", "0px");
		revertButton.getStyle().set("border-bottom-right-radius", "0px");
		getStyle().setPosition(Style.Position.ABSOLUTE);
		getStyle().setZIndex(100);
		getStyle().setBottom("var(--lumo-space-s)");
		getStyle().setRight("var(--lumo-space-s)");
		setSpacing(false);
		add(revertButton, saveButton);
	}


	public void setSaveListener(ComponentEventListener<ClickEvent<Button>> clickEvent) {
		if (saveRegistration != null) {
			saveRegistration.remove();
		}
		saveRegistration = saveButton.addClickListener(clickEvent);
	}


	public void setRevertListener(ComponentEventListener<ClickEvent<Button>> clickEvent) {
		if (revertRegistration != null) {
			revertRegistration.remove();
		}
		revertRegistration = revertButton.addClickListener(clickEvent);
	}


	@Override
	public void beforeLeave(BeforeLeaveEvent beforeLeaveEvent) {
		if (this.isAttached() && this.isVisible()) {
			// Es sind noch offene Changes auf der Seite
			beforeLeaveEvent.postpone().cancel();
		}
	}

	public void open() {
		if(!this.isAttached()) {
			systems.grebe.i18n.util.ComponentUtil.attachModal(UI.getCurrent(), this);
		}
		if(!this.isVisible()) {
			this.setVisible(true);
		}
	}

	public void close() {
		if(this.isVisible()) {
			this.setVisible(false);
		}
		if(this.isAttached()) {
			this.removeFromParent();
		}
	}

	public void fireChangesSavedEvent() {
		this.fireEvent(new ChangesSavedEvent(this, false));
	}

	public void fireChangesRevertedEvent() {
		this.fireEvent(new ChangesRevertedEvent(this, false));
	}
	public void fireOpenChangesEvent() {
		this.fireEvent(new OpenChangesEvent(this, false));
	}

	public Registration addChangesSavedListener(ComponentEventListener<ChangesSavedEvent> listener) {
		return addListener(ChangesSavedEvent.class, listener);
	}

	public Registration addChangesRevertedListener(ComponentEventListener<ChangesRevertedEvent> listener) {
		return addListener(ChangesRevertedEvent.class, listener);
	}

	public Registration addOpenChangesListener(ComponentEventListener<OpenChangesEvent> listener) {
		return addListener(OpenChangesEvent.class, listener);
	}

	public Registration addCloseChangesListener(ComponentEventListener<CloseChangesEvent> listener) {
		return addListener(CloseChangesEvent.class, listener);
	}

	public static class OpenChangesEvent extends ComponentEvent<Component> {

		public OpenChangesEvent(Component source, boolean fromClient) {
			super(source, fromClient);
		}
	}

	public static class CloseChangesEvent extends ComponentEvent<Component> {

		public CloseChangesEvent(Component source, boolean fromClient) {
			super(source, fromClient);
		}
	}

	public static class ChangesSavedEvent extends ComponentEvent<Component> {

		public ChangesSavedEvent(Component source, boolean fromClient) {
			super(source, fromClient);
		}
	}

	public static class ChangesRevertedEvent extends ComponentEvent<Component> {

		public ChangesRevertedEvent(Component source, boolean fromClient) {
			super(source, fromClient);
		}
	}
}


