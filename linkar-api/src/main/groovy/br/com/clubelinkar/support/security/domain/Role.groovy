package br.com.clubelinkar.support.security.domain

/**
 * @author Lennon Jesus
 */
enum Role {

    USER("USER"), ADMIN("ADMIN"), SALESMAN("SALESMAN"), CUSTOMER("CUSTOMER");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Role from(String str) {


        for (Role role : values()) {
            if (str == role.name) {
                return role
            }
        }

        null

    }
}
