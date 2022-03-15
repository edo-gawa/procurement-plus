package com.procurementplus.demo.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.procurementplus.demo.validation.DateOfBirthValidator;
import com.procurementplus.demo.validation.SocialNumberSecurityValidator;
import com.procurementplus.demo.validation.UserNameValidator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@UserNameValidator()
	private String userName;
	
	@SocialNumberSecurityValidator()
	private String socialNumberSecurity;
	
	@DateOfBirthValidator()
	private String dateOfBirth;
	
	private boolean deleted;
	
	private String createdBy;
	
	private Date createDate;
	
	private String updatedBy;
	
	private Date updateDate;
	
	
	public Integer getSocialNumbErSecurity() {
		if(socialNumberSecurity.length()==4) {
			return Integer.parseInt(socialNumberSecurity.concat("0"));
		}
		return Integer.parseInt(socialNumberSecurity);
	}
	
	public Date getDateOfBirth() {
		Date date;
		try {
			date = new SimpleDateFormat("YYYY-MM-DD").parse(dateOfBirth);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void setDateOfBirthFromDate(Date date) {
		dateOfBirth=new  SimpleDateFormat("YYYY-MM-DD").format(date);
	}
}
