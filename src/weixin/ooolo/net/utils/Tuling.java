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

	String question =con;// �����ϴ����ƻ����˵�����
	String INFO = URLEncoder.encode(question, "utf-8");
	String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY+ "&info=" + INFO;
	URL getUrl = new URL(getURL);
	HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
	// ȡ������������ʹ��Reader��ȡ
	BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
	StringBuffer sb = new StringBuffer();
	String line = "";
	while ((line = reader.readLine()) != null) {
		sb.append(line);
	}
	reader.close();
	// �Ͽ�����
	connection.disconnect();
	System.out.println("ͼ������˷��ص�ֵΪ��------>"+sb.toString());
	return sb.toString();
}
	
	public String chat2(String con,String userId) throws Exception{
		String APIKEY = "828a8bcdce85410eb543ee49b514db64";

		String question =con;// �����ϴ����ƻ����˵�����
		String INFO = URLEncoder.encode(question, "utf-8");
		String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY+ "&info=" + INFO+"&userid"+userId;
		URL getUrl = new URL(getURL);
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		// ȡ������������ʹ��Reader��ȡ
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer();
		String line = "";
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		// �Ͽ�����
		connection.disconnect();
		System.out.println("ͼ������˷��ص�ֵΪ��------>"+sb.toString());
		return sb.toString();
	}
}
