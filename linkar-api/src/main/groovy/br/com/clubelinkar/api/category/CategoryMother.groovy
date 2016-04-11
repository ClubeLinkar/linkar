package br.com.clubelinkar.api.category

/**
 * Created by felipe on 3/7/16.
 */
class CategoryMother {
    public static Category servicosMecanica() {
        return new Category(
                id: "category_1",
                name: "Serviços de mecânica"
        )
    }

    public static Category pecasMoto() {
        return new Category(
                id: "product_2",
                name: "Peças de moto"
        )
    }
}
