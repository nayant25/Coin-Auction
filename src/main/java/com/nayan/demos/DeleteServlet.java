package com.nayan.demos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String coinName = request.getParameter("coinName");

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        try {
            Delete deleteService = new Delete();
            boolean result = deleteService.delete(coinName);

            if (result) {
                out.println("Deleted Successfully");
            } else {
                out.println("Failed to delete coin");
            }
        } catch (Exception e) {
            out.println("An error occurred: " + e.getMessage());
        }
    }
}
