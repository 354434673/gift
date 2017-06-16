package com.gift.commons;
/**
 * @ClassName:  TreeJson   
 * @Description:将数据转换成特定JSON格式
 * @author YangNingSheng
 * @date 2017年5月26日 下午3:04:56
 */
public class TreeJson {
    
    private String id;
    private Long pId;
    private String name;
    private Boolean checked;
    private Boolean open;
    
    public TreeJson() {
	super();
	// TODO Auto-generated constructor stub
    }
    public TreeJson(String id, Long pId, String name, Boolean checked, Boolean open) {
	super();
	this.id = id;
	this.pId = pId;
	this.name = name;
	this.checked = checked;
	this.open = open;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Long getpId() {
        return pId;
    }
    public void setpId(Long pId) {
        this.pId = pId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Boolean getChecked() {
        return checked;
    }
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
    public Boolean getOpen() {
        return open;
    }
    public void setOpen(Boolean open) {
        this.open = open;
    }
    
    
    
}
