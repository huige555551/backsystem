package operation.controller.oss;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.category.Category;
import operation.pojo.pub.QueryModel;
import operation.service.category.CategoryService;
import tools.Config;
import tools.PageRequestTools;
import tools.ReponseDataTools;
import tools.ResponseContainer;
@RestController
@RequestMapping(value="oss/category")
public class OssCategoryController extends BaseController{
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 
	 * @Title: getSuperCategory
	 * @Description: 上传hyhnjhn
	 * @param dm
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping("getSuperCategory")
	public ResponseContainer getSuperCategory(QueryModel  dm){
		dm.setS(999);
		Pageable pageable=PageRequestTools.pageRequesMake(dm);
		Page<Category> categorys=categoryService.getSuperCategory(pageable);
		if(categorys!=null){
			ReponseDataTools.getClientReponseData(getReponseData(), categorys);
		}
			return addPageResponse(Config.STATUS_200, Config.MSG_200, getReponseData(), Config.RESP_MODE_10, "");
	}
	
	@RequestMapping("getSecondCategory")
	public ResponseContainer getSecondCategory(QueryModel  dm,String id){
		Pageable pageable=PageRequestTools.pageRequesMake(dm);
		Page<Category> categorys=categoryService.getSecondCategory(pageable,id);
		if(categorys!=null){
			ReponseDataTools.getClientReponseData(getReponseData(), categorys);
		}
			return addPageResponse(Config.STATUS_200, Config.MSG_200, getReponseData(), Config.RESP_MODE_10, "");
	}
	
	@RequestMapping("deleteSecond")
	public ResponseContainer deleteSecond(HttpServletRequest request){
		String postId=request.getParameter("categoryid");
		 
		categoryService.deleteSecondCategory(postId);
		 
			return addResponse(Config.STATUS_200, Config.MSG_200, true, Config.RESP_MODE_10, "");
	}
	
	
	@RequestMapping("changeSecond")
	public ResponseContainer changeSecond(HttpServletRequest request){
		String oldcategoryid=request.getParameter("oldcategoryid");
		String newcategoryid=request.getParameter("newcategoryid");
		 
		try {
			categoryService.changeSecondCategory(oldcategoryid,newcategoryid);
		} catch (XueWenServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
			return addResponse(Config.STATUS_200, Config.MSG_200, true, Config.RESP_MODE_10, "");
	}
	
	
}
