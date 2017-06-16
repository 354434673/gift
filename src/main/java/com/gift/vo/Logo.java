package com.gift.vo;

public class Logo {
    private String id;

    private String customerid;

    private String title;

    private String content;

    private String url;

    private Long creattime;

    private Long updatetime;

    private Long state;

    private String statedescribe;
    
    private Customer customer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid == null ? null : customerid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Long getCreattime() {
        return creattime;
    }

    public void setCreattime(Long creattime) {
        this.creattime = creattime;
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public String getStatedescribe() {
        return statedescribe;
    }

    public void setStatedescribe(String statedescribe) {
        this.statedescribe = statedescribe == null ? null : statedescribe.trim();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
}