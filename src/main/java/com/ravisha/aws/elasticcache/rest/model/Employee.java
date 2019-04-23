package com.ravisha.aws.elasticcache.rest.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.ravisha.aws.elasticcache.rest.constants.ModelConstants;

/**
 * Model class for holding the employee details. we can consider what ever fields we are
 * interested as per our business need, i had considered the following attribute
 * as this is testing use case.
 */
public class Employee {

    private String filingStatus;
    private String planName;
    private String sponsorSignedName;
    private String sponsorMailingState;
    private String sponsorCity;
    private String businessCode;

    public String getBusinessCode() {
        return businessCode;
    }


    @JsonSetter(ModelConstants.BUSINESS_CODE)
    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }





    public String getFilingStatus() {
        return filingStatus;
    }

    @JsonSetter(ModelConstants.FILING_STATUS)
    public void setFilingStatus(String filingStatus) {
        this.filingStatus = filingStatus;
    }

    public String getPlanName() {
        return planName;
    }

    @JsonSetter(ModelConstants.PLAN_NAME)
    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getSponsorSignedName() {
        return sponsorSignedName;
    }

    @JsonSetter(ModelConstants.SPONSOR_NAME)
    public void setSponsorSignedName(String sponsorSignedName) {
        this.sponsorSignedName = sponsorSignedName;
    }

    public String getSponsorMailingState() {
        return sponsorMailingState;
    }

    @JsonSetter(ModelConstants.SPONSOR_STATE)
    public void setSponsorMailingState(String sponsorMailingState) {
        this.sponsorMailingState = sponsorMailingState;
    }

    public String getSponsorCity() {
        return sponsorCity;
    }

    @JsonSetter(ModelConstants.SPONSOR_CITY)
    public void setSponsorCity(String sponsorCity) {
        this.sponsorCity = sponsorCity;
    }
}
