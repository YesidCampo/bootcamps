package com.bootcamps.application.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Comparator;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.bootcamps.application.services.BootcampForCapabilityService;
import com.bootcamps.application.services.BootcampService;
import com.bootcamps.domain.exception.InvalidCapabilityException;
import com.bootcamps.domain.models.Bootcamp;
import com.bootcamps.domain.models.BootcampForCapability;
import com.bootcamps.domain.models.Capability;
import com.bootcamps.domain.models.Technology;
import com.bootcamps.domain.ports.in.CreateBootcampUseCase;
import com.bootcamps.domain.ports.in.RetrieveBootcampoUseCase;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BootcampServiceImpl implements BootcampService {

    private final CreateBootcampUseCase createBootcampUseCase;
    private final BootcampForCapabilityService bootcampForCapabilityService;
    private final WebClient webClient;
    private final RetrieveBootcampoUseCase retrieveBootcampoUseCase;

    public BootcampServiceImpl(CreateBootcampUseCase createBootcampUseCase,
            BootcampForCapabilityService bootcampForCapabilityService, WebClient webClient,
            RetrieveBootcampoUseCase retrieveBootcampoUseCase) {
        this.createBootcampUseCase = createBootcampUseCase;
        this.bootcampForCapabilityService = bootcampForCapabilityService;
        this.webClient = webClient;
        this.retrieveBootcampoUseCase = retrieveBootcampoUseCase;
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

    @Override
    public Mono<Bootcamp> getBootCampByName(String name) {
        return this.retrieveBootcampoUseCase.getBootCampByName(name);
    }

    @Override
    public Flux<Bootcamp> getAllBootCamp(Pageable pageable, boolean ascendingByName,
            boolean ascendingByCapabilitgyNumber) {
        return this.retrieveBootcampoUseCase.getAllBootCamp(pageable, ascendingByName, ascendingByCapabilitgyNumber)
                .flatMap(bootcampResult -> this.bootcampForCapabilityService
                        .findByBootcampId(bootcampResult.getId())
                        .map(BootcampForCapability::getCapabilityId)
                        .collectList()
                        .flatMap(capabilitiesIds -> getCapabilitiesAllByIds(capabilitiesIds)
                                .collectList()
                                .map(capabilities -> {
                                    bootcampResult.setCapabilities(capabilities);
                                    return bootcampResult;
                                })))

                .sort((cap1, cap2) -> {
                    Comparator<Bootcamp> comparator = Comparator.comparing(Bootcamp::getName);
                    if (!ascendingByName) {
                        comparator = comparator.reversed();
                    }
                    Comparator<Bootcamp> techNumberComparator = Comparator
                            .comparing(cap -> cap.getCapabilities().size());
                    if (!ascendingByCapabilitgyNumber) {
                        techNumberComparator = techNumberComparator.reversed();
                    }
                    return comparator.thenComparing(techNumberComparator).compare(cap1, cap2);
                })
                .skip(pageable.getPageNumber() * pageable.getPageSize())
                .take(pageable.getPageSize());
    }

    @Override
    public Mono<Bootcamp> getBootCampById(Long id) {
        return this.retrieveBootcampoUseCase.getBootCampById(id)
                .flatMap(bootcampResult -> this.bootcampForCapabilityService
                        .findByBootcampId(bootcampResult.getId())
                        .map(BootcampForCapability::getCapabilityId)
                        .collectList()
                        .flatMap(capabilitiesIds -> getCapabilitiesAllByIds(capabilitiesIds)
                                .collectList()
                                .map(capablilities -> {
                                    bootcampResult.setCapabilities(capablilities);
                                    return bootcampResult;
                                })))
                .switchIfEmpty(Mono.error(new Exception("Capability not found")));
    }

    @Override
    public Flux<Bootcamp> getBootCampsByIds(List<Long> ids) {
        return this.retrieveBootcampoUseCase.getBootCampsByIds(ids)
                .flatMap(bootcampResult -> this.bootcampForCapabilityService
                        .findByBootcampId(bootcampResult.getId())
                        .map(BootcampForCapability::getCapabilityId)
                        .collectList()
                        .flatMap(capabilitiesIds -> getCapabilitiesAllByIds(capabilitiesIds)
                                .collectList()
                                .map(capablilities -> {
                                    bootcampResult.setCapabilities(capablilities);
                                    return bootcampResult;
                                })))
                .switchIfEmpty(Flux.error(new Exception("Capability not found")));
    }

    public Mono<Capability> getCapbilityById(Long capabilityId) {
        return webClient.get()
                .uri("/api/capability/" + capabilityId)
                .retrieve()
                .bodyToMono(Capability.class)
                .onErrorResume(e -> Mono.error(new RuntimeException("Failed to retrieve capability")));
    }

    public Flux<Capability> getCapabilitiesAllByIds(List<Long> technologyIds) {
        return webClient.post()
                .uri("/api/capability/capabilities/ids")
                .bodyValue(technologyIds)
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<Capability>() {
                })
                .onErrorResume(e -> {
                    if (e instanceof WebClientResponseException.NotFound) {
                        return Flux.error(new RuntimeException("User with id:  not exist"));
                    }
                    return Flux.error(e);
                });
    }

}
