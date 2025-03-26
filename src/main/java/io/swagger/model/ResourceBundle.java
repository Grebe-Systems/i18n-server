package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import io.swagger.configuration.NotUndefined;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ResourceBundle
 */
@Validated
@NotUndefined
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-03-26T16:42:46.826388125Z[GMT]")
public class ResourceBundle {

	@JsonProperty("locale")
	@JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
	@JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
	private Locale locale = null;

	@JsonProperty("project")
	@JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
	@JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
	private Project project = null;

	@JsonProperty("translations")
	@Valid
	private List<Translation> translations = null;

	@JsonProperty("version")
	@JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
	@JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
	private String version = null;


	public ResourceBundle locale(Locale locale) {

		this.locale = locale;
		return this;
	}


	/**
	 * Get locale
	 *
	 * @return locale
	 **/

	@Schema(description = "")

	@Valid
	public Locale getLocale() {
		return locale;
	}


	public void setLocale(Locale locale) {
		this.locale = locale;
	}


	public ResourceBundle project(Project project) {

		this.project = project;
		return this;
	}


	/**
	 * Get project
	 *
	 * @return project
	 **/

	@Schema(description = "")

	@Valid
	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}


	public ResourceBundle translations(List<Translation> translations) {

		this.translations = translations;
		return this;
	}


	public ResourceBundle addTranslationsItem(Translation translationsItem) {
		if (this.translations == null) {
			this.translations = new ArrayList<Translation>();
		}
		this.translations.add(translationsItem);
		return this;
	}


	/**
	 * Get translations
	 *
	 * @return translations
	 **/

	@Schema(description = "")
	@Valid
	public List<Translation> getTranslations() {
		return translations;
	}


	public void setTranslations(List<Translation> translations) {
		this.translations = translations;
	}


	public ResourceBundle version(String version) {

		this.version = version;
		return this;
	}


	/**
	 * Get version
	 *
	 * @return version
	 **/

	@Schema(description = "")

	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ResourceBundle resourceBundle = (ResourceBundle) o;
		return Objects.equals(this.locale, resourceBundle.locale) &&
				Objects.equals(this.project, resourceBundle.project) &&
				Objects.equals(this.translations, resourceBundle.translations) &&
				Objects.equals(this.version, resourceBundle.version);
	}


	@Override
	public int hashCode() {
		return Objects.hash(locale, project, translations, version);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ResourceBundle {\n");

		sb.append("    locale: ").append(toIndentedString(locale)).append("\n");
		sb.append("    project: ").append(toIndentedString(project)).append("\n");
		sb.append("    translations: ").append(toIndentedString(translations)).append("\n");
		sb.append("    version: ").append(toIndentedString(version)).append("\n");
		sb.append("}");
		return sb.toString();
	}


	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
