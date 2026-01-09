package org.secondchancetech.servlet;

import org.secondchancetech.dao.GadgetDAO;
import org.secondchancetech.model.Gadget;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/product-details")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        GadgetDAO dao = new GadgetDAO();
        String idParam = req.getParameter("id");

        if (idParam != null) {
            try {
                int gadgetId = Integer.parseInt(idParam);

                // Fetch the gadget using the ID from the URL
                Gadget gadget = dao.getAllGadgets().stream()
                        .filter(g -> g.getGadgetId() == gadgetId)
                        .findFirst()
                        .orElse(null);

                if (gadget != null) {
                    req.setAttribute("gadget", gadget);
                    req.getRequestDispatcher("/WEB-INF/views/product-details.jsp").forward(req, resp);
                    return;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(req.getContextPath() + "/gadgets");
    }
}