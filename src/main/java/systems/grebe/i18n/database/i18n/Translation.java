package systems.grebe.i18n.database.i18n;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import systems.grebe.i18n.database.DBObject;

@Getter
@Setter
@Entity
@Table(name = "translation", indexes = {
		@Index(name = "idx_translation", columnList = "translationKey")
})
public class Translation extends DBObject {

	private String translationKey;
	private String translationValue;

	@ManyToOne
	@JoinColumn(name = "resource_bundle_id")
	private ResourceBundle resourceBundle;

}