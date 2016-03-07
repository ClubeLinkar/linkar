package br.com.clubelinkar.support.mail

import groovy.transform.EqualsAndHashCode
import org.thymeleaf.context.Context
import org.thymeleaf.context.IContext

/**
 * @author Lennon Jesus
 */
@EqualsAndHashCode
public class Mail {

    private String subject

    private String from

    private String to

    private MailTemplate template

    private Context parameters

    public Mail() {
        this.parameters = new Context(new Locale("pt_BR"))
    }

    public String getSubject() {
        return subject
    }

    public String getFrom() {
        return from
    }

    public String getTo() {
        return to
    }

    public MailTemplate getTemplate() {
        return template
    }

    public IContext getParameters() {
        return parameters
    }

    public Mail from(String from) {
        this.from = from

        return this
    }

    public Mail to(String to) {
        this.to = to

        return this
    }

    public Mail subject(String subject) {
        this.subject = subject

        return this
    }

    public Mail template(MailTemplate template) {
        this.template = template

        return this
    }

    public Mail addParameter(String name, Object value) {
        this.parameters.setVariable(name, value)

        return this
    }

}
