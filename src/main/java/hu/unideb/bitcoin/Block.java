package hu.unideb.bitcoin;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Random;

@Slf4j
public class Block {
    @Getter
    private String hash;
    @Getter
    @Setter
    private String previousHash;
    private String merkleRoot;
    private MerkleTree merkleTree;
    private Date timeStamp;
    private int nonce; // Number used only once

    public Block(String previousHash) {
        this.previousHash = previousHash;
        timeStamp = new Date();
        merkleTree = new MerkleTree();
        generateNonce();
    }

    public Block() {
        timeStamp = new Date();
        merkleTree = new MerkleTree();
        generateNonce();
    }

    public String calculateHash() {
        merkleRoot = merkleTree.getHash();
        String dataToHash = previousHash + timeStamp + nonce + merkleRoot;
        MessageDigest digest;
        byte[] bytes = null;

        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }

        return buffer.toString();
    }

    public void AddTransaction(String name, String message) {
        merkleTree.addTransaction(name, message);
        merkleRoot = merkleTree.getHash();
    }

    private void generateNonce() {
        int min, max;
        min = 1000;
        max = 5000;
        Random random = new Random();
        try {
            nonce = random.ints(min, max + 1).limit(1).findFirst().getAsInt();
        } catch (Exception e) {
            log.error("Error with generating nonce");
            nonce = min;
        }

    }
}
