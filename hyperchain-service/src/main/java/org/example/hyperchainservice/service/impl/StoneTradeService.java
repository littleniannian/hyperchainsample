package org.example.hyperchainservice.service.impl;

import cn.hyperchain.sdk.account.Account;
import cn.hyperchain.sdk.account.Algo;
import cn.hyperchain.sdk.common.utils.Decoder;
import cn.hyperchain.sdk.common.utils.FileUtil;
import cn.hyperchain.sdk.exception.RequestException;
import cn.hyperchain.sdk.provider.DefaultHttpProvider;
import cn.hyperchain.sdk.provider.ProviderManager;
import cn.hyperchain.sdk.response.ReceiptResponse;
import cn.hyperchain.sdk.service.AccountService;
import cn.hyperchain.sdk.service.ContractService;
import cn.hyperchain.sdk.service.ServiceManager;
import cn.hyperchain.sdk.transaction.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ContractRes;
import org.example.hyperchainservice.dto.Result;
import org.example.hyperchainservice.function.*;
import org.example.hyperchainservice.helper.ContractFileHelper;
import org.example.hyperchainservice.service.IStoneTradeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Service
public class StoneTradeService implements IStoneTradeService {
    // TODO:启动前修改合约JAR包路径
    private static final String JAR_PATH = "D:\\DEV\\ideaprojects\\hyperchain-mdtest\\hyperchain-contract\\target\\hyperchain-contract-1.0-SNAPSHOT.jar";
    private final static DefaultHttpProvider defaultHttpProvider;
    private final static ProviderManager providerManager;
    private final static ContractService contractService;
    private final static AccountService accountService;
    private final static Account account;
    private final static String contractAddress;

    static {
        defaultHttpProvider = new DefaultHttpProvider.Builder().setUrl("192.168.56.101:8081").build();
        providerManager = ProviderManager.createManager(defaultHttpProvider);
        contractService = ServiceManager.getContractService(providerManager);
        accountService = ServiceManager.getAccountService(providerManager);
        account = accountService.genAccount(Algo.ECRAW);
        String contractAddress1 = ContractFileHelper.readContent();
        try {
            if(contractAddress1 == null || "".equals(contractAddress1)){
                InputStream is = FileUtil.readFileAsStream(JAR_PATH);
                // 部署合约
                Transaction transaction = new Transaction.HVMBuilder(account.getAddress()).deploy(is).build();
                // 对交易进行签名
                transaction.sign(account);
                ReceiptResponse receiptResponse = contractService.deploy(transaction).send().polling();
                // 获取合约地址
                contractAddress1 = receiptResponse.getContractAddress();
                // 记录
                ContractFileHelper.writeContent(contractAddress1);
                log.info("contract address is deployed [{}]",contractAddress1);
            }
        } catch (IOException e) {
            log.error("contract deploy is error detail is [{}]",e.getMessage());
        } catch (RequestException e) {
            log.error("contract deploy is error detail is [{}]",e.getMessage());
        }
        contractAddress = contractAddress1;
        log.info("contract address is existed [{}]",contractAddress1);
    }

    @Override
    public Result<ContractRes> createProduct(String accountName, String stone, Integer count) throws Exception {
        Transaction transaction = new Transaction
                .HVMBuilder(account.getAddress())
                .invoke(contractAddress,new CreateProductFunc(accountName,stone,count))
                .build();
        transaction.sign(account);
        ReceiptResponse receiptResponse = contractService.invoke(transaction).send().polling();
        ContractRes res = Decoder.decodeHVM(receiptResponse.getRet(),ContractRes.class);
        return new Result<>(HttpStatus.OK,res);
    }

    @Override
    public Result<ContractRes> createUser(String name)throws Exception {
        Transaction transaction = new Transaction
                .HVMBuilder(account.getAddress())
                .invoke(contractAddress,new CreateUserFunc(name))
                .build();
        transaction.sign(account);
        ReceiptResponse receiptResponse = contractService.invoke(transaction).send().polling();
        ContractRes decodeHVM = Decoder.decodeHVM(receiptResponse.getRet(), ContractRes.class);
        return new Result<>(HttpStatus.OK,decodeHVM);
    }

    @Override
    public Result<ContractRes> selectBalance(String accountName) throws Exception {
        Transaction transaction = new Transaction
                .HVMBuilder(account.getAddress())
                .invoke(contractAddress,new SelectBalanceFunc(accountName))
                .build();
        transaction.sign(account);
        ReceiptResponse receiptResponse = contractService.invoke(transaction).send().polling();
        ContractRes res = Decoder.decodeHVM(receiptResponse.getRet(),ContractRes.class);
        return new Result<>(HttpStatus.OK,res);
    }

    @Override
    public Result<ContractRes> selectRecord(String stoneName, Integer num) throws Exception {
        Transaction transaction = new Transaction
                .HVMBuilder(account.getAddress())
                .invoke(contractAddress,new SelectRecordFunc(stoneName,num))
                .build();
        transaction.sign(account);
        ReceiptResponse receiptResponse = contractService.invoke(transaction).send().polling();
        ContractRes res = Decoder.decodeHVM(receiptResponse.getRet(),ContractRes.class);
        return new Result<>(HttpStatus.OK,res);
    }

    @Override
    public Result<ContractRes> transfer(String from, String to, String stone, Integer count) throws Exception {
        Transaction transaction = new Transaction
                .HVMBuilder(account.getAddress())
                .invoke(contractAddress,new TransferFunc(from,to,stone,count))
                .build();
        transaction.sign(account);
        ReceiptResponse receiptResponse = contractService.invoke(transaction).send().polling();
        ContractRes res = Decoder.decodeHVM(receiptResponse.getRet(),ContractRes.class);
        return new Result<>(HttpStatus.OK,res);
    }
}
