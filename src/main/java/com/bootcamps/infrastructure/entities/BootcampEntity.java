package com.bootcamps.infrastructure.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import com.bootcamps.domain.models.Capability;

@Table(name = "bootcamps")
public class BootcampEntity {

    @Id
    private Long id;
    private String name;
    private String description;
    @Transient
    private List<Capability> capabilities;

    public BootcampEntity() {
    }

    public BootcampEntity(Long id, String name, String description, List<Capability> capabilities) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.capabilities = capabilities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Capability> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<Capability> capabilities) {
        this.capabilities = capabilities;
    }

}
