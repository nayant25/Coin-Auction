package com.nayan.demos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/read")
public class ReadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ReadServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        Read readService = new Read();
        List<Coin> coins = readService.readAll();

        out.println("<html><body>");
        out.println("<h2>Coins List</h2>");
        out.println("<table border='1'>");
        out.println("<tr><th>Name</th><th>Quantity</th><th>Country</th><th>Manufacture Date</th></tr>");
        for (Coin coin : coins) {
            out.println("<tr>");
            out.println("<td>" + coin.getName() + "</td>");
            out.println("<td>" + coin.getQuantity() + "</td>");
            out.println("<td>" + coin.getCountry() + "</td>");
            out.println("<td>" + coin.getMfd() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</body></html>");
    }
}
