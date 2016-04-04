package br.com.clubelinkar.support.security.password.policy

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by felipe on 3/19/16.
 */
class PasswordPolicyShould extends Specification {

    IPasswordPolicy policy

    def setup() {
        policy = new PasswordPolicy()
    }

    @Unroll
    void "password #pwd must have at least 8 chars, one upper case and one number"() {
        expect:
        isValid == policy.matches(pwd)

        where:
        pwd           | isValid
        null          | false
        ""            | false
        "   "         | false
        "12345678"    | false
        "aBc1"        | false
        "abcdefgh"    | false
        "abcDefgh"    | false
        "abcdefg8"    | false
        "abcdefG8"    | true
        "abCdefG8"    | true
        "abCdefG89Ik" | true
    }
}
