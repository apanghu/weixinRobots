package weixin.ooolo.net.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

import weixin.ooolo.net.entity.ImageBase;
import weixin.ooolo.net.entity.MusicBase;
import weixin.ooolo.net.entity.NewsBase;
import weixin.ooolo.net.message.ImageMessage;
import weixin.ooolo.net.message.MusicMessage;
import weixin.ooolo.net.message.MyTestMessage;
import weixin.ooolo.net.message.NewsMessage;

public class MessageUtils {
	/**
     * ���������Ϣ����
     */
    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_MUSIC = "music";
    public static final String MESSAGE_VIDEO = "video";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";
    public static final String MESSAGE_EVENT = "event";
    public static final String MESSAGE_SUBSCRIBE = "subscribe";
    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
    public static final String MESSAGE_CLICK = "CLICK";
    public static final String MESSAGE_VIEW = "VIEW";
    //ɨ���¼�
    public static final String MESSAGE_SCANCODE = "scancode_push";
 
    /**
     * XML��ʽתΪmap��ʽ
     * @param request
     * @return
     */
    public static Map<String , String> xmlToMap(HttpServletRequest request){
        Map<String ,String> map = new HashMap<String , String>();
        try {
            InputStream inputStream =null;
            inputStream = request.getInputStream();
            SAXReader reader = new SAXReader();
            Document doc = reader.read(inputStream);
            Element rootElement = doc.getRootElement();
            List<Element> elements = rootElement.elements();
            for (Element el:elements) {
                map.put(el.getName() , el.getText());
            }
            inputStream.close();
            return map ;
        } catch (Exception e) {
            e.printStackTrace();
            return null ;
        }
    }
    /**
     * �ı���Ϣ����תΪxml��ʽ
     * @param myTestMessage
     * @return
     */
    public static String textMessage2Xml(MyTestMessage myTestMessage){
        XStream xStream = new XStream();
        xStream.alias("xml" , myTestMessage.getClass());
        return xStream.toXML(myTestMessage);
    }
    

    /**
     * ������Ҫ���ص��ı���Ϣ
     * @param fromUserName
     * @param toUserName
     * @param content
     * @return
     */
    public static String initText(String fromUserName , String toUserName , String content){
        MyTestMessage text = new MyTestMessage();
        //ע�������Ϣ�ͷ�����Ϣ��˳��Ҫ������
        text.setFromUserName(toUserName);
        text.setToUserName(fromUserName);
        text.setMsgType(MessageUtils.MESSAGE_TEXT);
        long time = System.currentTimeMillis();
        text.setCreateTime(String.valueOf(time));
        text.setContent(content);
        return MessageUtils.textMessage2Xml(text);
    }
   
    /**
     * ����ͼƬ��Ϣ(������Ƶ����Ƶ����һ���ķ�ʽ��ֻ��Ҫ�������ͼ��ɣ�����Image�޸�Ϊvideo��voice)
     * @param fromUserName
     * @param toUserName
     * @return
     */
    public static String initImageMessage(String fromUserName , String toUserName ){
        ImageBase imageBase = new ImageBase();
        //���media_Id����֮ǰִ�й��ϴ�ͼƬ���صõ�����Ϣ
        imageBase.setMediaId("KMYHci3INDDl2RN6RlNkXxWoZ5OcZ3p0FB9qKpTbKiBtTbDmuLwocouuTIzt_RTP");
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setFromUserName(toUserName);
        imageMessage.setToUserName(fromUserName);
        imageMessage.setMsgType(MessageUtils.MESSAGE_IMAGE);
        long time = System.currentTimeMillis();
        imageMessage.setCreateTime(String.valueOf(time));
        imageMessage.setImage(imageBase);
       
        return imageMessage2XML(imageMessage);
    }
    
    /**
     * ��ͼƬ��Ϣ����ת��ΪͼƬ��ʽ��XML
     * @return
     */
    public static String imageMessage2XML(ImageMessage imageMessage){
        XStream xStream = new XStream();
        //����Ҫ�޸ĵ�һЩ��ǩ�����滻
        xStream.alias("xml" , imageMessage.getClass());
        xStream.alias("Image" , new ImageBase().getClass());
        return xStream.toXML(imageMessage);
    }

