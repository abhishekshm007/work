package com.o9paathshala.security.validation;

import java.util.ArrayList;
import java.util.List;

import com.o9paathshala.constants.ErrorMessageConstants;
import com.o9paathshala.institute.dto.InstituteDTO;


public class InstituteValidation extends Validation {
	
	List<String> validateResults = new ArrayList<String>();
	public List<String> validate(InstituteDTO instituteData){
		if(!validateInstituteName(instituteData.getInstituteName())){
			validateResults.add(ErrorMessageConstants.INSTITUTE_NAME_INCORRECT);
		}
		if(!instituteData.getConfirmEmail().equalsIgnoreCase(instituteData.getEmail())){
			validateResults.add(ErrorMessageConstants.EMAIL_MATCH_FAILED);
		}
		if(!validateEmail(instituteData.getEmail())){
			validateResults.add(ErrorMessageConstants.EMAIL_FAILED);
		}
		return validateResults;
	}
}
