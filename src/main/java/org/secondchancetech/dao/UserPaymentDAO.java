package org.secondchancetech.dao;

import org.secondchancetech.model.UserPayment;
import org.secondchancetech.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserPaymentDAO {

    // ---------- CREATE ----------
    public void createUserPayment(UserPayment payment) throws SQLException {
        String sql = """
            INSERT INTO user_payment
            (cardholder_name, card_number, exp_date, cvv)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, payment.getCardholderName());
            ps.setString(2, payment.getCardNumber());
            ps.setString(3, payment.getExpDate());
            ps.setString(4, payment.getCvv());

            ps.executeUpdate();
        }
    }

    // ---------- READ ----------
    public UserPayment getUserPaymentById(int userPaymentId) {
        String sql = "SELECT * FROM user_payment WHERE user_payment_id = ?";
        UserPayment payment = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userPaymentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    payment = mapRow(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payment;
    }

    public List<UserPayment> getAllUserPayments() {
        List<UserPayment> list = new ArrayList<>();
        String sql = "SELECT * FROM user_payment";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ---------- UPDATE ----------
    public boolean updateUserPayment(UserPayment payment) {
        String sql = """
            UPDATE user_payment SET
                cardholder_name = ?, card_number = ?, exp_date = ?, cvv = ?
            WHERE user_payment_id = ?
        """;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, payment.getCardholderName());
            ps.setString(2, payment.getCardNumber());
            ps.setString(3, payment.getExpDate());
            ps.setString(4, payment.getCvv());
            ps.setInt(5, payment.getUserPaymentId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ---------- DELETE ----------
    public boolean deleteUserPayment(int userPaymentId) {
        String sql = "DELETE FROM user_payment WHERE user_payment_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userPaymentId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ---------- MAPPER ----------
    private UserPayment mapRow(ResultSet rs) throws SQLException {
        UserPayment p = new UserPayment();
        p.setUserPaymentId(rs.getInt("user_payment_id"));
        p.setCardholderName(rs.getString("cardholder_name"));
        p.setCardNumber(rs.getString("card_number"));
        p.setExpDate(rs.getString("exp_date"));
        p.setCvv(rs.getString("cvv"));
        return p;
    }
}
