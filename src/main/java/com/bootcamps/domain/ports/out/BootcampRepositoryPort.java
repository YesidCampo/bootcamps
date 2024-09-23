package com.bootcamps.domain.ports.out;

import com.bootcamps.domain.models.Bootcamp;

import reactor.core.publisher.Mono;

public interface BootcampRepositoryPort {

    Mono<Bootcamp> save(Bootcamp bootcamp);

    Mono<Bootcamp> findByName(String name);

}
