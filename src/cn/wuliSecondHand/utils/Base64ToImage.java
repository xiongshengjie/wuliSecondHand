package cn.wuliSecondHand.utils;

import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import Decoder.BASE64Decoder;

public class Base64ToImage {

	public static boolean GenerateImage(String imgStr, String path,HttpServletRequest request) {  
	       if (imgStr == null) // 图像数据为空  
	           return false;  
	       BASE64Decoder decoder = new BASE64Decoder();  
	       try {  
	           // Base64解码  
	           byte[] b = decoder.decodeBuffer(imgStr);  
	           for (int i = 0; i < b.length; ++i) {  
	               if (b[i] < 0) {// 调整异常数据  
	                   b[i] += 256;  
	               }  
	           }  
	           // 生成jpeg图片 System.currentTimeMillis()  
	           String realpath = request.getServletContext().getRealPath("/");
	           OutputStream out = new FileOutputStream(realpath+"/"+path);  
	           out.write(b);  
	           out.flush();  
	           out.close();  
	           return true;  
	       } catch (Exception e) {  
	    	   e.printStackTrace();
	           return false;  
	       }  
	   }  
}
