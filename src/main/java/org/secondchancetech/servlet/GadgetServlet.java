package org.secondchancetech.servlet;

import org.secondchancetech.dao.GadgetDAO;
import org.secondchancetech.model.Gadget;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/gadgets")
public class GadgetServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        GadgetDAO dao = new GadgetDAO();
        List<Gadget> gadgets = dao.getAllGadgets();
        req.setAttribute("gadgets", gadgets);
        req.getRequestDispatcher("/jsp/gadgets.jsp").forward(req, resp);
    }
}
