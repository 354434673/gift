package com.gift.commons.image;

/**
 * 加到图片上的 图片相关信息
 * @ClassName:  ImgInfo   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author kevin
 * @date 2017年5月17日 下午4:04:33
 */
public class ImgInfo {
	private String wmarkPath;	//图片路径
	private boolean pathIsUrl = false;	//图片路径默认本地
	private int x;	//图片横向坐标
	private int y;	//图片竖向坐标
	
	public ImgInfo() {
		super();
	}
	public ImgInfo(String wmarkPath, int x, int y) {
		super();
		this.wmarkPath = wmarkPath;
		this.x = x;
		this.y = y;
	}
	public String getWmarkPath() {
		return wmarkPath;
	}
	public void setWmarkPath(String wmarkPath) {
		this.wmarkPath = wmarkPath;
	}
	public boolean getPathIsUrl() {
		return pathIsUrl;
	}
	public void setPathIsUrl(boolean pathIsUrl) {
		this.pathIsUrl = pathIsUrl;
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
