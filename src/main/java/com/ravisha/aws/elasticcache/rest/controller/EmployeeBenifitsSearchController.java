package com.ravisha.aws.elasticcache.rest.controller;


import com.ravisha.aws.elasticcache.rest.constants.RequestConstants;
import com.ravisha.aws.elasticcache.rest.constants.SwaggerConstants;
import com.ravisha.aws.elasticcache.rest.exception.EmployeeException;
import com.ravisha.aws.elasticcache.rest.model.Employee;
import com.ravisha.aws.elasticcache.rest.service.interfaces.EmployeeSearchService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Employee benifits search controller for the whole search operations for the employees
 * with the given plan or Sponsor or sponsor state.
 *
 * @author ravi Katta.
 */
@RestController
@RequestMapping(value = RequestConstants.BASE_URI)
@CrossOrigin(origins = {"*"})
public class EmployeeBenifitsSearchController {

    @Autowired
    private EmployeeSearchService employeeSearchService;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    /**
     * Responsible for fetching the employee list based on plan name, size and offset are optional fields.
     *
     * @param planName
     *        Name of the plan.
     * @param size
     *        Number of records to fetch, if the records are less than the given size, it will display those
     *        number of records only.
     * @param offset
     *        Specifies the offset from where the recrods has to be fetched.
     * @return
     *        List of employee satisfying the given criteria.
     */
    @RequestMapping(value = "",params = RequestConstants.REQUEST_PARAM_PLAN_NAME, method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> findByPlanName(@RequestParam(value = RequestConstants.REQUEST_PARAM_PLAN_NAME) String planName,@RequestParam(value = RequestConstants.REQUEST_PARAM_SIZE,defaultValue = RequestConstants.REQUEST_PARAM_SIZE_DEFAULT, required=false) int size,@RequestParam(value = RequestConstants.REQUEST_PARAM_OFFSET, defaultValue = RequestConstants.REQUEST_PARAM_OFFSET_DEFAULT, required=false) int offset ) {
        LOGGER.debug("Fetching employee details with plan name.");
        List<Employee> employeeList = employeeSearchService.findByPlanName(planName,size,offset);
        return ResponseEntity.status(HttpStatus.OK).body(employeeList);
    }

    /**
     * Responsible for fetching the employee list based on plan name, size and offset are optional fields.
     *
     * @param sponsorName
     *        Name of the sponsor.
     * @param size
     *        Number of records to fetch, if the records are less than the given size, it will display those
     *        number of records only.
     * @param offset
     *        Specifies the offset from where the recrods has to be fetched.
     * @return
     *        List of employee satisfying the given criteria.
     */
    @RequestMapping(value = "",params = RequestConstants.REQUEST_PARAM_SPONSOR_NAME, method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> findBySponsorName(@RequestParam(value = RequestConstants.REQUEST_PARAM_SPONSOR_NAME) String sponsorName,@RequestParam(value = RequestConstants.REQUEST_PARAM_SIZE, defaultValue = RequestConstants.REQUEST_PARAM_SIZE_DEFAULT, required=false) int size,@RequestParam(value = RequestConstants.REQUEST_PARAM_OFFSET, defaultValue = RequestConstants.REQUEST_PARAM_OFFSET_DEFAULT , required=false) int offset ) {
        LOGGER.debug("Fetching employee details with sponsor name.");
        List<Employee> employeeList = employeeSearchService.findBySponsorName(sponsorName,size,offset);
        return ResponseEntity.status(HttpStatus.OK).body(employeeList);
    }

    /**
     * Responsible for fetching the employee list based on plan name, size and offset are optional fields.
     *
     * @param sponsorState
     *        Name of the sponsor.
     * @param size
     *        Number of records to fetch, if the records are less than the given size, it will display those
     *        number of records only.
     * @param offset
     *        Specifies the offset from where the recrods has to be fetched.
     * @return
     *        List of employee satisfying the given criteria.
     */
    @RequestMapping(value = "",params = RequestConstants.REQUEST_PARAM_SPONSOR_STATE,method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> findBySponsorState(@RequestParam(value = RequestConstants.REQUEST_PARAM_SPONSOR_STATE)  String sponsorState,@RequestParam(value = RequestConstants.REQUEST_PARAM_SIZE, defaultValue = RequestConstants.REQUEST_PARAM_SIZE_DEFAULT, required=false) int size,@RequestParam(value = RequestConstants.REQUEST_PARAM_OFFSET,defaultValue = RequestConstants.REQUEST_PARAM_OFFSET_DEFAULT, required=false) int offset ) {
        LOGGER.debug("Fetching employee details with sponsor state.");
        List<Employee> employeeList = employeeSearchService.findBySponsorState(sponsorState,size,offset);
        return ResponseEntity.status(HttpStatus.OK).body(employeeList);
    }



}