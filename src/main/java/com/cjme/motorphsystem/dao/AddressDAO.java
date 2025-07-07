/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.Address;
import java.util.List;

/**
 *
 * @author JustAMob
 */
public interface AddressDAO {
    int addAddress(Address address);
    Address getAddressById(int addressId);
    List<Address> getAllAddresses();
    boolean updateAddress(Address address);
    boolean deleteAddress(int addressId);
}
