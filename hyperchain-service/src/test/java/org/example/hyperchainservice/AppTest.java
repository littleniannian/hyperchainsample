package org.example.hyperchainservice;

import cn.hyperchain.sdk.account.Account;
import cn.hyperchain.sdk.account.Algo;
import cn.hyperchain.sdk.common.utils.Decoder;
import cn.hyperchain.sdk.common.utils.FileUtil;
import cn.hyperchain.sdk.provider.DefaultHttpProvider;
import cn.hyperchain.sdk.provider.ProviderManager;
import cn.hyperchain.sdk.response.ReceiptResponse;
import cn.hyperchain.sdk.service.AccountService;
import cn.hyperchain.sdk.service.ContractService;
import cn.hyperchain.sdk.service.ServiceManager;
import cn.hyperchain.sdk.transaction.Transaction;
import org.example.dto.ContractRes;
import org.example.hyperchainservice.function.*;
import org.example.hyperchainservice.helper.ContractFileHelper;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private final static String jarPath = "D:\\DEV\\ideaprojects\\hyperchain-mdtest\\hyperchain-contract\\target\\hyperchain-contract-1.0-SNAPSHOT.jar";//自己工程的jar包路径
    private final static DefaultHttpProvider defaultHttpProvider;
    private final static ProviderManager providerManager;
    private final static ContractService contractService;
    private final static AccountService accountService;
    private final static Account account;
    private final static String contractAddress = "0x6f1fdb5af0e2d441f0c21d8379584c8f6226a015";

    static {
        defaultHttpProvider = new DefaultHttpProvider.Builder().setUrl("192.168.56.101:8081").build();
        providerManager = ProviderManager.createManager(defaultHttpProvider);
        contractService = ServiceManager.getContractService(providerManager);
        accountService = ServiceManager.getAccountService(providerManager);
        account = accountService.genAccount(Algo.ECRAW);
    }
    /**
     * Rigorous Test :-)
     */



    @Test
    public void contractDeployTest()throws Exception {
        InputStream is = FileUtil.readFileAsStream(jarPath);
        System.out.println("account address: "+account.getAddress());
        Transaction transaction = new Transaction.HVMBuilder(account.getAddress()).deploy(is).build();
        transaction.sign(account);
        ReceiptResponse receiptResponse = contractService.deploy(transaction).send().polling();
        String contractAddress = receiptResponse.getContractAddress();
        System.out.println("contractAddress: "+contractAddress);
    }

    @Test
    public void createUserTest()throws Exception{
        System.out.println("accountJson: "+account.toJson()+"password: "+account.getPrivateKey());
        Transaction transaction = new Transaction
                .HVMBuilder(account.getAddress())
                .invoke(contractAddress,new CreateUserFunc("AAA"))
                .build();
        transaction.sign(account);
        ReceiptResponse receiptResponse = contractService.invoke(transaction).send().polling();
        ContractRes decodeHVM = Decoder.decodeHVM(receiptResponse.getRet(), ContractRes.class);
        System.out.println("decode: "+decodeHVM);
    }

    @Test
    public void createProduct()throws Exception{
        Transaction transaction = new Transaction
                .HVMBuilder(account.getAddress())
                .invoke(contractAddress,new CreateProductFunc("AAA","铁矿石",1000))
                .build();
        transaction.sign(account);
        ReceiptResponse receiptResponse = contractService.invoke(transaction).send().polling();
        ContractRes result = Decoder.decodeHVM(receiptResponse.getRet(),ContractRes.class);
        System.out.println(result);
    }

    @Test
    public void selectBalanceTest()throws Exception{
        Transaction transaction = new Transaction
                .HVMBuilder(account.getAddress())
                .invoke(contractAddress,new SelectBalanceFunc("AAA"))
                .build();
        transaction.sign(account);
        ReceiptResponse receiptResponse = contractService.invoke(transaction).send().polling();
        ContractRes res = Decoder.decodeHVM(receiptResponse.getRet(),ContractRes.class);
        System.out.println(res);
    }

    @Test
    public void transferTest() throws Exception{
        Transaction transaction = new Transaction
                .HVMBuilder(account.getAddress())
                .invoke(contractAddress,new TransferFunc("AAA","BBB","铁矿石",100))
                .build();
        transaction.sign(account);
        ReceiptResponse receiptResponse = contractService.invoke(transaction).send().polling();
        ContractRes res = Decoder.decodeHVM(receiptResponse.getRet(),ContractRes.class);
        System.out.println(res);
    }

    @Test
    public void selectRecordsTest() throws Exception{
        Transaction transaction = new Transaction
                .HVMBuilder(account.getAddress())
                .invoke(contractAddress,new SelectRecordFunc("铁矿石",105))
                .build();
        transaction.sign(account);
        ReceiptResponse receiptResponse = contractService.invoke(transaction).send().polling();
        ContractRes res = Decoder.decodeHVM(receiptResponse.getRet(),ContractRes.class);
        System.out.println(res);
    }

    @Test
    public void fileReadTest(){
       String content = ContractFileHelper.readContent();
       System.out.println(content);
    }

}
