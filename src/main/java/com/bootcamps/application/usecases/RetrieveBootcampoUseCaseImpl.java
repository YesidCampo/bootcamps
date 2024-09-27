package com.bootcamps.application.usecases;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.bootcamps.domain.models.Bootcamp;
import com.bootcamps.domain.ports.in.RetrieveBootcampoUseCase;
import com.bootcamps.domain.ports.out.BootcampRepositoryPort;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RetrieveBootcampoUseCaseImpl implements RetrieveBootcampoUseCase {

    private final BootcampRepositoryPort bootcampRepositoryPort;

    public RetrieveBootcampoUseCaseImpl(BootcampRepositoryPort bootcampRepositoryPort) {
        this.bootcampRepositoryPort = bootcampRepositoryPort;
    }

    @Override
    public Mono<Bootcamp> getBootCampByName(String name) {
        return this.bootcampRepositoryPort.findByName(name);
    }

    @Override
    public Flux<Bootcamp> getAllBootCamp(Pageable pageable, boolean ascendingByName,
            boolean ascendingByCapabilitgyNumber) {
        return this.bootcampRepositoryPort.findAll(pageable, ascendingByName, ascendingByCapabilitgyNumber);
    }

    @Override
    public Mono<Bootcamp> getBootCampById(Long id) {
        return this.bootcampRepositoryPort.findById(id);
    }

    @Override
    public Flux<Bootcamp> getBootCampsByIds(List<Long> ids) {
        return this.bootcampRepositoryPort.findAllById(ids);
    }

}
