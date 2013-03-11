package com.adivi.ncsa.fasttrack.sport;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.adivi.ncsa.fasttrack.common.SearchForm;
import com.adivi.ncsa.fasttrack.common.User;

public final class SportAction extends DispatchAction {

//	 Logging
	static Logger logger = Logger.getLogger(SportAction.class.getName());

    public ActionForward create(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
        throws IOException, ServletException {
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("user");
		try {
			SportForm sportForm = ((SportForm) form);
			SportBean sportBean = new SportBean();
			BeanUtils.copyProperties(sportBean, sportForm);
			sportBean.setModifyUser(user.getUserID());
			if(!(SportDAO.createSport(sportBean))) {

				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,
				new ActionError("error.db.connect"));
				saveErrors(request,errors);
				// return to input page
				return (new ActionForward(mapping.getInput()));
			}
			session.removeAttribute("sportForm");
			SearchForm search = new SearchForm();
			search.setId(new Integer(sportForm.getCollegeID()).toString());
			request.setAttribute("searchForm",search);
			return (mapping.findForward("success"));

		} catch (IllegalAccessException e) {
			logger.error(user.getUserName(),e);
		} catch (InvocationTargetException e) {
			logger.error(user.getUserName(),e);
		} catch (SportException e) {
			logger.error(user.getUserName(),e);
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,
			new ActionError("error.db.connect"));
			saveErrors(request,errors);
			// return to input page
			return (new ActionForward(mapping.getInput()));
		}

return null;
    }

	public ActionForward update(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
		throws IOException, ServletException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
			SportBean sportBean = new SportBean();
		try {
			SportForm sportForm = ((SportForm) form);
			BeanUtils.copyProperties(sportBean, sportForm);

			sportBean.setModifyUser(user.getUserID());
			if(!(SportDAO.updateSport(sportBean))) {

				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,
				new ActionError("error.db.connect"));
				saveErrors(request,errors);
				// return to input page
				return (new ActionForward(mapping.getInput()));
			}
			session.removeAttribute("sportForm");
			SearchForm search = new SearchForm();
			search.setId(new Integer(sportForm.getCollegeID()).toString());
			request.setAttribute("searchForm",search);
			return (mapping.findForward("success"));

		} catch (IllegalAccessException e) {
			logger.error(user.getUserName(),e);
		} catch (InvocationTargetException e) {
			logger.error(user.getUserName(),e);
		} catch (SportException e) {
			logger.error(user.getUserName(),e);
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,
			new ActionError("error.db.connect"));
			saveErrors(request,errors);
			// return to input page
			return (new ActionForward(mapping.getInput()));
		}

return null;
	}

	public ActionForward createPosition(ActionMapping mapping, ActionForm form,
													HttpServletRequest request,HttpServletResponse response)
													throws IOException, ServletException {

		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("user");
		try {
			PositionForm positionForm = ((PositionForm) form);
			if(positionForm.getAction().compareTo("submit")==0){
				int entryUser = currentUser.getUserID();
				SportDAO.createPositionForSport(positionForm.getSportID(), positionForm.getPositionName(),positionForm.getPositionCode(),entryUser, positionForm.getModifyDate());
			}
			return mapping.findForward("success");
		} catch (SportException e) {
			logger.error(currentUser.getUserName(),e);
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("error.db.connect"));
			saveErrors(request,errors);
			// return to input page
			return (new ActionForward(mapping.getInput()));
		}
	}

	public ActionForward cancel( ActionMapping mapping,ActionForm form,
																HttpServletRequest request, HttpServletResponse response)
																throws IOException, ServletException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		try{
			SportForm sport = (SportForm)form;
			session.removeAttribute("sportForm");
			SearchForm search = new SearchForm();
			search.setId(sport.getCollegeID()+"");
			request.setAttribute("searchForm",search);
			return mapping.findForward("cancel");
		}catch(Exception e){
			logger.error(user.getUserName(),e);
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.system.exception"));
			saveErrors(request,errors);
			// return to input page
			return (new ActionForward(mapping.getInput()));
		}
	}

} // End Action
