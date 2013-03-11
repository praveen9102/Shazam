package com.adivi.ncsa.fasttrack.sport;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.wintecinc.struts.action.ValidatorForm;


/**
 * @author devanhalli
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SportForm extends ValidatorForm {

	private int collegeID=0;

	private int sportID=0;

	private int athleticStrength=0;

	private int divisionID=0;

	private String phoneNumber="";

	private String fax="";

	private String addr1="";

	private String addr2="";

	private String addr3="";

	private String city="";

	private int stateId=0;

	private String zip="";

	private int addressID=0;

	private int modifyUser=0;

	public ActionErrors validate(ActionMapping mapping,
								 HttpServletRequest request) {

		ActionErrors errors = null;
		errors = super.validate(mapping, request);
		if (null==errors) errors = new ActionErrors();

		if ((stateId == 0))
				errors.add("stateId",
				new ActionError("form.stateId.required"));

		if ((divisionID == 0))
				errors.add("divisionID",
				new ActionError("sportform.divisionid.required"));

		if ((divisionID == 0))
				errors.add("sportID",
				new ActionError("form.sport.required"));

		return errors;

	}

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

	public void reset(ActionMapping mapping,
		HttpServletRequest request) {


	}

	/**
	 * Returns the modifyUser.
	 * @return int
	 */
	public int getModifyUser() {
		return modifyUser;
	}

	/**
	 * Sets the modifyUser.
	 * @param modifyUser The modifyUser to set
	 */
	public void setModifyUser(int modifyUser) {
		this.modifyUser = modifyUser;
	}

}
