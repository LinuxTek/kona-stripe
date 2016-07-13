/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.stripe.entity;

import java.io.Serializable;

/**
 * StripeException.
 */

@SuppressWarnings("serial")
public class KStripeException extends RuntimeException 
    implements Serializable {

    // GWT requires a no-arg constructor
    public KStripeException() {
    }

    public KStripeException(String message) {
        super(message);
    }

    public KStripeException(String ex, Throwable cause) {
        super(ex, cause);
    }

    public KStripeException(Throwable cause) {
        super(cause);
    }
}
