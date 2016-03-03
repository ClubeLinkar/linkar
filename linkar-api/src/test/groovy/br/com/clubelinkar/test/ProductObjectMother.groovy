package br.com.clubelinkar.test

import br.com.clubelinkar.api.product.Product

/**
 * @author Lennon Jesus
 */
class ProductObjectMother {

    def static getaProduct() {
        new Product(
                name: "Riser de Guidão",
                description: "Riser de guidão preto fosco",
                brand: "ANKER",
                price: 99.00,
                storeId: "idAllMotos"
        )
    }

    def static getaProductWithCategories() {
        Product product = aProduct
        product.categories = ["cat1", "cat2"] as SortedSet
        return product
    }

    def static getaProductWithRepeatedCategories() {
        Product product = aProductWithCategories
        product.addCategory("cat2")
        return product
    }

    def static getAnotherProduct() {
        new Product(
                name: "Kit Relação Top",
                description: "Kit Coroa, Corrente e Pinhão Top Aço 1049",
                brand: "Riffel",
                price: 543.21,
                storeId: "idHomaMotos"
        )
    }

}
