package tools;


import org.springframework.data.domain.Page;

public class ReponseDataTools {

	/**
	 * 分页修正
	 * @param rsData
	 * @param pl
	 * @return
	 */
	public static ReponseData getClientReponseData(ReponseData rsData,Page<? extends Object> pl)
	{
		 rsData.resetPageInfo();//清除上一次分页相关的信息
		if (pl.getTotalElements() == 0) {
			rsData.setCurr_page(pl.getNumber());
			int curr_rows=pl.getNumber()*pl.getSize();
			if(curr_rows>pl.getTotalElements()){
				curr_rows=new Long(pl.getTotalElements()).intValue();
				rsData.setCurr_rows(curr_rows);
			}else{
				rsData.setCurr_rows(curr_rows);
			}
		} else {
			rsData.setCurr_page(pl.getNumber() + 1);
			int curr_rows=(pl.getNumber()+1)*pl.getSize();
			if(curr_rows>pl.getTotalElements()){
				curr_rows=new Long(pl.getTotalElements()).intValue();
				rsData.setCurr_rows(curr_rows);
			}else{
				rsData.setCurr_rows(curr_rows);
			}
		}
		rsData.setTotal_rows(pl.getTotalElements());
		rsData.setPage_rows(pl.getTotalPages());
		rsData.setResult(pl.getContent());
		
		return rsData;
	}
}
