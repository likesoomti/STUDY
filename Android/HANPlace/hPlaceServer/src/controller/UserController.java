package controller;

import dao.UserDao;
import domain.User;

public class UserController {
	private UserDao userDao;
	
	public UserController() {
		userDao = new UserDao();
	}

	public String requestInsertUser(User newUser) throws Exception {
		
		return userDao.insert(newUser);
		
	}
	
	public int requestSetUserNickname(String nickname, String email, String gender, String profile) throws Exception {
		
		return userDao.insertNickname(nickname, email, gender, profile);
		
	}
	public String requestUserInfo(String user_id) throws Exception {
		
		return userDao.requestUserInfo(user_id);
		
	}
	public String requestUserUpdate(int user_id,String nickname,String intro) throws Exception {
		
		return userDao.getUpdateUser(user_id,nickname,intro);
		
	}
	
}
