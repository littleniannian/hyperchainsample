package org.example.contract;

import cn.hyperchain.annotations.StoreField;
import cn.hyperchain.contract.BaseContract;
import cn.hyperchain.core.HyperMap;
import org.apache.commons.lang3.StringUtils;
import org.example.constant.ComConstants;
import org.example.constant.ResConstants;
import org.example.dto.ContractRes;
import org.example.entity.Account;
import org.example.entity.Balance;
import org.example.entity.Record;
import org.example.util.ComUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StoneTransaction extends BaseContract implements IStoneTransaction {
    @StoreField
    public HyperMap<String, Account> accounts  = new HyperMap<String, Account>();
    @StoreField
    public HyperMap<String, Balance> balances = new HyperMap<String,Balance>();
    @StoreField
    public HyperMap<String,Record> records = new HyperMap<String,Record>();

    @Override
    public void onInit() {
        super.onInit();
    }

    @Override
    public ContractRes<Account> createUser(String name) {
        if(this.accounts.get(name)!=null){
           return new ContractRes<Account>(ResConstants.ACCOUNT_HAS_EXIST,this.accounts.get(name));
        }else{
            this.accounts.put(name,new Account(name,new HashSet<>()));
           return new ContractRes<>(ResConstants.OK,null);
        }
    }

    @Override
    public ContractRes<Balance> createProduct(String account,String stone,Integer count) {
        if (this.accounts.get(account) == null){
            return new ContractRes<>(ResConstants.ACCOUNT_NOT_EXIST,null);
        }
        Account act = this.accounts.get(account);
        String balanceKey = ComUtil.genKey(account,stone);
        Balance balance = this.balances.get(balanceKey);
        if(balance == null){
            balance = new Balance();
            balance.setId(balanceKey);
            balance.setStart(1);
            balance.setEnd(count);
            balance.setName(stone);
            Set<String> accStones = act.getStones();
            accStones.add(stone);
            act.setStones(accStones);
            this.accounts.put(account,act);
        }else{
            balance.setEnd(balance.getEnd()+count);
        }
        this.balances.put(balanceKey,balance);
        return new ContractRes<>(ResConstants.OK,balance);
    }

    @Override
    public ContractRes<Balance> transfer(String from, String to, String stone, Integer count) {
        if (this.accounts.get(from) == null || this.accounts.get(to) == null){
            return new ContractRes<>(ResConstants.ACCOUNT_NOT_EXIST,null);
        }
        Balance fromBalance = this.balances.get(ComUtil.genKey(from,stone));
        Balance toBalance = this.balances.get(ComUtil.genKey(to,stone));
        int fromCount = fromBalance.getEnd() - fromBalance.getStart()+1;
        if(fromCount<count)
            return new ContractRes<>(ResConstants.BALANCE_NOT_ENOUGH,null);
        String startNum = String.valueOf(fromBalance.getStart());
        String endNum = String.valueOf(fromBalance.getStart()+count-1);
        fromBalance.setStart(fromBalance.getStart()+count);
        this.balances.put(ComUtil.genKey(from,stone),fromBalance);
        if(toBalance == null){
            Account toAccount = this.accounts.get(to);
            Set<String> stones = toAccount.getStones();
            stones.add(stone);
            toAccount.setStones(stones);
            this.accounts.put(to,toAccount);
            toBalance = new Balance(ComUtil.genKey(to,stone),stone,1,count);
        }else{
            toBalance.setEnd(toBalance.getEnd()+count);
        }
        this.balances.put(ComUtil.genKey(to,stone),toBalance);
        // 插入记录
        Record record = new Record(ComUtil.genKey(stone,startNum,endNum),stone,from,to,count,Integer.valueOf(startNum)
                ,Integer.valueOf(endNum));
        this.records.put(ComUtil.genKey(stone,startNum,endNum),record);
        return new ContractRes<>(ResConstants.OK,this.balances.get(ComUtil.genKey(from,stone)));
    }

    @Override
    public ContractRes<List<Balance>> selectBalance(String account) {
        Account act = this.accounts.get(account);
        if(act == null){
            return new ContractRes<>(ResConstants.ACCOUNT_NOT_EXIST,null);
        }
        Set<String> stones = act.getStones();
        List<Balance> balances = new ArrayList<>();
        for (String stoneName: stones) {
            balances.add(this.balances.get(ComUtil.genKey(account,stoneName)));
        }
        return new ContractRes<>(ResConstants.OK,balances);
    }

    @Override
    public ContractRes<List<Record>> selectRecord(String stoneName, Integer num) {
        List<Record> records = new ArrayList<>();
        Set<String> recordKeys = this.records.keySet();
        for (String key: recordKeys) {
            String[] keyArr = key.split(ComConstants.SPLIT_CHAR);
            if(keyArr[0].equals(stoneName)
                && Integer.parseInt(keyArr[1])<=num
                && Integer.parseInt(keyArr[2])>=num){
                records.add(this.records.get(key));
            }
        }
        return new ContractRes<>(ResConstants.OK,records);
    }
}
