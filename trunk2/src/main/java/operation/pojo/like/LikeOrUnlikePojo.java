package operation.pojo.like;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="likeOrUnlikePojo")
public class LikeOrUnlikePojo {
	@Id
	private String id;
	private String domain;
	@Indexed
	private String type;// subject,image,share
	@Indexed
	private String typeId;
	private int likeCount;
	private int unLikeCount;

	private List<Object> whoLiked;
	private List<Object> whoUnLiked;

	private Long ctime;
	private Long utime;

	public LikeOrUnlikePojo() {
		super();
		this.setCtime(System.currentTimeMillis());
		this.setUtime(System.currentTimeMillis());
	}

	public boolean setIncLike() {
		this.setLikeCount(this.getLikeCount() + 1);
		return true;
	}

	public boolean setDecLike() {
		this.setLikeCount(this.getLikeCount() - 1);
		return true;
	}

	public boolean addWhoLiked(String userId) {
		List<Object> whoLiked = null;
		if (this.getWhoLiked().contains(userId)) {
			return false;
		} else {
			if (null == this.getWhoLiked() || this.getWhoLiked().size() <= 0) {
				whoLiked = new ArrayList<Object>();
			} else {
				whoLiked = this.getWhoLiked();
			}
			whoLiked.add(userId);
			this.setWhoLiked(whoLiked);
			this.setIncLike();
			if (this.getWhoUnLiked().contains(userId)) {
				this.getWhoUnLiked().remove(userId);
				this.setWhoUnLiked(this.getWhoUnLiked());
				this.setDecUnLike();
			}
			return true;
		}
	}

	public boolean addWhoUnLiked(String userId) {
		List<Object> whoUnLiked = null;
		if (this.getWhoUnLiked().contains(userId)) {
			return false;
		} else {
			if (null == this.getWhoUnLiked()
					|| this.getWhoUnLiked().size() <= 0) {
				whoUnLiked = new ArrayList<Object>();
			} else {
				whoUnLiked = this.getWhoUnLiked();
			}
			whoUnLiked.add(userId);
			this.setWhoUnLiked(whoUnLiked);
			this.setIncUnLike();
			if (this.getWhoLiked().contains(userId)) {
				this.getWhoLiked().remove(userId);
				this.setWhoLiked(this.getWhoLiked());
				this.setDecLike();
			}
			return true;
		}
	}

	public boolean setIncUnLike() {
		this.setUnLikeCount(this.getUnLikeCount() + 1);
		return true;
	}

	public boolean setDecUnLike() {
		this.setUnLikeCount(this.getUnLikeCount() - 1);
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getUnLikeCount() {
		return unLikeCount;
	}

	public void setUnLikeCount(int unLikeCount) {
		this.unLikeCount = unLikeCount;
	}

	public List<Object> getWhoLiked() {
		if (null == whoLiked || whoLiked.size() <= 0) {
			return new ArrayList<Object>();
		} else {
			return whoLiked;
		}
	}

	public void setWhoLiked(List<Object> whoLiked) {
		this.whoLiked = whoLiked;
	}

	public List<Object> getWhoUnLiked() {
		if (null == whoUnLiked || whoUnLiked.size() <=0) {
			return new ArrayList<Object>();
		} else {
			return whoUnLiked;
		}
	}

	public void setWhoUnLiked(List<Object> whoUnLiked) {
		this.whoUnLiked = whoUnLiked;
	}

	public Long getCtime() {
		return ctime;
	}

	public void setCtime(Long ctime) {
	}

	public Long getUtime() {
		return utime;
	}

	public void setUtime(Long utime) {
		this.utime = utime;
	}

}
