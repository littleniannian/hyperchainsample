package org.example.entity;

import java.util.Objects;

/**
 * 用户产品余额表
 */
public class Balance {
    // 复合主键:用户名称-产品名称
    private String id;
    // 产品名称: 铁矿石
    private String name;
    // 产品开始编号
    private Integer start;
    // 产品结束编号
    private Integer end;

    public Balance() {
    }

    public Balance(String id, String name, Integer start, Integer end) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance balance = (Balance) o;
        return Objects.equals(getId(), balance.getId()) &&
                Objects.equals(getName(), balance.getName()) &&
                Objects.equals(getStart(), balance.getStart()) &&
                Objects.equals(getEnd(), balance.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getStart(), getEnd());
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }
}
