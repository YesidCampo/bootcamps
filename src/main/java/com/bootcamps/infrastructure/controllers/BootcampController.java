package com.bootcamps.infrastructure.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamps.application.services.BootcampService;
import com.bootcamps.domain.models.Bootcamp;
import com.bootcamps.domain.models.Capability;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/bootcamp")
public class BootcampController {

    private final BootcampService bootcampService;

    public BootcampController(BootcampService bootcampService) {
        this.bootcampService = bootcampService;
    }

    @PostMapping("/")
    @Operation(summary = "Create botcamp", description = "Save botcamp in the system. **Warning:**  ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "botcamp saved correctly"),
            @ApiResponse(responseCode = "400", description = "Bad request: botcamp Not Created or Bad request: The field is empty or botcamp not created due to duplicate name or The field is not valid format", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
    })
    public Mono<Bootcamp> createCapability(
            @RequestBody @Schema(example = "{\"name\": \"bootcamp\",\"description\": \"bootcamp\"}") Bootcamp capability) {
        return this.bootcampService.createBootcamp(capability);
    }

    @GetMapping("/bootcamps")
    @Operation(summary = "Get All bootcamps", description = "Return all bootcamps of system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "bootcamps data found."),
            @ApiResponse(responseCode = "400", description = "Bad request: Not found bootcamps", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
    })
    public Flux<Bootcamp> getAllBootCamp(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "true") boolean ascendingName,
            @RequestParam(defaultValue = "true") boolean ascendingByCapabilitgyNumber) {
        Pageable pageable = PageRequest.of(page, size);
        return this.bootcampService.getAllBootCamp(pageable, ascendingName, ascendingByCapabilitgyNumber);

    }
}
