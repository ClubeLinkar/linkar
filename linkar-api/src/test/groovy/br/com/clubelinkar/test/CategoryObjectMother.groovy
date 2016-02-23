package br.com.clubelinkar.test

import br.com.clubelinkar.api.category.Category

/**
* @author Lennon Jesus
*/
class CategoryObjectMother {

    def static getaCategory() {
        new Category (
                name: "Serviços de mecânica"
        )
    }

    def static getAnotherCategory() {
        new Category(
                name: "Peças de moto"
        )
    }

}
