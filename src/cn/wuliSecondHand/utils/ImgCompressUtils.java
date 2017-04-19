package cn.wuliSecondHand.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.*;

import javax.imageio.ImageIO;

public class ImgCompressUtils {
	
	private Image img;  
    private int width;  
    private int height;  
    
    
    public static void compress(String oldpath , String compath) throws Exception {  
        ImgCompressUtils imgCom = new ImgCompressUtils(oldpath);  
        imgCom.resizeFix(400, 400 ,oldpath ,compath);   
    }  
    /** 
     * 构造函数 
     */  
    public ImgCompressUtils(String fileName) throws IOException {  
        File file = new File(fileName);// 读入文件  
        img = ImageIO.read(file);      // 构造Image对象  
        width = img.getWidth(null);    // 得到源图宽  
        height = img.getHeight(null);  // 得到源图长 
    }  
    /** 
     * 按照宽度还是高度进行压缩 
     * @param w int 最大宽度 
     * @param h int 最大高度 
     */  
    public void resizeFix(int w, int h , String oldpath , String compath) throws IOException {  
        if (width / height > w / h) {  
            resizeByWidth(w , oldpath , compath);  
        } else {  
            resizeByHeight(h, oldpath , compath);  
        }  
    }  
    /** 
     * 以宽度为基准，等比例放缩图片 
     * @param w int 新宽度 
     */  
    public void resizeByWidth(int w , String oldpath , String compath) throws IOException {  
        int h = (int) (height * w / width);  
        resize(w, h ,oldpath , compath);  
    }  
    /** 
     * 以高度为基准，等比例缩放图片 
     * @param h int 新高度 
     */  
    public void resizeByHeight(int h , String oldpath , String compath) throws IOException {  
        int w = (int) (width * h / height);  
        resize(w, h,oldpath,compath);  
    }  
    /** 
     * 强制压缩/放大图片到固定的大小 
     * @param w int 新宽度 
     * @param h int 新高度 
     */  
    @SuppressWarnings("restriction")
	public void resize(int w, int h ,String oldpath , String path) throws IOException {  
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢  
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );   
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图  
        File destFile = new File(path);  
        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流  
        // 可以正常实现bmp、png、gif转jpg  
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
        encoder.encode(image); // JPEG编码  
        out.close();  
    }  

}
