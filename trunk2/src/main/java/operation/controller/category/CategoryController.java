package operation.controller.category;

import javax.servlet.http.HttpServletRequest;

import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.category.Category;
import operation.service.category.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.Config;
import tools.ResponseContainer;

@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController{
	@Autowired
	public CategoryService categoryService;
	
	
	public CategoryController() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 创建一级分类
	 * @param request
	 * @param category
	 * @return
	 */
	@RequestMapping("createPrimary")
	public @ResponseBody ResponseContainer createPrimary(HttpServletRequest request) {
		try {
			String categoryName = request.getParameter("categoryName");
			String logoUrl = request.getParameter("logoUrl");
			return addResponse(Config.STATUS_200, Config.MSG_CREATE_200,
					categoryService.createPrimary(categoryName, logoUrl), Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}
	}
	/**
	 * 创建二级分类
	 * @param request
	 * @return
	 */
	@RequestMapping("createSecond")
	public @ResponseBody ResponseContainer createSecond(HttpServletRequest request) {
		try {
			String categoryName = request.getParameter("categoryName");
			String logoUrl = request.getParameter("logoUrl");
			String parentId = request.getParameter("parentId");
			return addResponse(Config.STATUS_200, Config.MSG_CREATE_200,
					categoryService.createSecond(categoryName, logoUrl, parentId), Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}
	}
	
	/**
	 * 修改分类信息，只能修改分类的中文描述，和默认图片
	 * @param request
	 * @param category
	 * @return
	 */
	@RequestMapping("update")
	public @ResponseBody ResponseContainer update(HttpServletRequest request,Category category) {
		try {
			return addResponse(Config.STATUS_200, Config.MSG_CREATE_200,
					categoryService.update(category), Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}
	}
	/**
	 * 根据Id查询分类，如为一级分类Id，则返回此一级分类以及其下所有的二级分类 ，如为二级分类，则只返回二级分类数据
	 * @param request
	 * @param category
	 * @return
	 */
	@RequestMapping("one")
	public @ResponseBody ResponseContainer one(HttpServletRequest request,Category category) {
		try {
			String categoryId = request.getParameter("categoryId");
			return addResponse(Config.STATUS_200, Config.MSG_CREATE_200,
					categoryService.formateCategory(categoryService.findOneCategoryById(categoryId)), Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}
	}
	
	/**
	 * 获取所有的一级分类
	 * @param request
	 * @param category
	 * @return
	 */
	@RequestMapping("allPrimary")
	public @ResponseBody ResponseContainer allPrimary(HttpServletRequest request,Category category) {
		try {
			String type = request.getParameter("categoryType");
			return addResponse(Config.STATUS_200, Config.MSG_CREATE_200,
					categoryService.formatePrimaryList(categoryService.findAllPrimary(), type), Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}
	}
	/**
	 * 根据一级分类Id获取此分类下的所有二级分类
	 * @param request
	 * @param category
	 * @return
	 */
	@RequestMapping("allSecond")
	public @ResponseBody ResponseContainer allSecond(HttpServletRequest request,Category category) {
		try {
			String parentId = request.getParameter("parentId");
			String type = request.getParameter("categoryType");
			String groupId = request.getParameter("groupId");
			return addResponse(Config.STATUS_200, Config.MSG_CREATE_200,
					categoryService.formateSecondList(categoryService.findSecondByPrimaryId(parentId), type,groupId), Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}
	}
	
	
	
	/**
	 * 所有分类查询接口
	 * @param request
	 * @param category
	 * @return
	 */
	@RequestMapping("all")
	public @ResponseBody ResponseContainer all(HttpServletRequest request,Category category) {
		try {
			return addResponse(Config.STATUS_200, Config.MSG_CREATE_200,
					categoryService.findCategory(), Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}
	}
	
	
	


}
