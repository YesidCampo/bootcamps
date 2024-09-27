package com.bootcamps.infrastructure.repositories.impl;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.bootcamps.domain.models.Bootcamp;
import com.bootcamps.domain.ports.out.BootcampRepositoryPort;
import com.bootcamps.infrastructure.repositories.BootcampRepository2;
import com.bootcamps.infrastructure.utils.BootCampMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class BootcampAdapter implements BootcampRepositoryPort {

    private final BootcampRepository2 bootcampRepository;

    public BootcampAdapter(BootcampRepository2 bootcampRepository) {
        this.bootcampRepository = bootcampRepository;
    }

    @Override
    public Mono<Bootcamp> save(Bootcamp bootcamp) {
        return this.bootcampRepository.save(BootCampMapper.fromDomainModel(bootcamp))
                .map(BootCampMapper::toDomainModel);
    }

    @Override
    public Mono<Bootcamp> findByName(String name) {
        return this.bootcampRepository.findByName(name).map(BootCampMapper::toDomainModel);
    }

    @Override
    public Flux<Bootcamp> findAll(Pageable pageable, boolean ascendingByName, boolean ascendingByCapabilitgyNumber) {
        return this.bootcampRepository.findAll().map(BootCampMapper::toDomainModel);
    }

    @Override
    public Mono<Bootcamp> findById(Long id) {
        return this.bootcampRepository.findById(id).map(BootCampMapper::toDomainModel);
    }

    @Override
    public Flux<Bootcamp> findAllById(List<Long> ids) {
        return this.bootcampRepository.findAllById(ids).map(BootCampMapper::toDomainModel);
    }

}
