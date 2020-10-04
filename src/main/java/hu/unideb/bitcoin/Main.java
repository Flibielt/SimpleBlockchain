package hu.unideb.bitcoin;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private List<Block> blockchain;

    public static void main(String[] args) {
        Main main = new Main();
        main.addBlock();
    }

    public Main() {
        blockchain = new ArrayList<>();
        Block block = new Block();
        blockchain.add(block);
    }

    public void addBlock() {
        blockchain.get(blockchain.size() - 1).calculateHash();
        Block block = new Block(blockchain.get(blockchain.size() - 1).getHash());
    }

    public void addTransaction(String name, String message) {
        blockchain.get(blockchain.size() - 1).AddTransaction(name, message);
    }

}
