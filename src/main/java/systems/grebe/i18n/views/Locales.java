package systems.grebe.i18n.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Locales")
@Route(value = "/locales")
@Menu(order = 2, title = "Locales", icon = "COMMENTS")
public class Locales extends Composite<SplitLayout> {
}
