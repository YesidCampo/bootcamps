package com.bootcamps.infrastructure.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "bootcamp_capability")
public class BootcampForCapabilityEntity {

    @Id
    private Long id;
    private Long bootcampId;
    private Long capabilityId;

    public BootcampForCapabilityEntity() {
    }

    public Long getBootcampId() {
        return bootcampId;
    }

    public void setBootcampId(Long bootcampId) {
        this.bootcampId = bootcampId;
    }

    public Long getCapabilityId() {
        return capabilityId;
    }

    public void setCapabilityId(Long capabilityId) {
        this.capabilityId = capabilityId;
    }

    public BootcampForCapabilityEntity(Long id, Long bootcampId, Long capabilityId) {
        this.id = id;
        this.bootcampId = bootcampId;
        this.capabilityId = capabilityId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
