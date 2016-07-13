/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.stripe.entity;

@SuppressWarnings("serial")
public class KCharge implements KStripeEntity {
	String id;
	Integer amount; // cents
	Long created;
	String currency;
	boolean livemode;
	boolean paid;
	boolean refunded;
	boolean disputed;
	Integer fee;
	String description;
	String receiptEmail;
	String receiptNumber;
	String failureMessage;
	Integer amountRefunded;
	KCard card;
	String customerId;
	String invoiceId;
    
	public String getReceiptEmail() {
		return receiptEmail;
	}

	public void setReceiptEmail(String receiptEmail) {
		this.receiptEmail = receiptEmail;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}


	public Integer getAmount() {
		return amount;
	}

	public Integer getAmountRefunded() {
		return amountRefunded;
	}

	public KCard getCard() {
		return card;
	}

	public Long getCreated() {
		return created;
	}

	public String getCurrency() {
		return currency;
	}

	public String getCustomer() {
		return getCustomerId();
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getDescription() {
		return description;
	}

	public String getFailureMessage() {
		return failureMessage;
	}

	public Integer getFee() {
		return fee;
	}

	public String getId() {
		return id;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public boolean isDisputed() {
		return disputed;
	}

	public boolean isLivemode() {
		return livemode;
	}

	public boolean isPaid() {
		return paid;
	}

	public boolean isRefunded() {
		return refunded;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public void setAmountRefunded(Integer amountRefunded) {
		this.amountRefunded = amountRefunded;
	}

	public void setCard(KCard card) {
		this.card = card;
	}

	public void setCreated(Long created) {
		this.created = created;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setCustomer(String customer) {
		setCustomerId(customer);
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDisputed(boolean disputed) {
		this.disputed = disputed;
	}

	public void setFailureMessage(String failureMessage) {
		this.failureMessage = failureMessage;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public void setLivemode(boolean livemode) {
		this.livemode = livemode;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public void setRefunded(boolean refunded) {
		this.refunded = refunded;
	}
}
