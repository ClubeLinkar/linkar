package br.com.clubelinkar.api.category

/**
 * Created by felipe on 3/7/16.
 */
class CategoryMother {
    public static Category servicosMecanica() {
        Category category = new Category()

        category.setId("category_1")
        category.setName("Serviços de mecânica")

        return category
    }

    public static Category pecasMoto() {
        Category category = new Category()

        category.setId("product_2")
        category.setName("Peças de moto")

        return category
    }
}
