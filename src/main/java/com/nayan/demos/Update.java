package com.nayan.demos;

public class Update {
    public boolean update(Coin coin) {
        DataAccess dataAccess = new Percistance();
        return dataAccess.update(coin);
    }
}