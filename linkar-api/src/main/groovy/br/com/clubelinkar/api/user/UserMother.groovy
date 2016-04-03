package br.com.clubelinkar.api.user

import org.springframework.security.crypto.bcrypt.BCrypt

/**
 * @author Lennon Jesus
 */
public class UserMother {

    public static User lennonJesus() {
        return new User(
                id: "user_1",
                name: "Lennon Jesus",
                email: "lennon.jesus@gmail.com",
                password: BCrypt.hashpw("123456", BCrypt.gensalt()),
                cpf: "099.743.617-45",
                district: "Jardim Primavera",
                city: "Duque de Caxias",
                state: "RJ"
        )
    }

    public static User carlaVidal() {
        return new User(
                id: "user_2",
                name: "Carla Vidal",
                email: "blahblah@gmail.com",
                password: BCrypt.hashpw("1234567890", BCrypt.gensalt()),
                cpf: "099.617.743-45",
                district: "Marechal Hermes",
                city: "Rio de Janeiro",
                state: "RJ"
        )
    }

    public static User felipe() {
        return new User(
                id: null,
                name: "Felipe Abrantes",
                email: "felipe@blah.com",
                password: BCrypt.hashpw("1234567890", BCrypt.gensalt()),
                cpf: "111.2222.333-45",
                district: "Blah",
                city: "Rio de Janeiro",
                state: "RJ"
        )
    }


}
