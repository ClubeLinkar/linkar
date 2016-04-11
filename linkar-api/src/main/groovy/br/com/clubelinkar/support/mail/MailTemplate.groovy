package br.com.clubelinkar.support.mail

/**
 * @author Lennon Jesus
 */
public enum MailTemplate {

    USER_REGISTRATION("newuser"),
    STORE_REGISTRATION("newstore")

    private String name

    MailTemplate(String name) {
        this.name = name
    }

    public String getName() {
        return name
    }
}
