package br.com.clubelinkar.test

import br.com.clubelinkar.api.company.Company

/**
 * @author Lennon Jesus
 */
class CompanyObjectMother {

    def static getaStore() {
        new Company(name: 'All Motos',
                description: 'All Motos',
                cnpj: "123.456.789/0001-23",
                password: "654321",
                address: 'Rua Siqueira Campos, 243, loja B, Copacabana, Rio de Janeiro - RJ',
                phones: '(21) 3442-3584 - WhatsApp (21) 98081-0033',
                url: 'allmotos.com.br',
                email: 'allmotos@allmotos.com.br')
    }

    def static getAnotherStore() {
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
