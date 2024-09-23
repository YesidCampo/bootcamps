package com.bootcamps.application.services.impl;

import java.util.HashSet;

import org.springframework.web.reactive.function.client.WebClient;

import com.bootcamps.application.services.BootcampForCapabilityService;
import com.bootcamps.application.services.BootcampService;
import com.bootcamps.domain.exception.InvalidCapabilityException;
import com.bootcamps.domain.models.Bootcamp;
import com.bootcamps.domain.models.BootcampForCapability;
import com.bootcamps.domain.models.Capability;
import com.bootcamps.domain.ports.in.CreateBootcampUseCase;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BootcampServiceImpl implements BootcampService {

    private final CreateBootcampUseCase createBootcampUseCase;
    private final BootcampForCapabilityService bootcampForCapabilityService;
    private final WebClient webClient;

    public BootcampServiceImpl(CreateBootcampUseCase createBootcampUseCase,
            BootcampForCapabilityService bootcampForCapabilityService, WebClient webClient) {
        this.createBootcampUseCase = createBootcampUseCase;
        this.bootcampForCapabilityService = bootcampForCapabilityService;
        this.webClient = webClient;
    }

    @Override
    public Mono<Bootcamp> createBootcamp(Bootcamp bootcamp) {
        int numberCapacibilities = bootcamp.getCapabilities().size();

        if (numberCapacibilities < 1) {
            return Mono.error(new InvalidCapabilityException("Debe tener mas de 1 relaciones de tegnologia"));
        }
        if (numberCapacibilities > 4) {
            return Mono.error(new InvalidCapabilityException("Debe tener menos de 4 relaciones de tecnologia"));
        }
        return this.createBootcampUseCase.createBootcamp(bootcamp)
                .flatMap(createdBootcamp -> Flux.fromStream(bootcamp.getCapabilities().stream().distinct())
                        .flatMap(capability -> getCapbilityById(capability.getId())
                                .flatMap(existingCapability -> this.bootcampForCapabilityService
                                        .createBootcampoforCapability(
                                                new BootcampForCapability(createdBootcamp.getId(), capability.getId()))
                                        .then(Mono.just(existingCapability))))
                        .collectList()
                        .flatMap(capabilitiesList -> {
                            createdBootcamp.setCapabilities(capabilitiesList); // Convierte a Set si el método
                                                                               // setCapabilities espera un Set
                            return Mono.just(createdBootcamp);
                        }))
                .switchIfEmpty(Mono.error(new Exception("Bootcamp not created")));

    }

    public Mono<Capability> getCapbilityById(Long capabilityId) {
        return webClient.get()
                .uri("/api/capability/" + capabilityId)
                .retrieve()
                .bodyToMono(Capability.class)
                .onErrorResume(e -> Mono.error(new RuntimeException("Failed to retrieve capability")));
    }

}
