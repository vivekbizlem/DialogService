/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.biz.model;

/**
 *
 * @author Akhilesh yadav
 */
import java.io.Serializable;
import java.util.Date;

/**
 * @author Akhilesh yadav
 *
 */
public class SkillFlowContext implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
        private String sessionId;
	private String contextId;
	private String contextName;
	private String contextCode;
	private String contextState;
	//private SkillFlow skillFlow;
	//private TaskFlow currentTaskFlow;
	private String currentStep;
	private String nextStep;
	private Date creationDate;
	private String payload;
	private String queryParameter;
	private boolean isAuthorized;
	private String rbacResponse;
	private String api_aiResponse;
	private String nlpResponse;
        private String keyresponse;
        private String valueresponse;

    public String getKeyresponse() {
        return keyresponse;
    }

    public void setKeyresponse(String keyresponse) {
        this.keyresponse = keyresponse;
    }

    public String getValueresponse() {
        return valueresponse;
    }

    public void setValueresponse(String valueresponse) {
        this.valueresponse = valueresponse;
    }

    public String getApi_aiResponse() {
        return api_aiResponse;
    }

    public void setApi_aiResponse(String api_aiResponse) {
        this.api_aiResponse = api_aiResponse;
    }

    public String getNlpResponse() {
        return nlpResponse;
    }

    public void setNlpResponse(String nlpResponse) {
        this.nlpResponse = nlpResponse;
    }
        
        
	public String getSessionId() {
		return sessionId;
	}
        public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	/**
	 * @return the contextId
	 */
	public String getContextId() {
		return contextId;
	}
	/**
	 * @param contextId the contextId to set
	 */
	public void setContextId(String contextId) {
		this.contextId = contextId;
	}
	/**
	 * @return the contextName
	 */
	public String getContextName() {
		return contextName;
	}
	/**
	 * @param contextName the contextName to set
	 */
	public void setContextName(String contextName) {
		this.contextName = contextName;
	}
	/**
	 * @return the contextCode
	 */
	public String getContextCode() {
		return contextCode;
	}
	/**
	 * @param contextCode the contextCode to set
	 */
	public void setContextCode(String contextCode) {
		this.contextCode = contextCode;
	}
	/**
	 * @return the contextState
	 */
	public String getContextState() {
		return contextState;
	}
	/**
	 * @param contextState the contextState to set
	 */
	public void setContextState(String contextState) {
		this.contextState = contextState;
	}
	/**
	 * @return the skillFlow
	 */
//	public SkillFlow getSkillFlow() {
//		return skillFlow;
//	}
	/**
	 * @param skillFlow the skillFlow to set
	 */
//	public void setSkillFlow(SkillFlow skillFlow) {
//		this.skillFlow = skillFlow;
//	}
	/**
	 * @return the currentTaskFlow
	 */
//	public TaskFlow getCurrentTaskFlow() {
//		return currentTaskFlow;
//	}
	/**
	 * @param currentTaskFlow the currentTaskFlow to set
	 */
//	public void setCurrentTaskFlow(TaskFlow currentTaskFlow) {
//		this.currentTaskFlow = currentTaskFlow;
//	}
	/**
	 * @return the currentStep
	 */
	public String getCurrentStep() {
		return currentStep;
	}
	/**
	 * @param currentStep the currentStep to set
	 */
	public void setCurrentStep(String currentStep) {
		this.currentStep = currentStep;
	}
	/**
	 * @return the nextStep
	 */
	public String getNextStep() {
		return nextStep;
	}
	/**
	 * @param nextStep the nextStep to set
	 */
	public void setNextStep(String nextStep) {
		this.nextStep = nextStep;
	}
	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return the payload
	 */
	public String getPayload() {
		return payload;
	}
	/**
	 * @param payload the payload to set
	 */
	public void setPayload(String payload) {
		this.payload = payload;
	}
	/**
	 * @return the queryParameter
	 */
	public String getQueryParameter() {
		return queryParameter;
	}
	/**
	 * @param queryParameter the queryParameter to set
	 */
	public void setQueryParameter(String queryParameter) {
		this.queryParameter = queryParameter;
	}
	/**
	 * @return the isAuthorized
	 */
	public boolean isAuthorized() {
		return isAuthorized;
	}
	/**
	 * @param isAuthorized the isAuthorized to set
	 */
	public void setAuthorized(boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
	}
	/**
	 * @return the rbacResponse
	 */
	public String getRbacResponse() {
		return rbacResponse;
	}
	/**
	 * @param rbacResponse the rbacResponse to set
	 */
	public void setRbacResponse(String rbacResponse) {
		this.rbacResponse = rbacResponse;
	}
	
}

