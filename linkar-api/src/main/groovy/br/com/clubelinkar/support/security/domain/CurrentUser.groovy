package br.com.clubelinkar.support.security.domain

import br.com.clubelinkar.api.user.User
import org.springframework.security.core.authority.AuthorityUtils

/**
 * @author Lennon Jesus
 */
class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user

    public CurrentUser(User user) {
        super(user.email, user.password, AuthorityUtils.createAuthorityList(user.role))
        this.user = user
    }

    public User getUser() {
        user
    }

    public String getId() {
        user.id
    }

    public Role getRole() {
        Role.from(user.role)
    }

}