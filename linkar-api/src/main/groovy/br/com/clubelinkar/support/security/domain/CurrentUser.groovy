package br.com.clubelinkar.support.security.domain

import br.com.clubelinkar.api.user.User
import org.springframework.security.core.authority.AuthorityUtils

/**
 * @author Lennon Jesus
 */
class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user

    public CurrentUser(User user) {
        super(user.email, user.password, AuthorityUtils.createAuthorityList(Role.ADMIN.name))
        this.user = user
    }

    public User getUser() {
        return user
    }

    public String getId() {
        return user.id
    }

    public Role getRole() {
        return Role.ADMIN
    }

}