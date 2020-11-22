package org.example.hyperchainservice.function;

import cn.hyperchain.contract.BaseInvoke;
import org.example.contract.IStoneTransaction;
import org.example.dto.ContractRes;

public class CreateProductFunc implements BaseInvoke<ContractRes, IStoneTransaction> {
    private String account;
    private String stone;
    private Integer count;

    public CreateProductFunc() {
    }

    public CreateProductFunc(String account, String stone, Integer count) {
        this.account = account;
        this.stone = stone;
        this.count = count;
    }

    @Override
    public ContractRes invoke(IStoneTransaction iStoneTransaction) {
        return iStoneTransaction.createProduct(account,stone,count);
    }
}
