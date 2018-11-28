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
 * 接收微信服务器发送的4个参数并返回echostr
 */
public class weixinServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 接收微信服务器以Get请求发送的4个参数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
    
        PrintWriter out = response.getWriter();
        if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);        // 校验通过，原样返回echostr参数内容
        }         
    }
   
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        backTestFunction(request , response );   
    }

	/**
	 * 文字回复功能开发
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void backTestFunction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 防止进行post提交和响应的消息乱码
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			// 将发送过来的消息XML形式转为map内容
			Map<String, String> map = MessageUtils.xmlToMap(request);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			String message = null;
			// 文本
			if (MessageUtils.MESSAGE_TEXT.equals(msgType)) {
				// 进行关键字回复功能
				if ("1".equals(content)) {
					message = MessageUtils.initText(fromUserName, toUserName, MessageUtils.inputNumber1());
				} else if ("2".equals(content)) {
					message = MessageUtils.initText(fromUserName, toUserName, MessageUtils.inputNumber2());

				} else if ("3".equals(content)) {
					// 客户端输入“3”，返回一条图片消息
					message = MessageUtils.initImageMessage(fromUserName, toUserName);

				} else if ("4".equals(content)) {
					// 客户端输入“4”，返回一条音乐消息
					message = MessageUtils.initMusicMessage(fromUserName, toUserName);
				} else if ("5".equals(content)) {
					// 客户端输入“5”，返回一条图文消息
					message = MessageUtils.initNewSMessage(fromUserName, toUserName);
				} else {

					try {
						int length = content.length();
						
					    // beginIndex -- 起始索引（包括）, 索引从 0 开始。
					    //endIndex -- 结束索引（不包括）。

						String ss = content.substring(length - 2, length);

						if ("图片".equals(ss)) {
							// 对接图灵机器人测试
							Tuling tuling = new Tuling();
							JSONObject J = JSONObject.parseObject((tuling.chat(content)));
							message = MessageUtils.initText(fromUserName, toUserName,
									J.getString("text") + "\n" + J.getString("url"));

						} else if ("天气".equals(ss)) {

							Tuling tuling = new Tuling();
							JSONObject J = JSONObject.parseObject((tuling.chat2(content, toUserName)));
							message = MessageUtils.initText(fromUserName, toUserName, J.getString("text"));

						} else if ("股票".equals(ss)) {
							// 对接图灵机器人测试
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
				// 图片事件
			} else if (MessageUtils.MESSAGE_IMAGE.equals(msgType)) {
				message = MessageUtils.initImageMessage(fromUserName, toUserName);
				// 订阅事件
			} else if (MessageUtils.MESSAGE_EVENT.equals(msgType)) {
				String eventType = map.get("Event");
				// 完成订阅时候返回的内容
				if (MessageUtils.MESSAGE_SUBSCRIBE.equals(eventType)) {
					message = MessageUtils.initText(fromUserName, toUserName, MessageUtils.menuText());
				} else if (MessageUtils.MESSAGE_CLICK.equals(eventType)) {
					// 进行的是click按钮的点击事件（这里就推送一下主菜单内容）
					message = MessageUtils.initText(fromUserName, toUserName, MessageUtils.menuText());
				} else if (MessageUtils.MESSAGE_VIEW.equals(eventType)) {
					// 进行的是view按钮的点击事件（这里就推送一下主菜单内容）
					String viewUrl = map.get("EventKey");
					message = MessageUtils.initText(fromUserName, toUserName, viewUrl);
				} else if (MessageUtils.MESSAGE_SCANCODE.equals(eventType)) {
					// 进行的是扫码事件
					String key = map.get("EventKey");
					message = MessageUtils.initText(fromUserName, toUserName, key);
				}
			} else if (MessageUtils.MESSAGE_LOCATION.equals(msgType)) {
				// 进行的是地理位置信息
				String label = map.get("Label");
				message = MessageUtils.initText(fromUserName, toUserName, label);
			}
			// 打印输出的xml格式内容，方便进行调试
			System.out.println(message);

			out.print(message);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}