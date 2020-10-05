package hu.unideb.bitcoin;

public class Main {

    private static final String RSA = "RSA";

    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        String publicKey1, publicKey2;
        publicKey1 = "PK1";
        publicKey2 = "PK2";

        blockchain.addTransaction("sender1", "message1", publicKey1);
        blockchain.addTransaction("sender2", "message2", publicKey2);
        blockchain.mineBlock();
        System.out.println("Block added");

        blockchain.addBlock();

        blockchain.addTransaction("sender3", "message3", publicKey1);
        blockchain.addTransaction("sender4", "message4", publicKey2);
        blockchain.mineBlock();
        System.out.println("Block added");
    }

}
