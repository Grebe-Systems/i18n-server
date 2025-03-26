package io.swagger.api;

import io.swagger.model.Project;
import io.swagger.model.ResourceBundle;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.Generated;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-03-26T16:42:46.826388125Z[GMT]")
@RestController
public class ApiApiController implements ApiApi {

    private static final Logger log = LoggerFactory.getLogger(ApiApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ApiApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Project>> apiI18nListProjectsGet() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Project>>(objectMapper.readValue("[ {\n  \"supportedLocales\" : [ {\n    \"code\" : \"code\"\n  }, {\n    \"code\" : \"code\"\n  } ],\n  \"name\" : \"name\",\n  \"id\" : \"id\"\n}, {\n  \"supportedLocales\" : [ {\n    \"code\" : \"code\"\n  }, {\n    \"code\" : \"code\"\n  } ],\n  \"name\" : \"name\",\n  \"id\" : \"id\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Project>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Project>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<ResourceBundle>> apiI18nProjectIDBundleLocaleCodeGet(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("projectID") String projectID
,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("localeCode") String localeCode
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<ResourceBundle>>(objectMapper.readValue("[ {\n  \"translations\" : [ {\n    \"value\" : \"value\",\n    \"key\" : \"key\"\n  }, {\n    \"value\" : \"value\",\n    \"key\" : \"key\"\n  } ],\n  \"project\" : {\n    \"supportedLocales\" : [ {\n      \"code\" : \"code\"\n    }, {\n      \"code\" : \"code\"\n    } ],\n    \"name\" : \"name\",\n    \"id\" : \"id\"\n  },\n  \"locale\" : {\n    \"code\" : \"code\"\n  },\n  \"version\" : \"version\"\n}, {\n  \"translations\" : [ {\n    \"value\" : \"value\",\n    \"key\" : \"key\"\n  }, {\n    \"value\" : \"value\",\n    \"key\" : \"key\"\n  } ],\n  \"project\" : {\n    \"supportedLocales\" : [ {\n      \"code\" : \"code\"\n    }, {\n      \"code\" : \"code\"\n    } ],\n    \"name\" : \"name\",\n    \"id\" : \"id\"\n  },\n  \"locale\" : {\n    \"code\" : \"code\"\n  },\n  \"version\" : \"version\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<ResourceBundle>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<ResourceBundle>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ResourceBundle> apiI18nProjectIDBundleLocaleCodeLatestGet(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("projectID") String projectID
,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("localeCode") String localeCode
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ResourceBundle>(objectMapper.readValue("{\n  \"translations\" : [ {\n    \"value\" : \"value\",\n    \"key\" : \"key\"\n  }, {\n    \"value\" : \"value\",\n    \"key\" : \"key\"\n  } ],\n  \"project\" : {\n    \"supportedLocales\" : [ {\n      \"code\" : \"code\"\n    }, {\n      \"code\" : \"code\"\n    } ],\n    \"name\" : \"name\",\n    \"id\" : \"id\"\n  },\n  \"locale\" : {\n    \"code\" : \"code\"\n  },\n  \"version\" : \"version\"\n}", ResourceBundle.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ResourceBundle>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ResourceBundle>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ResourceBundle> apiI18nProjectIDBundleLocaleCodeVersionGet(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("localeCode") String localeCode
,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("projectID") String projectID
,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("version") String version
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ResourceBundle>(objectMapper.readValue("{\n  \"translations\" : [ {\n    \"value\" : \"value\",\n    \"key\" : \"key\"\n  }, {\n    \"value\" : \"value\",\n    \"key\" : \"key\"\n  } ],\n  \"project\" : {\n    \"supportedLocales\" : [ {\n      \"code\" : \"code\"\n    }, {\n      \"code\" : \"code\"\n    } ],\n    \"name\" : \"name\",\n    \"id\" : \"id\"\n  },\n  \"locale\" : {\n    \"code\" : \"code\"\n  },\n  \"version\" : \"version\"\n}", ResourceBundle.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ResourceBundle>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ResourceBundle>(HttpStatus.NOT_IMPLEMENTED);
    }

}
