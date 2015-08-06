package operation.pojo.category;

import org.springframework.data.annotation.Id;

import tools.Config;

public class Category {
	@Id
	private String id;
	
	private String categoryType;  //  分类类型  0：一级分类  1： 二级分类  
	 
	private String parentId;  //二级分类时，所属的一级分类Id
	
	private String categoryName;  //分类中文描述
	
	private String logoUrl;  //分类图片
	

	public Category(){
		super();
	}
	/**
	 * 一级分类构造方法
	 * @param categoryName
	 * @param logoUrl
	 */
	public Category(String categoryName,String logoUrl){
		super();
		this.categoryName=categoryName;
		this.categoryType=Config.CATEGORY_PRIMARY;
		this.logoUrl=logoUrl;
	}
	
	/**
	 * 二级分类构造方法
	 * @param categoryName
	 * @param logoUrl
	 * @param parentId
	 */
	public Category(String categoryName,String logoUrl,String parentId){
		super();
		this.categoryName=categoryName;
		this.categoryType=Config.CATEGORY_SENCOND;
		this.logoUrl=logoUrl;
		this.parentId=parentId;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	
}
