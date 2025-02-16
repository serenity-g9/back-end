package com.serenity.api.serenity.configuration;

import com.serenity.api.serenity.impl.BaseRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.serenity.api.serenity",
        repositoryBaseClass = BaseRepositoryImpl.class
)
public class JpaConfig {
}

