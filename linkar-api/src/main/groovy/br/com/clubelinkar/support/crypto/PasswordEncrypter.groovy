package br.com.clubelinkar.support.crypto

import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Component

@Component
class PasswordEncrypter implements IPasswordEncrypter {

    @Override
    String encrypt(String plainPassword) {
        if(null == plainPassword){
            return null
        }
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt())
    }
}
