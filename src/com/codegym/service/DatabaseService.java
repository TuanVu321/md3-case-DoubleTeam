package com.codegym.service;

import com.codegym.model.SignupAccount;

public interface DatabaseService {
    void registerAccountToDB(SignupAccount account, String emailAddress);
}
