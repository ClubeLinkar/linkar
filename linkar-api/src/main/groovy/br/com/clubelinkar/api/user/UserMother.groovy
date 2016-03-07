package br.com.clubelinkar.api.user

import br.com.clubelinkar.api.category.Category
import br.com.clubelinkar.api.company.Company
import br.com.clubelinkar.api.product.Product
import br.com.clubelinkar.api.user.User

/**
 * @author Lennon Jesus
 */
public class UserMother {

    public static User lennonJesus() {
        User user = new User()

        user.setId("user_1")
        user.setName("Lennon Jesus")
        user.setEmail("lennon.jesus@gmail.com")
        user.setPassword("123456")
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
        user.setPassword("1234567890")
        user.setCpf("099.617.743-45")
        user.setDistrict("Marechal Hermes")
        user.setCity("Rio de Janeiro")
        user.setState("RJ")

        return user
    }


}
