package com.gift.commons.image;
/**
 * 加到图片上的 文字相关信息
 * @ClassName:  TextInfo   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author kevin
 * @date 2017年5月17日 下午4:05:43
 */
public class TextInfo {
	private String content;	//内容
	private int x;	//横向坐标
	private int y;	//竖向坐标
	
	public TextInfo() {
		super();
	}
	public TextInfo(String content, int x, int y) {
		super();
		this.content = content;
		this.x = x;
		this.y = y;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

}
