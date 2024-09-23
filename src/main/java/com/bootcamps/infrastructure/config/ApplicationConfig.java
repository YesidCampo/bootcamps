package com.bootcamps.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import com.bootcamps.application.services.BootcampForCapabilityService;
import com.bootcamps.application.services.BootcampService;
import com.bootcamps.application.services.impl.BootcampForCapabilityServiceImpl;
import com.bootcamps.application.services.impl.BootcampServiceImpl;
import com.bootcamps.application.usecases.CreateBootcampForCapabilityUseCaseImpl;
import com.bootcamps.application.usecases.CreateBootcampUseCaseImpl;
import com.bootcamps.application.usecases.RetrieveBootCampForCapabilityUseCaseImpl;
import com.bootcamps.domain.ports.out.BootcampForCapabilityRepositoryPort;
import com.bootcamps.domain.ports.out.BootcampRepositoryPort;
import com.bootcamps.infrastructure.repositories.impl.BootcampAdapter;
import com.bootcamps.infrastructure.repositories.impl.BootcampForCapabilityAdapter;

@Configuration
public class ApplicationConfig {

    @Bean
    public BootcampService bootcampService(BootcampRepositoryPort bootcampRepositoryPort,
            BootcampForCapabilityService bootcampForCapabilityService, WebClient webClient) {
        return new BootcampServiceImpl(
                new CreateBootcampUseCaseImpl(bootcampRepositoryPort),
                new BootcampForCapabilityServiceImpl(bootcampForCapabilityService, bootcampForCapabilityService),
                webClient);
    }

    @Bean
    public BootcampForCapabilityService bootcampForCapabilityService(
            BootcampForCapabilityRepositoryPort bootcampForCapabilityRepositoryPort) {
        return new BootcampForCapabilityServiceImpl(
                new CreateBootcampForCapabilityUseCaseImpl(bootcampForCapabilityRepositoryPort),
                new RetrieveBootCampForCapabilityUseCaseImpl(bootcampForCapabilityRepositoryPort));
    }

    @Bean
    public BootcampForCapabilityRepositoryPort bootcampForCapabilityRepositoryPort(BootcampForCapabilityAdapter bootcampForCapabilityAdapter) {
        return bootcampForCapabilityAdapter;
    }

    @Bean
    public BootcampRepositoryPort bootcampRepositoryPort(BootcampAdapter bootcampAdapter) {
        return bootcampAdapter;

    }

  

}
