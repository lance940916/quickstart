package com.snailwu.mybatis.model;

import java.util.Date;
import javax.annotation.Generated;

public class VideoData {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long videoId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String videoData;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getVideoId() {
        return videoId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getCreateTime() {
        return createTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getVideoData() {
        return videoData;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setVideoData(String videoData) {
        this.videoData = videoData;
    }
}