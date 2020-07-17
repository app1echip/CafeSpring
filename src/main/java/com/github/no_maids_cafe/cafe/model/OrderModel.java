package com.github.no_maids_cafe.cafe.model;

import java.util.Date;
import java.util.Map;

public class OrderModel {
    private String id;
    private Map<String, Integer> content;
    private Date time;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setContent(Map<String, Integer> content) {
        this.content = content;
    }

    public Map<String, Integer> getContent() {
        return content;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
    }
}