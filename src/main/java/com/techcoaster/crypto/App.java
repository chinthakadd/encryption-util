package com.techcoaster.crypto;

import java.security.PrivateKey;
import java.security.PublicKey;

public class App {

    public static void main(String[] args) throws Exception {
        PublicKey publicKey = RsaEncryptionUtil.getPublic("/public_key.pem");
        String encContent = RsaEncryptionUtil.encryptText("encrypt me", publicKey);
        System.out.println(encContent);

        PrivateKey privateKey = RsaEncryptionUtil.getPrivate("/private_key_pkcs8_one_line.pem");
        System.out.println(RsaEncryptionUtil.decryptText(encContent, privateKey));
    }
}
