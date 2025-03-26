package systems.grebe.i18n.util;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {

	@Getter
	private static ApplicationContext context;
	@Getter
	private static ApplicationEventPublisher eventPublisher;


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
		eventPublisher = applicationContext;
	}

}
