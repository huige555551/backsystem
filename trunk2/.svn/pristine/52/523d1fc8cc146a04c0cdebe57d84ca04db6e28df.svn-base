package tools;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import operation.pojo.cloudfile.Citem;
import operation.pojo.course.Course;
import operation.pojo.course.Knowledge;
import operation.pojo.course.Lesson;
import operation.pojo.course.NewChapter;
import operation.pojo.course.NewCourse;
import operation.service.course.KnowledgeService;
import operation.service.course.LessonService;
import operation.service.course.NewChapterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csvreader.CsvReader;

@Service
public class ImportUtil {
	@Autowired
	private LessonService lessonService;
	@Autowired
	private KnowledgeService knowledgeService;
	@Autowired
	private NewChapterService newChapterService;
	
	public  Knowledge createKnowledge(Lesson lesson){
		// 2014-12-23 修改knowledge
				Knowledge knowledge = new Knowledge();
				knowledge.setFurl(lesson.getLocalUrl());
				knowledge.setName(lesson.getTitle());
				knowledge.setLogoUrl(lesson.getLogoUrl());
				knowledge.setCuser("54b5b90be4b0d38094124f4d");
				knowledge.setIsPublic(true);
				List<Citem> citems = new ArrayList<Citem>();

				if ("Video".equals(lesson.getType())) {

					knowledge.setKngType(1);
					knowledge.setDuration(lesson.getTimer());
					Citem citem = new Citem();
					citem.setFormat("mp4");
					
					citem.setFurl(lesson.getLocalUrl().replace("_enc.mp4", ".mp4"));
					Citem citem1 = new Citem();
					citem1.setFormat("flv");
					citem1.setFurl(lesson.getLocalUrl().substring(0,
							lesson.getLocalUrl().lastIndexOf(".") + 1)
							+ "flv");
					citems.add(citem1);
					citems.add(citem);

				} else {
					// 文档 类 默认追加两个item
					Citem citem = new Citem();
					citem.setFormat("pdf");
					citem.setCode(0);
					citem.setFurl(lesson.getLocalUrl().substring(0,
							lesson.getLocalUrl().lastIndexOf(".") + 1)
							+ "pdf");

					Citem citem1 = new Citem();
					citem1.setFormat("swf");
					citem1.setCode(0);
					citem1.setFurl(lesson.getLocalUrl().substring(0,
							lesson.getLocalUrl().lastIndexOf(".") + 1)
							+ "swf");
					citems.add(citem);
					citems.add(citem1);

					knowledge.setKngType(2);
					knowledge.setPages(Integer.valueOf(lesson.getTimer()+""));

				}
				// 内容中心获取的知识 默认设置审核不通过
				knowledge.setStatus(Config.KNOWLEDGE_STAT_PROCESS);
				knowledge.setCcode(0);
				knowledge.setCitems(citems);
				// 添加适合pc 和app端的citems
				knowledgeService.addviewcitems(knowledge, citems);
				knowledge.setLogoUrl(lesson.getLogoUrl());
				knowledge.setCtime(lesson.getCtime());
				knowledge.setUtime(System.currentTimeMillis());
				return knowledge;
	}

	
	
	public   void redCsvFile(){
        try {
			CsvReader r = new CsvReader("/Users/tangli/Documents/workspace-sts-3.6.2.RELEASE/yunxuetang_api/src/main/resources/222.csv",',',Charset.forName("GBK"));
			       r.readHeaders();
				   List<NewChapter> chapters=new ArrayList<NewChapter>();
			       while (r.readRecord()) {
				   //按列名读取这条记录的值
				   r.getRawRecord();
				   NewChapter newchapter=null;
				   List<String>lessonIds = null;
				   if(r.get("")=="chapter"){
					   if(newchapter!=null){
						   newchapter.setLessonIds(lessonIds);
						   chapters.add(newchapter);
					   }
					   newchapter=new  NewChapter();
					   lessonIds=new ArrayList<String>();
					   //Todo
				   }else{
					   Lesson lesson=new Lesson();
					   lesson.setTitle(r.get("NAME"));
					   lesson.setIntro(r.get("SUMMARY"));
					   lesson.setLogoUrl(r.get("IMAGEURL"));
					   lesson.setOrder(Integer.valueOf(r.get("ORDERINDEX")));
					   String url = r.get("KNOWLEDGEFILEURL");
					   lesson.setLocalUrl(url);
					   lesson.setType(r.get("CoursewareItemType"));
					   lesson.setLength(Long.valueOf(r.get("Bytes")));
					   String timeString = r.get("FileSize");
						if ("Video".equals(lesson.getType())) {
						//	String timeString = r.get("FileSize");
							lesson.setTimer(DateUtil.time2Long(timeString));
						} else {
							timeString=timeString.replaceAll("页", "").trim();
							timeString=timeString.replaceAll("码", "").trim();
							lesson.setTimer(Integer.valueOf(timeString));
							lesson.setCtime(System.currentTimeMillis());
						}
						Knowledge knowledge=createKnowledge(lesson);
						knowledgeService.save(knowledge);
						lesson.setKnowledge(knowledge.getId());
						lessonService.save(lesson);
						lessonIds.add(lesson.getId());
						
				   }
				   newChapterService.save(chapters);
				   NewCourse course=new NewCourse();
				   course.setTitle("import");
				   List<Object>idsList=new ArrayList<Object>();
				   for (NewChapter newChapter2 : chapters) {
					idsList.add(newChapter2.getId());
				}
				   course.setChapters(idsList);
				    }
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}

	
}
