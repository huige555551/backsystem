package operation.service.live;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import operation.exception.XueWenServiceException;
import operation.pojo.live.VhallWebinar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import tools.StringUtil;


@Service
public class VhallWebinarService {
	
	private final static Logger l=Logger.getLogger(VhallWebinarService.class);
	@Value("${vh.url}")
	private String vhUrl;
	@Value("${vh.uid}")
	private String uid;
	@Value("${vh.ukey}")
	private String ukey;
	

	/**
	 * 根据房间号查询出
	 * @param webinar_id
	 * @throws XueWenServiceException
	 */
	public VhallWebinar getVhLiveInfo(String webinar_id)throws Exception{
		//建立HttpPost对象
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		//建立一个NameValuePair数组，用于存储欲传送的参数
		params.add(new BasicNameValuePair("op","webinarinfo"));
		params.add(new BasicNameValuePair("uid",uid));
		params.add(new BasicNameValuePair("ukey",ukey));
		params.add(new BasicNameValuePair("webinar_id",webinar_id));
		params.add(new BasicNameValuePair("fields","subject,t_start,t_end,host,exist_3rd_auth,auth_url,auth_failure_url,webinar_desc,channel_pass,exist_3rd_auth,auth_url,auth_failure_url"));
		String  responseBody =this.sendPost(vhUrl, params);
		if(!StringUtil.isBlank(responseBody)){
			JSONObject j=JSONObject.fromObject(responseBody);
			if(j!=null){
				int  code=j.getInt("code");
				if(code == 1){
					String vhStr=j.getString("data");
					JSONObject vh=JSONObject.fromObject(vhStr);
					return (VhallWebinar)JSONObject.toBean(vh,VhallWebinar.class);
				}
			}
		}
		return null;
		

	}
	/**
	 * 发送Post请求
	 * @param url
	 * @param params
	 * @throws Exception
	 */
	public String sendPost(String url,List<NameValuePair> params)throws Exception{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost post=new HttpPost(url);
		try {
			//添加参数
			post.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
		} catch (UnsupportedEncodingException e1) {
			l.error("=======");
		}
		ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
            public String handleResponse(
                    final HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }
        };
//		String responseBody;
		return httpclient.execute(post,responseHandler);
//			if(!StringUtil.isBlank(responseBody)){
//				JSONObject j=JSONObject.fromObject(responseBody);
//				if(j!=null){
//					JSONObject f=j.getJSONObject("format");
//					if(f!=null){
//						Object s=f.get("size"); //视频文件大小
//						Object d=f.get("duration");//视频时长
//						if(s!=null){
//						}
//						if(d !=null){
//							String dur=d.toString();
//							int end=dur.indexOf(".");
//						}
//					}
//				}
//				
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}


}
