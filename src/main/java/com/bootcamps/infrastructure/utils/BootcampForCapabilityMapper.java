package com.bootcamps.infrastructure.utils;

import com.bootcamps.domain.models.BootcampForCapability;
import com.bootcamps.infrastructure.entities.BootcampForCapabilityEntity;

public class BootcampForCapabilityMapper {

    private BootcampForCapabilityMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static BootcampForCapabilityEntity fromDomainModel(BootcampForCapability bootcampForCapability) {
        return new BootcampForCapabilityEntity(bootcampForCapability.getId(), bootcampForCapability.getBootcampId(),
                bootcampForCapability.getCapabilityId());
    }

    public static BootcampForCapability toDomainModel(BootcampForCapabilityEntity bootcampForCapabilityEntity) {
        return new BootcampForCapability(bootcampForCapabilityEntity.getId(),
                bootcampForCapabilityEntity.getBootcampId(),
                bootcampForCapabilityEntity.getCapabilityId());
    }

}
