package operation.pojo.user;

public class Message {
	private String msgType;
	private String text;
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public Message(String msgType, String text) {
		super();
		this.msgType = msgType;
		this.text = text;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
