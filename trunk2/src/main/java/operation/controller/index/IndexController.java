package operation.controller.index;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import operation.BaseController;
import operation.exception.XueWenServiceException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.Config;
import tools.ResponseContainer;
import tools.StringUtil;

@RestController
@RequestMapping("/")
public class IndexController extends BaseController{
	@RequestMapping("")
	public void  create(HttpServletRequest request,HttpServletResponse response) {
		try {
			OutputStream out = response.getOutputStream();
			String s="<html><body>success</body></html>";
			byte[] b=s.getBytes();
			out.write(b);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
