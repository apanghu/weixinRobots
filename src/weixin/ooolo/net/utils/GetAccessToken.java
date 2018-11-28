package weixin.ooolo.net.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSONObject;

public class  GetAccessToken {


		/**
	     * 微信公众号的APPID和Appsecret，这个是每个微信公众号都唯一的，以后配置不同的公众号配置这里即可
	     */
	
	    /* 个人
	    private static final String APPID = "wx2050335db73af733";
	    private static final String APPSECRET = "e3103ae0f79734a3ad748e0a594e376f";
	   */
	
	    //体验账号
	    private static final String APPID = "wxff8a4e4af41f2e9e";
	    private static final String APPSECRET = "778311f8a7afe32df4bd084bdeb48025";
	   
	    //获取access_token的URL
	    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APPSECRET+"";
		
	    //两个小时有限期
	    //String access_token="15_hhzBuI8CMFzKZ9hRA9IDHOZYUYTxS1rYX5hiPUQiuYrxBO6F71spJAEjZ6okDLzZZ71K1SRyyq_tKMcEigwbmZ_sdkjDx7yk1_lYVZKB0o76vcDTaHmnE_AT1tnFOk7HsPRMECpIR6MynTryITQjADARCZ";
		String media_id_image="KMYHci3INDDl2RN6RlNkXxWoZ5OcZ3p0FB9qKpTbKiBtTbDmuLwocouuTIzt_RTP";
		String media_id_video="yuI13kJLt60wJ22n_M-2FxaD-dG0y7uhXR7OEBz-tjf29yxAFcIgbCo1TV6j1M7o";

	public String getAccessToken() throws ClientProtocolException, IOException {
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		HttpGet httpGet = new HttpGet(ACCESS_TOKEN_URL);

		client = HttpClients.createDefault();
		response = client.execute(httpGet);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity);
		JSONObject jsStr = JSONObject.parseObject(result);

		String accessToken = jsStr.getString("access_token");

		String expires_in = jsStr.getString("expires_in");
		// System.out.println("accessToken="+accessToken);
		// System.out.println("expires_in="+expires_in);

		return accessToken;
	}
		
		public String getUploadPicToWxMedia_id() {
			

	    	UploadPicToWx uploadPicToWx=new UploadPicToWx();
	    	//音频
	    //	System.out.println(uploadPicToWx.getMediaIdFromUrl("http://pay.ooolo.net/images/404.mp3", "voice"));
	    	//图片
	    	//System.out.println(uploadPicToWx.getMediaIdFromUrl("http://img1.juimg.com/170303/330474-1F3030I21924.jpg", "image"));
	
			return "";
		}
		
	
}
