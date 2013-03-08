package com.donkka.connection;

public class ServerURLs {
	
	public static final String BASE_URL = "http://donkka.com/TextGameUI/";
	public static final String FUNCTION_CREATE_USER_WITH_EMAIL = "createUserWithEmail";
	public static final String FUNCTION_DOES_EMAIL_EXIST = "doesEmailExist";
	public static final String FUNCTION_DOES_USERNAME_EXIST = "doesUsernameExist";
	public static final String FUNCTION_LOGIN_WITH_FACEBOOK = "loginWithFacebook";
	public static final String FUNCTION_LOGIN_WITH_EMAIL_AND_PASSWORD = "loginWithEmailAndPassword";
	public static final String FUNCTION_FIND_RANDOM_OPPONENT = "findRandomOpponent";
	public static final String FUNCTION_GET_GAMES = "getGames";
	
	public static String getCreateUserWithEmailURL(String email, String username){
		return BASE_URL + "?f=" + FUNCTION_CREATE_USER_WITH_EMAIL + "&e=" + email + "&u=" + username; 
	}
	
	public static String getLoginWithFacebookURL(String email, String username){
		return BASE_URL + "?f=" + FUNCTION_LOGIN_WITH_FACEBOOK + "&e=" + email + "&u=" + username; 
	}
	
	public static String getLoginWithEmailAndPasswordURL(String email, String password){
		return BASE_URL + "?f=" + FUNCTION_LOGIN_WITH_EMAIL_AND_PASSWORD + "&e=" + email + "&p=" + password; 
	}
	
	public static String getFindRandomOpponentURL(int userID){
		return BASE_URL + "?f=" + FUNCTION_FIND_RANDOM_OPPONENT + "&uid=" + userID; 
	}	
	
	public static String getGameDataURL(int userID){
		return BASE_URL + "?f=" + FUNCTION_GET_GAMES + "&uid=" + userID; 
	}	
	
	public static String getDoesEmailExistURL(String email){
		return BASE_URL + "?f=" + FUNCTION_DOES_EMAIL_EXIST + "&e=" + email; 
	}	
	
	public static String getDoesUsernameExistURL(String user){
		return BASE_URL + "?f=" + FUNCTION_DOES_USERNAME_EXIST + "&u=" + user; 
	}	
}
