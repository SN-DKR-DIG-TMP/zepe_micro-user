package net.atos.sn.bl.micro.user.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Lob;

import org.aspectj.weaver.tools.Trace;
import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

import net.atos.sn.bl.micro.user.utils.UserStatus;

public class UserDto {
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private UserStatus status;
	
	//Personal Information
	/*@Type(type="org.hibernate.type.BinaryType")
	@Column(nullable = true, length = Integer.MAX_VALUE)
	private byte[] profilePic;
	
	@Column(nullable = true)
	private Integer cniNumber;
	
	@Type(type="org.hibernate.type.BinaryType")
	@Column(nullable = true, length = Integer.MAX_VALUE)
	private byte[] cniRecto;
	
	@Type(type="org.hibernate.type.BinaryType")
	@Column(nullable = true, length = Integer.MAX_VALUE)
	private byte[] cniVerso;
	
	@Column(nullable = true)
	private String address;
	
	@Column(nullable = true)
	private Date dateOfBirth;
	
	@Column(nullable = true)
	private String placeOfBirth;
	
	@Column(nullable = true)
	private Integer bp; //Boite postale
	
	//Social Media Information
	@Column(nullable = true)
	private String linkedIn;
	
	@Column(nullable = true)
	private String twitter;
	
	@Column(nullable = true)
	private String facebook;
	
	@Column(nullable = true)
	private String github;*/
	
	public String getUserId() {
		return userId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the status
	 */
	public UserStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(UserStatus status) {
		this.status = status;
	}

	/*public byte[] getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}

	public Integer getCniNumber() {
		return cniNumber;
	}

	public void setCniNumber(Integer cniNumber) {
		this.cniNumber = cniNumber;
	}

	public byte[] getCniRecto() {
		return cniRecto;
	}

	public void setCniRecto(byte[] cniRecto) {
		this.cniRecto = cniRecto;
	}

	public byte[] getCniVerso() {
		return cniVerso;
	}

	public void setCniVerso(byte[] cniVerso) {
		this.cniVerso = cniVerso;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public Integer getBp() {
		return bp;
	}

	public void setBp(Integer bp) {
		this.bp = bp;
	}

	public String getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}*/
}
