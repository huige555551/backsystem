package operation.pojo.words;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="stopwords")
public class StopWords {
	@Id
	private String id;
	private int type;
	private Set<String> content;
	
	public Set<String> getContent() {
		return content;
	}
	public void setContent(Set<String> content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
	public void addWord(String word){
		if(content!=null){
			content.add(word);
		}else{
			content=new HashSet<String>();
			content.add(word);
		}
	}
	
	public void deleteWord(String word){
		if(content!=null){
			content.remove(word);
		}
	}
	
}
