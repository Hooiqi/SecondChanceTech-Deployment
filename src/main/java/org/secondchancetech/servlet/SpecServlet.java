package org.secondchancetech.servlet;

import org.secondchancetech.dao.SpecDAO;
import org.secondchancetech.model.Spec;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/specs")
public class SpecServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        SpecDAO dao = new SpecDAO();
        List<Spec> specs = dao.getAllSpecs();
        req.setAttribute("specs", specs);
        req.getRequestDispatcher("/jsp/specs.jsp").forward(req, resp);
    }
}
