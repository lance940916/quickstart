package com.snailwu.quickstart.mybatis.model;

import java.util.Date;
import javax.annotation.Generated;

public class MbgTable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date dateDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date tsDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private byte[] introBlob;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private byte[] introBlob2;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String introText;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getDateDate() {
        return dateDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDateDate(Date dateDate) {
        this.dateDate = dateDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getTsDate() {
        return tsDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setTsDate(Date tsDate) {
        this.tsDate = tsDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public byte[] getIntroBlob() {
        return introBlob;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setIntroBlob(byte[] introBlob) {
        this.introBlob = introBlob;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public byte[] getIntroBlob2() {
        return introBlob2;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setIntroBlob2(byte[] introBlob2) {
        this.introBlob2 = introBlob2;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getIntroText() {
        return introText;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setIntroText(String introText) {
        this.introText = introText;
    }
}