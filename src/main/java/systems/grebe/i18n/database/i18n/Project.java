package systems.grebe.i18n.database.i18n;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import systems.grebe.i18n.database.DBObject;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "project")
public class Project extends DBObject {

  private String name;
  private String description;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "project_locales",
          joinColumns = @JoinColumn(name = "project_id"),
          inverseJoinColumns = @JoinColumn(name = "locales_id"))
  private Set<Locale> locales = new LinkedHashSet<>();

  @OneToMany(mappedBy = "project", orphanRemoval = true, fetch = FetchType.EAGER)
  private Set<ProjectVersion> projectVersions = new LinkedHashSet<>();

  public ProjectVersion getLatestVersion() {
    return projectVersions.stream().filter(p -> p.getActive() != null).filter(ProjectVersion::getActive).findFirst().orElse(null);
  }

}