package com.bootcamps.domain.models;

import java.util.List;

public class Bootcamp {

    private Long id;
    private String name;
    private String description;
    private List<Capability> capabilities;

    public Bootcamp() {
    }

    public Bootcamp(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Bootcamp(Long id, String name, String description, List<Capability> capabilities) {
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
