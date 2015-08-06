package operation.pojo.course;

import java.util.List;

import operation.pojo.topics.PostResponse;

public class TopicShareResponse{
	private Object topicResponse;//课程
	
	private List<PostResponse> postResponse;//相关课程

	public Object getTopicResponse() {
		return topicResponse;
	}

	public void setTopicResponse(Object topicResponse) {
		this.topicResponse = topicResponse;
	}

	public List<PostResponse> getPostResponse() {
		return postResponse;
	}

	public void setPostResponse(List<PostResponse> postResponse) {
		this.postResponse = postResponse;
	}



	
}