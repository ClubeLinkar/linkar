package br.com.clubelinkar.support.security.domain

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Lennon Jesus
 */
public class RoleUnitSpec extends Specification {


    @Unroll
    def "Deve retornar Role from String"() {

        expect:
        Role.from(str) == role


        where:
        str         | role
        "ADMIN"     | Role.ADMIN
        "CUSTOMER"  | Role.CUSTOMER
        "SALESMAN"  | Role.SALESMAN
        "USER"      | Role.USER

    }

}