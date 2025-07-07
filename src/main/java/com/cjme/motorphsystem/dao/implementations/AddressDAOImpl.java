/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.dao.AddressDAO;
import com.cjme.motorphsystem.model.Address;
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
public class AddressDAOImpl implements AddressDAO{
    private final Connection conn;

    public AddressDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int addAddress(Address address) {
        String sql = "INSERT INTO address (building, street, province, zipcode) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, address.getBuilding());
            stmt.setString(2, address.getStreet());
            stmt.setString(3, address.getProvince());
            stmt.setString(4, address.getZipcode());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // return generated address_id
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Address getAddressById(int addressId) {
        String sql = "SELECT * FROM address WHERE address_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, addressId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Address(
                    rs.getInt("address_id"),
                    rs.getString("building"),
                    rs.getString("street"),
                    rs.getString("city"),
                    rs.getString("province"),
                    rs.getString("zipcode")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Address> getAllAddresses() {
        List<Address> list = new ArrayList<>();
        String sql = "SELECT * FROM address";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Address address = new Address(
                    rs.getInt("address_id"),
                    rs.getString("building"),
                    rs.getString("street"),
                    rs.getString("city"),
                    rs.getString("province"),
                    rs.getString("zipcode")
                );
                list.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updateAddress(Address address) {
        String sql = "UPDATE address SET building = ?, street = ?, province = ?, zipcode = ? WHERE address_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, address.getBuilding());
            stmt.setString(2, address.getStreet());
            stmt.setString(3, address.getProvince());
            stmt.setString(4, address.getZipcode());
            stmt.setInt(5, address.getAddressId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAddress(int addressId) {
        String sql = "DELETE FROM address WHERE address_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, addressId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
