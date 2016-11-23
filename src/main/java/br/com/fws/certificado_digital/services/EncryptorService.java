package br.com.fws.certificado_digital.services;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.jboss.security.Base64Encoder;

public class EncryptorService {

	public String encrypt(String raw) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");

			byte[] hashed = messageDigest.digest(raw.getBytes());

			String encoded = Base64Encoder.encode(hashed);

			return encoded;
		} catch (NoSuchAlgorithmException | IOException e) {			
			throw new RuntimeException(e);
		}
	}

}
