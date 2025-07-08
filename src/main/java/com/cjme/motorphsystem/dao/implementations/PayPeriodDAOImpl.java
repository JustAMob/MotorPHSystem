/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.dao.PayPeriodDAO;
import com.cjme.motorphsystem.model.PayPeriod;
import com.cjme.motorphsystem.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JustAMob
 */
public class PayPeriodDAOImpl implements PayPeriodDAO {
    
    @Override
    public int addPayPeriod(PayPeriod payPeriod) {
        String sql = "INSERT INTO pay_period_table (pay_period_start, pay_period_end) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDate(1, payPeriod.getPayPeriodStart());
            stmt.setDate(2, payPeriod.getPayPeriodEnd());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // return generated pay_period_id
            }

        } catch (SQLException e) {
        }

        return -1;
    }

    @Override
    public PayPeriod getPayPeriodById(int id) {
        String sql = "SELECT * FROM pay_period_table WHERE pay_period_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                PayPeriod pp = new PayPeriod();
                pp.setPayPeriodId(rs.getInt("pay_period_id"));
                pp.setPayPeriodStart(rs.getDate("pay_period_start"));
                pp.setPayPeriodEnd(rs.getDate("pay_period_end"));
                return pp;
            }

        } catch (SQLException e) {
        }

        return null;
    }

    @Override
    public List<PayPeriod> getAllPayPeriods() {
        List<PayPeriod> list = new ArrayList<>();
        String sql = "SELECT * FROM pay_period_table";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PayPeriod pp = new PayPeriod();
                pp.setPayPeriodId(rs.getInt("pay_period_id"));
                pp.setPayPeriodStart(rs.getDate("pay_period_start"));
                pp.setPayPeriodEnd(rs.getDate("pay_period_end"));
                list.add(pp);
            }

        } catch (SQLException e) {
        }

        return list;
    }

    @Override
    public void updatePayPeriod(PayPeriod payPeriod) {
        String sql = "UPDATE pay_period_table SET pay_period_start = ?, pay_period_end = ? WHERE pay_period_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, payPeriod.getPayPeriodStart());
            stmt.setDate(2, payPeriod.getPayPeriodEnd());
            stmt.setInt(3, payPeriod.getPayPeriodId());
            stmt.executeUpdate();

        } catch (SQLException e) {
        }
    }

    @Override
    public void deletePayPeriod(int id) {
        String sql = "DELETE FROM pay_period_table WHERE pay_period_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
        }
    }
}
