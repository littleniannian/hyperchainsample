package org.example.hyperchainservice.service;

import org.example.dto.ContractRes;
import org.example.hyperchainservice.dto.Result;

public interface IStoneTradeService {
    Result<ContractRes> createProduct(String account, String stone, Integer count) throws Exception;

    Result<ContractRes> createUser(String name) throws Exception;

    Result<ContractRes> selectBalance(String account) throws Exception;

    Result<ContractRes> selectRecord(String stoneName, Integer num) throws Exception;

    Result<ContractRes> transfer(String from, String to, String stone, Integer count) throws Exception;
}
