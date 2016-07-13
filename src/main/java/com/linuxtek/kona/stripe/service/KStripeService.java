/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.stripe.service;

import java.math.BigDecimal;
import java.util.Map;

import com.linuxtek.kona.stripe.entity.KCard;
import com.linuxtek.kona.stripe.entity.KCharge;
import com.linuxtek.kona.stripe.entity.KCustomer;
import com.linuxtek.kona.stripe.entity.KStripeException;

import com.linuxtek.kona.remote.service.KService;


/**
 * KStripe 
 */
public interface KStripeService extends KService {

    // NOTE: SERVICE_PATH must begin with rpc/ prefix
    public static final String SERVICE_PATH = "rpc/kona/StripeService";

    public KCustomer addCustomer(Long appId, KCustomer customer) 
            throws KStripeException;

    public KCustomer addCustomer(Long appId, String email, String description) 
            throws KStripeException;

    public KCustomer addCustomer(Long appId, String email, String description, 
            String cardToken) throws KStripeException;

    public KCustomer updateCustomer(Long appId, KCustomer customer) 
            throws KStripeException;

    public KCustomer fetchCustomerById(Long appId, String customerId) 
            throws KStripeException;

    public void deleteCustomer(Long appId, String customerId) 
            throws KStripeException;

    public KCard addCustomerCard(Long appId, String customerId, KCard card) 
            throws KStripeException;

    public KCard addCustomerCard(Long appId, String customerId, String tokenId) 
            throws KStripeException;

    public KCard fetchCustomerActiveCard(Long appId, String customerId) 
            throws KStripeException;

    public KCharge chargeCustomer(Long appId, String customerId,
            BigDecimal amount, String description, String receiptEmail, 
            Map<String,Object> metadata, Map<String,Object> shipping) throws KStripeException;

    public KCharge chargeCard(Long appId, KCard card, BigDecimal amount, 
            String description, String receiptEmail, 
            Map<String,Object> metadata, Map<String,Object> shipping) throws KStripeException;

    public KCharge chargeCard(Long appId, String cardToken, BigDecimal amount, 
            String description, String receiptEmail, 
            Map<String,Object> metadata, Map<String,Object> shipping) throws KStripeException;


}
