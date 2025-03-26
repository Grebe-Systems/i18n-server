package systems.grebe.i18n.database;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import systems.grebe.i18n.database.i18n.Locale;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class DBObject {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private UUID id;

	@Version
	@Column(name = "version", nullable = false)
	private Long version;

	@Override
	public final boolean equals(Object o) {
		if(o == null) return false;
		if(o instanceof DBObject obj) {
			return Objects.equals(id, obj.id);
		}
		return Objects.equals(o, this);
	}


	@Override
	public final int hashCode() {
		return Objects.hash(id);
	}
}