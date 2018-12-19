package cn.tedu.store.util;

/**
 * 验证器：用于验证字符串的格式是否正确
 * @author soft01
 *
 */
public final class Validator {
	
	private Validator() {
		
	}
	/**
	 * 验证用户名的正则表达式
	 */
	public static final String REGEX_USERNAME = "[A-Za-z][A-Za-z1-9_-]+";
	
	/**
	 * 验证用户密码的正则表达式
	 */
	public static final String REGEX_PASSWORD = "[A-Za-z][A-Za-z1-9_-]+";
	
	/**
	 * 验证用户电话的正则表达式
	 */
	public static final String REGEX_PHONE = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
	
	/**
	 * 验证用户邮箱的正则表达式
	 */
	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	
	/**
	 * 验证用户名
	 * @param username 需要被验证格式的用户名
	 * @return 如果符合要求，返回true，否则返回false
	 */
	public static boolean checkUsername(String username) {
		return username.matches(REGEX_USERNAME);
	}

	/**
	 * 验证密码
	 * @param password 需要被验证格式的密码
	 * @return 如果符合要求，返回true，否则返回false
	 */
	public static boolean checkPassword(String password) {
		return password.matches(REGEX_PASSWORD);
	}
	
	/**
	 * 验证电话
	 * @param phone 需要被验证格式的电话
	 * @return 如果符合要求，返回true，否则返回false
	 */
	public static boolean checkPhone(String phone) {
		return phone.matches(REGEX_PHONE);
	}
	
	/**
	 * 验证邮箱
	 * @param phone 需要被验证格式的邮箱
	 * @return 如果符合要求，返回true，否则返回false
	 */
	public static boolean checkEmail(String email) {
		return email.matches(REGEX_EMAIL);
	}
	
}
