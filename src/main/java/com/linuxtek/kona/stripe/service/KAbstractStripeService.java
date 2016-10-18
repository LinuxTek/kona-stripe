/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.stripe.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.stripe.entity.KCard;
import com.linuxtek.kona.stripe.entity.KCharge;
import com.linuxtek.kona.stripe.entity.KCustomer;
import com.linuxtek.kona.stripe.entity.KStripeException;
import com.linuxtek.kona.util.KClassUtil;
import com.linuxtek.kona.util.KStringUtil;
import com.stripe.Stripe;

/**
 * KStripe.
 */

public abstract class KAbstractStripeService implements KStripeService {

    private static Logger logger = LoggerFactory.getLogger(KAbstractStripeService.class);

    private Mapper mapper = new DozerBeanMapper();
    
    protected abstract String getStripeApiKey(Long appId);

    // ---------------------------------------------------------------------

    private Map<String,Object> toCustomerParams(KCustomer customer) {
        if (customer == null) {
        	return null;
        }
        
        Map<String, Object> params = new HashMap<String, Object>();
        
        params.put("email", customer.getEmail());
        params.put("description", customer.getDescription());

        KCard card = customer.getDefaultCard();
        
        if (card != null && card.getNumber() != null) {
            params.put("source", toCardParams(customer.getDefaultCard()));
        }
        
        return params;
    }

    private Map<String,Object> toCardParams(KCard card) {
        if (card == null) {
        	return null;
        }
        
        Map<String,Object> params = new HashMap<String,Object>();
        
        params.put("number", card.getNumber());
        params.put("exp_month", card.getExpMonth());
        params.put("exp_year", card.getExpYear());
        params.put("cvc", card.getCvc());
        params.put("name", card.getName());
        params.put("address_line1", card.getAddressLine1());
        params.put("address_line2", card.getAddressLine2());
        params.put("address_zip", card.getAddressZip());
        params.put("address_city", card.getAddressCity());
        params.put("address_state", card.getAddressState());
        params.put("address_country", card.getAddressCountry());
        
        return params;
    }

    private Map<String,Object> toChargeParams(KCharge charge,  String receiptEmail, 
            Map<String,Object> metadata, Map<String,Object> shipping) {
        return toChargeParams(charge, null, receiptEmail, metadata, shipping);
    }

    private Map<String,Object> toChargeParams(KCharge charge, String cardToken, 
    		String receiptEmail, Map<String,Object> metadata, Map<String,Object> shipping) {
        
        if (charge == null) {
        	return null;
        }
        
        Map<String,Object> params = new HashMap<String,Object>();
        
        params.put("amount", charge.getAmount()); //required
        
        String currency = charge.getCurrency();
        
        if (currency == null) {
            currency = "usd";
        }
        
        params.put("currency", currency); //required

        if (charge.getDescription() != null) {
            params.put("description", charge.getDescription()); //optional
        }

        //card or customer req
        if (charge.getCustomerId() != null) {
            params.put("customer", charge.getCustomerId()); 
        }

        if (charge.getCard() != null) {
            params.put("source", toCardParams(charge.getCard()));
        }

        if (cardToken != null) {
            params.put("source", cardToken);
        }
        
        if (metadata != null) {
        	params.put("metadata", metadata);
        }
        
        if (shipping != null) {
        	params.put("shipping", shipping);
        }
        
        // email address to send receipt
        if (receiptEmail != null) {
        	params.put("receipt_email", receiptEmail);
        }

        return params;
    }

    private KCustomer toCustomer(com.stripe.model.Customer c) {
        logger.debug("KCustomer.toCustomer: customer: " + KClassUtil.toString(c));
        
        if (c == null) {
        	return null;
        }
        
        KCustomer customer = new KCustomer();
        customer.setId(c.getId());
        customer.setEmail(c.getEmail());
        customer.setDeleted(c.getDeleted());
        customer.setDelinquent(c.getDelinquent());
        customer.setDescription(c.getDescription());
        customer.setLivemode(c.getLivemode());
        customer.setAccountBalance(c.getAccountBalance());
        customer.setCreated(c.getCreated());

        
        if (c.getDefaultSource() != null) {
        	com.stripe.model.Card source = null;

        	try {
        		source = (com.stripe.model.Card) c.getSources().retrieve(c.getDefaultSource());
        	} catch (Exception e) {
        		logger.error("Unable to fetch default card: " + e.getMessage(), e);
        	}

        	if (source != null) {
        		KCard card = new KCard();

        		card.setAddressCity(source.getAddressCity());
        		card.setAddressCountry(source.getAddressCountry());
        		card.setAddressLine1(source.getAddressLine1());
        		card.setAddressLine1Check(source.getAddressLine1Check());
        		card.setAddressLine2(source.getAddressLine2());
        		card.setAddressState(source.getAddressState());
        		card.setAddressZip(source.getAddressZip());
        		card.setAddressZipCheck(source.getAddressZipCheck());
        		card.setCountry(source.getCountry());
        		card.setCvcCheck(source.getCvcCheck());
        		card.setExpMonth(source.getExpMonth());
        		card.setExpYear(source.getExpYear());
        		card.setFingerprint(source.getFingerprint());
        		card.setLast4(source.getLast4());
        		card.setName(source.getName());
        		card.setBrand(source.getBrand());

        		customer.setDefaultCard(card);
        	}
        }
        
        return customer;
    }

