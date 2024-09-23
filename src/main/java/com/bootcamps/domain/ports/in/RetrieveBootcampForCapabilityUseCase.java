package com.bootcamps.domain.ports.in;

import com.bootcamps.domain.models.BootcampForCapability;

import reactor.core.publisher.Mono;

public interface RetrieveBootcampForCapabilityUseCase {

    Mono<BootcampForCapability> findBootcampIdAndCapabilityId(Long bootcampId, Long capabilityId);

}
