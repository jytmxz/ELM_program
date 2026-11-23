package com.elm.pojo;

public class User {

	private Integer id;
	private String userId;
	private String password;
	private String userName;
	private Integer userSex;
	private String userImg;
	private Integer delTag;
	
	//鍏宠仈灞炴��
	private DeliveryAddress address;
	
	
	public User(String userId, String password, String userName, Integer userSex, String userImg) {

		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.userSex = userSex;
		this.userImg = userImg;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public DeliveryAddress getAddress() {
		return address;
	}
	public void setAddress(DeliveryAddress address) {
		this.address = address;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserSex() {
		return userSex;
	}
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	public Integer getDelTag() {
		return delTag;
	}
	public void setDelTag(Integer delTag) {
		this.delTag = delTag;
	}
	
	
}
