package com.techcoaster.crypto;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;


public class RsaEncryptionUtil {

    public static String encryptText(String msg, PublicKey key)
            throws InvalidKeyException,
            NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException,
            IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(cipher.doFinal(msg.getBytes(StandardCharsets.UTF_8)));
    }

    public static String decryptText(String msg, PrivateKey key)
            throws InvalidKeyException,
            NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException,
            IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.getDecoder().decode(msg)), StandardCharsets.UTF_8);
    }


    public static final PrivateKey getPrivate(String filename) throws Exception {
        List<String> privateKeyContent = Files.readAllLines(Paths.get(RsaEncryptionUtil.class.getResource(filename).toURI()));
        String concatString = privateKeyContent.stream().reduce((s1, s2) -> s1 + s2).get()
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "");
        byte[] decodedKeyBytes = Base64.getDecoder().decode(concatString.getBytes());
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decodedKeyBytes);
        return kf.generatePrivate(spec);
    }

    public static PublicKey getPublic(String filename) throws Exception {
        List<String> pubKeyContent = Files.readAllLines(Paths.get(RsaEncryptionUtil.class.getResource(filename).toURI()));
        String concatString = pubKeyContent.stream().reduce((s1, s2) -> s1 + s2).get()
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "");

        byte[] decodedKeyBytes = Base64.getDecoder().decode(concatString.getBytes());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decodedKeyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

}
