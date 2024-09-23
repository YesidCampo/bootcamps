package com.bootcamps.application.usecases;

import com.bootcamps.domain.models.Bootcamp;
import com.bootcamps.domain.ports.in.CreateBootcampUseCase;
import com.bootcamps.domain.ports.out.BootcampRepositoryPort;

import reactor.core.publisher.Mono;

public class CreateBootcampUseCaseImpl implements CreateBootcampUseCase {

    private final BootcampRepositoryPort bootcampRepositoryPort;

    public CreateBootcampUseCaseImpl(BootcampRepositoryPort bootcampRepositoryPort) {
        this.bootcampRepositoryPort = bootcampRepositoryPort;
    }

    @Override
    public Mono<Bootcamp> createBootcamp(Bootcamp bootcamp) {
        return this.bootcampRepositoryPort.save(bootcamp);
    }

}
