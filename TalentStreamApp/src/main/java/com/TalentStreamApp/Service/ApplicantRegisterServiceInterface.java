package com.TalentStreamApp.Service;

import com.TalentStreamApp.Entity.Applicant;

public interface ApplicantRegisterServiceInterface {
	public Applicant findByEmailAddress(String userEmail);

}
