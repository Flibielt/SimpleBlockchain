package hu.unideb.bitcoin;

public class Main {

    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();

        blockchain.addTransaction("sender1", "message1");
        blockchain.addTransaction("sender2", "message2");

        blockchain.addBlock();

        blockchain.addTransaction("sender3", "message3");
        blockchain.addTransaction("sender4", "message4");
    }

}
