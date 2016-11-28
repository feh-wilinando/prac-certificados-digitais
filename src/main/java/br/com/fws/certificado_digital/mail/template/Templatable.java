package br.com.fws.certificado_digital.mail.template;

/**
 * Created by nando on 28/11/16.
 */
public interface Templatable {


    default <T> T unwrap(Class<T> classe) {

        T cast = classe.cast(this);

        return cast;
    }
}
