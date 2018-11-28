package weixin.ooolo.net.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;
import weixin.ooolo.net.menu.BaseButton;
import weixin.ooolo.net.menu.ClickButton;
import weixin.ooolo.net.menu.CustomeMenu;
import weixin.ooolo.net.menu.ViewButton;

public class Menu {
	/**
     * ���ò˵�����ʽ
     * @return
     */
    public  CustomeMenu initMenu(){
        CustomeMenu customeMenu = new CustomeMenu();
        ClickButton clickButton = new ClickButton();
        clickButton.setName("click�˵�");
        clickButton.setType("click");
        clickButton.setKey("01");
 
        ViewButton viewButton = new ViewButton();
        viewButton.setName("view�˵�");
        viewButton.setType("view");
        viewButton.setUrl("https://baidu.com");
 
        ClickButton clickButton2 = new ClickButton();
        clickButton2.setName("ɨ���¼���click�˵�");
        clickButton2.setType("scancode_push");
        clickButton2.setKey("02");
 
        ClickButton clickButton3 = new ClickButton();
        clickButton3.setName("����λ�õ�click�˵�");
        clickButton3.setType("location_select");
        clickButton3.setKey("03");
 
        BaseButton baseButton = new BaseButton();
        baseButton.setName("�˵�");
        //��clickButton2,clickButton3��Ϊһ���Ӳ˵��еİ�ť
        baseButton.setSub_button(new BaseButton[]{clickButton2,clickButton3});
        //�Ѱ�ť�ֱ���뵽�˵���
        customeMenu.setButton(new BaseButton[]{clickButton,viewButton,baseButton});
 
        return customeMenu;
    }
 
    /**
     * �����Զ���˵�
     * @param token
     * @param menu
     * @return
     */
    public  int createMenu(String token , String menu){
        int result = 0;
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+token;
        JSONObject jsonObject = doPostStr(url, menu);
        if(jsonObject != null){
            //���ܷ��ػ����Ĳ����������0�����Ǵ����ɹ�
            result = jsonObject.getInt("errcode");
        }
        return result;
    }
 
    /**
     * �Բ˵����в�ѯ
     * @param token
     * @return
     */
    public  JSONObject queryMenu(String token){
        String url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token="+token;
        JSONObject jsonObject = doGetStr(url);
        return jsonObject;
    }
 
    /**
     * �Բ˵�����ɾ��
     * @param token
     * @return
     */
    public JSONObject deleteMenu(String token){
        String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+token;
        JSONObject jsonObject = doGetStr(url);
        return jsonObject;
    }
    
    /**
     * ��������post���󣬷��㵽һ��url�ӿ�����ȡ���
     * @param url
     * @param outStr
     * @return
     */
    public  JSONObject doPostStr(String url , String outStr)  {
     
		DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonObject = null;
        try {
            httpPost.setEntity(new StringEntity(outStr , "UTF-8"));
            HttpResponse response = defaultHttpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if(entity != null){
                String result = EntityUtils.toString(entity, "UTF-8");
                jsonObject = JSONObject.fromObject(result);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * Get���󣬷��㵽һ��url�ӿ�����ȡ���
     * @param url
     * @return
     */
    public static JSONObject doGetStr(String url){
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        try{
            HttpResponse response = defaultHttpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if(entity != null){
                String result = EntityUtils.toString(entity, "UTF-8");
                jsonObject = JSONObject.fromObject(result);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
