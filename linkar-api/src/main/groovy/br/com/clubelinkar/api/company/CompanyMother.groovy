package br.com.clubelinkar.api.company

/**
 * Created by felipe on 3/7/16.
 */
class CompanyMother {
    public static Company allMotos() {
        Company company = new Company();

        company.setId("store_1");
        company.setName("All Motos");
        company.setDescription("A All Motos é uma loja de comércio eletrônico de peças e acessórios para motos. Selcionamos os melhores produtos para você e buscamos agilidade no atendimento.");
        company.setAddress("Rua Siqueira Campos, 243 Loja B, Copacabana, 22031-071, Rio de Janeiro - RJ");
        company.setPhones("(21) 3442-3584");
        company.setEmail("allmotos@allmotos.com.br");
        company.setResponsiblePerson("Tino Marcos");
        company.setUrl("http://www.allmotos.com.br");
        company.setCnpj("XX.XXX.XXX/YYYY-ZZ");
        company.setPassword("654321");

        return company;
    }

    public static Company mulherNaModa() {
        Company company = new Company();

        company.setId("store_2");
        company.setName("Mulher na Moda");
        company.setDescription("A casa da mulher bem resolvida.");
        company.setAddress("Av. Brigadeiro Lima e Silva, 1590, Centro, Duque de Caxias, RJ");
        company.setPhones("(21) 2771-1114");
        company.setEmail("mulhernamoda@blah123.com");
        company.setUrl("http://www.blahblahblah123.com.br");
        company.setCnpj("XX.XXX.XXX/YYYY-ZZ");
        company.setPassword("987654321");

        return company;
    }
}
