package weixin.ooolo.net.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Mohon on 2017/8/10.
 */
public class UploadPicToWx {

    /**
     * ����ͼƬ�ϴ���΢�ŷ�����
     *
     * @param urlPath ͼƬ·��
     * @return JSONObject
     * @throws Exception
     */
    public String getMediaIdFromUrl(String urlPath, String fileType) throws Exception {
        String result = null;
        String url = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=15_9MUyAVnKlN5OqLDiphE63rhLD88hcmjFNYN5gVpFdIi84EkjnjD6iK6k-K2CRUerhUAqZe0e2BCLySVQfxcnBAaWzcIBXKNHYpYpi9UH6cvDVH0-pcbePIZoXCBerfizGhkTIF0548tUK9X4RAKfAFAXGQ&type=" + fileType + "";
        String fileName = urlPath.substring(urlPath.lastIndexOf("/") + 1);
        // ��ȡ����ͼƬ
        URL mediaUrl = new URL(urlPath);
        HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl.openConnection();
        meidaConn.setDoOutput(true);
        meidaConn.setRequestMethod("GET");

        /**
         * ��һ����
         */
        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        con.setRequestMethod("POST"); // ��Post��ʽ�ύ����Ĭ��get��ʽ
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false); // post��ʽ����ʹ�û���
        // ��������ͷ��Ϣ
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");
        // ���ñ߽�
        String BOUNDARY = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
        // ����������Ϣ
        // ��һ���֣�
        StringBuilder sb = new StringBuilder();
        sb.append("--"); // �����������
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"media\";filename=\"" + fileName + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        byte[] head = sb.toString().getBytes("utf-8");
        // ��������
        OutputStream out = new DataOutputStream(con.getOutputStream());
        // �����ͷ
        out.write(head);
        // �ļ����Ĳ���
        // ���ļ������ļ��ķ�ʽ ���뵽url��
        DataInputStream in = new DataInputStream(meidaConn.getInputStream());
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();
        // ��β����
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// ����������ݷָ���
        out.write(foot);
        out.flush();
        out.close();
        meidaConn.disconnect();
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        try {
            // ����BufferedReader����������ȡURL����Ӧ
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (result == null) {
                result = buffer.toString();
            }
        } catch (IOException e) {
          
            e.printStackTrace();
            throw new IOException("���ݶ�ȡ�쳣");
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        JSONObject jsonObj = JSON.parseObject(result);
      
        return jsonObj.getString("media_id");
    }
}
