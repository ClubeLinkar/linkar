package br.com.clubelinkar.support.security.password.policy

/**
 * Created by felipe on 3/19/16.
 */
interface IPasswordPolicy {
    boolean matches(String password)
}