    /**
     * �ظ��ؼ���"1"��ʱ�������
     * @return
     */
    public static String inputNumber1(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("���Ǿ�ϲ111����������ϲ����ϲ��");
        return stringBuilder.toString();
    }
    /**
     * �ظ��ؼ���"2"��ʱ�������
     * @return
     */
    public static String inputNumber2(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("���Ǿ�ϲ2222����������ϲ����ϲ��");
        return stringBuilder.toString();
    }
    /**
     * ����������Ϣ
     * @param fromUserName
     * @param toUserName
     * @return
     */
    public static String initMusicMessage(String fromUserName , String toUserName ){
        MusicBase musicBase = new MusicBase();
        //���ThumbMediaId����֮ǰִ�й��ϴ�����ͼ���صõ�����Ϣ��������ϴ�ͼƬ�ķ�����һ���ģ����ǵ���pictureUtils�е��ϴ�������
        musicBase.setThumbMediaId("yuI13kJLt60wJ22n_M-2FxaD-dG0y7uhXR7OEBz-tjf29yxAFcIgbCo1TV6j1M7o");
        musicBase.setTitle("��һ������");
        musicBase.setDescription("���������׸�ɣ�");
        //���ø��������ʵĸ���·�������Ժ�һ���������ֵ�·��һ��
        musicBase.setHQMusicUrl("http://pay.ooolo.net/images/404.mp3");
        //�������ֵ�·��
        musicBase.setMusicUrl("http://pay.ooolo.net/images/404.mp3");
        MusicMessage musicMessage = new MusicMessage();
        musicMessage.setFromUserName(toUserName);
        musicMessage.setToUserName(fromUserName);
        //��������Ϊ����
        musicMessage.setMsgType(MessageUtils.MESSAGE_MUSIC);
        long time = System.currentTimeMillis();
        musicMessage.setCreateTime(String.valueOf(time));
        musicMessage.setMusic(musicBase);
        return musicMessage2XML(musicMessage);
    }

    /**
     * ��������Ϣ����ת��ΪͼƬ��ʽ��XML
     * @return
     */
    public static String musicMessage2XML(MusicMessage musicMessage){
        XStream xStream = new XStream();
        //����Ҫ�޸ĵ�һЩ��ǩ�����滻
        xStream.alias("xml" , musicMessage.getClass());
        xStream.alias("Music" , new MusicBase().getClass());
        return xStream.toXML(musicMessage);
    }
    /**
     * ���ö���ʱ�����ز˵�������
     * @return
     */
    public static String menuText(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("�˵�1���ظ�����1���о�ϲ\n");
        stringBuilder.append("�˵�2���ظ�����2���о�ϲ\n");
        stringBuilder.append("�˵�3���ظ�����3���о�ϲ\n");
        stringBuilder.append("�˵�4���ظ�����3���о�ϲ\n");
        stringBuilder.append("�˵�5���ظ�����3���о�ϲ\n");
        stringBuilder.append("�˵�6���ظ�����δ֪�Ķ�����Ҳ�����о�ϲŶ\n");
        return stringBuilder.toString();
    }
    
    /**
     * ��ͼ����Ϣ����ת��Ϊͼ�ĸ�ʽ��XML
     * @return
     */
    public static String newsMessage2XML(NewsMessage newsMessage){
        XStream xStream = new XStream();
        //����Ҫ�޸ĵ�һЩ��ǩ�����滻
        xStream.alias("xml" , newsMessage.getClass());
        xStream.alias("item" , new NewsBase().getClass());
        return xStream.toXML(newsMessage);
    }
    /**
     * ������Ҫ���ص�ͼ����Ϣ
     * @param fromUserName
     * @param toUserName
     * @return
     */
    public static String initNewSMessage(String fromUserName , String toUserName ){
        String message = null;
        List<NewsBase> newList = new ArrayList<NewsBase>();
        NewsMessage newsMessage = new NewsMessage();
        NewsBase  newsBase = new NewsBase();
        newsBase.setTitle("����ͼ����Ϣ");
        newsBase.setDescription("���Բ��Բ��Բ��Բ��Բ��Բ��Բ��Բ���");
        newsBase.setPicUrl("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=677717294,4155848424&fm=27&gp=0.jpg");
        newsBase.setUrl("www.baidu.com");
        //���ͼ����Ϣ������
        newList.add(newsBase);
 
        //ע�������Ϣ�ͷ�����Ϣ��˳��Ҫ����������Ϊ�����Ǵӷ��������з����ˣ����ͻ����ǽ��ն���
        newsMessage.setFromUserName(toUserName);
        newsMessage.setToUserName(fromUserName);
        newsMessage.setCreateTime(String.valueOf(System.currentTimeMillis()));
        newsMessage.setMsgType("news");
        newsMessage.setArticleCount(newList.size());
        newsMessage.setArticles(newList);
        //����תΪͼ�ĵ�XML
        return MessageUtils.newsMessage2XML(newsMessage);
    }

   
}
