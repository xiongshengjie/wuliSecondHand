package cn.wuliSecondHand.utils;

import java.io.IOException;  
import java.util.ArrayList;  
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.NameValuePair;  
import org.apache.http.client.ClientProtocolException;  
import org.apache.http.client.methods.CloseableHttpResponse;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.client.methods.HttpRequestBase;  
import org.apache.http.impl.client.CloseableHttpClient;  
import org.apache.http.impl.client.HttpClients;  
  
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;  
import org.apache.http.message.BasicNameValuePair;  

public class HttpClientUtils {

	 private static PoolingHttpClientConnectionManager cm;   
	  
	    private static void init() {  
	        if (cm == null) {  
	            cm = new PoolingHttpClientConnectionManager();  
	            cm.setMaxTotal(50);// 整个连接池最大连接数  
	            cm.setDefaultMaxPerRoute(5);// 每路由最大连接数，默认值是2  
	        }  
	    }  
	  
	    /** 
	     * 通过连接池获取HttpClient 
	     *  
	     * @return 
	     */  
	    private static CloseableHttpClient getHttpClient() {  
	        init();  
	        return HttpClients.custom().setConnectionManager(cm).build();  
	    }  
	  
	  
	    public static boolean httpPostRequest(String url) {  
	        HttpPost httpPost = new HttpPost(url);  
	        return getResult(httpPost);  
	    }     
	  
	    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {  
	        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();  
	        for (Map.Entry<String, Object> param : params.entrySet()) {  
	            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));  
	        }  
	  
	        return pairs;  
	    }  
	  
	    /** 
	     * 处理Http请求 
	     *  
	     * @param request 
	     * @return 
	     */  
	    private static boolean getResult(HttpRequestBase request) {  
	        // CloseableHttpClient httpClient = HttpClients.createDefault();  
	        CloseableHttpClient httpClient = getHttpClient();  
	        try {  
				for (int i = 0; i < 10; i++) {
					CloseableHttpResponse response = httpClient.execute(request);
					// response.getStatusLine().getStatusCode();
					Header[] headers = response.getHeaders("Set-Cookie");
					int cookiecount = headers.length;
					if (cookiecount == 2) {
						response.close();
						// httpClient.close();
						return true;
					} else if(cookiecount == 1){
						response.close();
						return false;
					}
				}
	        } catch (ClientProtocolException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	        	
	        }  
	  
	        return false;  
	    }  
}
