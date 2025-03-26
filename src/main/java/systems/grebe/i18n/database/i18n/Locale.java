package systems.grebe.i18n.database.i18n;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import systems.grebe.i18n.database.DBObject;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "locale", indexes = {
		@Index(name = "idx_locale_code", columnList = "code")
})
public class Locale extends DBObject {

	private String code;
	private String name;

	@OneToMany(mappedBy = "locale", orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<ResourceBundle> resourceBundles = new LinkedHashSet<>();


	@ManyToMany(mappedBy = "locales", fetch = FetchType.EAGER)
	private Set<Project> projects = new LinkedHashSet<>();

}