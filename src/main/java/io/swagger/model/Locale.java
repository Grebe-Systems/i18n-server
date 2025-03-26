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
 * Locale
 */
@Validated
@NotUndefined
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-03-26T16:42:46.826388125Z[GMT]")
public class Locale {


	@JsonProperty("code")
	@JsonInclude(JsonInclude.Include.NON_ABSENT)
	@JsonSetter(nulls = Nulls.FAIL)
	private String code = null;


	public Locale code(String code) {
		this.code = code;
		return this;
	}


	/**
	 * Get code	 *	 * @return code
	 **/
	@Schema(description = "")
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Locale locale = (Locale) o;
		return Objects.equals(this.code, locale.code);
	}


	@Override
	public int hashCode() {
		return Objects.hash(code);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Locale {\n");
		sb.append("    code: ").append(toIndentedString(code)).append("\n");
		sb.append("}");
		return sb.toString();
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