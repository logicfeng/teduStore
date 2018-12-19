package cn.tedu.store.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7597875054212568622L;
	private Integer id;
	private String username;
	private String password;
	private Integer gender;
	private String phone;
	private String email;
	private String avator;
	private String uuid;
	private String createUser;
	private Date createTime;
	private String modifiedUser;
	private Date modifiedTime;

	public User() {
		super();
	}
	
	

	public User(Integer id, String username, Integer gender, String phone, String email, String avator) {
		super();
		this.id = id;
		this.username = username;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.avator = avator;
	}



	public User(Integer id, String username, Integer gender, String phone, String email, String modifiedUser,
			Date modifiedTime) {
		super();
		this.id = id;
		this.username = username;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.modifiedUser = modifiedUser;
		this.modifiedTime = modifiedTime;
	}

	public User(String username, String password, Integer gender, String phone, String email) {
		super();
		setUsername(username);
		setPassword(password);
		setGender(gender);
		setPhone(phone);
		setEmail(email);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvator() {
		return avator;
	}

	public void setAvator(String avator) {
		this.avator = avator;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", gender=" + gender
				+ ", phone=" + phone + ", email=" + email + ", avator=" + avator + ", uuid=" + uuid + ", createUser="
				+ createUser + ", createTime=" + createTime + ", modifiedUser=" + modifiedUser + ", modifiedTime="
				+ modifiedTime + "]";
	}

}
