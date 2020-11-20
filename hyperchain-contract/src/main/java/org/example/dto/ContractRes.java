package org.example.dto;

import org.example.constant.ResConstants;

import java.util.Objects;

public class ContractRes<T> {
    public ResConstants msg;
    public T data;

    public ContractRes() {
    }

    public ContractRes(ResConstants msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractRes<?> that = (ContractRes<?>) o;
        return getMsg() == that.getMsg() &&
                Objects.equals(getData(), that.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMsg(), getData());
    }

    public ResConstants getMsg() {
        return msg;
    }

    public void setMsg(ResConstants msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
