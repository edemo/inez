package io.github.magwas.inez.tests;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.map.repository.config.EnableMapRepositories;

import io.github.magwas.konveyor.annotations.Glue;

@Configuration
@PropertySource("classpath:test.properties")
@ComponentScan("io.github.magwas")
@EnableMapRepositories("io.github.magwas.inez")
@Glue
public class TestConfig {}
