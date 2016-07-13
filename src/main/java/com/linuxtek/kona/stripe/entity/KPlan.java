/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.stripe.entity;

@SuppressWarnings("serial")
public class KPlan implements KStripeEntity {
    Integer amount;
    String currency;
    String id;
    String interval;
    String name;
    boolean livemode;
    Integer trialPeriodDays;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLivemode() {
        return livemode;
    }

    public void setLivemode(boolean livemode) {
        this.livemode = livemode;
    }

    public Integer getTrialPeriodDays() {
        return trialPeriodDays;
    }

    public void setTrialPeriodDays(Integer trialPeriodDays) {
        this.trialPeriodDays = trialPeriodDays;
    }
}
