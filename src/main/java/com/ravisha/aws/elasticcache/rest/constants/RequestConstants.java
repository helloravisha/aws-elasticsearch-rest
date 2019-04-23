package com.ravisha.aws.elasticcache.rest.constants;

/**
 * Defining the constants used for request for the controller.  the privilege we get in externalizing this
 * it acts as one stop for changing any request path or variable.
 *
 * @author ravi Katta.
 */
public interface RequestConstants {
    String BASE_URI = "/api/v1/employees/search";
    String REQUEST_PARAM_PLAN_NAME = "planname";
    String REQUEST_PARAM_SPONSOR_NAME = "sponsorname";
    String REQUEST_PARAM_SPONSOR_STATE = "sponsorstate";
    String REQUEST_PARAM_SIZE = "size";
    String REQUEST_PARAM_SIZE_DEFAULT = "10";
    String REQUEST_PARAM_OFFSET = "offset";
    String REQUEST_PARAM_OFFSET_DEFAULT = "0";
}
