package com.nayan.demos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String coinName = request.getParameter("coinName");
        String quantityStr = request.getParameter("quantity");
        String country = request.getParameter("country");
        String mfdStr = request.getParameter("manufactureDate");

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        try {
            int quantity = Integer.parseInt(quantityStr);
            LocalDate mfd = LocalDate.parse(mfdStr);

            Coin coin = new Coin(coinName, quantity, country, mfd);
            Update updateService = new Update();
            boolean result = updateService.update(coin);

            if (result) {
                out.println("Updated Successfully");
            } else {
                out.println("Failed to update coin");
            }
        } catch (NumberFormatException e) {
            out.println("Invalid quantity format: " + quantityStr);
        } catch (DateTimeParseException e) {
            out.println("Invalid date format: " + mfdStr);
        } catch (Exception e) {
            out.println("An error occurred: " + e.getMessage());
        }
    }
}
