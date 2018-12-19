package cn.tedu.store.entity;

import java.io.Serializable;
import java.util.Date;

public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 932002186795918747L;
	
	private Date createdTime;
	private String createdUser;
	private Integer id;
	private String isDefault;
	private Date modifiedTime;
	private String modifiedUser;
	private String recvAddress;
	private String recvArea;
	private String recvCity;
	private String recvDistrict;
	private String recvName;
	private String recvPhone;
	private String recvProvince;
	private String recvTag;
	private String recvTel;
	private String recvZip;
	private Integer uid;

	public Address() {
		super();
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public Integer getId() {
		return id;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public String getModifiedUser() {
		return modifiedUser;
	}

	public String getRecvAddress() {
		return recvAddress;
	}

	public String getRecvArea() {
		return recvArea;
	}

	public String getRecvCity() {
		return recvCity;
	}

	public String getRecvDistrict() {
		return recvDistrict;
	}

	public String getRecvName() {
		return recvName;
	}

	public String getRecvPhone() {
		return recvPhone;
	}

	public String getRecvProvince() {
		return recvProvince;
	}

	public String getRecvTag() {
		return recvTag;
	}

	public String getRecvTel() {
		return recvTel;
	}

	public String getRecvZip() {
		return recvZip;
	}

	public Integer getUid() {
		return uid;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public void setRecvAddress(String recvAddress) {
		this.recvAddress = recvAddress;
	}

	public void setRecvArea(String recvArea) {
		this.recvArea = recvArea;
	}

	public void setRecvCity(String recvCity) {
		this.recvCity = recvCity;
	}

	public void setRecvDistrict(String recvDistrict) {
		this.recvDistrict = recvDistrict;
	}

	public void setRecvName(String recvName) {
		this.recvName = recvName;
	}

	public void setRecvPhone(String recvPhone) {
		this.recvPhone = recvPhone;
	}

	public void setRecvProvince(String recvProvince) {
		this.recvProvince = recvProvince;
	}

	public void setRecvTag(String recvTag) {
		this.recvTag = recvTag;
	}

	public void setRecvTel(String recvTel) {
		this.recvTel = recvTel;
	}

	public void setRecvZip(String recvZip) {
		this.recvZip = recvZip;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", uid=" + uid + ", recvName=" + recvName + ", recvProvince=" + recvProvince
				+ ", recvCity=" + recvCity + ", recvArea=" + recvArea + ", recvDistrict=" + recvDistrict
				+ ", recvAddress=" + recvAddress + ", recvPhone=" + recvPhone + ", recvTel=" + recvTel + ", recvZip="
				+ recvZip + ", recvTag=" + recvTag + ", createdUser=" + createdUser + ", createdTime=" + createdTime
				+ ", modifiedUser=" + modifiedUser + ", modifiedTime=" + modifiedTime + "]";
	}

}
