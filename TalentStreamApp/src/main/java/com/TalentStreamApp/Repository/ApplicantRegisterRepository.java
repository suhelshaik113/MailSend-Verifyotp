package com.TalentStreamApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TalentStreamApp.Entity.Applicant;


@Repository
public interface ApplicantRegisterRepository extends JpaRepository<Applicant, Integer> {
	
	Applicant findByEmail(String email);
	
		//boolean existsByEmail(String email);
		//Applicant findById(Long id);

		//Applicant getApplicantById(long applicantid);

	 		

	}
