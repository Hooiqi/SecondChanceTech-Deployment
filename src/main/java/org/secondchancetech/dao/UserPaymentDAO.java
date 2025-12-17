package org.secondchancetech.dao;

import org.secondchancetech.model.UserPayment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.secondchancetech.util.DBUtil;


public class UserPaymentDAO {

    public List<UserPayment> getAllPayments() {
        List<UserPayment> list = new ArrayList<>();
        String sql = "SELECT * FROM user_payment";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                UserPayment up = new UserPayment();
                up.setUserPaymentId(rs.getInt("user_payment_id"));
                up.setCardholderName(rs.getString("cardholder_name"));
                up.setCardNumber(rs.getString("card_number"));
                up.setExpDate(rs.getString("exp_date"));
                up.setCvv(rs.getString("cvv"));
                list.add(up);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
