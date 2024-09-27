package com.bootcamps.infrastructure.repositories.impl;

import org.springframework.stereotype.Component;

import com.bootcamps.domain.models.BootcampForCapability;
import com.bootcamps.domain.ports.out.BootcampForCapabilityRepositoryPort;
import com.bootcamps.infrastructure.repositories.BootcampForCapabilityRepository;
import com.bootcamps.infrastructure.utils.BootcampForCapabilityMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class BootcampForCapabilityAdapter implements BootcampForCapabilityRepositoryPort {

    private final BootcampForCapabilityRepository bootcampForCapabilityRepository;

    public BootcampForCapabilityAdapter(BootcampForCapabilityRepository bootcampForCapabilityRepository) {
        this.bootcampForCapabilityRepository = bootcampForCapabilityRepository;
    }

    @Override
    public Mono<BootcampForCapability> save(BootcampForCapability bootcampForCapability) {
        return this.bootcampForCapabilityRepository
                .save(BootcampForCapabilityMapper.fromDomainModel(bootcampForCapability))
                .map(BootcampForCapabilityMapper::toDomainModel);
    }

    @Override
    public Mono<BootcampForCapability> findBootcampIdAndCapabilityId(Long bootcampId, Long capabilityId) {
        return this.bootcampForCapabilityRepository.findByBootcampIdAndCapabilityId(bootcampId, capabilityId)
                .map(BootcampForCapabilityMapper::toDomainModel);
    }

    @Override
    public Flux<BootcampForCapability> findAllByBootcampId(Long bootcampId) {
        return this.bootcampForCapabilityRepository.findAllByBootcampId(bootcampId)
                .map(BootcampForCapabilityMapper::toDomainModel);
    }

}