    private KCharge toCharge(com.stripe.model.Charge c) {
        if (c == null) return null;
        
        logger.debug("converting charge object to KCharge:\n" + KClassUtil.toString(c));
        
        KCard card = null;
        
        if (c.getSource() != null) {
        	if (c.getSource() instanceof com.stripe.model.Card) {
        		card = mapper.map(c.getSource(), KCard.class);
        	}
        }
        
        KCharge charge = mapper.map(c, KCharge.class);
        
        if (charge.getCard() == null && card != null) {
        	charge.setCard(card);
        }
        
        logger.debug("converting charge object to KCharge:\n" + KClassUtil.toString(charge));
        return charge;
    }

    // ---------------------------------------------------------------------

    @Override
    public KCustomer addCustomer(Long appId, KCustomer customer) 
            throws KStripeException {
        
        try {
            Stripe.apiKey = getStripeApiKey(appId);
            
            Map<String, Object> params = toCustomerParams(customer);
            
            return toCustomer(com.stripe.model.Customer.create(params));
        } catch (com.stripe.exception.StripeException e) {
            throw new KStripeException(e);
        }
    }
    
    // ---------------------------------------------------------------------

    @Override
    public KCustomer addCustomer(Long appId, String email, String description) 
            throws KStripeException {
        return addCustomer(appId, email, description, null);
    }
    
    // ---------------------------------------------------------------------
    
    @Override
    public KCustomer addCustomer(Long appId, String email, String description, String cardToken) 
    		throws KStripeException {
        
        try {
            Stripe.apiKey = getStripeApiKey(appId);
            
            Map<String, Object> params = new HashMap<String, Object>();
            
            params.put("email", email);
            params.put("description", description);
            params.put("source", cardToken);
            
            return toCustomer(com.stripe.model.Customer.create(params));
        } catch (com.stripe.exception.StripeException e) {
            throw new KStripeException(e);
        }
        
    }

    // ---------------------------------------------------------------------

    @Override
    public KCustomer updateCustomer(Long appId, KCustomer customer) 
            throws KStripeException {
        try {
            Stripe.apiKey = getStripeApiKey(appId);
            
            com.stripe.model.Customer c = 
                com.stripe.model.Customer.retrieve(customer.getId());
            
            Map<String, Object> params = toCustomerParams(customer);
            
            return toCustomer(c.update(params));
        } catch (com.stripe.exception.StripeException e) {
            throw new KStripeException(e);
        }
    }

    // ---------------------------------------------------------------------

    @Override
    public KCustomer fetchCustomerById(Long appId, String customerId) 
            throws KStripeException {
        
        try {
            Stripe.apiKey = getStripeApiKey(appId);
            
            return toCustomer(com.stripe.model.Customer.retrieve(customerId));
        } catch (com.stripe.exception.StripeException e) {
            throw new KStripeException(e);
        }
    }

    // ---------------------------------------------------------------------

    @Override
    public void deleteCustomer(Long appId, String customerId) 
            throws KStripeException {
        try {
            Stripe.apiKey = getStripeApiKey(appId);
            
            com.stripe.model.Customer c = 
                com.stripe.model.Customer.retrieve(customerId);
        
            c.delete();
        } catch (com.stripe.exception.StripeException e) {
            throw new KStripeException(e);
        }
    }
    
    // ---------------------------------------------------------------------

    @Override
    public KCard addCustomerCard(Long appId, String customerId, KCard card) 
            throws KStripeException {
        
        KCustomer customer = fetchCustomerById(appId, customerId);
        
        if (customer != null) {
            customer.setDefaultCard(card);
            customer = updateCustomer(appId, customer);
        }
        
        return customer.getDefaultCard();
    }

