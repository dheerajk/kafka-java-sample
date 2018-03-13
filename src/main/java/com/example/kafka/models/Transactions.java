package com.example.kafka.models;

import java.util.List;

public class Transactions {

    private List<Transaction> txns;

    public List<Transaction> getTxns() {
        return txns;
    }

    public void setTxns(List<Transaction> txns) {
        this.txns = txns;
    }
}
