package br.com.clubelinkar.support.crypto

/**
 * Created by felipe on 3/14/16.
 */
interface IPasswordEncrypter {

    /**
     * @param plainPassword The original String
     * @return A hash representation of {@param plainPassword} in SHA-512 representation
     */
    String encrypt(String plainPassword)
}
