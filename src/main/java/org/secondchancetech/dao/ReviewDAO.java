package org.secondchancetech.dao;

import org.secondchancetech.model.Review;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.secondchancetech.util.DBUtil;

public class ReviewDAO {

    public List<Review> getAllReviews() {
        List<Review> list = new ArrayList<>();
        String sql = "SELECT * FROM review";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Review r = new Review();
                r.setReviewId(rs.getInt("review_id"));
                r.setStarNumbers(rs.getInt("star_numbers"));
                r.setUserId(rs.getInt("user_id"));
                r.setComment(rs.getString("comment"));
                list.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
