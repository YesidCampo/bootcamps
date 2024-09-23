package com.bootcamps.application.usecases;

import com.bootcamps.domain.models.BootcampForCapability;
import com.bootcamps.domain.ports.in.CreateBootcampForCapabilityUseCase;
import com.bootcamps.domain.ports.out.BootcampForCapabilityRepositoryPort;

import reactor.core.publisher.Mono;

public class CreateBootcampForCapabilityUseCaseImpl implements CreateBootcampForCapabilityUseCase {

    private final BootcampForCapabilityRepositoryPort bootcampForCapabilityRepositoryPort;

    public CreateBootcampForCapabilityUseCaseImpl(
            BootcampForCapabilityRepositoryPort bootcampForCapabilityRepositoryPort) {
        this.bootcampForCapabilityRepositoryPort = bootcampForCapabilityRepositoryPort;
    }

    @Override
    public Mono<BootcampForCapability> createBootcampoforCapability(BootcampForCapability bootcampForCapability) {
        return this.bootcampForCapabilityRepositoryPort.save(bootcampForCapability);
    }

}
