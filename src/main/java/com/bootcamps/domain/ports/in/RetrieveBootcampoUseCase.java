package com.bootcamps.domain.ports.in;

import org.springframework.data.domain.Pageable;

import com.bootcamps.domain.models.Bootcamp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RetrieveBootcampoUseCase {

    Mono<Bootcamp> getBootCampByName(String name);

    Flux<Bootcamp> getAllBootCamp(Pageable pageable, boolean ascendingByName, boolean ascendingByCapabilitgyNumber);

    Mono<Bootcamp> getBootCampById(Long id);

    Flux<Bootcamp> getBootCampsByIds(List<Long> ids);
}
