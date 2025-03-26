package systems.grebe.i18n.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Home")
@Route(value = "")
@Menu(order = 0, title = "Home", icon = "HOME")
public class Home extends Composite<VerticalLayout> {

	public Home() {

	}
}
