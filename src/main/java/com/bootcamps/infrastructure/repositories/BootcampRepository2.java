package com.bootcamps.infrastructure.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bootcamps.infrastructure.entities.BootcampEntity;

import reactor.core.publisher.Mono;

@Repository
public interface BootcampRepository2 extends ReactiveCrudRepository<BootcampEntity, Long> {

    Mono<BootcampEntity> findByName(String name);
}   
