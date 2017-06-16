package com.gift.commons.image;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * 图片水印测试类
 * @ClassName:  ImgWmarkTest   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author kevin
 * @date 2017年5月17日 下午4:05:28
 */
public class ImgWmarkTest {

    @Test
    public void testLocalImg() {
        System.out.println("正在添加水印,请稍候...");
        TextInfo textInfo = new TextInfo("我是谁", 205, 160);
        ImgInfo imgInfo = new ImgInfo("D:\\code.jpg", 100, 720);
        ImgWmark.ofLocal("D:\\tplt.jpg").fontSize(20).text(textInfo).resetG().img(imgInfo).toFile("D:\\final.jpg", "jpg").close();
        System.out.println("水印添加完成!");
    }

    @Test
    public void testLocalImgFile() {
        System.out.println("正在添加水印,请稍候...");
        TextInfo textInfo = new TextInfo("我是谁", 205, 160);
        ImgInfo imgInfo = new ImgInfo("D:\\code.jpg", 100, 720);
        ImgWmark.ofLocal(new File("D:\\tplt.jpg")).fontSize(20).text(textInfo).resetG().img(imgInfo).toFile("D:\\haha.png", "png").close();
        System.out.println("水印添加完成!");
    }

    @Test
    public void testRemoteImg() {
        System.out.println("正在添加水印,请稍候...");
        TextInfo textInfo = new TextInfo("我是谁", 50, 50);
        ImgInfo imgInfo = new ImgInfo("D:\\code.jpg", 100, 100);
        ImgWmark.ofUrl("http://img.hao224.com/2016/06/04/lashou_shenyang_215751_2706.jpg").fontSize(20).text(textInfo).resetG().img(imgInfo).toFile("D:\\net.jpg", "jpg").close();
        System.out.println("水印添加完成!");
    }

    @Test
    public void testRemoteImgURL() throws MalformedURLException {
        System.out.println("正在添加水印,请稍候...");
        TextInfo textInfo = new TextInfo("我是谁", 50, 50);
        ImgInfo imgInfo = new ImgInfo("D:\\code.jpg", 100, 100);
        ImgWmark.ofUrl(new URL("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg")).fontSize(20).text(textInfo).resetG().img(imgInfo).toFile("D:\\net.png", "png").close();
        System.out.println("水印添加完成!");
    }

    @Test
    public void testImgIo() throws FileNotFoundException {
        System.out.println("正在添加水印,请稍候...");
        TextInfo textInfo = new TextInfo("我是谁我在娜", 205, 160);
        ImgInfo imgInfo = new ImgInfo("D:\\b005.png", 300, 120);
        ImgWmark.ofIO(new FileInputStream("D:\\tplt.jpg")).fontSize(20).text(textInfo).resetG().img(imgInfo).toFile("D:\\bbb.png", "png").close();
        System.out.println("水印添加完成!");
    }

    @Test
    public void test() {
        System.out.println("正在添加水印,请稍候...");
        TextInfo textInfo = new TextInfo("广告", 370, 35);
        ImgWmark.ofUrl("http://img.hao224.com/2016/06/04/lashou_shenyang_215751_2706.jpg").fontFamily("华文彩云").fontSize(30).text(textInfo).toFile("D:\\ad.jpg", "jpg").close();
        System.out.println("水印添加完成!");
    }

}
