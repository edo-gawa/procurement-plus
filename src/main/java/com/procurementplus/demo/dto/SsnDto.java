package com.procurementplus.demo.dto;

import java.io.Serializable;

import com.procurementplus.demo.validation.SocialNumberSecurityValidator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SsnDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6855618972389338458L;
	@SocialNumberSecurityValidator()
	private String id;
	
	public Integer getSsn() {
		return Integer.parseInt(id);
	}

}
