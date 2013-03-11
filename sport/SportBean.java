package com.adivi.ncsa.fasttrack.sport;

import java.util.GregorianCalendar;

/**
 * @author devanhalli
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SportBean {

	private int collegeID;

	private int sportID;

	private int athleticStrength;

	private int divisionID=0;

	private String phoneNumber;

	private String fax;

	private String addr1;

	private String addr2;

	private String addr3;

	private String city;

	private int stateId;

	private String zip;

	private int addressID;
	
	private int modifyUser = 0;
	
	private GregorianCalendar calendar = new  GregorianCalendar();
	private long modifyDate = calendar.getTimeInMillis();

	public void setCollegeID (int collegeID) {
		this.collegeID = collegeID;
	}

	public int getCollegeID () {
		return collegeID;
	}

	public void setSportID (int sportID) {
		this.sportID = sportID;
	}

	public int getSportID () {
		return sportID;
	}

	public void setAthleticStrength (int athleticStrength) {
		this.athleticStrength = athleticStrength;
	}

	public int getAthleticStrength () {
		return athleticStrength;
	}

	public void setDivisionID (int divisionID) {
		this.divisionID = divisionID;
	}

	public int getDivisionID () {
		return divisionID;
	}

	public void setPhoneNumber (String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber () {
		return phoneNumber;
	}

	public void setFax (String fax) {
		this.fax = fax;
	}

	public String getFax () {
		return fax;
	}

	public void setAddr1 (String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr1 () {
		return addr1;
	}

	public void setAddr2 (String addr2) {
		this.addr2 = addr2;
	}

	public String getAddr2 () {
		return addr2;
	}

	public void setAddr3 (String addr3) {
		this.addr3 = addr3;
	}

	public String getAddr3 () {
		return addr3;
	}

	public void setCity (String city) {
		this.city = city;
	}

	public String getCity () {
		return city;
	}

	public void setStateId (int stateId) {
		this.stateId = stateId;
	}

	public int getStateId () {
		return stateId;
	}

	public void setZip (String zip) {
		this.zip = zip;
	}

	public String getZip () {
		return zip;
	}

	public void setAddressID (int addressID) {
		this.addressID = addressID;
	}

	public int getAddressID () {
		return addressID;
	}

	/**
	 * Returns the modifyDate.
	 * @return long
	 */
	public long getModifyDate() {
		return modifyDate;
	}

	/**
	 * Returns the modifyUser.
	 * @return int
	 */
	public int getModifyUser() {
		return modifyUser;
	}

	/**
	 * Sets the modifyDate.
	 * @param modifyDate The modifyDate to set
	 */
	public void setModifyDate(long modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * Sets the modifyUser.
	 * @param modifyUser The modifyUser to set
	 */
	public void setModifyUser(int modifyUser) {
		this.modifyUser = modifyUser;
	}

}
