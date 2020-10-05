package hu.unideb.bitcoin;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private final List<Block> blockchain;

    public Blockchain() {
        blockchain = new ArrayList<>();
        Block block = new Block();
        // Create genesis block
        blockchain.add(block);
    }

    public void addBlock() {
        blockchain.get(blockchain.size() - 1).mineBlock();
        Block block = new Block(blockchain.get(blockchain.size() - 1).getHash());
        blockchain.add(block);
    }

    public void mineBlock() {
        blockchain.get(blockchain.size() - 1).mineBlock();
    }

    public void addTransaction(String name, String message, Key privateKey) {
        blockchain.get(blockchain.size() - 1).AddTransaction(name, message, privateKey);
    }
}
