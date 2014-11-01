/**
 * @Project: main-framework
 * @File: ImageUtil.java
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
package onekr.framework.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.imageio.ImageIO;

/** 
 * @ClassName: ImageUtil 
 * @Description: 
 * @author micwing
 * @date 2013-3-26 下午5:33:14 
 */ 
public class ImageUtil {
	
//	/**
//     * 图像切割(按指定起点坐标和宽高切割)
//     * @param srcImageFile 源图像地址
//     * @param result 切片后的图像地址
//     * @param x 目标切片起点坐标X
//     * @param y 目标切片起点坐标Y
//     * @param width 目标切片宽度
//     * @param height 目标切片高度
//     */
//    public final static void cutAndWrite(BufferedImage bi, File out,
//            int x, int y, int width, int height, String fileFormat) {
//        try {
//            // 读取源图像
//            int srcWidth = bi.getHeight(); // 源图宽度
//            int srcHeight = bi.getWidth(); // 源图高度
//            if (srcWidth > 0 && srcHeight > 0) {
//                Image image = bi.getScaledInstance(srcWidth, srcHeight,
//                        Image.SCALE_DEFAULT);
//                // 四个参数分别为图像起点坐标和宽高
//                // 即: CropImageFilter(int x,int y,int width,int height)
//                ImageFilter cropFilter = new CropImageFilter(x, y, width, height);
//                Image img = Toolkit.getDefaultToolkit().createImage(
//                        new FilteredImageSource(image.getSource(),
//                                cropFilter));
//                BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//                Graphics g = tag.getGraphics();
//                g.drawImage(img, 0, 0, width, height, null); // 绘制切割后的图
//                g.dispose();
//                // 输出为文件
//                ImageIO.write(tag, fileFormat, out);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
//    /**
//     * 缩放图像（按高度和宽度缩放）
//     * @param srcImageFile 源图像文件地址
//     * @param result 缩放后的图像地址
//     * @param height 缩放后的高度
//     * @param width 缩放后的宽度
//     * @param bb 比例不对时是否需要补白：true为补白; false为不补白;
//     * @param byMax true表示按照图片width、height中像素值大的为准缩成指定像素值，false表示按照图片width、height中像素值小的为准缩成指定像素值
//     */
//    public final static BufferedImage scaleByWidthAndHeight(BufferedImage bi, int width, int height, boolean bb, boolean byMax) {
//        try {
//            double ratio = 0.0; // 缩放比例
//            Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);
////            Image itemp = bi.getScaledInstance(bi.getWidth(), bi.getHeight(), bi.SCALE_SMOOTH);
//            // 计算比例
//            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
//            	if (byMax) {
//            		if (bi.getHeight() > bi.getWidth()) {
//                        ratio = (new Integer(height)).doubleValue()
//                                / bi.getHeight();
//                    } else {
//                        ratio = (new Integer(width)).doubleValue() / bi.getWidth();
//                    }
//            	} else {
//            		if (bi.getHeight() < bi.getWidth()) {
//                        ratio = (new Integer(height)).doubleValue()
//                                / bi.getHeight();
//                    } else {
//                        ratio = (new Integer(width)).doubleValue() / bi.getWidth();
//                    }
//            	}
//                
//                AffineTransformOp op = new AffineTransformOp(AffineTransform
//                        .getScaleInstance(ratio, ratio), null);
//                itemp = op.filter(bi, null);
//            }
//            if (bb) {//补白
//                BufferedImage image = new BufferedImage(width, height,
//                        BufferedImage.TYPE_INT_RGB);
//                Graphics2D g = image.createGraphics();
//                g.setColor(Color.white);
//                g.fillRect(0, 0, width, height);
//                if (width == itemp.getWidth(null))
//                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
//                            itemp.getWidth(null), itemp.getHeight(null),
//                            Color.white, null);
//                else
//                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
//                            itemp.getWidth(null), itemp.getHeight(null),
//                            Color.white, null);
//                g.dispose();
//                itemp = image;
//            }
//            return (BufferedImage) itemp;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

	/** 
	* 从指定文件名读取图像，目前只支持读取以下格式图像：bmp,wbmp,gif,jpge,png。 
	* 
	* @param file 
	*            文件名 
	* @return BufferedImage 图像 
	* @since 1.0 
	* 
	* 
	* <pre> 
	* BufferedImage image; 
	* image = ImageUtils.readImage(new File(&quot;myImage.jpg&quot;)); 
	* image = ImageUtils.readImage(new File(&quot;myImage.gif&quot;)); 
	* image = ImageUtils.readImage(new File(&quot;myImage.bmp&quot;)); 
	* image = ImageUtils.readImage(new File(&quot;myImage.png&quot;)); 
	* </pre> 
	*/  
	public static BufferedImage readImage(File file) {  
	   BufferedImage image = null;  
	   if (file != null && file.isFile() && file.exists()) {  
	    try {  
	     image = ImageIO.read(file);  
	    } catch (IOException e) {  
	      e.printStackTrace();  
	    }  
	   }  
	   return image;  
	}  
	  
