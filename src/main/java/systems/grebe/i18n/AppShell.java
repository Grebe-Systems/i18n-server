package systems.grebe.i18n;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.ServiceException;
import com.vaadin.flow.server.SessionInitEvent;
import com.vaadin.flow.server.SessionInitListener;
import com.vaadin.flow.shared.communication.PushMode;
import com.vaadin.flow.shared.ui.Transport;
import com.vaadin.flow.theme.Theme;
import jakarta.annotation.PostConstruct;
import systems.grebe.i18n.util.EventBusComponent;

/**
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 */
@Push(value = PushMode.AUTOMATIC, transport = Transport.WEBSOCKET_XHR)
@PWA(name = "I18n-Server", shortName = "I18n")
public class AppShell implements AppShellConfigurator, SessionInitListener {

	@Override
	public void sessionInit(SessionInitEvent sessionInitEvent) throws ServiceException {
		sessionInitEvent.getSession().setAttribute(EventBusComponent.class, new EventBusComponent());
	}
}
