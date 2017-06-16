package com.gift.commons.image;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by sjy on 2016/12/29.
 * 水印处理器(主体类)
 */
public class ImgHandler {
	private static final Color DEFAULT_BACK_COLOR = Color.WHITE;	//字体默认背景
	private static final Color DEFAULT_FONT_COLOR = Color.BLACK;	//字体默认颜色
	private static final String DEFAULT_FONT_FAMILY = "Microsoft Yahei";	//字体默认类型
	private static final int DEFAULT_FONT_STYLE = Font.PLAIN;	//字体默认风格
	private static final int DEFAULT_FONT_SIZE = 12;	//字体默认大小
	private static final Font DEFAULT_FONT = new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_STYLE, DEFAULT_FONT_SIZE);	//默认字体
	
	private BufferedImage bufferedImage;	//需要加水印的图片缓冲
	private Graphics2D g;	//画笔
	private String fontFamily;	//字体类型
	private int fontStyle;	//字体风格
	private int fontSize; //字体大小

	/**
	 * 初始化数据
	 * @param imgPath 需要加水印的文件路径
	 * @param isLocal 是否是本地文件
	 */
	public void initData(String imgPath, Boolean isLocal) {
		this.bufferImage(imgPath, isLocal);
		this.createG();
	}

	/**
	 * 初始化数据
	 * @param imgFile 本地图片文件
	 */
	public void initData(File imgFile) {
		this.bufferImage(imgFile);
		this.createG();
	}

	/**
	 * 初始化数据
	 * @param imgURL 远程图片文件
	 */
	public void initData(URL imgURL) {
		this.bufferImage(imgURL);
		this.createG();
	}

	/**
	 * 初始化数据
	 * @param inputStream 图片文件的输入流
	 */
	public void initData(InputStream inputStream) {
		this.bufferImage(inputStream);
		this.createG();
	}

	/**
	 * 把图片加载到缓冲区
	 * @param imgPath 图片路径(本地或远程)
	 * @param isLocal 是否是本地
	 */
	private void bufferImage(String imgPath, Boolean isLocal) {
		try {
			if(isLocal) {
				this.bufferedImage = ImageIO.read(new File(imgPath));
			} else {
				this.bufferedImage = ImageIO.read(new URL(imgPath));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 把图片加载到缓冲区
	 * @param imgFile 图片本地路径
	 */
	private void bufferImage(File imgFile) {
		try {
			this.bufferedImage = ImageIO.read(imgFile);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 把图片加载到缓冲区
	 * @param imgURL 图片远程路径
	 */
	private void bufferImage(URL imgURL) {
		try {
			this.bufferedImage = ImageIO.read(imgURL);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 把图片加载到缓冲区
	 * @param inputStream 图片的输入流
	 */
	private void bufferImage(InputStream inputStream) {
		try {
			this.bufferedImage = ImageIO.read(inputStream);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 创建画笔
	 */
	private void createG() {
		this.g = bufferedImage.createGraphics();
		this.g.setBackground(DEFAULT_BACK_COLOR);
		this.g.setColor(DEFAULT_FONT_COLOR);
		this.g.setFont(DEFAULT_FONT);
	}

	/**
	 * 设置画笔背景颜色
	 * @param backColor 背景颜色
	 * @return this
	 */
	public ImgHandler backColor(Color backColor) {
		this.g.setBackground(backColor);
		return this;
	}

	/**
	 * 设置画笔颜色
	 * @param fontColor 画笔颜色
	 * @return this
	 */
	public ImgHandler fontColor(Color fontColor) {
		this.g.setColor(fontColor);
		return this;
	}

	/**
	 * 设置画笔字体类型
	 * @param fontFamily 字体类型
	 * @return this
	 */
	public ImgHandler fontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
		this.g.setFont(new Font(this.fontFamily, this.fontStyle, this.fontSize));
		return this;
	}

	/**
	 * 设置画笔字体风格
	 * @param fontStyle 字体风格
	 * @return this
	 */
	public ImgHandler fontStyle(int fontStyle) {
		this.fontStyle = fontStyle;
		this.g.setFont(new Font(this.fontFamily, this.fontStyle, this.fontSize));
		return this;
	}

	/**
	 * 设置画笔字体大小
	 * @param fontSize 字体大小
	 * @return this
	 */
	public ImgHandler fontSize(int fontSize) {
		this.fontSize = fontSize;
		this.g.setFont(new Font(this.fontFamily, this.fontStyle, this.fontSize));
		return this;
	}

	/**
	 * 设置画笔字体
	 * @param fontFamily 字体类型
	 * @param fontStyle 字体风格
	 * @param fontSize 字体大小
	 * @return this
	 */
	public ImgHandler font(String fontFamily, int fontStyle, int fontSize) {
		this.fontFamily = fontFamily;
		this.fontStyle = fontStyle;
		this.fontSize = fontSize;
		this.g.setFont(new Font(this.fontFamily, this.fontStyle, this.fontSize));
		return this;
	}

	/**
	 * 往图片上指定位置写入文字
	 * @param textInfo 文字相关信息
	 * @return this
	 */
	public ImgHandler text(TextInfo textInfo) {
		int x = textInfo.getX();
		int y = textInfo.getY();
		x = x < 0 ? 0 : x;
		y = y < 0 ? 0 : y;
		g.drawString(textInfo.getContent(), x, y);
		return this;
	}

	/**
	 * 往图片上指定位置写入另一张图片
	 * @param imgInfo 图片相关信息
	 * @return this
	 */
	public ImgHandler img(ImgInfo imgInfo) {
		try {
			BufferedImage wmarkImg = null;
			if (imgInfo.getPathIsUrl()) {
				wmarkImg = ImageIO.read(new URL(imgInfo.getWmarkPath()));
			} else {
				wmarkImg = ImageIO.read(new File(imgInfo.getWmarkPath()));
			}
			int x = imgInfo.getX();
			int y = imgInfo.getY();
			x = x < 0 ? 0 : x;
			y = y < 0 ? 0 : y;
			g.drawImage(wmarkImg, x, y, wmarkImg.getWidth(), wmarkImg.getHeight(), null);
			return this;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 重置画笔参数
	 * @return this
	 */
	public ImgHandler resetG() {
		this.g.setBackground(DEFAULT_BACK_COLOR);
		this.g.setColor(DEFAULT_FONT_COLOR);
		this.g.setFont(DEFAULT_FONT);
		return this;
	}

	/**
	 * 生成最终的图片
	 * @param desPath 目标路径
	 * @param imgType 图片类型
	 * @return this
	 */
	public ImgHandler toFile(String desPath, String imgType) {
		try {
			File desFile = new File(desPath);
			if(!desFile.exists()) {
				desFile.mkdirs();
			}
			ImageIO.write(this.bufferedImage, imgType, desFile);
			return this;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 生成最终的图片
	 * @param desFile 目标文件
	 * @param imgType 文件类型
	 * @return this
	 */
	public ImgHandler toFile(File desFile, String imgType) {
		try {
			if(!desFile.exists()) {
				desFile.mkdirs();
			}
			ImageIO.write(this.bufferedImage, imgType, desFile);
			return this;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 把图片写入流中
	 * @param outputStream 输出流
	 * @param imgType 图片类型
	 * @return this
	 */
	public ImgHandler toOutputStream(OutputStream outputStream, String imgType) {
		try {
			ImageIO.write(this.bufferedImage, imgType, outputStream);
			return this;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 释放资源
	 */
	public void close() {
		this.g.dispose();
	}

}
