package controller;

import dao.LoginDao;
import domain.User;

public class LoginController {

	private LoginDao loginDao;
	
	public LoginController() {
		loginDao = new LoginDao();
	}
	
	public String requestLogin(String email, String pw) throws Exception {
		
		return loginDao.login(email, pw);
		
	}
	
	public String requestFacebookLogin() throws Exception {
		
		return "null";
		
	}
	
	public String requestUserInfoCheck(String email, String nickname) throws Exception {
		
		return loginDao.userInfoCheck(email, nickname);
		
	}
	
	public String requestUserNickname(String email) throws Exception {
		
		return loginDao.userNickname(email);
	
	}
	
	public int requestUserId(String email) throws Exception {
		
		return loginDao.userId(email);
	}

}
