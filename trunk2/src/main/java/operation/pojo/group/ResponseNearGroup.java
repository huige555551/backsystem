package operation.pojo.group;

import java.util.List;

public class ResponseNearGroup {
	
	private List<ResponseGroup> nearGroup;
	
	private long groupCount;
	
	public ResponseNearGroup(List<ResponseGroup> nearGroup,long groupCount){
		this.nearGroup = nearGroup;
		this.groupCount = groupCount;
	}

	

	public long getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(long groupCount) {
		this.groupCount = groupCount;
	}



	public List<ResponseGroup> getNearGroup() {
		return nearGroup;
	}



	public void setNearGroup(List<ResponseGroup> nearGroup) {
		this.nearGroup = nearGroup;
	}

}
