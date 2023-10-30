package com.TalentStreamApp.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.TalentStreamApp.Entity.Applicant;
import com.TalentStreamApp.Entity.OtpVerification;
import com.TalentStreamApp.Service.ApplicantRegisterServiceInterface;
import com.TalentStreamApp.ServiceImpl.ApplicantRegisterImpl;
import com.TalentStreamApp.ServiceImpl.EmailService;
import com.TalentStreamApp.ServiceImpl.OtpService;



@RestController
public class ApplicantRegisterController {
	
	@Autowired
	private ApplicantRegisterServiceInterface applicantRegisterService; // Inject the service
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private OtpService otpService;
	private Map<String, Boolean> otpVerificationMap = new HashMap<>();
	
	@PostMapping("/applicant/sendotp")
    public ResponseEntity<String> sendOtp(@RequestBody Applicant  request) {
        String userEmail = request.getEmail();
        Applicant applicant = applicantRegisterService.findByEmailAddress(userEmail);
        if (applicant == null) {     
            String otp = otpService.generateOtp(userEmail);
         	            emailService.sendOtpEmail(userEmail, otp);
 	            otpVerificationMap.put(userEmail, true);
 	            return ResponseEntity.ok("OTP sent to your email.");
        }

        else {
        	 return ResponseEntity.badRequest().body("Email is already  registered.");
        }
    }
	
	
	 @PostMapping("/applicant/verifyotp")
	    public ResponseEntity<String> verifyOtp( @RequestBody  OtpVerification verificationRequest

	    ) {
	        String otp=verificationRequest.getOtp();
	        String email=verificationRequest.getEmail();
	        System.out.println(otp+email);

	        if (otpService.validateOtp(email, otp)) {
	            return ResponseEntity.ok("OTP verified successfully");
	        } else {
	            return ResponseEntity.badRequest().body("Incorrect OTP.");
	        }

	    }

}
