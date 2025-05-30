/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.4.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.assessment.work.grandKapital_api.controllers;

import com.assessment.work.grandKapital_api.models.Error;
import com.assessment.work.grandKapital_api.models.Message;
import com.assessment.work.grandKapital_api.models.Phone;
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

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-25T14:24:03.374571600+03:00[Europe/Minsk]")
@Validated
@Tag(name = "phones", description = "User phone number management")
public interface PhonesApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /phones : Add phone to user
     * Add new phone number for user (must be unique and in format 79200234567)
     *
     * @param userId user id (required)
     * @param phone new phone (required)
     * @return Phone added successfully (status code 200)
     *         or bad request (status code 400)
     */
    @Operation(
        operationId = "addUserPhone",
        summary = "Add phone to user",
        description = "Add new phone number for user (must be unique and in format 79200234567)",
        tags = { "phones" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Phone added successfully", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Phone.class)))
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/phones",
        produces = { "application/json" }
    )
    default ResponseEntity<List<Phone>> addUserPhone(
        @NotNull @Parameter(name = "userId", description = "user id", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "userId", required = true) Long userId,
        @NotNull @Pattern(regexp = "^7\\d{10}$") @Parameter(name = "phone", description = "new phone", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "phone", required = true) String phone
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"phone\" : \"79207654321\" }, { \"phone\" : \"79207654321\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /phones : Replace phone with new one
     * Replace user phone
     *
     * @param userId user id (required)
     * @param oldPhone  (required)
     * @param newPhone  (required)
     * @return Phone number change successfully (status code 200)
     *         or bad request (status code 400)
     */
    @Operation(
        operationId = "changeUserPhone",
        summary = "Replace phone with new one",
        description = "Replace user phone",
        tags = { "phones" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Phone number change successfully", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Message.class))
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/phones",
        produces = { "application/json" }
    )
    default ResponseEntity<Message> changeUserPhone(
        @NotNull @Parameter(name = "userId", description = "user id", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "userId", required = true) Long userId,
        @NotNull @Pattern(regexp = "^7\\d{10}$") @Parameter(name = "oldPhone", description = "", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "oldPhone", required = true) String oldPhone,
        @NotNull @Pattern(regexp = "^7\\d{10}$") @Parameter(name = "newPhone", description = "", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "newPhone", required = true) String newPhone
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"Success!\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /phones : Remove user phone
     * Remove user phone number
     *
     * @param userId user id (required)
     * @param phone  (required)
     * @return Phone removed successfully (status code 200)
     *         or bad request (status code 400)
     */
    @Operation(
        operationId = "removeUserPhone",
        summary = "Remove user phone",
        description = "Remove user phone number",
        tags = { "phones" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Phone removed successfully", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Message.class))
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/phones",
        produces = { "application/json" }
    )
    default ResponseEntity<Message> removeUserPhone(
        @NotNull @Parameter(name = "userId", description = "user id", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "userId", required = true) Long userId,
        @NotNull @Pattern(regexp = "^7\\d{10}$") @Parameter(name = "phone", description = "", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "phone", required = true) String phone
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"Success!\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
