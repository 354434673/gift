package com.gift.commons.image;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * 图片水印类(使用由此开始,如:ImgWmark.ofLocal...)
 * @ClassName:  ImgWmark   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author kevin
 * @date 2017年5月17日 下午4:05:07
 */
public class ImgWmark {

	/**
	 * 指定本地图片路径
	 * @param imgPath 本地图片路径
	 * @return ImgHandler
	 */
	public static ImgHandler ofLocal(String imgPath) {
		ImgHandler imgHandler = new ImgHandler();
		imgHandler.initData(imgPath, true);
		return imgHandler;
	}

	/**
	 * 指定本地图片文件
	 * @param imgFile 本地图片文件
	 * @return ImgHandler
	 */
	public static ImgHandler ofLocal(File imgFile) {
		ImgHandler imgHandler = new ImgHandler();
		imgHandler.initData(imgFile);
		return imgHandler;
	}

	/**
	 * 指定远程图片路径
	 * @param imgPath 远程图片路径
	 * @return ImgHandler
	 */
	public static ImgHandler ofUrl(String imgPath) {
		ImgHandler imgHandler = new ImgHandler();
		imgHandler.initData(imgPath, false);
		return imgHandler;
	}

	/**
	 * 指定远程图片文件
	 * @param imgUrl 远程图片文件
	 * @return ImgHandler
	 */
	public static ImgHandler ofUrl(URL imgUrl) {
		ImgHandler imgHandler = new ImgHandler();
		imgHandler.initData(imgUrl);
		return imgHandler;
	}

	/**
	 * 指定图片文件的输入流
	 * @param inputStream 图片的输入流
	 * @return ImgHandler
	 */
	public static ImgHandler ofIO(InputStream inputStream) {
		ImgHandler imgHandler = new ImgHandler();
		imgHandler.initData(inputStream);
		return imgHandler;
	}

}
