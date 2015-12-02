package br.com.clubelinkar.test

import br.com.clubelinkar.api.user.User

/**
 * @author Lennon Jesus
 */
class UserObjectMother {

    def static User getAnUser() {
        new User(
                name: "Lennon Jesus",
                email: "lennon.jesus@gmail.com",
                cpf: "09974361745",
                district: "Jardim Primavera",
                city: "Duque de Caxias",
                state: "RJ",
                password: "123456"
        )
    }

    def static User getAnotherUser() {
        new User(
                name: "Lorem Ipsum Dolor User",
                email: "anotheruser@domain.com",
                cpf: "22233344410",
                district: "São José do Barreiro",
                city: "Bananal",
                state: "SP",
                password: "um2três"
        )
    }

}
