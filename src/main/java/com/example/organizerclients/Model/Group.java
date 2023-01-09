package com.example.organizerclients.Model;

public class Group {
    private int groupId;
    private String name;
    private String code;
    private Boolean isSetFlag;
    private Boolean isParticipant;

    public Group(int groupId, String name, String code, Boolean isSetFlag, Boolean isParticipant) {
        this.groupId = groupId;
        this.name = name;
        this.code = code;
        this.isSetFlag = isSetFlag;
        this.isParticipant = isParticipant;
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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Boolean getParticipant() {
        return isParticipant;
    }

    public void setParticipant(Boolean participant) {
        isParticipant = participant;
    }
}
