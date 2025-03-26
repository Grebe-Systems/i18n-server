package systems.grebe.i18n.util;

import com.vaadin.flow.data.provider.DataProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

public class DataProviderUtil {

	public static <T> DataProvider<T, Void> createForRepository(JpaRepository<T, ?> repository) {
		DataProvider<T, ?> dataProvider = DataProvider.fromCallbacks(
				query -> {
					Pageable p = Pageable.ofSize(query.getPageSize()).withPage(query.getPage());
					return repository.findAll(p).stream();
				},
				query -> Math.toIntExact(repository.count()));
		return (DataProvider<T, Void>) dataProvider;
	}

}
