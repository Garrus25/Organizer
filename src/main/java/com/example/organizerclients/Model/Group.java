package com.example.organizerclients.Model;

public class Group {
    private String name;
    private String code;
    private Boolean isSetFlag;

    public Group(String name, String code, Boolean isSetFlag) {
        this.name = name;
        this.code = code;
        this.isSetFlag = isSetFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getIsSetFlag() {
        return isSetFlag;
    }

    public void setIsSetFlag(Boolean set) {
        isSetFlag = set;
    }
}
