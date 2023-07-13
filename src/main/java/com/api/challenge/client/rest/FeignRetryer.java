package com.api.challenge.client.rest;

import feign.RetryableException;
import feign.Retryer;

import java.util.concurrent.TimeUnit;

public class FeignRetryer implements Retryer {

    @Override
    public void continueOrPropagate(RetryableException e) {
        throw e;
    }

    @Override
    public Retryer clone() {
        return new Default(1000, TimeUnit.SECONDS.toMillis(1), 5);
    }
}