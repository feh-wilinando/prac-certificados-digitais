package br.com.fws.certificado_digital.services;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptorService {

	public String encrypt(String raw) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");

			byte[] digest = messageDigest.digest(raw.getBytes());


			String encoded = Base64.encodeBase64String(digest);

			return encoded;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}
