package operation.service.words;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import operation.exception.XueWenServiceException;
import operation.pojo.words.StopWords;
import operation.repo.words.StopWordsReposity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tools.Config;

/**
 * O
 * @ClassName: StopWordsService
 * @Description: 过滤禁用词库 业务
 * @author Jack Tang
 * @date 2015年2月11日 上午11:07:09
 *
 */
@Service
public class StopWordsService {
	@Autowired
	private StopWordsReposity stopWordsReposity;
	
	
	/**
	 * 
	 * @Title: save
	 * @Description: 插入或更新
	 * @param stopWords void
	 * @throws
	 */
	public void save(StopWords stopWords){
		stopWordsReposity.save(stopWords);
	}
	
	/**
	 * 
	 * @Title: findByWordsIn
	 * @Description: 通过词查找
	 * @param word
	 * @return StopWords
	 * @throws
	 */
	public StopWords findByWordsIn(String word){
		
		return stopWordsReposity.findOneByContentIn(word);
	}
	
	/**
	 * 
	 * @Title: findOne
	 * @Description: 取词库
	 * @return StopWords
	 * @throws
	 */
	public StopWords findOne(){
		List<StopWords> stopWords=stopWordsReposity.findAll();
		return stopWords.size()==0?null:stopWords.get(0);
	}
	
	
	/**
	 * 
	 * @Title: addWords
	 * @Description: 添加词
	 * @param word
	 * @throws XueWenServiceException void
	 * @throws
	 */
	public void addWords(String word ) throws XueWenServiceException{
		if(findByWordsIn(word)!=null){
			throw new XueWenServiceException(Config.STATUS_201, "该词汇已存在", null);
		}else{
			 StopWords stopWord=findOne();
			 if(stopWord!=null){
				stopWord.addWord(word); 
			 }
			 else{
				 stopWord =new StopWords();
				 Set<String> set;
				try {
					set = getwords();
					stopWord.setContent(set);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				
			 }
			 save(stopWord);
			 
		}
		
	}

	public Set<String> getwords() throws Exception, Exception{
		File file=new File("E:/workspace/yunxuetang3_api/src/main/resources/word.txt");
		InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),"UTF-8");//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
				Set<String>set=new HashSet<String>();

                while((lineTxt = bufferedReader.readLine()) != null){
                    set.add(lineTxt);
                }
                read.close();
                
                return set;
	}
	
	public Set<String> getwords(InputStream is) throws Exception, Exception{
		InputStreamReader read = new InputStreamReader(
                is,"UTF-8");//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
				Set<String>set=new HashSet<String>();

                while((lineTxt = bufferedReader.readLine()) != null){
                    set.add(lineTxt);
                }
                read.close();
                
                return set;
	}
	
	
	
	/**
	 * 
	 * @Title: delete
	 * @auther Tangli
	 * @Description: 删除敏感词汇中的一个单词
	 * @param word
	 * @throws XueWenServiceException void
	 * @throws
	 */
	public void delete(String word) throws XueWenServiceException {
		if(findByWordsIn(word)==null){
			throw new XueWenServiceException(Config.STATUS_201, "该词不存在", null);
		}else{
			 StopWords stopWord=findOne();
			 if(stopWord!=null){
				stopWord.deleteWord(word);
				save(stopWord);
			 }
		}		
	}
	
	/**
	 * 
	 * @Title: deleteAll
	 * @auther Tangli
	 * @Description: 删除所有词汇
	 * @param word
	 * @throws XueWenServiceException void
	 * @throws
	 */
	public void deleteAll() throws XueWenServiceException {
			 StopWords stopWord=findOne();
			 if(stopWord!=null){
				stopWord.setContent(null);;
				save(stopWord);
			 }
		}
	
	/**
	 * @throws IOException 
	 * @Title: addWordFile
	 * @auther Tangli
	 * @Description: 添加文件
	 * @param myfiles void
	 * @throws
	 */
	public void addWordFile(MultipartFile[] myfiles) throws Exception {
	  if(myfiles!=null&&myfiles.length!=0){
		  for (MultipartFile multipartFile : myfiles) {
			if(!multipartFile.isEmpty()){
				InputStream is=multipartFile.getInputStream();
				Set<String>sets=getwords(is);
				addSets(sets);
			}
		}
	  }
		
	}
	
	/**
	 * 
	 * @Title: addSets
	 * @auther Tangli
	 * @Description: 批量添加
	 * @param set void
	 * @throws
	 */
	public void addSets(Set<String> set){
		 StopWords stopWord=findOne();
		 if(stopWord!=null){
			stopWord.getContent().addAll(set);
			save(stopWord);
		 }
	}
	

}
