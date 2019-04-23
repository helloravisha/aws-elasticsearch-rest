package com.ravisha.aws.elasticcache.rest.dao.interfaces;


import com.ravisha.aws.elasticcache.rest.exception.EmployeeException;
import com.ravisha.aws.elasticcache.rest.model.Employee;
import java.util.List;

/**
 *
 * Employee search DAO layer  responsible for fetching the employee details using elastic search
 * rest high level client with the given plan or Sponsor or sponsor state.
 *
 * @author ravi katta.
 */
public interface EmployeeSearchDAO {

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
     List<Employee> findByPlanName(String planName,int size,int from) throws EmployeeException;

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
     List<Employee> findBySponsorName(String sponsorName,int size,int from) throws EmployeeException;

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
     List<Employee> findBySponsorState(String sponsorState,int size,int from) throws EmployeeException;
}
