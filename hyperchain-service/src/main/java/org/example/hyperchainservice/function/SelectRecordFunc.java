package org.example.hyperchainservice.function;

import cn.hyperchain.contract.BaseInvoke;
import org.example.contract.IStoneTransaction;
import org.example.dto.ContractRes;

public class SelectRecordFunc implements BaseInvoke<ContractRes, IStoneTransaction> {
    private String stoneName;
    private Integer num;

    public SelectRecordFunc() {
    }

    public SelectRecordFunc(String stoneName, Integer num) {
        this.stoneName = stoneName;
        this.num = num;
    }

    @Override
    public ContractRes invoke(IStoneTransaction iStoneTransaction) {
        return iStoneTransaction.selectRecord(stoneName,num);
    }
}
