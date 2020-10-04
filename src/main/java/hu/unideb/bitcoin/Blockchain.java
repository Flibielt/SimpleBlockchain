package hu.unideb.bitcoin;

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
        blockchain.get(blockchain.size() - 1).calculateHash();
        Block block = new Block(blockchain.get(blockchain.size() - 1).getHash());
        blockchain.add(block);
    }

    public void addTransaction(String name, String message) {
        blockchain.get(blockchain.size() - 1).AddTransaction(name, message);
    }
}
