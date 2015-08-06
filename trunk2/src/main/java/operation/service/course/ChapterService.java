package operation.service.course;

import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.course.Chapter;
import operation.pojo.course.Course;
import operation.repo.course.ChapterRepository;
import operation.repo.course.CourseRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
public class ChapterService {
	private static final Logger logger = Logger.getLogger(ChapterService.class);
	
	@Autowired
	public ChapterRepository chapterRepository;
	
	@Autowired
	public CourseService courseService;
	
	@Autowired
	public CourseRepository courseRepository;
	public ChapterService(){
		super();
	}
	
	public Chapter getChapter(String chapterId)throws XueWenServiceException{
		return chapterRepository.findOneById(chapterId);
	}
	
	/**
	 * 得到章节的时长
	 * @param id
	 * @return
	 * @throws XueWenServiceException
	 */
	public int getChapterTimer(String id)throws XueWenServiceException{
		Chapter ct=chapterRepository.findById(id);
		if(ct !=null){
			return ct.getTimer();
		}else{
			return 0;
		}
	}

	/**
	 * 测试环境假数据，为课程章节生成序号
	 * @throws XueWenServiceException
	 */
//	public void setChapterOrder()throws XueWenServiceException{
//		List<Course> courses=courseRepository.findAll();
//		for(Course c:courses){
//			List<Object> chapters=chapterRepository.findByIdIn(c.getChapters());
//			int i=1;
//			for(Chapter ct:chapters){
//				ct.setOrder(i);
//				i++;
//			}
//			chapterRepository.save(chapters);
//		}
//	}
	/**
	 * 根据多章节ID查询章节集合
	 * @param chapter
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Object> findChapter(List<Object> chapter)throws XueWenServiceException{
			return chapterRepository.findByIdIn(chapter);
			
		}
}
