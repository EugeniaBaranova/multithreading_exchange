package com.epam.multithreading.entity;

import java.math.BigDecimal;

public class ExchangeParticipant {

    private BigDecimal eurosAmount;
    private BigDecimal dollarsAmount;
    private BigDecimal rublesAmount;

    public BigDecimal getEurosAmount() {
        return eurosAmount;
    }

    public void setEurosAmount(BigDecimal eurosAmount) {
        this.eurosAmount = eurosAmount;
    }

    public BigDecimal getDollarsAmount() {
        return dollarsAmount;
    }

    public void setDollarsAmount(BigDecimal dollarsAmount) {
        this.dollarsAmount = dollarsAmount;
    }

    public BigDecimal getRublesAmount() {
        return rublesAmount;
    }

    public void setRublesAmount(BigDecimal rublesAmount) {
        this.rublesAmount = rublesAmount;
    }

}
