package edu.calstatela.jobsapi.model;

import javax.validation.constraints.NotBlank;

public class UserLogin {
	
    @NotBlank
    private String userEmailId;

    @NotBlank
    private String password;

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}