package br.com.clubelinkar.support.mail;

/**
 * @author Lennon Jesus
 */
public enum MailTemplate {

    USER_REGISTRATION("newuser");

    private String name;

    MailTemplate(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
