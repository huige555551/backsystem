package operation.pojo.user;

public class UserMoreMessage {
	
	private int groupCount;
	
	private long dryCount;
	
	private int courseCount;
	
	private long xuanYeCount;
	
	public UserMoreMessage(int groupCount,long dryCount,int courseCount,long xuanYeCount){
		this.groupCount = groupCount;
		this.dryCount = dryCount;
		this.courseCount = courseCount;
		this.xuanYeCount = xuanYeCount;
	}

	public int getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}


	public int getCourseCount() {
		return courseCount;
	}

	public void setCourseCount(int courseCount) {
		this.courseCount = courseCount;
	}
	public long getDryCount() {
		return dryCount;
	}

	public void setDryCount(long dryCount) {
		this.dryCount = dryCount;
	}

	public long getXuanYeCount() {
		return xuanYeCount;
	}

	public void setXuanYeCount(long xuanYeCount) {
		this.xuanYeCount = xuanYeCount;
	}
}
