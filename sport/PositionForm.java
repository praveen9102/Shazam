package com.adivi.ncsa.fasttrack.sport;

import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.wintecinc.struts.action.ValidatorForm;


public final class PositionForm extends ValidatorForm {

	private String positionName;
	private String positionCode;
	private int sportID;
	private GregorianCalendar calendar = new  GregorianCalendar();
	private long modifyDate = calendar.getTimeInMillis();
	private String action;
	private String method;
// -------------------------------------------------- Public Methods


    /**
     * Reset all properties to their default values.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
	public void reset(ActionMapping mapping,
		HttpServletRequest request) {

	}


    /**
     * Ensure that both fields have been input.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {

		ActionErrors errors = null;
		errors = super.validate(mapping, request);
		if (null==errors) errors = new ActionErrors();

		if ((sportID == 0))
				errors.add("position",
				new ActionError("positionform.sport.error"));
				
        return errors;

    }


	/**
	 * Returns the positionCode.
	 * @return String
	 */
	public String getPositionCode() {
		return positionCode;
	}

	/**
	 * Returns the positionName.
	 * @return String
	 */
	public String getPositionName() {
		return positionName;
	}

	/**
	 * Returns the sportID.
	 * @return int
	 */
	public int getSportID() {
		return sportID;
	}

	/**
	 * Sets the positionCode.
	 * @param positionCode The positionCode to set
	 */
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	/**
	 * Sets the positionName.
	 * @param positionName The positionName to set
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/**
	 * Sets the sportID.
	 * @param sportID The sportID to set
	 */
	public void setSportID(int sportID) {
		this.sportID = sportID;
	}

	/**
	 * Returns the modifyDate.
	 * @return long
	 */
	public long getModifyDate() {
		return modifyDate;
	}

	/**
	 * Sets the modifyDate.
	 * @param modifyDate The modifyDate to set
	 */
	public void setModifyDate(long modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * Returns the action.
	 * @return String
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Sets the action.
	 * @param action The action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Returns the method.
	 * @return String
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * Sets the method.
	 * @param method The method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

} // End Form
