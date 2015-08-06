package operation.pojo.group;

import java.util.List;

public class MyAllGroup {
	
	List<Object>  owner;
	List<Object>  admin;
	List<Object>  member;
	public MyAllGroup(List<Object> owner, List<Object> admin, List<Object> member){
		this.owner = owner;
		this.admin = admin;
		this.member = member;
	}
	
	public List<Object> getOwner() {
		return owner;
	}
	public void setOwner(List<Object> owner) {
		this.owner = owner;
	}
	public List<Object> getAdmin() {
		return admin;
	}
	public void setAdmin(List<Object> admin) {
		this.admin = admin;
	}
	public List<Object> getMember() {
		return member;
	}
	public void setMember(List<Object> member) {
		this.member = member;
	}


}
