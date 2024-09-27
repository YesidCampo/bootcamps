package com.bootcamps.domain.ports.out;

import org.springframework.data.domain.Pageable;

import com.bootcamps.domain.models.Bootcamp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BootcampRepositoryPort {

    Mono<Bootcamp> save(Bootcamp bootcamp);

    Mono<Bootcamp> findByName(String name);

    Flux<Bootcamp> findAll(Pageable pageable, boolean ascendingByName, boolean ascendingByCapabilitgyNumber);

    Mono<Bootcamp> findById(Long id);

    Flux<Bootcamp> findAllById(List<Long> ids);

}
