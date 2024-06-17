package com.nayan.demos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jakarta.servlet.annotation.MultipartConfig;
//import jakarta.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig
public class Upload extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream inputStream = request.getPart("file").getInputStream();

        // Get current list of coins from database or session
        DataAccess ref = new Percistance();
        List<Coin> currentCoins = ref.getAll();

        // Process CSV using business logic
        List<Coin> updatedCoins = CSVBusinessLogic.processCSV(inputStream, currentCoins);

        // Update database with updatedCoins
        boolean success = true;
        for (Coin coin : updatedCoins) {
            success &= ref.addNew(coin);
        }

        // Respond to the user
        if (success) {
            response.getWriter().println("Coins added/updated successfully from CSV file.");
        } else {
            response.getWriter().println("Failed to add/update coins from CSV file.");
        }
    }
}
