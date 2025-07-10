package io.github.magwas.inez;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.map.repository.config.EnableMapRepositories;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@PropertySource("classpath:test.properties")
@ComponentScan("io.github.magwas.inez")
@EnableMapRepositories("io.github.magwas.inez")
public class TestConfig {

}
