package org.example.entity;

import java.util.Objects;

/**
 * 转账记录表
 */
public class Record {
    // TODO 这样查找的复杂度高
    // 复合主键:产品名-开始编号-结束编号
    private String id;
    // 矿石名
    private String stoneName;
    // 转出方
    private String from;
    // 转入方
    private String to;
    // 数量
    private Integer count;
    // 开始编号
    private Integer startNum;
    // 结束编号
    private Integer endNum;

    public Record() {
    }

    public Record(String id, String stoneName, String from, String to, Integer count, Integer startNum, Integer endNum) {
        this.id = id;
        this.stoneName = stoneName;
        this.from = from;
        this.to = to;
        this.count = count;
        this.startNum = startNum;
        this.endNum = endNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoneName() {
        return stoneName;
    }

    public void setStoneName(String stoneName) {
        this.stoneName = stoneName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStartNum() {
        return startNum;
    }

    public void setStartNum(Integer startNum) {
        this.startNum = startNum;
    }

    public Integer getEndNum() {
        return endNum;
    }

    public void setEndNum(Integer endNum) {
        this.endNum = endNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(id, record.id) &&
                Objects.equals(stoneName, record.stoneName) &&
                Objects.equals(from, record.from) &&
                Objects.equals(to, record.to) &&
                Objects.equals(count, record.count) &&
                Objects.equals(startNum, record.startNum) &&
                Objects.equals(endNum, record.endNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stoneName, from, to, count, startNum, endNum);
    }
}
