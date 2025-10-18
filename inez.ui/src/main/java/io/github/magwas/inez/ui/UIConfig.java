package io.github.magwas.inez.ui;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.map.repository.config.EnableMapRepositories;

@Configuration
@ComponentScan("io.github.magwas")
@EnableMapRepositories("io.github.magwas.inez")
public class UIConfig {}
