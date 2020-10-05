package hu.unideb.bitcoin;

import lombok.Data;

import java.util.Date;

@Data
public class Transaction {
    private String name;
    private Date date;
    private String message;
    private String publicKey;

    @Override
    public String toString() {
        return name + publicKey + date.toString() + message;
    }
}
