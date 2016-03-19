package br.com.clubelinkar.api.user

/**
 * Created by felipe on 3/19/16.
 */
interface IPasswordPolicy {
    boolean matches(String password)
}
