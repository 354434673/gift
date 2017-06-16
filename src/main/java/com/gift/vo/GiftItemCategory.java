package com.gift.vo;

public class GiftItemCategory {
    private String id;

    private Long parentid;

    private String name;

    private Long isparent;

    private Long sortorder;

    private Long state;

    private Long created;

    private Long uptated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getIsparent() {
        return isparent;
    }

    public void setIsparent(Long isparent) {
        this.isparent = isparent;
    }

    public Long getSortorder() {
        return sortorder;
    }

    public void setSortorder(Long sortorder) {
        this.sortorder = sortorder;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getUptated() {
        return uptated;
    }

    public void setUptated(Long uptated) {
        this.uptated = uptated;
    }
}