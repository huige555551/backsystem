package operation.pojo.user;

import java.util.List;

public class ResponseOpenFireUser {
	private List<String> serverList;
	private String openFireUserName;
	private String openFireNickName;
	private String openFireEmail;
	private Object openFireData;

	public ResponseOpenFireUser(OpenFireUser openFireUser) {

		this.setOpenFireUserName(openFireUser.getOpenFireUserName());
		this.setOpenFireNickName(openFireUser.getOpenFireNickName());
		this.setOpenFireEmail(openFireUser.getOpenFireEmail());
		this.setOpenFireData(openFireUser.getOpenFireData());
		this.setServerList(openFireUser.getServerList());

	}

	public ResponseOpenFireUser() {

	}

	public List<String> getServerList() {
		return serverList;
	}

	public void setServerList(List<String> serverList) {
		this.serverList = serverList;
	}

	public String getOpenFireUserName() {
		return openFireUserName;
	}

	public void setOpenFireUserName(String openFireUserName) {
		this.openFireUserName = openFireUserName;
	}

	public String getOpenFireNickName() {
		return openFireNickName;
	}

	public void setOpenFireNickName(String openFireNickName) {
		this.openFireNickName = openFireNickName;
	}

	public String getOpenFireEmail() {
		return openFireEmail;
	}

	public void setOpenFireEmail(String openFireEmail) {
		this.openFireEmail = openFireEmail;
	}

	public Object getOpenFireData() {
		return openFireData;
	}

	public void setOpenFireData(Object openFireData) {
		this.openFireData = openFireData;
	}

}
