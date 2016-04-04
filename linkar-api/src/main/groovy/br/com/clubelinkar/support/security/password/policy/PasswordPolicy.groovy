package br.com.clubelinkar.support.security.password.policy

import org.springframework.stereotype.Component

import static org.apache.commons.lang3.StringUtils.isNotBlank

@Component
class PasswordPolicy implements IPasswordPolicy {

    boolean matches(String password) {
        return isNotBlank(password) &&
                hasMinimumOf8Characters(password) &&
                hasAtLeastOneUppercaseCharacter(password) &&
                hasAtLeastOneNumber(password)
    }

    private Boolean hasMinimumOf8Characters(String password) {
        return password.length() >= 8
    }

    private Boolean hasAtLeastOneUppercaseCharacter(String password) {
        return password =~ /[A-Z]{1,}/
    }

    private Boolean hasAtLeastOneNumber(String password) {
        return password =~ /[0-9]{1,}/
    }
}
