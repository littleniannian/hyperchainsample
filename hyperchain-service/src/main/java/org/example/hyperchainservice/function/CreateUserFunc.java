package org.example.hyperchainservice.function;

import cn.hyperchain.contract.BaseInvoke;
import org.example.contract.IStoneTransaction;
import org.example.dto.ContractRes;

public class CreateUserFunc implements BaseInvoke<ContractRes, IStoneTransaction> {
    private String name;

    public CreateUserFunc() {
    }

    public CreateUserFunc(String name) {
        this.name = name;
    }

    @Override
    public ContractRes invoke(IStoneTransaction iStoneTransaction) {
        return iStoneTransaction.createUser(name);
    }
}
