package com.nayan.demos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVBusinessLogic {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Method to process CSV file and update coin list
    public static List<Coin> processCSV(InputStream inputStream, List<Coin> currentCoins) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<Coin> updatedCoins = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length >= 4) {
                String coinName = data[0].trim();
                int quantity = Integer.parseInt(data[1].trim());
                String country = data[2].trim();
                String manufactureDateStr = data[3].trim();

                // Convert manufactureDateStr to LocalDate
                LocalDate manufactureDate = LocalDate.parse(manufactureDateStr, dateFormatter);

                // Check if coin exists in currentCoins list
                boolean coinExists = false;
                for (Coin coin : currentCoins) {
                    if (coin.getName().equals(coinName)) {
                        coin.setQuantity(coin.getQuantity() + quantity);
                        updatedCoins.add(coin);
                        coinExists = true;
                        break;
                    }
                }

                // If coin does not exist, create new Coin object and add to updatedCoins list
                if (!coinExists) {
                    Coin newCoin = new Coin(coinName, quantity, country, manufactureDate);
                    updatedCoins.add(newCoin);
                }
            }
        }

        reader.close();
        return updatedCoins;
    }
}
