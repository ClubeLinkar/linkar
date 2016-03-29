package br.com.clubelinkar.support.security.domain

/**
 * @author Lennon Jesus
 */
enum Role {

    USER("USER"), ADMIN("ADMIN"), SALESMAN("SALESMAN");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
