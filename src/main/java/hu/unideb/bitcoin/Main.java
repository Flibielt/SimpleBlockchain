package hu.unideb.bitcoin;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

public class Main {

    private static final String RSA = "RSA";

    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        KeyPair keyPair1, keyPair2;
        keyPair1 = null;
        keyPair2 = null;
        try {
            keyPair1 = generateRSAKeyPair();
            keyPair2 = generateRSAKeyPair();
        } catch (Exception e) {
            System.out.println("Cannot generate key pair");
        }

        blockchain.addTransaction("sender1", "message1", keyPair1.getPrivate());
        blockchain.addTransaction("sender2", "message2", keyPair2.getPrivate());
        blockchain.mineBlock();
        System.out.println("Block added");

        blockchain.addBlock();

        blockchain.addTransaction("sender3", "message3", keyPair1.getPrivate());
        blockchain.addTransaction("sender4", "message4", keyPair2.getPrivate());
        blockchain.mineBlock();
        System.out.println("Block added");
    }

    public static KeyPair generateRSAKeyPair() throws Exception
    {
        SecureRandom secureRandom = new SecureRandom();

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);

        keyPairGenerator.initialize(2048, secureRandom);

        return keyPairGenerator.generateKeyPair();
    }

}
