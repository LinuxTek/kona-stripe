/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.stripe.entity;

@SuppressWarnings("serial")
public class KCard implements KStripeEntity {
	String number;
	String cvc;
	Integer expMonth;
	Integer expYear;
	String last4;
	String country;
	String brand;
	String name;
	String addressLine1;
	String addressLine2;
	String addressCity;
	String addressZip;
	String addressState;
	String addressCountry;
	String addressZipCheck;
	String addressLine1Check;
	String cvcCheck;
	String fingerprint;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public Integer getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(Integer expMonth) {
		this.expMonth = expMonth;
	}

	public Integer getExpYear() {
		return expYear;
	}

	public void setExpYear(Integer expYear) {
		this.expYear = expYear;
	}

	public String getLast4() {
		return last4;
	}

	public void setLast4(String last4) {
		this.last4 = last4;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressZip() {
		return addressZip;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressZip(String addressZip) {
		this.addressZip = addressZip;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressState() {
		return addressState;
	}

	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}

	public String getAddressCountry() {
		return addressCountry;
	}

	public void setAddressCountry(String addressCountry) {
		this.addressCountry = addressCountry;
	}

	public String getAddressZipCheck() {
		return addressZipCheck;
	}

	public void setAddressZipCheck(String addressZipCheck) {
		this.addressZipCheck = addressZipCheck;
	}

	public String getAddressLine1Check() {
		return addressLine1Check;
	}

	public void setAddressLine1Check(String addressLine1Check) {
		this.addressLine1Check = addressLine1Check;
	}

	public String getCvcCheck() {
		return cvcCheck;
	}

	public void setCvcCheck(String cvcCheck) {
		this.cvcCheck = cvcCheck;
	}

	public String getFingerprint() {
		return fingerprint;
	}

	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}
}
