package com.bootcamps.domain.ports.in;

import com.bootcamps.domain.models.Bootcamp;

import reactor.core.publisher.Mono;

public interface CreateBootcampUseCase {
    Mono<Bootcamp> createBootcamp(Bootcamp bootcamp);

}
