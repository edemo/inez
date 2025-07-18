package io.github.magwas.inez;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.map.repository.config.EnableMapRepositories;

import io.github.magwas.kodekonveyorannotations.Glue;

@Configuration
@PropertySource("classpath:test.properties")
@ComponentScan("io.github.magwas.inez")
@EnableMapRepositories("io.github.magwas.inez")
@Glue
public class TestConfig {

}
