package onekr.framework.code2d;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

@SuppressWarnings("serial")  
public class QrCodeServlet extends HttpServlet {  
    private static final int BLACK = -16777216;  
    private static final int WHITE = -1;  
      
    private BufferedImage toBufferedImage(BitMatrix matrix) {  
        int width = matrix.getWidth();  
        int height = matrix.getHeight();  
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_ARGB);  
        for (int x = 0; x < width; x++) {  
            for (int y = 0; y < height; y++) {  
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);  
            }  
        }  
        return image;  
    }  
      
    @Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        try {  
            String content = req.getParameter("m");  
            if(content==null||content.equals("")){  
                resp.setContentType("text/plain;charset=UTF-8");  
                resp.getOutputStream().write("二维码内容不能为空!".getBytes("utf-8"));  
                resp.getOutputStream().close();  
            }  
            int imgWidth = 110;  
            int imgHeight = 110;  
              
            String width = req.getParameter("w");  
            String height = req.getParameter("h");  
            if(width!=null&&!width.equals("")){  
                try {  
                    imgWidth = Integer.parseInt(width);  
                } catch (Exception e) {}  
            }  
            if(height!=null&&!height.equals("")){  
                try {  
                    imgHeight = Integer.parseInt(height);  
                } catch (Exception e) {}  
            }  
              
            BitMatrix byteMatrix;  
            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();  
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
            byteMatrix = new MultiFormatWriter().encode(  
                    new String(content.getBytes("UTF-8"),"ISO-8859-1"),  
                    BarcodeFormat.QR_CODE,   
                    imgWidth,   
                    imgHeight,   
                    hints);  
  
            BufferedImage image = toBufferedImage(byteMatrix);  
            resp.setContentType("image/png");  
            ImageIO.write(image, "png", resp.getOutputStream());    
        } catch (Exception e) {  
              
        }  
    }  
} 