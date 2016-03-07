package br.com.clubelinkar.api.product

/**
 * Created by felipe on 3/7/16.
 */
class ProductMother {
    public static Product riserGuidao() {
        Product product = new Product()

        product.setId("product_1")
        product.setName("Riser de guidão Anker para Triumhp 800 XC")
        product.setDescription("Os Riser’s de guidão Anker Acessórios são projetados para ajustar a altura do guidão, elevando-o para uma postura correta de pilotagem e com isso proporcionando maior conforto. Usinados a partir de alumínio naval com traços que remetem à originalidade da motocicleta, são disponíveis na versão alongador e alongador recuado/avançado.")
        product.setBrand("ANKER")
        product.setPrice(new BigDecimal(189))
        product.setStoreId("store_1")
        product.addCategory("cat 1")
        product.addCategory("cat funda")

        return product
    }

    public static Product espadrilla() {
        Product product = new Product()

        product.setId("product_2")
        product.setName("Espadrille Caramelo & Preta 7,5cmC")
        product.setDescription("Espadrille nas cores caramelo e preta.\n" +
                "Fechamento por tira para amarração no tornozelo.\n" +
                "Parte posterior com recorte vazado.\n" +
                "Salto médio.\n" +
                "Palmilha com inscrição da marca.\n" +
                "Solado antiderrapante.")
        product.setBrand("Azaléia")
        product.setPrice(new BigDecimal(189))
        product.setStoreId("store_2")
        product.addCategory("cat 2")

        return product
    }
}
