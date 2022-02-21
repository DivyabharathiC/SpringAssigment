package com.repo.mongo.demo.exception;


public class CustomerIsNotEligibleToDelete extends RuntimeException {
    public CustomerIsNotEligibleToDelete(String s){
        super(s);
    }
}
