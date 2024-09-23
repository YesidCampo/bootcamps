package com.bootcamps.infrastructure.utils;

import com.bootcamps.domain.models.Bootcamp;
import com.bootcamps.infrastructure.entities.BootcampEntity;

public class BootCampMapper {

    private BootCampMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static BootcampEntity fromDomainModel(Bootcamp bootcamp) {
        return new BootcampEntity(bootcamp.getId(), bootcamp.getName(), bootcamp.getDescription(),
                bootcamp.getCapabilities());
    }

    public static Bootcamp toDomainModel(BootcampEntity bootcampEntity) {
        return new Bootcamp(bootcampEntity.getId(), bootcampEntity.getName(), bootcampEntity.getDescription(),
                bootcampEntity.getCapabilities());
    }
}
