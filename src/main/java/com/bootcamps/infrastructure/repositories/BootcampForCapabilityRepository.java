package com.bootcamps.infrastructure.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bootcamps.infrastructure.entities.BootcampForCapabilityEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BootcampForCapabilityRepository extends ReactiveCrudRepository<BootcampForCapabilityEntity, Long> {

    Flux<BootcampForCapabilityEntity> findAllByBootcampId(Long capabilityId);

    @Query("SELECT * FROM bootcamp_capability WHERE bootcamp_id = :bootcampId AND capability_id = :capabilityId")
    Mono<BootcampForCapabilityEntity> findByBootcampIdAndCapabilityId(@Param("bootcampId") Long bootcampId,
            @Param("capabilityId") Long capabilityId);

}
