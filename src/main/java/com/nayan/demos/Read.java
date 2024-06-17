package com.nayan.demos;

import java.util.List;

public class Read {
    public List<Coin> readAll() {
        DataAccess dataAccess = new Percistance();
        return dataAccess.getAll();
    }

    public Coin read(String name) {
        DataAccess dataAccess = new Percistance();
        return dataAccess.get(name);
    }
}