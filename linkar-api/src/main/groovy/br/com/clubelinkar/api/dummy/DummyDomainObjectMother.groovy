package br.com.clubelinkar.api.dummy

import br.com.clubelinkar.api.category.Category
import br.com.clubelinkar.api.user.User
import br.com.clubelinkar.api.product.Product
import br.com.clubelinkar.api.store.Store

import java.math.BigDecimal

/**
 * @author Lennon Jesus
 */
public class DummyDomainObjectMother {

    public static User getUser() {
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

    public static User getAnotherUser() {
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

    public static Product getProduct() {
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

    public static Product getAnotherProduct() {
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

    public static Store getStore() {
        Store store = new Store()

        store.setId("store_1")
        store.setName("All Motos")
        store.setDescription("A All Motos é uma loja de comércio eletrônico de peças e acessórios para motos. Selcionamos os melhores produtos para você e buscamos agilidade no atendimento.")
        store.setAddress("Rua Siqueira Campos, 243 Loja B, Copacabana, 22031-071, Rio de Janeiro - RJ")
        store.setPhones("(21) 3442-3584")
        store.setEmail("allmotos@allmotos.com.br")
        store.setUrl("http://www.allmotos.com.br")
        store.setCnpj("XX.XXX.XXX/YYYY-ZZ")
        store.setPassword("654321")

        return store
    }

    public static Store getAnotherStore() {
        Store store = new Store()

        store.setId("store_2")
        store.setName("Mulher na Moda")
        store.setDescription("A casa da mulher bem resolvida.")
        store.setAddress("Av. Brigadeiro Lima e Silva, 1590, Centro, Duque de Caxias, RJ")
        store.setPhones("(21) 2771-1114")
        store.setEmail("mulhernamoda@blah123.com")
        store.setUrl("http://www.blahblahblah123.com.br")
        store.setCnpj("XX.XXX.XXX/YYYY-ZZ")
        store.setPassword("987654321")

        return store
    }

    public static Category getCategory() {
        Category category = new Category()

        category.setId("category_1")
        category.setName("Serviços de mecânica")

        return category
    }

    public static Category getAnotherCategory() {
        Category category = new Category()

        category.setId("product_2")
        category.setName("Peças de moto")

        return category
    }



}
