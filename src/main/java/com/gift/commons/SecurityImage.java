package com.gift.commons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/**
 * @ClassName:  SecurityImage   
 * @Description:验证码图片
 * @author YangNingSheng
 * @date 2017年4月25日 下午2:50:51
 */
public class SecurityImage {
	
    private static final int WIDTH = 120;//生成的图片的宽度
    private static final int HEIGHT = 40;//生成的图片的高度
    private static final int FONT_SIZE = 20;//字体大小
    
    public static BufferedImage createImage(String securityCode){

    	final int CODE_LENGTH = securityCode.length();//验证码长度
        //图片
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = image.createGraphics();
        
        g.setColor(Color.WHITE);//设置背景色

        g.fillRect(0, 0, WIDTH, HEIGHT);//填充背景

        g.setColor(Color.LIGHT_GRAY);//设置边框颜色

        g.setFont(new Font("Arial", Font.BOLD, HEIGHT-2));//边框字体样式

        g.drawRect(0, 0, WIDTH - 2, HEIGHT - 2);//绘制边框

        //绘制噪点

        Random rand = new Random();

        g.setColor(Color.LIGHT_GRAY);

        // 设置线条个数并画线
        for (int i = 0; i < 5; i++) {
            int x1 = new Random().nextInt(WIDTH);
            
            int y1 = new Random().nextInt(HEIGHT);
            
            int x2 = new Random().nextInt(WIDTH);
            
            int y2 = new Random().nextInt(HEIGHT);
            
            g.drawLine(x1, y1, x2, y2);
        }
        //绘制验证码
        int codeY = HEIGHT-10;

        g.setColor(new Color(19,148,246));

        g.setFont(new Font("Georgia", Font.BOLD, FONT_SIZE));
        
        for(int i=0;i<CODE_LENGTH;i++){
        	double deg=new Random().nextDouble()*20;
        	g.rotate(Math.toRadians(deg), i*16+13,codeY-7.5);
            g.drawString(String.valueOf(securityCode.charAt(i)), i*16+5, codeY);
            g.rotate(Math.toRadians(-deg), i*16+13,codeY-7.5);
        }
        g.dispose();//关闭资源

        return image;
    }

    /**
     * 返回验证码图片的流格式
     * @param securityCode
     * @return
     */
    public static ByteArrayInputStream getImageAsInputStream(String securityCode){

        BufferedImage image = createImage(securityCode);

        return convertImageToStream(image);

    }
    
    /**
     * 将BufferedImage转换成ByteArrayinputStream
     * @param image
     * @return
     */
    public static ByteArrayInputStream convertImageToStream(BufferedImage image){

        ByteArrayInputStream inputStream = null;

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outStream);

        try {
            encoder.encode(image);

            byte[] b = outStream.toByteArray();

            inputStream = new ByteArrayInputStream(b);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return inputStream;
    }
}
