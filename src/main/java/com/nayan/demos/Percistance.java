package com.nayan.demos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Percistance implements DataAccess {

    private static final String URL = "jdbc:mysql://localhost:3306/coin";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addNew(Coin coin) {
        String insert = "INSERT INTO coin (coinname, quantity, country, manufacturingdate) VALUES (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(insert)) {
            stmt.setString(1, coin.getName());
            stmt.setInt(2, coin.getQuantity());
            stmt.setString(3, coin.getCountry());
            stmt.setString(4, coin.getMfd().toString());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Coin> getAll() {
        String query = "SELECT * FROM coin";
        List<Coin> coins = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Coin coin = new Coin(
                        rs.getString("coinname"),
                        rs.getInt("quantity"),
                        rs.getString("country"),
                        LocalDate.parse(rs.getString("manufacturingdate"))
                );
                coins.add(coin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coins;
    }

    @Override
    public Coin get(String name) {
        String query = "SELECT * FROM coin WHERE coinname = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Coin(
                            rs.getString("coinname"),
                            rs.getInt("quantity"),
                            rs.getString("country"),
                            LocalDate.parse(rs.getString("manufacturingdate"))
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Coin coin) {
        String update = "UPDATE coin SET quantity = ?, country = ?, manufacturingdate = ? WHERE coinname = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(update)) {
            stmt.setInt(1, coin.getQuantity());
            stmt.setString(2, coin.getCountry());
            stmt.setString(3, coin.getMfd().toString());
            stmt.setString(4, coin.getName());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String name) {
        String delete = "DELETE FROM coin WHERE coinname = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(delete)) {
            stmt.setString(1, name);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
