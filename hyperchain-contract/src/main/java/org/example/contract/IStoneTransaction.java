package org.example.contract;

import org.example.entity.Account;
import org.example.entity.Balance;

public interface IStoneTransaction {
    Account createUser(String name);

    Balance createProduct(String account,String stone,Integer count);

    Balance transfer(String from,String to,String stone,Integer count);
    // TODO
    Balance selectBalance(String account);
}
