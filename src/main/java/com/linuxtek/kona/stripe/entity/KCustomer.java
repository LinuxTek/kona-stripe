/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.stripe.entity;

@SuppressWarnings("serial")
public class KCustomer implements KStripeEntity {
    Long created;
    String id;
    boolean livemode;
    boolean deleted;
    String description;
    KCard defaultCard;
    String email;
    boolean delinquent;
    Integer accountBalance;
    
    /*
    String plan;
    Long trialEnd;
    KDiscount discount;
    KNextRecurringCharge nextRecurringCharge;
    KSubscription subscription;
    */

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isLivemode() {
        return livemode;
    }

    public void setLivemode(Boolean livemode) {
        if (livemode == null) livemode = false;
        this.livemode = livemode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public KCard getDefaultCard() {
        return defaultCard;
    }

    public void setDefaultCard(KCard defaultCard) {
        this.defaultCard = defaultCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*
    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public Long getTrialEnd() {
        return trialEnd;
    }

    public void setTrialEnd(Long trialEnd) {
        this.trialEnd = trialEnd;
    }

    public KDiscount getDiscount() {
        return discount;
    }

    public void setDiscount(KDiscount discount) {
        this.discount = discount;
    }

    public KNextRecurringCharge getNextRecurringCharge() {
        return nextRecurringCharge;
    }

    public void setNextRecurringCharge(
            KNextRecurringCharge nextRecurringCharge) {
        this.nextRecurringCharge = nextRecurringCharge;
    }

    public KSubscription getSubscription() {
        return subscription;
    }

    public void setSubscription(KSubscription subscription) {
        this.subscription = subscription;
    }
    */

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        if (deleted == null) deleted = false;
        this.deleted = deleted;
    }

    public boolean isDelinquent() {
        return delinquent;
    }

    public void setDelinquent(Boolean delinquent) {
        if (delinquent == null) delinquent = false;
        this.delinquent = delinquent;
    }

    public Integer getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Integer accountBalance) {
        this.accountBalance = accountBalance;
    }
}
