package com.bootcamps.application.usecases;

import com.bootcamps.domain.models.BootcampForCapability;
import com.bootcamps.domain.ports.in.RetrieveBootcampForCapabilityUseCase;
import com.bootcamps.domain.ports.out.BootcampForCapabilityRepositoryPort;

import reactor.core.publisher.Mono;

public class RetrieveBootCampForCapabilityUseCaseImpl implements RetrieveBootcampForCapabilityUseCase {

    private final BootcampForCapabilityRepositoryPort bootcampForCapabilityRepositoryPort;

    public RetrieveBootCampForCapabilityUseCaseImpl(
            BootcampForCapabilityRepositoryPort bootcampForCapabilityRepositoryPort) {
        this.bootcampForCapabilityRepositoryPort = bootcampForCapabilityRepositoryPort;
    }

    @Override
    public Mono<BootcampForCapability> findBootcampIdAndCapabilityId(Long bootcampId, Long capabilityId) {
        return this.bootcampForCapabilityRepositoryPort.findBootcampIdAndCapabilityId(bootcampId, capabilityId);
    }

}
