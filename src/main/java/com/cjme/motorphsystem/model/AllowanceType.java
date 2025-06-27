package com.cjme.motorphsystem.model;

import java.math.BigDecimal;

/**
 *
 * @author JustAMob
 */
public class AllowanceType {
    private int allowanceTypeId;
    private String code;
    private String category;
    private String description;
    private BigDecimal amount;

    public AllowanceType() {
    }

    public void setAllowanceTypeId(int allowanceTypeId) {
        this.allowanceTypeId = allowanceTypeId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getAllowanceTypeId() {
        return allowanceTypeId;
    }

    public String getCode() {
        return code;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    
    
    
}
