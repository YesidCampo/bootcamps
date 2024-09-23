package com.bootcamps.application.services.impl;

import com.bootcamps.application.services.BootcampForCapabilityService;
import com.bootcamps.domain.exception.InvalidCapabilityException;
import com.bootcamps.domain.models.BootcampForCapability;
import com.bootcamps.domain.ports.in.CreateBootcampForCapabilityUseCase;
import com.bootcamps.domain.ports.in.RetrieveBootcampForCapabilityUseCase;

import reactor.core.publisher.Mono;

public class BootcampForCapabilityServiceImpl implements BootcampForCapabilityService {

    private final CreateBootcampForCapabilityUseCase createBootcampForCapabilityUseCase;
    private final RetrieveBootcampForCapabilityUseCase retrieveBootcampForCapabilityUseCase;

    public BootcampForCapabilityServiceImpl(CreateBootcampForCapabilityUseCase createBootcampForCapabilityUseCase,
            RetrieveBootcampForCapabilityUseCase retrieveBootcampForCapabilityUseCase) {
        this.createBootcampForCapabilityUseCase = createBootcampForCapabilityUseCase;
        this.retrieveBootcampForCapabilityUseCase = retrieveBootcampForCapabilityUseCase;
    }

    @Override
    public Mono<BootcampForCapability> createBootcampoforCapability(BootcampForCapability bootcampForCapability) {
        return findBootcampIdAndCapabilityId(bootcampForCapability.getBootcampId(),
                bootcampForCapability.getCapabilityId())
                .flatMap(bootcampForCapabilityExisting -> Mono
                        .error(new InvalidCapabilityException("Techonology repeate!!")))
                .switchIfEmpty(
                        this.createBootcampForCapabilityUseCase.createBootcampoforCapability(bootcampForCapability)
                                .switchIfEmpty(Mono.error(new Exception("capabilityForTechnology not created"))))
                .map(BootcampForCapability.class::cast)
                .onErrorMap(Exception.class, ex -> new Exception(ex.getMessage()));
    }

    @Override
    public Mono<BootcampForCapability> findBootcampIdAndCapabilityId(Long bootcampId, Long capabilityId) {
        return this.retrieveBootcampForCapabilityUseCase.findBootcampIdAndCapabilityId(bootcampId, capabilityId);
    }

}
