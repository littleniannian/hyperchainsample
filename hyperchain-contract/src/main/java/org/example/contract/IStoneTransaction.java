package org.example.contract;

import org.example.dto.ContractRes;
import org.example.entity.Account;
import org.example.entity.Balance;
import org.example.entity.Record;

import java.util.List;

public interface IStoneTransaction {
    ContractRes<Account> createUser(String name);

    ContractRes<Balance> createProduct(String account,String stone,Integer count);

    ContractRes<Balance> transfer(String from,String to,String stone,Integer count);

    ContractRes<List<Balance>> selectBalance(String account);

    ContractRes<List<Record>> selectRecord(String stoneName,Integer num);
}
