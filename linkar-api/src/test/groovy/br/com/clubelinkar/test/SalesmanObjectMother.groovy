package br.com.clubelinkar.test

import br.com.clubelinkar.api.salesman.Salesman
import org.springframework.security.crypto.bcrypt.BCrypt

/**
 * @author Lennon Jesus
 */
class SalesmanObjectMother {

    public static Salesman getaSalesman() {
        return new Salesman(
                id: "user_1",
                name: "Lennon Jesus",
                email: "lennon.jesus@gmail.com",
                password: BCrypt.hashpw("123456", BCrypt.gensalt()),
                cpf: "099.743.617-45",
                phones: "21 92222-2222"
        )
    }

    public static Salesman getAnotherSalesman() {
        return new Salesman(
                id: "user_2",
                name: "Carla Vidal",
                email: "blahblah@gmail.com",
                password: BCrypt.hashpw("1234567890", BCrypt.gensalt()),
                cpf: "099.617.743-45",
                phones: "21 91111-1111"
        )
    }

    public static Salesman getaNewSalesman() {
        return new Salesman(
                id: null,
                name: "Vera Alice",
                email: "vera@gmail.com",
                password: BCrypt.hashpw("1234567890", BCrypt.gensalt()),
                cpf: "581.537.487-34",
                phones: "21 3333-3333"
        )
    }

}
