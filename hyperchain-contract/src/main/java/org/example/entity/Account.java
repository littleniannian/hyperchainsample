package org.example.entity;

import java.io.Serializable;
import java.util.Set;
import java.util.Objects;

/**
 * 账户表
 */
public class Account implements Serializable {
    // 名称:中国
    private String name;
    // 拥有的产品
    private Set<String> stones;

    public Account() {
    }

    public Account(String name, Set<String> stones) {
        this.name = name;
        this.stones = stones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(getName(), account.getName()) &&
                Objects.equals(getStones(), account.getStones());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStones());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getStones() {
        return stones;
    }

    public void setStones(Set<String> stones) {
        this.stones = stones;
    }
}
