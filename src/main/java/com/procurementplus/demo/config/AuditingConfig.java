package com.procurementplus.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.procurementplus.demo.auditrail.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditingConfig {
	
	 
	 @Bean
	  public AuditorAware<String> auditorAware() {
	    return new AuditorAwareImpl();
	  }
	  
	  //other configuration beans
	 
	}