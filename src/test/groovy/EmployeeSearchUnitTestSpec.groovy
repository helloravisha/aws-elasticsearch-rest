import com.ravisha.aws.elasticcache.rest.AWSElasticSearchApplication
import com.ravisha.aws.elasticcache.rest.service.interfaces.EmployeeSearchService
import spock.lang.Specification
import spock.lang.Unroll

import spock.lang.Shared
import spock.lang.Specification

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = AWSElasticSearchApplication.class)
class EmployeeSearchUnitTesting extends Specification {

    @Autowired
    private EmployeeSearchService employeeSearchService;


    def employeeDetailsSize = 0;
    def sponsorState = null
    def planName = null
    def sponsorName = null
    def employeeDetails = null
    def sponsorReturnedName = null;
    def count = 0
    def offset = 0

    def "Check if sponsor name  API is working "() {

        given: "given sponsor name , size and offset of records to be fetched"
        sponsorName = "ravi"
        count = 3
        offset = 0

        when: "Rest API for getting employee details called with given parameters"
        def employeeDetails = employeeSearchService.findBySponsorName(sponsorName,count,offset)
        employeeDetailsSize = employeeDetails.size()
        println "Total records Fetched : "+employeeDetailsSize
        println "First employee record fetched with given name : "+employeeDetails.get(0).getSponsorSignedName()

        then: "we should get three employee records as we asked for only 3 records"
        employeeDetailsSize == count

    }

    def "Check if sponsor state  API is working "() {

        given: "given sponsor name , size and offset of records to be fetched"
        sponsorState = "TX"
        count = 3
        offset = 0

        when: "Rest API for getting employee details called with given parameters"
        def employeeDetails = employeeSearchService.findBySponsorState(sponsorState,count,offset)
        employeeDetailsSize = employeeDetails.size()
        println "Total records Fetched : "+employeeDetailsSize
        println "First employee record fetched with given state : "+employeeDetails.get(0).getSponsorMailingState()


        then: "we should get three employee records as we asked for only 3 records"
        employeeDetailsSize == count
    }

    def "Check if plan name  API is working "() {

        given: "given plan name , size and offset of records to be fetched"
        planName = "401"
        count = 3
        offset = 0

        when: "Rest API for getting employee details called with given parameters"
        def employeeDetails = employeeSearchService.findByPlanName(planName,count,offset)
        employeeDetailsSize = employeeDetails.size()
        println "Total records Fetched : "+employeeDetailsSize
        println "First employee record fetched with given Plan : "+employeeDetails.get(0).getPlanName()

        then: "we should get three employee records as we asked for only 3 records"
        employeeDetailsSize == count

    }
}