package com.ravisha.aws.elasticcache.rest.dao.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ravisha.aws.elasticcache.rest.constants.ErrorConstants;
import com.ravisha.aws.elasticcache.rest.constants.ModelConstants;
import com.ravisha.aws.elasticcache.rest.dao.interfaces.EmployeeSearchDAO;
import com.ravisha.aws.elasticcache.rest.exception.EmployeeException;
import com.ravisha.aws.elasticcache.rest.model.Employee;
import com.ravisha.aws.elasticcache.rest.util.ElasticSearchUtil;
import com.ravisha.aws.elasticcache.rest.util.SearchCriteria;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Employee search DAO layer  responsible for fetching the employee details using elastic search
 * rest high level client with the given plan or Sponsor or sponsor state.
 *
 * @author ravi katta.
 */
@Repository
public class EmployeeSearchDAOImpl implements EmployeeSearchDAO {

    //Rest high level client instance
    private RestHighLevelClient client;
    // Object mapper used for mapping dao to service layer.
    private ObjectMapper objectMapper;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public EmployeeSearchDAOImpl(RestHighLevelClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
        // We may not be intrested in all the fields that we fetch from DB. We can use the following property to meet that requirement.
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }

    /**
     * Responsible for fetching the employee list based on plan name, size and from.
     *
     * @param planName
     *        Plan Name for fetching the user details.
     * @param size
     *        Number of records to be fetched.
     * @param from
     *        offset from where the records has to be fetched,
     * @return
     *        returns the list of employee, if no records are there retuns an empty list.
     *
     * @throws EmployeeException
     *         Throws an excpetion , when there is any issue at the dao layer.
     */
    public List<Employee> findByPlanName(String planName,int size,int from) throws EmployeeException {
        List<Employee> employeeList = new ArrayList<>();
        try {
            employeeList =  findEmployeeByAttributeValue(ModelConstants.PLAN_NAME, planName,size,from);
        }catch (EmployeeException employeeException){
            LOGGER.error(getErrorMessage(ErrorConstants.ERROR_READING_PLAN_NAME,employeeException));
            throw new EmployeeException(getErrorMessage(ErrorConstants.ERROR_READING_PLAN_NAME,employeeException));
        }
        return employeeList;
    }


    /**
     * Responsible for fetching the employee list based on sponsor name, size and from.
     *
     * @param sponsorName
     *        sponsorName for fetching the user details.
     * @param size
     *        Number of records to be fetched.
     * @param from
     *        offset from where the records has to be fetched,
     * @return
     *        returns the list of employee, if no records are there retuns an empty list.
     *
     * @throws EmployeeException
     *         Throws an excpetion , when there is any issue at the dao layer.
     */
    public List<Employee> findBySponsorName(String sponsorName,int size,int from) throws EmployeeException {

        List<Employee> employeeList = new ArrayList<>();
        try {
            employeeList =  findEmployeeByAttributeValue(ModelConstants.SPONSOR_NAME, sponsorName,size,from);
        }catch (EmployeeException employeeException){
            LOGGER.error(getErrorMessage(ErrorConstants.ERROR_READING_WITH_SPONSOR_NAME,employeeException));
            throw new EmployeeException(getErrorMessage(ErrorConstants.ERROR_READING_WITH_SPONSOR_NAME,employeeException));
        }
        return employeeList;
    }

    /**
     * Responsible for fetching the employee list based on sponsor state, size and from.
     *
     * @param sponsorState
     *        sponsorState for fetching the user details.
     * @param size
     *        Number of records to be fetched.
     * @param from
     *        offset from where the records has to be fetched,
     * @return
     *        returns the list of employee, if no records are there retuns an empty list.
     *
     * @throws EmployeeException
     *         Throws an excpetion , when there is any issue at the dao layer.
     */
    public List<Employee> findBySponsorState(String sponsorState,int size,int from) throws EmployeeException {
        List<Employee> employeeList = new ArrayList<>();
        try {
            employeeList =  findEmployeeByAttributeValue(ModelConstants.SPONSOR_STATE, sponsorState,size,from);
        }catch (EmployeeException employeeException){
            LOGGER.error(getErrorMessage(ErrorConstants.ERROR_READING_WITH_SPONSOR_STATE,employeeException));
            throw new EmployeeException(getErrorMessage(ErrorConstants.ERROR_READING_WITH_SPONSOR_STATE,employeeException));
        }
        return employeeList;
    }


    /**
     * Helper method for getting the reuqired data from elastic search using elastic search core libraries
     * @param attributeName
     *        The criteria on which the records need to be fetched
     * @param attributeValue
     *        Value for the criteria can be passed here.
     * @param size
     *        Number of maximum records to fetch.
     * @param from
     *        offset from where the records has to be fetched.
     * @return
     *        list of employee satifying the given condtion.
     * @throws EmployeeException
     *        Any exception. from the underlying core libraries if any condition is not met.
     */
    private List<Employee> findEmployeeByAttributeValue(String attributeName, String attributeValue,int size,int from) throws EmployeeException {
        SearchRequest searchRequest = ElasticSearchUtil.getSearchRequest(new SearchCriteria(attributeName,attributeValue,size,from));
        SearchResponse searchResponse =
                null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new EmployeeException(e.getMessage());
        }
        try {
            return getSearchResult(searchResponse);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new EmployeeException(e.getMessage());
        }

    }


    /**
     * Utility method for mapping the given records.
     * @param response
     *        result set  provided by elastic search
     * @return
     *        returns the business model object.
     * @throws Exception
     *        Any exception when modeling the business object.
     */
    private  List<Employee> getSearchResult(SearchResponse response) throws Exception{
        SearchHit[] searchHit = response.getHits().getHits();
        List<Employee> employees = new ArrayList<>();
        for (SearchHit hit : searchHit) {
            employees
                    .add(objectMapper
                            .convertValue(hit
                                    .getSourceAsMap(), Employee.class));
        }
        return employees;
    }

    /**
     * Used for  constructing the error message.
     * @param message
     *        message from where the error is popped up.
     * @param employeeException
     *        exception holding the required data.
     * @return
     *        constructed error message.
     */
    private String getErrorMessage(String message , EmployeeException employeeException){
        return ErrorConstants.ERROR_AT_DAO+message+employeeException.getMessage();

    }
}
