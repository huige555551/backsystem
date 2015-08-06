package operation.pojo.course;

import java.util.List;

public class CourseShareResponse{
	private Object course;//课程
	
	private List<NewCourse> courseRelevant;//相关课程

	public Object getCourse() {
		return course;
	}

	public void setCourse(Object course) {
		this.course = course;
	}

	public List<NewCourse> getCourseRelevant() {
		return courseRelevant;
	}

	public void setCourseRelevant(List<NewCourse> courseRelevant) {
		this.courseRelevant = courseRelevant;
	}


	
}