	/** 
	* 从文件流读取图像，目前只支持读取以下格式图像：bmp,wbmp,gif,jpge,png。 
	* 
	* @param input 
	*            输入文件流 
	* @return BufferedImage 图像 
	* @since 1.0 
	*/  
	public static BufferedImage readImage(InputStream input) {  
	   BufferedImage image = null;  
	   if (input != null) {  
	    try {  
	     image = ImageIO.read(input);  
	    } catch (IOException e) {  
	      e.printStackTrace();  
	    }  
	   }  
	   return image;  
	}  
	  
	/** 
	* 从URL读取图像，目前只支持读取以下格式图像：bmp,wbmp,gif,jpge,png。 
	* 
	* @param url 
	* @return BufferedImage 图像 
	* @since 1.0 
	*/  
	public static BufferedImage readImage(URL url) {  
	   BufferedImage image = null;  
	   if (url != null) {  
	    try {  
	     image = ImageIO.read(url);  
	    } catch (IOException e) {  
	      e.printStackTrace();  
	    }  
	   }  
	   return image;  
	}  
	  
	/** 
	* 获得指定图像的像素宽 
	* 
	* @param image 
	*            图像 
	* @return 图像的像素宽 
	* @since 1.0 
	*/  
	public static int getWidth(BufferedImage image) {  
	   return image.getWidth();  
	}  
	  
	/** 
	* 获得指定图像的像素高 
	* 
	* @param image 
	*            图像 
	* @return 图像的像素高 
	* @since 1.0 
	*/  
	public static int getHeight(BufferedImage image) {  
	   return image.getHeight();  
	}  
	  
	/** 
	* 按指定格式输出<code>BufferedImage</code>到文件out中，如果没有指定image或formatName或输出文件out, 
	* 则do nothing 。目前只支持写入以下格式图像：bmp,wbmp,jpeg,png。 
	* 
	* @param image 
	*            图像 
	* @param formatName 
	*            格式 
	* @param out 
	*            输出文件 
	* @throws IOException 
	* @since 1.0 
	* @see com.sitechasia.webx.core.utils.image.ImageConstants.FormatName 
	* 
	* 
	* <pre> 
	* ImageUtils.writeImage(imageToSave, ImageConstants.FormatName.BMP, new File(&quot;newImage.bmp&quot;)); 
	* ImageUtils.writeImage(imageToSave, ImageConstants.FormatName.JPEG, new File(&quot;newImage.jpg&quot;)); 
	* ImageUtils.writeImage(imageToSave, ImageConstants.FormatName.PNG, new File(&quot;newImage.png&quot;)); 
	* ImageUtils.writeImage(imageToSave, ImageConstants.FormatName.WBMP, new File(&quot;newImage.wbmp&quot;)); 
	* </pre> 
	* 
	*/  
	public static void writeImage(BufferedImage image, String formatName, File out) throws IOException {  
	   if (image != null && formatName != null && !"".equals(formatName) && out != null) {  
	    ImageIO.write(image, formatName, out);  
	   }  
	}  
	  
	/** 
	* 按指定格式输出<code>BufferedImage</code>到out中，如果没有指定image或formatName或输出流out, 
	* 则do nothing 。目前只支持写入以下格式图像：bmp,wbmp,jpeg,png。 
	* 
	* @param image 
	*            图像 
	* @param formatName 
	*            格式 
	* @param out 
	*            输出流 
	* @throws IOException 
	* @since 1.0 
	* @see com.sitechasia.webx.core.utils.image.ImageConstants.FormatName 
	*/  
	public static void writeImage(BufferedImage image, String formatName, OutputStream out) throws IOException {  
	   if (image != null && formatName != null && !"".equals(formatName) && out != null) {  
	    ImageIO.write(image, formatName, out);  
	   }  
	}  
	  
	/** 
	* 按指定像素大小缩放图像 
	* 
	* @param image 
	*            图像 
	* @param width 
	*            宽度像素 
	* @param heigth 
	*            高度像素 
	* @return 缩放后的图像 
	*/  
	public static BufferedImage zoom(BufferedImage image, int width, int heigth) {  
	    
	   BufferedImage tag = new BufferedImage(width,heigth, BufferedImage.TYPE_INT_RGB);     
	        /*  
	         * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的  
	         * 优先级比速度高 生成的图片质量比较好 但速度慢  
	         */   
	        tag.getGraphics().drawImage(  
	           image.getScaledInstance(width, heigth,     
	                        Image.SCALE_SMOOTH), 0, 0, null);     
	        return tag;  
	}  
	  
