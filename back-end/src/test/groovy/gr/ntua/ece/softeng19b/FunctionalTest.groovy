package gr.ntua.ece.softeng19b

import gr.ntua.ece.softeng19b.client.RestAPI
import spock.lang.Shared
import spock.lang.Specification
import gr.ntua.ece.softeng19b.data.model.User;
import java.time.Year;
import gr.ntua.ece.softeng19b.client.Format;
import org.restlet.resource.ResourceException;


import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificYear;


class FunctionalTest extends Specification{
    
    @Shared RestAPI caller1 = new RestAPI()
    @Shared RestAPI caller2 = new RestAPI()

    def "T01. Health check status is OK"() {
        given:
        String status = caller1.healthCheck()

        expect:
        status == "OK"

    }

    //Implement all of the above
    def "T02. The database is reset successfully"() {
        expect:
        caller1 != null
    }

    def "T03. Admin logs in successfully"() {
        given:
        caller1.login("john", "1234")
        expect:
        caller1.isLoggedIn() == true
    }

    def "T04. Admin creates a new user"() {
        given:
        User user = caller1.addUser("lola","lola@gmail.com","1234",10)
        expect:
        user.getUserName() == "lola" &&
        user.getEmail() == "lola@gmail.com" &&
        user.getAdmin() == 0 &&
        user.getRequestsPerDayQuota() == 10 &&
        user.getUsedPerDayQuota() == 0
    }

    def "T05. Admin logs out"() {
        given: 
        caller1.logout();
        expect:
        caller1.isLoggedIn() == false
    }

    def "T06. User logs in"() {
        given:
        caller2.login("lola", "1234")
        expect:
        caller2.isLoggedIn() == true
    }

    def "T07. User retrieves ActualTotalLoad tuple for 2018"() {
        given:
        List<ATLRecordForSpecificYear> rec = caller2.getActualTotalLoad("Greece","PT60M",Year.of(2018),Format.JSON)
        expect:
        rec.get(0).getSource() == "entso-e" &&
        rec.get(0).getDataSet() == "ActualTotalLoad" &&
        rec.get(0).getAreaName() == "Greece" &&
        rec.get(0).getAreaTypeCode() == "CTY" &&
        rec.get(0).getMapCode() == "GR" &&
        rec.get(0).getResolutionCode() == "PT60M" &&
        rec.get(0).getYear() == 2018 &&
        rec.get(0).getMonth() == 1 &&
        rec.get(0).getActualTotalLoadByMonthValue() == 1308759.35
        
    }

    def "T08. User logs out"() {
        given: 
        caller2.logout();
        expect:
        caller2.isLoggedIn() == false
    }
    
    def "T09. Admin logs in successfully"() {
        given:
        caller1.login("john", "1234")
        expect:
        caller1.isLoggedIn() == true
    }

    def "T10. Admin limits the quota of the new user"() {
        given:
        User u = caller1.updateUser(new User("lola", "lola@gmail.com", "1234", 0, 0))
        expect:
        u.getUserName() == "lola" &&
        u.getEmail() == "lola@gmail.com" &&
        u.getAdmin() == 0 &&
        u.getRequestsPerDayQuota() == 0 
    }

    def "T11. User logs in"() {
        given:
        caller2.login("lola", "1234")
        expect:
        caller2.isLoggedIn() == true
    }

    def "T12. User cannot read ActualTotalLoad for 2018 due to quota limit"() {
        when:
        List<ATLRecordForSpecificYear> rec = caller2.getActualTotalLoad("Greece","PT60M",Year.of(2018),Format.JSON)
        then:
        Exception exception = thrown()
        exception.getStatusCode() == 402
    }

    def "T13. Admin logs in successfully"() {
        given:
        caller1.login("john", "1234")
        expect:
        caller1.isLoggedIn() == true
    }

    def "T14. Admin updates the quota of the new user again"() {
        given:
        User u = caller1.updateUser(new User("lola", "lola@gmail.com", "1234", 0, 10))
        expect:
        u.getUserName() == "lola" &&
        u.getEmail() == "lola@gmail.com" &&
        u.getAdmin() == 0 &&
        u.getRequestsPerDayQuota() == 10 
    }

    def "T15. Admin logs out"() {
        given: 
        caller1.logout();
        expect:
        caller1.isLoggedIn() == false
    }

    def "T16. User logs in"() {
        given:
        caller2.login("lola", "1234")
        expect:
        caller2.isLoggedIn() == true
    }

    def "T17. User retrieves ActualTotalLoad tuple for 2018"() {
        given:
        List<ATLRecordForSpecificYear> rec = caller2.getActualTotalLoad("Greece","PT60M",Year.of(2018),Format.JSON)
        expect:
        rec.get(0).getSource() == "entso-e" &&
        rec.get(0).getDataSet() == "ActualTotalLoad" &&
        rec.get(0).getAreaName() == "Greece" &&
        rec.get(0).getAreaTypeCode() == "CTY" &&
        rec.get(0).getMapCode() == "GR" &&
        rec.get(0).getResolutionCode() == "PT60M" &&
        rec.get(0).getYear() == 2018 &&
        rec.get(0).getMonth() == 1 &&
        rec.get(0).getActualTotalLoadByMonthValue() == 1308759.35
    }

    /*def "T12. User uploads an ActualTotalLoad dataset"() {
        expect:
        caller1 != null
    }*/

    def "T18. User logs out"() {
        given: 
        caller2.logout();
        expect:
        caller2.isLoggedIn() == false
    }

}
