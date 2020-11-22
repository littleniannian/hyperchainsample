package org.example.hyperchainservice.function;

import cn.hyperchain.contract.BaseInvoke;
import org.example.contract.IStoneTransaction;
import org.example.dto.ContractRes;

public class SelectBalanceFunc implements BaseInvoke<ContractRes, IStoneTransaction> {

    private String account;

    public SelectBalanceFunc() {
    }

    public SelectBalanceFunc(String account) {
        this.account = account;
    }

    @Override
    public ContractRes invoke(IStoneTransaction iStoneTransaction) {
        return iStoneTransaction.selectBalance(account);
    }
}
