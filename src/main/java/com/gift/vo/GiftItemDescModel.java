package com.gift.vo;

import java.util.List;

/**
 *	自定义giftItemDesc 集合接收表单参数
 * @ClassName:  GiftItemDescModel   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author kevin
 * @date 2017年5月16日 上午10:13:04
 */
public class GiftItemDescModel {
	
	private List<GiftItemDesc> itemDescs;

	public List<GiftItemDesc> getItemDescs() {
		return itemDescs;
	}

	public void setItemDescs(List<GiftItemDesc> itemDescs) {
		this.itemDescs = itemDescs;
	}

	public GiftItemDescModel(List<GiftItemDesc> itemDescs) {
		super();
		this.itemDescs = itemDescs;
	}

	public GiftItemDescModel() {
		super();
	}
	
	
	
}
