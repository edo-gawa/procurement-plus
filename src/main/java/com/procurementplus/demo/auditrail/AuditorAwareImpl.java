package com.procurementplus.demo.auditrail;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		// TODO Auto-generated method stub
		return  Optional.of("SPRING_BOOT_TEST");
	}
	
	

}
