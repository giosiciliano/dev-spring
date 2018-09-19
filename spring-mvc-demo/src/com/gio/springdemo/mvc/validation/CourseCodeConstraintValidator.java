package com.gio.springdemo.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements 
	ConstraintValidator<CourseCode, String> {

	private String[] coursePrefixes;
	//private String coursePrefix;
	
	@Override
	public void initialize(CourseCode theCourseCode) {
		//coursePrefix = theCourseCode.value();
		coursePrefixes = theCourseCode.value();
	}
	
	@Override
	public boolean isValid(String theCode, 
			ConstraintValidatorContext theContstraitValidatorContext) {
		
		/*// single prefix validation
		if (theCode != null) {
			return theCode.startsWith(coursePrefix);
		}
		
		// return true because its not required
		return true;
		*/
		
		// multiple prefix validations
        boolean result = false;
        
        if (theCode != null) {
            
            //
            // loop thru course prefixes
            //
            // check to see if code matches any of the course prefixes
            //
            for (String tempPrefix : coursePrefixes) {
                result = theCode.startsWith(tempPrefix);
                
                // if we found a match then break out of the loop
                if (result) {
                    break;
                }
            }
        }
        else {
            result = true;
        }
        
        return result;
  }

}
