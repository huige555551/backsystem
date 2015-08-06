package operation.controller.oss;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import operation.OssController;
import operation.exception.XueWenServiceException;
import operation.pojo.drycargo.Drycargo;
import operation.pojo.pub.QueryModelMul;
import operation.service.drycargo.DrycargoService;
import operation.service.schedule.ScheduleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.Config;
import tools.PageRequestTools;
import tools.ReponseDataTools;
import tools.ResponseContainer;

@RestController
@RequestMapping("/oss/schedule")
@Configuration
public class OssScheduleController extends OssController{
	@Autowired
	public ScheduleService scheduleService;

	public OssScheduleController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 查询干货列表 包括没有关联群组的
	 * 
	 * @param request
	 * @param dm
	 */
	@RequestMapping("addIndex")
	public @ResponseBody void addIndex(HttpServletRequest request) {
			scheduleService.schedule();
		
	}
}
