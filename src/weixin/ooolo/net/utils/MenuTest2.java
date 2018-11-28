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
        //获取到access_token
        String accessToken =getAccessToken.getAccessToken();

        System.out.println("-------------------->"+accessToken); 
        //获取到自定义菜单的格式（JSONObject将对象转为json，然后再需要转为字符串型）
        String menu2 = JSONObject.fromObject(menu.initMenu()).toString();
      System.err.println(menu2);
        //调用创建菜单
        int result = menu.createMenu(accessToken, menu2);
        if(result == 0){
            //如果调用方法之后，返回的是0，那么就表示创建成功。
            System.out.println("创建成功");
        }else{
            System.out.println("创建失败");
        }
    }
	/**
     * 查询菜单
	 * @throws IOException 
	 * @throws ClientProtocolException 
     */
    @Test
    public void queryMenuTest() throws ClientProtocolException, IOException{
    	GetAccessToken getAccessToken =new GetAccessToken();
    	Menu menu=new Menu();
        //获取到access_token
        String accessToken =getAccessToken.getAccessToken();
        //获取到access_token
       
        //调用菜单查询的方法，返回是的一个Json格式
        JSONObject jsonObject = menu.queryMenu(accessToken);
        System.out.println(jsonObject);
    }
    /**
     * 删除菜单
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    @Test
    public void deleteMenuTest() throws ClientProtocolException, IOException{
    	GetAccessToken getAccessToken =new GetAccessToken();
    	Menu menu=new Menu();
        //获取到access_token
        String accessToken =getAccessToken.getAccessToken();
        //获取到access_token
      
        //调用菜单查询的方法，返回是的一个Json格式
        JSONObject jsonObject = menu.deleteMenu(accessToken);
        if(jsonObject.getInt("errcode") == 0){
            //返回0，表示的是删除成功
            System.out.println("删除成功");
        }else{
            System.out  .println("删除失败");
        }
 
    }

}
