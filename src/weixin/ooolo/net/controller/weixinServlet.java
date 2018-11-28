package weixin.ooolo.net.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import sun.tools.jar.resources.jar;
import weixin.ooolo.net.utils.CheckUtil;
import weixin.ooolo.net.utils.MessageUtils;
import weixin.ooolo.net.utils.Tuling;

/**
 * ����΢�ŷ��������͵�4������������echostr
 */
public class weixinServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ����΢�ŷ�������Get�����͵�4������
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
    
        PrintWriter out = response.getWriter();
        if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);        // У��ͨ����ԭ������echostr��������
        }         
    }
   
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        backTestFunction(request , response );   
    }

	/**
	 * ���ֻظ����ܿ���
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void backTestFunction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// ��ֹ����post�ύ����Ӧ����Ϣ����
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			// �����͹�������ϢXML��ʽתΪmap����
			Map<String, String> map = MessageUtils.xmlToMap(request);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			String message = null;
			// �ı�
			if (MessageUtils.MESSAGE_TEXT.equals(msgType)) {
				// ���йؼ��ֻظ�����
				if ("1".equals(content)) {
					message = MessageUtils.initText(fromUserName, toUserName, MessageUtils.inputNumber1());
				} else if ("2".equals(content)) {
					message = MessageUtils.initText(fromUserName, toUserName, MessageUtils.inputNumber2());

				} else if ("3".equals(content)) {
					// �ͻ������롰3��������һ��ͼƬ��Ϣ
					message = MessageUtils.initImageMessage(fromUserName, toUserName);

				} else if ("4".equals(content)) {
					// �ͻ������롰4��������һ��������Ϣ
					message = MessageUtils.initMusicMessage(fromUserName, toUserName);
				} else if ("5".equals(content)) {
					// �ͻ������롰5��������һ��ͼ����Ϣ
					message = MessageUtils.initNewSMessage(fromUserName, toUserName);
				} else {

					try {
						int length = content.length();
						
					    // beginIndex -- ��ʼ������������, ������ 0 ��ʼ��
					    //endIndex -- ��������������������

						String ss = content.substring(length - 2, length);

						if ("ͼƬ".equals(ss)) {
							// �Խ�ͼ������˲���
							Tuling tuling = new Tuling();
							JSONObject J = JSONObject.parseObject((tuling.chat(content)));
							message = MessageUtils.initText(fromUserName, toUserName,
									J.getString("text") + "\n" + J.getString("url"));

						} else if ("����".equals(ss)) {

							Tuling tuling = new Tuling();
							JSONObject J = JSONObject.parseObject((tuling.chat2(content, toUserName)));
							message = MessageUtils.initText(fromUserName, toUserName, J.getString("text"));

						} else if ("��Ʊ".equals(ss)) {
							// �Խ�ͼ������˲���
							Tuling tuling = new Tuling();
							JSONObject J = JSONObject.parseObject((tuling.chat(content)));
							message = MessageUtils.initText(fromUserName, toUserName,
									J.getString("text") + "\n" + J.getString("url"));
						}else{
							Tuling tuling = new Tuling();
							JSONObject J = JSONObject.parseObject((tuling.chat(content)));
							message = MessageUtils.initText(fromUserName, toUserName, J.getString("text"));

						}
					} catch (Exception e) {
						// TODO: handle exception
						Tuling tuling = new Tuling();
						JSONObject J = JSONObject.parseObject((tuling.chat(content)));
						message = MessageUtils.initText(fromUserName, toUserName, J.getString("text"));

					}

				}
				// ͼƬ�¼�
			} else if (MessageUtils.MESSAGE_IMAGE.equals(msgType)) {
				message = MessageUtils.initImageMessage(fromUserName, toUserName);
				// �����¼�
			} else if (MessageUtils.MESSAGE_EVENT.equals(msgType)) {
				String eventType = map.get("Event");
				// ��ɶ���ʱ�򷵻ص�����
				if (MessageUtils.MESSAGE_SUBSCRIBE.equals(eventType)) {
					message = MessageUtils.initText(fromUserName, toUserName, MessageUtils.menuText());
				} else if (MessageUtils.MESSAGE_CLICK.equals(eventType)) {
					// ���е���click��ť�ĵ���¼������������һ�����˵����ݣ�
					message = MessageUtils.initText(fromUserName, toUserName, MessageUtils.menuText());
				} else if (MessageUtils.MESSAGE_VIEW.equals(eventType)) {
					// ���е���view��ť�ĵ���¼������������һ�����˵����ݣ�
					String viewUrl = map.get("EventKey");
					message = MessageUtils.initText(fromUserName, toUserName, viewUrl);
				} else if (MessageUtils.MESSAGE_SCANCODE.equals(eventType)) {
					// ���е���ɨ���¼�
					String key = map.get("EventKey");
					message = MessageUtils.initText(fromUserName, toUserName, key);
				}
			} else if (MessageUtils.MESSAGE_LOCATION.equals(msgType)) {
				// ���е��ǵ���λ����Ϣ
				String label = map.get("Label");
				message = MessageUtils.initText(fromUserName, toUserName, label);
			}
			// ��ӡ�����xml��ʽ���ݣ�������е���
			System.out.println(message);

			out.print(message);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}