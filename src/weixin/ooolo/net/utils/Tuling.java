package weixin.ooolo.net.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Tuling {
	public String chat(String con) throws Exception{
	String APIKEY = "828a8bcdce85410eb543ee49b514db64";

	String question =con;// 这是上传给云机器人的问题
	String INFO = URLEncoder.encode(question, "utf-8");
	String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY+ "&info=" + INFO;
	URL getUrl = new URL(getURL);
	HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
	// 取得输入流，并使用Reader读取
	BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
	StringBuffer sb = new StringBuffer();
	String line = "";
	while ((line = reader.readLine()) != null) {
		sb.append(line);
	}
	reader.close();
	// 断开连接
	connection.disconnect();
	System.out.println("图灵机器人返回的值为：------>"+sb.toString());
	return sb.toString();
}
	
	public String chat2(String con,String userId) throws Exception{
		String APIKEY = "828a8bcdce85410eb543ee49b514db64";

		String question =con;// 这是上传给云机器人的问题
		String INFO = URLEncoder.encode(question, "utf-8");
		String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY+ "&info=" + INFO+"&userid"+userId;
		URL getUrl = new URL(getURL);
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		// 取得输入流，并使用Reader读取
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer();
		String line = "";
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		// 断开连接
		connection.disconnect();
		System.out.println("图灵机器人返回的值为：------>"+sb.toString());
		return sb.toString();
	}
}
