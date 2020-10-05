package hu.unideb.bitcoin;

import lombok.Data;

import java.util.Date;

@Data
public class Transaction {
    private String name;
    private Date date;
    private String message;
    private byte[] digitalSignature;

    @Override
    public String toString() {
        return name + date.toString() + message;
    }
}