    // ---------------------------------------------------------------------

    @Override
    public KCard addCustomerCard(Long appId, String customerId, String cardToken) 
            throws KStripeException {
        try {
            Stripe.apiKey = getStripeApiKey(appId);

            Map<String, Object> params = new HashMap<String, Object>();
            
            params.put("source", cardToken);

            com.stripe.model.Customer c = 
                com.stripe.model.Customer.retrieve(customerId);

            return toCustomer(c.update(params)).getDefaultCard();
        } catch (com.stripe.exception.StripeException e) {
            throw new KStripeException(e);
        }
    }

    // ---------------------------------------------------------------------
    
    @Override
    public KCard fetchCustomerActiveCard(Long appId, String customerId) 
            throws KStripeException {
        KCard card = null;
        
        KCustomer customer = fetchCustomerById(appId, customerId);
        
        logger.debug("fetchCustomerActiveCard: customer: " + KClassUtil.toString(customer));
        
        if (customer != null) {
            card = customer.getDefaultCard();
        }
        
        return card;
    }

    // ---------------------------------------------------------------------
    
    @Override
    public KCharge chargeCustomer(Long appId, String customerId, BigDecimal amount, 
            String description, String receiptEmail, 
            Map<String,Object> metadata, Map<String,Object> shipping) throws KStripeException {
        if (amount == null) {
            logger.info("Amount is null; returning");
            return null;
        }

        amount = amount.multiply(new BigDecimal(100));
        Integer amt = amount.intValue();

        if (amt < 50) {
            throw new KStripeException("Minimum charge amount is 50 cents.");
        }

        KCharge charge = new KCharge();
        
        charge.setCurrency("usd");
        charge.setAmount(amt);
        charge.setDescription(description);
        charge.setCustomerId(customerId);

        try {
            Stripe.apiKey = getStripeApiKey(appId);

            Map<String, Object> params = toChargeParams(charge, receiptEmail, metadata, shipping);
            
            return toCharge(com.stripe.model.Charge.create(params));
        } catch (com.stripe.exception.StripeException e) {
            throw new KStripeException(e);
        }
    }

    // ---------------------------------------------------------------------
    
    @Override
    public KCharge chargeCard(Long appId, KCard card, BigDecimal amount, 
            String description, String receiptEmail, 
            Map<String,Object> metadata, Map<String,Object> shipping) 
            throws KStripeException {
        
        if (amount == null) {
            logger.info("Amount is null; returning");
            return null;
        }

        amount = amount.multiply(new BigDecimal(100));
        
        Integer amt = amount.intValue();

        if (amt < 50) {
            throw new KStripeException("Minimum charge amount is 50 cents.");
        }

        KCharge charge = new KCharge();
        
        charge.setCurrency("usd");
        charge.setAmount(amt);
        charge.setDescription(description);
        charge.setCard(card);

        try {
            Stripe.apiKey = getStripeApiKey(appId);

            Map<String, Object> params = toChargeParams(charge, receiptEmail, metadata, shipping);
            
            return toCharge(com.stripe.model.Charge.create(params));
        } catch (com.stripe.exception.StripeException e) {
            throw new KStripeException(e);
        }
    }
    
    // ---------------------------------------------------------------------

    @Override
    public KCharge chargeCard(Long appId, String cardToken, BigDecimal amount, 
            String description, String receiptEmail, 
            Map<String,Object> metadata, Map<String,Object> shipping) throws KStripeException {
        
        if (amount == null) {
            logger.info("Amount is null; returning");
            return null;
        }

        amount = amount.multiply(new BigDecimal(100));
        
        Integer amt = amount.intValue();

        if (amt < 50) {
            throw new KStripeException("Minimum charge amount is 50 cents.");
        }

        KCharge charge = new KCharge();
        
        charge.setCurrency("usd");
        charge.setAmount(amt);
        charge.setDescription(description);

        try {
            Stripe.apiKey = getStripeApiKey(appId);

            Map<String, Object> params = toChargeParams(charge, cardToken, receiptEmail, metadata, shipping);
            
            //logger.debug("chargeCard: Stripe.apiKey: " + Stripe.apiKey);
            logger.debug("chargeCard: charge params: " + KStringUtil.toJson(params));
            
            return toCharge(com.stripe.model.Charge.create(params));
        } catch (com.stripe.exception.StripeException e) {
            throw new KStripeException(e);
        }
    }
}
