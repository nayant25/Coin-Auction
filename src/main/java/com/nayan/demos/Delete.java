package com.nayan.demos;

public class Delete {
    public boolean delete(String name) {
        DataAccess dataAccess = new Percistance();
        return dataAccess.delete(name);
    }
}
