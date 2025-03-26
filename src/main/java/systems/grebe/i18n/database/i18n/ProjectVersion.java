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
@Table(name = "project_version")
public class ProjectVersion extends DBObject {

	private Boolean active;
	private Long major;
	private Long minor;
	private Long patch;


	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;

	@OneToMany(mappedBy = "projectVersion", orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<ResourceBundle> resourceBundles = new LinkedHashSet<>();

	public String getVersionName() {
		return String.format("%d.%d.%d", major, minor, patch);
	}

}