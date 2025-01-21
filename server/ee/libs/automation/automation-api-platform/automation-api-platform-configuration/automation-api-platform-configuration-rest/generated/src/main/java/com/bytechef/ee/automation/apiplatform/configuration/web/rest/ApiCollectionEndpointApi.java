/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.10.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.bytechef.ee.automation.apiplatform.configuration.web.rest;

import com.bytechef.ee.automation.apiplatform.configuration.web.rest.model.ApiCollectionEndpointModel;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-01-21T06:33:47.577231+01:00[Europe/Zagreb]", comments = "Generator version: 7.10.0")
@Validated
@Tag(name = "api-collection-endpoint", description = "The Automation API Platform Collection Endpoint Internal API")
public interface ApiCollectionEndpointApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /api-collection-endpoints : Create a new API collection endpoint
     * Create a new API collection endpoint.
     *
     * @param apiCollectionEndpointModel  (required)
     * @return The API collection endpoint object. (status code 200)
     */
    @Operation(
        operationId = "createApiCollectionEndpoint",
        summary = "Create a new API collection endpoint",
        description = "Create a new API collection endpoint.",
        tags = { "api-collection-endpoint" },
        responses = {
            @ApiResponse(responseCode = "200", description = "The API collection endpoint object.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ApiCollectionEndpointModel.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api-collection-endpoints",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<ApiCollectionEndpointModel> createApiCollectionEndpoint(
        @Parameter(name = "ApiCollectionEndpointModel", description = "", required = true) @Valid @RequestBody ApiCollectionEndpointModel apiCollectionEndpointModel
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"lastModifiedDate\" : \"2000-01-23T04:56:07.000+00:00\", \"lastModifiedBy\" : \"lastModifiedBy\", \"apiCollectionId\" : 6, \"httpMethod\" : \"DELETE\", \"enabled\" : false, \"workflowReferenceCode\" : \"workflowReferenceCode\", \"path\" : \"path\", \"__version\" : 5, \"createdDate\" : \"2000-01-23T04:56:07.000+00:00\", \"createdBy\" : \"createdBy\", \"name\" : \"name\", \"projectDeploymentWorkflowId\" : 5, \"id\" : 1, \"lastExecutionDate\" : \"2000-01-23T04:56:07.000+00:00\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /api-collection-endpoints/{id} : Delete an API collection endpoint
     * Delete an API collection endpoint.
     *
     * @param id The id of an API collection endpoint. (required)
     * @return Successful operation. (status code 204)
     */
    @Operation(
        operationId = "deleteApiCollectionEndpoint",
        summary = "Delete an API collection endpoint",
        description = "Delete an API collection endpoint.",
        tags = { "api-collection-endpoint" },
        responses = {
            @ApiResponse(responseCode = "204", description = "Successful operation.")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/api-collection-endpoints/{id}"
    )
    
    default ResponseEntity<Void> deleteApiCollectionEndpoint(
        @Parameter(name = "id", description = "The id of an API collection endpoint.", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /api-collection-endpoints/{id} : Get an API collection endpoint by id
     * Get an API collection endpoint by id.
     *
     * @param id The id of an API collection endpoint. (required)
     * @return The API collection endpoint object. (status code 200)
     */
    @Operation(
        operationId = "getApiCollectionEndpoint",
        summary = "Get an API collection endpoint by id",
        description = "Get an API collection endpoint by id.",
        tags = { "api-collection-endpoint" },
        responses = {
            @ApiResponse(responseCode = "200", description = "The API collection endpoint object.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ApiCollectionEndpointModel.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api-collection-endpoints/{id}",
        produces = { "application/json" }
    )
    
    default ResponseEntity<ApiCollectionEndpointModel> getApiCollectionEndpoint(
        @Parameter(name = "id", description = "The id of an API collection endpoint.", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"lastModifiedDate\" : \"2000-01-23T04:56:07.000+00:00\", \"lastModifiedBy\" : \"lastModifiedBy\", \"apiCollectionId\" : 6, \"httpMethod\" : \"DELETE\", \"enabled\" : false, \"workflowReferenceCode\" : \"workflowReferenceCode\", \"path\" : \"path\", \"__version\" : 5, \"createdDate\" : \"2000-01-23T04:56:07.000+00:00\", \"createdBy\" : \"createdBy\", \"name\" : \"name\", \"projectDeploymentWorkflowId\" : 5, \"id\" : 1, \"lastExecutionDate\" : \"2000-01-23T04:56:07.000+00:00\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /api-collection-endpoints/{id} : Update an existing API collection endpoint
     * Update an existing API collection endpoint.
     *
     * @param id The id of an API collection endpoint. (required)
     * @param apiCollectionEndpointModel  (required)
     * @return The updated API collection endpoint object. (status code 200)
     */
    @Operation(
        operationId = "updateApiCollectionEndpoint",
        summary = "Update an existing API collection endpoint",
        description = "Update an existing API collection endpoint.",
        tags = { "api-collection-endpoint" },
        responses = {
            @ApiResponse(responseCode = "200", description = "The updated API collection endpoint object.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ApiCollectionEndpointModel.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/api-collection-endpoints/{id}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<ApiCollectionEndpointModel> updateApiCollectionEndpoint(
        @Parameter(name = "id", description = "The id of an API collection endpoint.", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "ApiCollectionEndpointModel", description = "", required = true) @Valid @RequestBody ApiCollectionEndpointModel apiCollectionEndpointModel
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"lastModifiedDate\" : \"2000-01-23T04:56:07.000+00:00\", \"lastModifiedBy\" : \"lastModifiedBy\", \"apiCollectionId\" : 6, \"httpMethod\" : \"DELETE\", \"enabled\" : false, \"workflowReferenceCode\" : \"workflowReferenceCode\", \"path\" : \"path\", \"__version\" : 5, \"createdDate\" : \"2000-01-23T04:56:07.000+00:00\", \"createdBy\" : \"createdBy\", \"name\" : \"name\", \"projectDeploymentWorkflowId\" : 5, \"id\" : 1, \"lastExecutionDate\" : \"2000-01-23T04:56:07.000+00:00\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
