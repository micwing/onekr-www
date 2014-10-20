/**
 * @Project: main-framework
 * @File: ImageValidateCode.java
 * @package onekr.framework.utils
 * @Description:
 * @author micwing
 * @date 2013-3-26 下午4:52:45
 * @version V1.0
 *
 * Copyright (c) 2013 OneKr Soft Studio. All Rights Reserved.
 *
 * Copying of this document or code and giving it to others and the
 * use or communication of the contents thereof, are forbidden without
 * expressed authority. Offenders are liable to the payment of damages.
 * All rights reserved in the event of the grant of a invention patent or the
 * registration of a utility model, design or code.
 */
package onekr.framework.verifycode;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/** 
 * @ClassName: ImageValidateCode 
 * @Description: 图片验证码
 * @author micwing
 * @date 2013-3-26 下午5:35:45 
 */ 
public class SunImageValidateCode {

    /** 图片宽度 */
    private int     width          = 60;

    /** 图片高度 */
    private int     height         = 24;

    /** 文字颜色，红、橙、蓝、绿、紫、褐 */
    private Color[] fontColors     = { new Color(210, 51, 11), new Color(255, 127, 39),
            new Color(34, 21, 191), new Color(10, 84, 32), new Color(97, 0, 97),
            new Color(192, 122, 87) };

    /** 文字颜色数量 */
    private int     fontColorCount = fontColors.length;

    /** 随机数 */
    private Random  random         = new Random();

    /**
     * 生成图片
     * @param texts 文字
     * @return 图片
     */
    public BufferedImage generate(String texts) {
        BufferedImage image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // 画背景
        g2d.setColor(new Color(222, 211, 233));
        g2d.fillRect(0, 0, this.width, this.height);

        // 画边框
        g2d.setColor(Color.GRAY);
        g2d.drawRect(0, 0, this.width - 1, this.height - 1);

        // [begin] 画文字
        Font font = new Font("Batmos Regular", Font.PLAIN, this.height - 2);
        g2d.setFont(font);

        FontMetrics fontMetrics = g2d.getFontMetrics();
        int textsWidth = fontMetrics.stringWidth(texts);
        int textsCount = texts.length();

        int x = (this.width - textsWidth) / 2;
        int y = this.height - 4;

        for (int i = 0; i < textsCount; i++) {
            // 文字颜色随机选
            Color fontColor = this.fontColors[this.random.nextInt(this.fontColorCount)];
            g2d.setColor(fontColor);

            // 画文字
            String text = String.valueOf(texts.charAt(i));
            g2d.drawString(text, x, ((i % 2) == 0) ? (y + 1) : (y - 1));

            x += fontMetrics.stringWidth(text);
        }
        // [end] 画文字

        // [begin] 画噪音线
        int noiseLineCount = this.width / 4;
        int xAdd = this.width / 3;
        int yAdd = this.height / 3;
        int x1, y1;

        for (int i = 0; i < noiseLineCount; i++) {
            Color lineColor = this.fontColors[this.random.nextInt(this.fontColorCount)];
            g2d.setColor(lineColor);

            x = this.random.nextInt(this.width);
            y = this.random.nextInt(this.height);
            x1 = random.nextInt(xAdd);
            y1 = random.nextInt(yAdd);
            g2d.drawLine(x, y, x + x1, y + y1);
        }
        // [end] 画噪音线

        return image;
    }

    public static void main(String[] args) throws Exception {
        OutputStream os = new FileOutputStream("d:/ImageValidateCode.jpg");
        JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(os);
        jpeg.encode((new SunImageValidateCode()).generate("3634"));
        os.close();
    }

}
