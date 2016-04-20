package com.loibv.t1p.service;

import com.loibv.t1p.model.Account;

import java.util.List;

/**
 * Created by vanloibui on 4/12/16.
 */
public interface IUserService {

    List<Account> getAllRelatedUser(String email);




}
