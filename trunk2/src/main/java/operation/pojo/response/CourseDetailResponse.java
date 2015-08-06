package operation.pojo.response;

import java.util.List;

import operation.pojo.user.UserCourseChapter;

public class CourseDetailResponse {
	
	
	private Object UserCourse;//课程
	
	private List<UserCourseChapter> userCourseChapter;
	
	private boolean  authority;
	
	private boolean  isbuy;
	
	private boolean isowner;

	public boolean isIsowner() {
		return isowner;
	}

	public void setIsowner(boolean isowner) {
		this.isowner = isowner;
	}

	public boolean isIsbuy() {
		return isbuy;
	}

	public void setIsbuy(boolean isbuy) {
		this.isbuy = isbuy;
	}

	public Object getUserCourse() {
		return UserCourse;
	}

	public void setUserCourse(Object userCourse) {
		UserCourse = userCourse;
	}

	public List<UserCourseChapter> getUserCourseChapter() {
		return userCourseChapter;
	}

	public void setUserCourseChapter(List<UserCourseChapter> userCourseChapter) {
		this.userCourseChapter = userCourseChapter;
	}

	public boolean isAuthority() {
		return authority;
	}

	public void setAuthority(boolean authority) {
		this.authority = authority;
	}
	
	
	
	
	

}
