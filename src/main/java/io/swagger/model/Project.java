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
 * Project
 */
@Validated
@NotUndefined
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-03-26T16:42:46.826388125Z[GMT]")
public class Project {

	@JsonProperty("name")
	@JsonInclude(JsonInclude.Include.NON_ABSENT)
	@JsonSetter(nulls = Nulls.FAIL)
	private String name = null;

	@JsonProperty("id")
	@JsonInclude(JsonInclude.Include.NON_ABSENT)
	@JsonSetter(nulls = Nulls.FAIL)
	private String id = null;

	@JsonProperty("supportedLocales")
	@Valid
	private List<Locale> supportedLocales = null;


	public Project name(String name) {
		this.name = name;
		return this;
	}


	/**
	 * Get name	 *	 * @return name
	 **/
	@Schema(description = "")
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Project id(String id) {
		this.id = id;
		return this;
	}


	/**
	 * Get id	 *	 * @return id
	 **/
	@Schema(description = "")
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Project supportedLocales(List<Locale> supportedLocales) {
		this.supportedLocales = supportedLocales;
		return this;
	}


	public Project addSupportedLocalesItem(Locale supportedLocalesItem) {
		if (this.supportedLocales == null) {
			this.supportedLocales = new ArrayList<Locale>();
		}
		this.supportedLocales.add(supportedLocalesItem);
		return this;
	}


	/**
	 * Get supportedLocales	 *	 * @return supportedLocales
	 **/
	@Schema(description = "")
	@Valid
	public List<Locale> getSupportedLocales() {
		return supportedLocales;
	}


	public void setSupportedLocales(List<Locale> supportedLocales) {
		this.supportedLocales = supportedLocales;
	}


	@Override
	public int hashCode() {
		return Objects.hash(name, id, supportedLocales);
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Project project = (Project) o;
		return Objects.equals(this.name, project.name) && Objects.equals(this.id, project.id) && Objects.equals(this.supportedLocales, project.supportedLocales);
	}


	@Override
	public String toString() {
		String sb = "class Project {\n" + "    name: " + toIndentedString(name) + "\n" + "    id: " + toIndentedString(id) + "\n" + "    supportedLocales: " + toIndentedString(supportedLocales) + "\n" + "}";
		return sb;
	}


	/**
	 * Convert the given object to string with each line indented by 4 spaces	 * (except the first line).
	 */
	private String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}