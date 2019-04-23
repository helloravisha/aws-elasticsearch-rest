package com.ravisha.aws.elasticcache.rest.service.impl;

import com.ravisha.aws.elasticcache.rest.dao.interfaces.EmployeeSearchDAO;
import com.ravisha.aws.elasticcache.rest.exception.EmployeeException;
import com.ravisha.aws.elasticcache.rest.model.Employee;
import com.ravisha.aws.elasticcache.rest.service.interfaces.EmployeeSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Employee service  layer  responsible for fetching the employee details using underlying dao
 * with the given plan or Sponsor or sponsor state.
 *
 * @author ravi katta.
 */
@Service
public class EmployeeSearchServiceImpl implements EmployeeSearchService {

    @Autowired
    private EmployeeSearchDAO employeeSearchDAO;

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
     *         Throws an exception , when there is any issue at the dao layer as there is no further
     *         data processing at the service layer.
     */
    public List<Employee> findByPlanName(String planName,int size,int from) throws EmployeeException {
       return  employeeSearchDAO.findByPlanName(planName,size,from);
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
     *         Throws an exception , when there is any issue at the dao layer as there is no further
     *         data processing at the service layer.
     */
    public List<Employee> findBySponsorName(String sponsorName,int size,int from) throws EmployeeException {
        return  employeeSearchDAO.findBySponsorName(sponsorName,size,from);
    }

    /**
     * Responsible for fetching the employee list based on sponsor state, size and from.
     *
     * @param sponsorState
     *        sponsorName for fetching the user details.
     * @param size
     *        Number of records to be fetched.
     * @param from
     *        offset from where the records has to be fetched,
     * @return
     *        returns the list of employee, if no records are there retuns an empty list.
     *
     * @throws EmployeeException
     *         Throws an exception , when there is any issue at the dao layer as there is no further
     *         data processing at the service layer.
     */
    public List<Employee> findBySponsorState(String sponsorState,int size,int from) throws EmployeeException {
        return  employeeSearchDAO.findBySponsorState(sponsorState,size,from);
    }

}

