package systems.grebe.i18n.database.i18n;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import systems.grebe.i18n.database.DBObject;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "resource_bundle")
public class ResourceBundle extends DBObject {

	@OneToMany(mappedBy = "resourceBundle", orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Translation> translations = new LinkedHashSet<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "locale_id")
	private Locale locale;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_version_id")
	private ProjectVersion projectVersion;

}