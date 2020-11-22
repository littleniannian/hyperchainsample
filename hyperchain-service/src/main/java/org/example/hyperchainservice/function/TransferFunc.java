package org.example.hyperchainservice.function;

import cn.hyperchain.contract.BaseInvoke;
import org.example.contract.IStoneTransaction;
import org.example.dto.ContractRes;

public class TransferFunc implements BaseInvoke<ContractRes, IStoneTransaction> {
    private String from;
    private String to;
    private String stone;
    private Integer count;

    public TransferFunc() {
    }

    public TransferFunc(String from, String to, String stone, Integer count) {
        this.from = from;
        this.to = to;
        this.stone = stone;
        this.count = count;
    }

    @Override
    public ContractRes invoke(IStoneTransaction iStoneTransaction) {
        return iStoneTransaction.transfer(from,to,stone,count);
    }
}
