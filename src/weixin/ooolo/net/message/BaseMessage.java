package weixin.ooolo.net.message;

/**
 * @author scw
 * @create 2018-01-17 13:37
 * @desc ����������Ϣ�Ļ�������
 **/
public class BaseMessage {
    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType;
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
}
