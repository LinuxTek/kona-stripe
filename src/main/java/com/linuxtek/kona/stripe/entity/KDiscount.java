/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.stripe.entity;

@SuppressWarnings("serial")
public class KDiscount implements KStripeEntity {
    String code;
    Long end;
    String id;
    Integer percentOff;
    Long start;
    KCoupon coupon;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Long getEnd() {
        return end;
    }
    public void setEnd(Long end) {
        this.end = end;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Integer getPercentOff() {
        return percentOff;
    }
    public void setPercentOff(Integer percentOff) {
        this.percentOff = percentOff;
    }
    public Long getStart() {
        return start;
    }
    public void setStart(Long start) {
        this.start = start;
    }
    public KCoupon getCoupon() {
        return coupon;
    }
    public void setCoupon(KCoupon coupon) {
        this.coupon = coupon;
    }
}
