package weixin.ooolo.net.utils;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.junit.Test;
import net.sf.json.JSONObject;

public class MenuTest2 {

	@Test
    public void creatMenuTest() throws ClientProtocolException, IOException{
    	GetAccessToken getAccessToken =new GetAccessToken();
    	Menu menu=new Menu();
        //��ȡ��access_token
        String accessToken =getAccessToken.getAccessToken();

        System.out.println("-------------------->"+accessToken); 
        //��ȡ���Զ���˵��ĸ�ʽ��JSONObject������תΪjson��Ȼ������ҪתΪ�ַ����ͣ�
        String menu2 = JSONObject.fromObject(menu.initMenu()).toString();
      System.err.println(menu2);
        //���ô����˵�
        int result = menu.createMenu(accessToken, menu2);
        if(result == 0){
            //������÷���֮�󣬷��ص���0����ô�ͱ�ʾ�����ɹ���
            System.out.println("�����ɹ�");
        }else{
            System.out.println("����ʧ��");
        }
    }
	/**
     * ��ѯ�˵�
	 * @throws IOException 
	 * @throws ClientProtocolException 
     */
    @Test
    public void queryMenuTest() throws ClientProtocolException, IOException{
    	GetAccessToken getAccessToken =new GetAccessToken();
    	Menu menu=new Menu();
        //��ȡ��access_token
        String accessToken =getAccessToken.getAccessToken();
        //��ȡ��access_token
       
        //���ò˵���ѯ�ķ����������ǵ�һ��Json��ʽ
        JSONObject jsonObject = menu.queryMenu(accessToken);
        System.out.println(jsonObject);
    }
    /**
     * ɾ���˵�
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    @Test
    public void deleteMenuTest() throws ClientProtocolException, IOException{
    	GetAccessToken getAccessToken =new GetAccessToken();
    	Menu menu=new Menu();
        //��ȡ��access_token
        String accessToken =getAccessToken.getAccessToken();
        //��ȡ��access_token
      
        //���ò˵���ѯ�ķ����������ǵ�һ��Json��ʽ
        JSONObject jsonObject = menu.deleteMenu(accessToken);
        if(jsonObject.getInt("errcode") == 0){
            //����0����ʾ����ɾ���ɹ�
            System.out.println("ɾ���ɹ�");
        }else{
            System.out  .println("ɾ��ʧ��");
        }
 
    }

}
