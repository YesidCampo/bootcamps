package com.bootcamps.domain.ports.out;

import com.bootcamps.domain.models.BootcampForCapability;

import reactor.core.publisher.Mono;

public interface BootcampForCapabilityRepositoryPort {

    Mono<BootcampForCapability> save(BootcampForCapability bootcampForCapability);

    Mono<BootcampForCapability> findBootcampIdAndCapabilityId(Long bootcampId, Long capabilityId);

}
