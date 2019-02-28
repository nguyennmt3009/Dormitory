package com.example.skyjar.dormitoryapp.Entities;

import android.media.Image;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ProblemReport implements Serializable {
    private String location;
    private String problemType;
    private String content;
    private boolean status;
    private Date createdDate;
    private List<Image> images;

    public ProblemReport(String location, String problemType, String content, boolean status, Date createdDate, List<Image> images) {
        this.location = location;
        this.problemType = problemType;
        this.content = content;
        this.status = status;
        this.createdDate = createdDate;
        this.images = images;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
