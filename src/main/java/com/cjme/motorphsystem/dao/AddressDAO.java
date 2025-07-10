/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.Address;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author JustAMob
 */
public interface AddressDAO {
    int addAddress(Address address, Connection conn) throws SQLException;
    Address getAddressById(int addressId, Connection conn) throws SQLException;
    List<Address> getAllAddresses(Connection conn) throws SQLException;
    boolean updateAddress(Address address, Connection conn) throws SQLException;
    boolean deleteAddress(int addressId, Connection conn) throws SQLException;
}
