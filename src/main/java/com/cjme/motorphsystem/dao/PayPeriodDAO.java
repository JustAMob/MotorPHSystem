/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.PayPeriod;
import java.util.List;

/**
 *
 * @author JustAMob
 */
public interface PayPeriodDAO {
    int addPayPeriod(PayPeriod payPeriod);
    PayPeriod getPayPeriodById(int id);
    List<PayPeriod> getAllPayPeriods();
    void updatePayPeriod(PayPeriod payPeriod);
    void deletePayPeriod(int id);
}
