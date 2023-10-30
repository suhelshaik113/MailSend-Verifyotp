package com.TalentStreamApp.ServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


import org.springframework.stereotype.Service;



@Service
public class OtpService {
	 private static final long OTP_VALID_DURATION_MS = 180 * 1000; // 180 seconds

	    private Map<String, OtpData> otpMap = new HashMap<>();

	    public String generateOtp(String userEmail) {
	        String otp = generateRandomOtp();
	        otpMap.put(userEmail, new OtpData(otp));
	        return otp;
	    }
	    
	 /*   private String generateRandomOtp() {
	        return RandomStringUtils.randomNumeric(6); // Generate a 6-digit OTP
	    }
*/
	    public static String generateRandomOtp() {
	        Random random = new Random();
	        int otp = 100000 + random.nextInt(900000); // 6-digit OTP
	        return String.valueOf(otp);
	    }
	    
	    public boolean validateOtp(String userEmail, String enteredOtp) {
	        OtpData otpData = otpMap.get(userEmail);
	        if (otpData != null && otpData.isValid(enteredOtp)) {
	            otpMap.remove(userEmail);
	            return true;
	        }
	        return false;
	    }
	    
	   
	    private static class OtpData {
	        private String otp;
	        private long creationTime;
	        public OtpData(String otp) {
	            this.otp = otp;
	            this.creationTime = System.currentTimeMillis();
	        }
	        
	        public boolean isValid(String enteredOtp) {
	            long currentTime = System.currentTimeMillis();
	            return currentTime - creationTime <= OTP_VALID_DURATION_MS && otp.equals(enteredOtp);
	        }
		    
	    }
}
