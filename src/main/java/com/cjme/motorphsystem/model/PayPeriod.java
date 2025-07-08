/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.model;

import java.sql.Date;

/**
 *
 * @author JustAMob
 */
public class PayPeriod {
    private int payPeriodId;
    private Date payPeriodStart;
    private Date payPeriodEnd;

    public PayPeriod() {
    }

    public PayPeriod(int payPeriodId, Date payPeriodStart, Date payPeriodEnd) {
        this.payPeriodId = payPeriodId;
        this.payPeriodStart = payPeriodStart;
        this.payPeriodEnd = payPeriodEnd;
    }

    public int getPayPeriodId() {
        return payPeriodId;
    }

    public void setPayPeriodId(int payPeriodId) {
        this.payPeriodId = payPeriodId;
    }

    public Date getPayPeriodStart() {
        return payPeriodStart;
    }

    public void setPayPeriodStart(Date payPeriodStart) {
        this.payPeriodStart = payPeriodStart;
    }

    public Date getPayPeriodEnd() {
        return payPeriodEnd;
    }

    public void setPayPeriodEnd(Date payPeriodEnd) {
        this.payPeriodEnd = payPeriodEnd;
    }
    
}
