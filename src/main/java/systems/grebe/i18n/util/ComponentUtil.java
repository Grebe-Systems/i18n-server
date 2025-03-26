package systems.grebe.i18n.util;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.internal.StateTree;
import com.vaadin.flow.router.NavigationTrigger;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.Registration;

import java.util.concurrent.atomic.AtomicReference;

public class ComponentUtil {

	/**
	 * Attaches a modal component to the specified UI. This ensures that the component is
	 * added to the modal context of the UI, and handles its lifecycle management based on
	 * navigation events or other triggers.
	 *
	 * @param ui the UI instance to which the modal component will be attached. Must not be null.
	 * @param component the component to be attached as a modal. Must not be null.
	 */
	public static void attachModal(UI ui, Component component) {
		StateTree.ExecutionRegistration addToUiRegistration = ui.beforeClientResponse(ui, (context) -> {
			if (component.getElement().getNode().getParent() == null) {
				ui.addToModalComponent(component);
				ui.setChildComponentModal(component, false);
			}
		});
		if (ui.getSession() != null) {
			AtomicReference<Registration> registrationRef = new AtomicReference<>();
			registrationRef.set(ui.addAfterNavigationListener((event) -> {
				if (event.getLocationChangeEvent().getTrigger() == NavigationTrigger.PROGRAMMATIC) {
					addToUiRegistration.remove();
					if(registrationRef.get() != null) {
						registrationRef.get().remove();
					}
				}
			}));
		}
	}

	/**
	 * Registers a global listener for a specified component event within a given Vaadin session.
	 * This listener will be notified of all instances of the specified event type that occur globally
	 * in the session.
	 *
	 * @param <T> the type of the component event to listen for
	 * @param session the Vaadin session in which the listener will operate
	 * @param componentEvent the class type of the event to listen for
	 * @param listener the event listener to be registered
	 * @return the registration object that can be used to remove the listener later, or null if the session
	 *         is null or if the session's lock is held
	 */
	public static <T extends ComponentEvent<?>> Registration listenGlobally(VaadinSession session, Class<T> componentEvent, ComponentEventListener<T> listener) {
		if(session == null) {
			return null;
		}
		if(session.hasLock()) {
			return null;
		}
		return com.vaadin.flow.component.ComponentUtil.addListener(session.getAttribute(EventBusComponent.class), componentEvent, listener);
	}

	/**
	 * Fires a component event globally within the provided Vaadin session.
	 * The event is dispatched to an EventBusComponent associated with the session.
	 * If the session is null or the session has a lock, the event will not be fired.
	 *
	 * @param <T>    the type of the component associated with the event
	 * @param session the Vaadin session where the event will be fired, should not be null
	 * @param event   the component event to fire, should not be null
	 */
	public static <T extends Component> void fireEventGlobally(VaadinSession session, ComponentEvent<T> event) {
		if(session == null) {
			return;
		}
		if(session.hasLock()) {
			return;
		}
		com.vaadin.flow.component.ComponentUtil.fireEvent(session.getAttribute(EventBusComponent.class), event);
	}

}