	/** 
	* 按指定比例缩放图像 
	* 
	* @param image 
	*            原图像 
	* @param sx 
	*            x轴缩放比例 
	* @param sy 
	*            y轴缩放比例 
	* @return 缩放后的图像 
	*/  
	public static BufferedImage zoom(BufferedImage image, double sx, double sy) {  
	    
	   int width = (int)(image.getWidth()*sx);  
	   int heigth = (int)(image.getHeight()*sy);  
	   return zoom(image, width, heigth);  
	}  
	/* 
	* 旋转  
	* 因为旋转后造成图像缺失，在旋转前需要平移一段距离  
	* @param image 原图  
	* @param angle 旋转角度  
	* @param color 无图像部分的填充色,默认值为{255,255,255}  
	* @return  
	*/  
	public static BufferedImage rotate(BufferedImage image, Double angle,Integer[] color) {  
	   int   width   =   image.getWidth();     
	   int   height   =   image.getHeight();   
	    
	   angle = (360-angle);  
	    
	//   无图像部分填充的颜色默认值  
	   if(color==null){  
	    Integer[] tempColors = {0,0,0};  
	    color = tempColors;  
	   }  
	    
	    
	//   需要平移的距离  
	   double _x;  
	   double _y;  
	   if(angle<=90){  
	    _x=0;  
	    _y=(width*Math.sin(angle/180*Math.PI));  
	   }else if(angle<=180){  
	    _x=(width*Math.cos(angle/180*Math.PI))*(-1);  
	    _y=(width*Math.sin(angle/180*Math.PI)-height*Math.cos(angle/180*Math.PI));  
	   }else if(angle<=270){  
	    _x=(width*Math.cos(angle/180*Math.PI)+height*Math.sin(angle/180*Math.PI))*(-1);  
	    _y=(height*Math.cos(angle/180*Math.PI))*(-1);  
	   }else {  
	    _x=(height*Math.sin(angle/180*Math.PI))*(-1);  
	    _y=0;  
	   }  
	    
	//   旋转后的图像大小  
	   int _w=(int)Math.round(width*Math.abs(Math.cos(angle/180*Math.PI))+height*Math.abs(Math.sin(angle/180*Math.PI)));  
	   int _h=(int)Math.round(width*Math.abs(Math.sin(angle/180*Math.PI))+height*Math.abs(Math.cos(angle/180*Math.PI)));  
	    
	//   平移图像  
	   AffineTransform   affineTransform   = AffineTransform.getTranslateInstance(_x, _y);     
	    
	//   使用_w，_h作为新图像的宽高  
	   BufferedImage   dstImage   =   new   BufferedImage(_w,   _h,   image.getType());     
	    
	//   无图像部分填充颜色  
	   Graphics2D   biContext   =   dstImage.createGraphics();     
	   biContext.setColor(new Color(color[0],color[1],color[2]));  
	   biContext.fillRect(0, 0, _w, _h);  
	   biContext.drawImage(dstImage,   0,   0,   null);     
	    
	//   旋转  
	   affineTransform.rotate(java.lang.Math.toRadians(360-angle));     
	   AffineTransformOp   affineTransformOp   =   new   AffineTransformOp(affineTransform, 1);   
	   return affineTransformOp.filter(image,   dstImage);  
	}  
	  
	  
	/** 
	*  
	* 裁剪 
	* @param image 原图 
	* @param x 起点坐标x 
	* @param y 起点坐标y 
	* @param destWidth 裁剪后宽度  
	* @param destHeight 裁剪后高度 
	* @return 
	*/  
	public static BufferedImage cut(BufferedImage image, int x, int y,  
	    int destWidth, int destHeight) {  
	    
	//   裁剪图像  
	   ImageFilter cropFilter = new CropImageFilter(x,y,destWidth,destHeight);   
	   Image destImage= Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(),cropFilter));  
	    
	//   将destImage转换为bufferedImage  
	   BufferedImage   dstBufferedImage   =   new   BufferedImage(destWidth,destHeight,BufferedImage.TYPE_INT_RGB);     
	   Graphics2D   biContext   =   dstBufferedImage.createGraphics();     
	   biContext.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  
	   biContext.drawImage(destImage,   0,   0,   null);   
	    
	   return dstBufferedImage;  
	}  
}
