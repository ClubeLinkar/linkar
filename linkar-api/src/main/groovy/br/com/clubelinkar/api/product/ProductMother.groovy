package br.com.clubelinkar.api.product

/**
 * Created by felipe on 3/7/16.
 */
class ProductMother {
    public static Product riserGuidao() {
        return new Product(
                id: "product_1",
                name: "Riser de guidão Anker para Triumhp 800 XC",
                description: "Os Riser’s de guidão Anker Acessórios são projetados para ajustar a altura do guidão, elevando-o para uma postura correta de pilotagem e com isso proporcionando maior conforto. Usinados a partir de alumínio naval com traços que remetem à originalidade da motocicleta, são disponíveis na versão alongador e alongador recuado/avançado.",
                brand: "ANKER",
                price: new BigDecimal(189),
                companyId: "store_1",
                categories: ["cat 1", "cat funda"] as SortedSet
        )
    }

    def static riserGuidaoWithCategories() {
        Product product = riserGuidao()
        product.categories = ["cat1", "cat2"] as SortedSet
        return product
    }

    def static riserGuidaoWithRepeatedCategories() {
        Product product = riserGuidaoWithCategories()
        product.addCategory("cat2")
        return product
    }

    public static Product espadrilla() {
        return new Product(
                id: "product_2",
                name: "Espadrille Caramelo & Preta 7,5cmC",
                description: "Espadrille nas cores caramelo e preta.\n" +
                        "Fechamento por tira para amarração no tornozelo.\n" +
                        "Parte posterior com recorte vazado.\n" +
                        "Salto médio.\n" +
                        "Palmilha com inscrição da marca.\n" +
                        "Solado antiderrapante.",
                brand: "Azaleia",
                price: new BigDecimal(189),
                companyId: "store_2",
                categories: ["cat 2"] as SortedSet
        )
    }
}
