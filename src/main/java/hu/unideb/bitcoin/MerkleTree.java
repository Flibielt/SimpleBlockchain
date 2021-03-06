package hu.unideb.bitcoin;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class MerkleTree {
    private final List<Transaction> transactions;
    private final List<String> hashList;
    private MessageDigest md;
    @Getter
    private String hash;

    public MerkleTree() {
        transactions = new ArrayList<>();
        hashList = new ArrayList<>();
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            log.error("Error with creating message digest instance");
        }
    }

    public void addTransaction(String name, String message, Key publicKey) {
        Transaction transaction = new Transaction();
        transaction.setName(name);
        transaction.setMessage(message);
        transaction.setDate(new Date());
        byte[] messageHash = md.digest(transaction.toString().getBytes());
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] digitalSignature = cipher.doFinal(messageHash);
            transaction.setDigitalSignature(digitalSignature);
        } catch (Exception e) {
            log.error("Cannot create digital signature");
        }


        transactions.add(transaction);
        buildTree();
    }

    private void buildTree() {
        int start, end;
        start = 0;
        hashList.clear();

        for (Transaction transaction : transactions) {
            byte[] messageDigest = md.digest(transaction.toString().getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hashText = new StringBuilder(no.toString(16));

            while (hashText.length() < 32) {
                hashText.insert(0, "0");
            }

            hashList.add(hashText.toString());
        }

        end = hashList.size();

        while (end - start > 1) {
            for (int index = start; index < end; index+=2) {
                String commonHash = hashList.get(index) + hashList.get(index++);
                byte[] messageDigest = md.digest(commonHash.getBytes());
                BigInteger no = new BigInteger(1, messageDigest);
                commonHash = no.toString(16);

                hashList.add(commonHash);
            }
            start = end;
            end = hashList.size();
        }

        hash = hashList.get(hashList.size() - 1);

    }

}
