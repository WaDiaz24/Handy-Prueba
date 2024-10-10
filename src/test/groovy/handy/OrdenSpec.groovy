package handy

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class OrdenSpec extends Specification implements DomainUnitTest<Orden> {

     void "test domain constraints"() {
        when:
        Orden domain = new Orden()
        //TODO: Set domain props here

        then:
        domain.validate()
     }
}
