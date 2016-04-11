package br.com.clubelinkar.support.security.password.crypto

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordEncrypter implements IPasswordEncrypter {

    @Autowired
    PasswordEncoder passwordEncoder

    @Override
    String encrypt(String plainPassword) {
        if(null == plainPassword){
            return null
        }
        return passwordEncoder.encode(plainPassword)
    }
}
