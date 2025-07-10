/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.dao.AddressDAO;
import com.cjme.motorphsystem.model.Address;
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
public class AddressDAOImpl implements AddressDAO {
    private Connection conn;

    public AddressDAOImpl() {
    }

    public AddressDAOImpl(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public int addAddress(Address address, Connection conn) throws SQLException {
        String sql = "INSERT INTO address (building, street, city, province, zipcode) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, address.getBuilding());
            stmt.setString(2, address.getStreet());
            stmt.setString(3, address.getCity());
            stmt.setString(4, address.getProvince());
            stmt.setString(5, address.getZipcode());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // return generated address_id
            }
        }
        return -1;
    }

    @Override
    public Address getAddressById(int addressId, Connection conn) throws SQLException {
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
        }
        return null;
    }

    
    @Override
    public Address getAddressById(int addressId) throws SQLException {
        String sql = "SELECT * FROM address WHERE address_id = ?";
        try (Connection connection = this.conn != null ? this.conn : DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

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
        }
        return null;
    }

    @Override
    public List<Address> getAllAddresses(Connection conn) throws SQLException {
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
        }
        return list;
    }

    @Override
    public boolean updateAddress(Address address, Connection conn) throws SQLException {
        String sql = "UPDATE address SET building = ?, street = ?, city = ?, province = ?, zipcode = ? WHERE address_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, address.getBuilding());
            stmt.setString(2, address.getStreet());
            stmt.setString(3, address.getCity());
            stmt.setString(4, address.getProvince());
            stmt.setString(5, address.getZipcode());
            stmt.setInt(6, address.getAddressId());

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteAddress(int addressId, Connection conn) throws SQLException {
        String sql = "DELETE FROM address WHERE address_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, addressId);
            return stmt.executeUpdate() > 0;
        }
    }
}