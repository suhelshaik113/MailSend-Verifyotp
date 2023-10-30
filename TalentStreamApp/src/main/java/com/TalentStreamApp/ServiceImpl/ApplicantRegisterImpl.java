package com.TalentStreamApp.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TalentStreamApp.Entity.Applicant;
import com.TalentStreamApp.Repository.ApplicantRegisterRepository;
import com.TalentStreamApp.Service.ApplicantRegisterServiceInterface;


@Service
public class ApplicantRegisterImpl implements ApplicantRegisterServiceInterface {
	
	@Autowired
	ApplicantRegisterRepository applicantRepository;
	
	public ApplicantRegisterImpl(ApplicantRegisterRepository applicantRepository) {
		
		this.applicantRepository = applicantRepository;
	}

	public Applicant findByEmailAddress(String userEmail) {

		return applicantRepository.findByEmail(userEmail);

	}


}
