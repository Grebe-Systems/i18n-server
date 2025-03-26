package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import io.swagger.configuration.NotUndefined;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Translation
 */
@Validated
@NotUndefined
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-03-26T16:42:46.826388125Z[GMT]")
public class Translation {

	@JsonProperty("key")
	@JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
	@JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
	private String key = null;

	@JsonProperty("value")
	@JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
	@JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
	private String value = null;


	public Translation key(String key) {
		this.key = key;
		return this;
	}


	/**
	 * Get key
	 *
	 * @return key
	 **/

	@Schema(description = "")
	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public Translation value(String value) {
		this.value = value;
		return this;
	}


	/**
	 * Get value
	 *
	 * @return value
	 **/

	@Schema(description = "")
	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	@Override
	public int hashCode() {
		return Objects.hash(key, value);
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Translation translation = (Translation) o;
		return Objects.equals(this.key, translation.key) &&
				Objects.equals(this.value, translation.value);
	}


	@Override
	public String toString() {
		String sb = "class Translation {\n" +
				"    key: " + toIndentedString(key) + "\n" +
				"    value: " + toIndentedString(value) + "\n" +
				"}";
		return sb;
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
