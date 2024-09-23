package com.bootcamps.domain.ports.in;

import com.bootcamps.domain.models.BootcampForCapability;

import reactor.core.publisher.Mono;

public interface CreateBootcampForCapabilityUseCase {

    Mono<BootcampForCapability> createBootcampoforCapability(BootcampForCapability bootcampForCapability);

}
