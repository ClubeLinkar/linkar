package br.com.clubelinkar.support.mail;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import java.util.Locale;

/**
 * @author Lennon Jesus
 */
public class Mail {

    private String subject;

    private String from;

    private String to;

    private MailTemplate template;

    private Context parameters;

    public Mail() {
        this.parameters = new Context(new Locale("pt_BR"));
    }

    public String getSubject() {
        return subject;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public MailTemplate getTemplate() {
        return template;
    }

    public IContext getParameters() {
        return parameters;
    }

    public Mail from(String from) {
        this.from = from;

        return this;
    }

    public Mail to(String to) {
        this.to = to;

        return this;
    }

    public Mail subject(String subject) {
        this.subject = subject;

        return this;
    }

    public Mail template(MailTemplate template) {
        this.template = template;

        return this;
    }

    public Mail addParameter(String name, Object value) {
        this.parameters.setVariable(name, value);

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Mail mail = (Mail) o;

        return new EqualsBuilder()
                .append(subject, mail.subject)
                .append(from, mail.from)
                .append(to, mail.to)
                .append(template, mail.template)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(subject)
                .append(from)
                .append(to)
                .append(template)
                .toHashCode();
    }
}
