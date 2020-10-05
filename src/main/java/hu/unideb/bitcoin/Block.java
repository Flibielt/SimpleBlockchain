package hu.unideb.bitcoin;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.Key;
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
    private final MerkleTree merkleTree;
    private final Date timeStamp;
    private int nonce; // Number used only once

    public Block(String previousHash) {
        this.previousHash = previousHash;
        timeStamp = new Date();
        merkleTree = new MerkleTree();
        hash = "";
    }

    public Block() {
        timeStamp = new Date();
        merkleTree = new MerkleTree();
        hash = "";
    }

    public String calculateHash(int prefix) {
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

        StringBuilder buffer = new StringBuilder();
        if (bytes != null) {
            for (byte b : bytes) {
                buffer.append(String.format("%02x", b));
            }
        }

        return buffer.toString();
    }

    public void AddTransaction(String name, String message, Key privateKey) {
        merkleTree.addTransaction(name, message, privateKey);
        merkleRoot = merkleTree.getHash();
    }

    public void mineBlock() {
        int prefix = 5;
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        hash = calculateHash(prefix);
        while (!hash.substring(0, prefix).equals(prefixString)) {
            nonce++;
            hash = calculateHash(prefix);
        }
    }

}
