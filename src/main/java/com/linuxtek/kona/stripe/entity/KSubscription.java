/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.stripe.entity;

@SuppressWarnings("serial")
public class KSubscription implements KStripeEntity {
    Long currentPeriodEnd;
    Long currentPeriodStart;
    String customer;
    Long start;
    String status;
    Long trialStart;
    Long trialEnd;
    KPlan plan;
    Long canceledAt;
    Long endedAt;

    public Long getCurrentPeriodEnd() {
        return currentPeriodEnd;
    }
    public void setCurrentPeriodEnd(Long currentPeriodEnd) {
        this.currentPeriodEnd = currentPeriodEnd;
    }
    public Long getCurrentPeriodStart() {
        return currentPeriodStart;
    }
    public void setCurrentPeriodStart(Long currentPeriodStart) {
        this.currentPeriodStart = currentPeriodStart;
    }
    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public Long getStart() {
        return start;
    }
    public void setStart(Long start) {
        this.start = start;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Long getTrialStart() {
        return trialStart;
    }
    public void setTrialStart(Long trialStart) {
        this.trialStart = trialStart;
    }
    public Long getTrialEnd() {
        return trialEnd;
    }
    public void setTrialEnd(Long trialEnd) {
        this.trialEnd = trialEnd;
    }
    public KPlan getPlan() {
        return plan;
    }
    public void setPlan(KPlan plan) {
        this.plan = plan;
    }
    public Long getCanceledAt() {
        return canceledAt;
    }
    public void setCanceledAt(Long canceledAt) {
        this.canceledAt = canceledAt;
    }
    public Long getEndedAt() {
        return endedAt;
    }
    public void setEndedAt(Long endedAt) {
        this.endedAt = endedAt;
    }
}
