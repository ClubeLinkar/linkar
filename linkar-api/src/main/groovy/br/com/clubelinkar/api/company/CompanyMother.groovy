package br.com.clubelinkar.api.company

/**
 * Created by felipe on 3/7/16.
 */
class CompanyMother {
    public static Company allMotos() {
        return new Company(
                id: "store_1",
                name: "All Motos",
                description: "A All Motos é uma loja de comércio eletrônico de peças e acessórios para motos. Selcionamos os melhores produtos para você e buscamos agilidade no atendimento.",
                address: "Rua Siqueira Campos, 243 Loja B, Copacabana, 22031-071, Rio de Janeiro - RJ",
                phones: "(21) 3442-3584",
                email: "allmotos@allmotos.com.br",
                responsiblePerson: "Tino Marcos",
                url: "http://www.allmotos.com.br",
                cnpj: "XX.XXX.XXX/YYYY-ZZ",
                password: "654321"
        )
    }

    public static Company mulherNaModa() {
        return new Company(
                id: "store_2",
                name: "Mulher na Moda",
                description: "A casa da mulher bem resolvida.",
                address: "Av. Brigadeiro Lima e Silva, 1590, Centro, Duque de Caxias, RJ",
                phones: "(21) 2771-1114",
                email: "mulhernamoda@blah123.com",
                responsiblePerson: "Tino Marcos",
                url: "http://www.blahblahblah123.com.br",
                cnpj: "XX.XXX.XXX/YYYY-ZZ",
                password: "987654321"
        )
    }

    public static Company novaLoja() {
        return new Company(
                id: null,
                name: "Nova Loja",
                description: "Essa loja é novinha em folha no sistema",
                address: "Blah Blah Blah",
                phones: "(21) 2771-1111",
                email: "novaloja@linkar.com",
                responsiblePerson: "Yamato Kushai Sang",
                url: "http://www.novinha.com.br",
                cnpj: "AA.BBB.CCC/YYYY-ZZ",
                password: "987654321"
        )
    }

    def static allMotosWithCategories() {
        Company c = allMotos()

        c.addCategory("c1")
        c.addCategory("c2")

        return c
    }

    def static allMotosWithRepeatedCategories() {
        Company c = allMotosWithCategories()

        c.addCategory("c1")
        c.addCategory("c2")

        return c
    }

    def static allMotosWithResponsible() {
        Company c = allMotos()

        c.responsiblePerson = "Tino Marcos"

        return c
    }

    def static homaMotos() {
        new Company(name: 'Homa Motos',
                description: 'Homa Motos',
                cnpj: "987.654.321-0001-23",
                password: "654321",
                address: 'Rua do Riachuelo, Centro, Rio de Janeiro - RJ',
                phones: '(21) 1111-2222',
                url: 'homamotos.com.br',
                email: 'contato@homamotos.com.br')
    }
}
