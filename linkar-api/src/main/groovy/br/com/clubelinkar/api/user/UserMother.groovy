package br.com.clubelinkar.api.user

import org.springframework.security.crypto.bcrypt.BCrypt

/**
 * @author Lennon Jesus
 */
public class UserMother {

    public static User lennonJesus() {
        User user = new User()

        user.setId("user_1")
        user.setName("Lennon Jesus")
        user.setEmail("lennon.jesus@gmail.com")
        user.setPassword(BCrypt.hashpw("123456", BCrypt.gensalt()))
        user.setCpf("099.743.617-45")
        user.setDistrict("Jardim Primavera")
        user.setCity("Duque de Caxias")
        user.setState("RJ")

        return user
    }

    public static User carlaVidal() {
        User user = new User()

        user.setId("user_2")
        user.setName("Carla Vidal")
        user.setEmail("blahblah@gmail.com")
        user.setPassword(BCrypt.hashpw("1234567890", BCrypt.gensalt()))
        user.setCpf("099.617.743-45")
        user.setDistrict("Marechal Hermes")
        user.setCity("Rio de Janeiro")
        user.setState("RJ")

        return user
    }


}
