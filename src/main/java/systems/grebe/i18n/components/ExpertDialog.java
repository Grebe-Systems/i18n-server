package systems.grebe.i18n.components;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import lombok.Getter;

public class ExpertDialog<BEAN> extends Dialog {

	@Getter
	private Binder<BEAN> binder;
	@Getter
	private Class<BEAN> beanType;

	@Getter
	private FormLayout layout;


	public ExpertDialog(Binder<BEAN> binder, Class<BEAN> beanClass) {
		this.binder = binder;
		this.beanType = beanClass;
		setModal(true);
		setDraggable(true);
		setCloseOnEsc(false);
		setCloseOnOutsideClick(false);
		init();
	}

	private void init() {
		layout = new FormLayout();
		layout.setSizeFull();
		add(layout);
	}


	@Override
	protected void onAttach(AttachEvent attachEvent) {
		super.onAttach(attachEvent);
		Button x = new Button(VaadinIcon.CLOSE.create());
		x.addClickListener(e -> {
			ComponentUtil.fireEvent(this, new ExpertDialogStatusEvent(this, false, ExpertDialogStatus.CLOSED));
			close();
		});
		getHeader().add(x);
		Button cancel = new Button("Cancel");
		cancel.addClickListener(e -> {
			ComponentUtil.fireEvent(this, new ExpertDialogStatusEvent(this, false, ExpertDialogStatus.CANCELED));
			close();
		});
		getFooter().add(cancel);
		Button done = new Button("Done");
		done.addClickListener(e -> {
			ComponentUtil.fireEvent(this, new ExpertDialogStatusEvent(this, false, ExpertDialogStatus.SUCCESS));
			close();
		});
		getFooter().add(done);
	}

	public Registration addExpertDialogStatusListener(ComponentEventListener<ExpertDialogStatusEvent> listener) {
		return addListener(ExpertDialogStatusEvent.class, listener);
	}

	public static class ExpertDialogStatusEvent extends ComponentEvent<ExpertDialog> {

		@Getter
		private ExpertDialogStatus status;

		public ExpertDialogStatusEvent(ExpertDialog source, boolean fromClient, ExpertDialogStatus status) {
			super(source, fromClient);
			this.status = status;
		}
	}

	public enum ExpertDialogStatus {
		SUCCESS,
		CANCELED,
		CLOSED
	}

}
