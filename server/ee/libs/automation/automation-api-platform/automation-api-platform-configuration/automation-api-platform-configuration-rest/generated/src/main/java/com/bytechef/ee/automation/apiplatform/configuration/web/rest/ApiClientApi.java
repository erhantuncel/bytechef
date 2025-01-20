/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.10.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.bytechef.ee.automation.apiplatform.configuration.web.rest;

import com.bytechef.ee.automation.apiplatform.configuration.web.rest.model.ApiClientModel;
import com.bytechef.ee.automation.apiplatform.configuration.web.rest.model.CreateApiClient200ResponseModel;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-01-17T10:36:09.845117+01:00[Europe/Zagreb]", comments = "Generator version: 7.10.0")
@Validated
@Tag(name = "api-client", description = "The Automation API Platform Client Internal API")
public interface ApiClientApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /api-clients : Create a new API client
     * Create a new API client.
     *
     * @param apiClientModel  (required)
     * @return The secret API key object. (status code 200)
     */
    @Operation(
        operationId = "createApiClient",
        summary = "Create a new API client",
        description = "Create a new API client.",
        tags = { "api-client" },
        responses = {
            @ApiResponse(responseCode = "200", description = "The secret API key object.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CreateApiClient200ResponseModel.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api-clients",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<CreateApiClient200ResponseModel> createApiClient(
        @Parameter(name = "ApiClientModel", description = "", required = true) @Valid @RequestBody ApiClientModel apiClientModel
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"secretKey\" : \"secretKey\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /api-client/{id} : Delete an API client
     * Delete an API client.
     *
     * @param id The id of an API client. (required)
     * @return Successful operation. (status code 204)
     */
    @Operation(
        operationId = "deleteApiClient",
        summary = "Delete an API client",
        description = "Delete an API client.",
        tags = { "api-client" },
        responses = {
            @ApiResponse(responseCode = "204", description = "Successful operation.")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/api-client/{id}"
    )
    
    default ResponseEntity<Void> deleteApiClient(
        @Parameter(name = "id", description = "The id of an API client.", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /api-client/{id} : Get an API client by id
     * Get an API client by id.
     *
     * @param id The id of an API client. (required)
     * @return The API client object. (status code 200)
     */
    @Operation(
        operationId = "getApiClient",
        summary = "Get an API client by id",
        description = "Get an API client by id.",
        tags = { "api-client" },
        responses = {
            @ApiResponse(responseCode = "200", description = "The API client object.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ApiClientModel.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api-client/{id}",
        produces = { "application/json" }
    )
    
    default ResponseEntity<ApiClientModel> getApiClient(
        @Parameter(name = "id", description = "The id of an API client.", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"createdDate\" : \"2000-01-23T04:56:07.000+00:00\", \"lastUsedDate\" : \"2000-01-23T04:56:07.000+00:00\", \"secretKey\" : \"secretKey\", \"createdBy\" : \"createdBy\", \"lastModifiedDate\" : \"2000-01-23T04:56:07.000+00:00\", \"lastModifiedBy\" : \"lastModifiedBy\", \"name\" : \"name\", \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /api-clients : Get API clients
     * Get API clients.
     *
     * @return The list of API clients. (status code 200)
     */
    @Operation(
        operationId = "getApiClients",
        summary = "Get API clients",
        description = "Get API clients.",
        tags = { "api-client" },
        responses = {
            @ApiResponse(responseCode = "200", description = "The list of API clients.", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ApiClientModel.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api-clients",
        produces = { "application/json" }
    )
    
    default ResponseEntity<List<ApiClientModel>> getApiClients(
        
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"createdDate\" : \"2000-01-23T04:56:07.000+00:00\", \"lastUsedDate\" : \"2000-01-23T04:56:07.000+00:00\", \"secretKey\" : \"secretKey\", \"createdBy\" : \"createdBy\", \"lastModifiedDate\" : \"2000-01-23T04:56:07.000+00:00\", \"lastModifiedBy\" : \"lastModifiedBy\", \"name\" : \"name\", \"id\" : 0 }, { \"createdDate\" : \"2000-01-23T04:56:07.000+00:00\", \"lastUsedDate\" : \"2000-01-23T04:56:07.000+00:00\", \"secretKey\" : \"secretKey\", \"createdBy\" : \"createdBy\", \"lastModifiedDate\" : \"2000-01-23T04:56:07.000+00:00\", \"lastModifiedBy\" : \"lastModifiedBy\", \"name\" : \"name\", \"id\" : 0 } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /api-client/{id} : Update an existing API client
     * Update an existing API client.
     *
     * @param id The id of an API client. (required)
     * @param apiClientModel  (required)
     * @return Successful operation. (status code 204)
     */
    @Operation(
        operationId = "updateApiClient",
        summary = "Update an existing API client",
        description = "Update an existing API client.",
        tags = { "api-client" },
        responses = {
            @ApiResponse(responseCode = "204", description = "Successful operation.")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/api-client/{id}",
        consumes = { "application/json" }
    )
    
    default ResponseEntity<Void> updateApiClient(
        @Parameter(name = "id", description = "The id of an API client.", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "ApiClientModel", description = "", required = true) @Valid @RequestBody ApiClientModel apiClientModel
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
