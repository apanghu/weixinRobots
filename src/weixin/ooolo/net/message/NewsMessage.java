package weixin.ooolo.net.message;

import java.util.List;

import weixin.ooolo.net.entity.NewsBase;

public class NewsMessage extends BaseMessage {
	 private int ArticleCount;
	    private List<NewsBase> Articles;
	 
	    public int getArticleCount() {
	        return ArticleCount;
	    }
	 
	    public void setArticleCount(int articleCount) {
	        ArticleCount = articleCount;
	    }
	 
	    public List<NewsBase> getArticles() {
	        return Articles;
	    }
	 
	    public void setArticles(List<NewsBase> articles) {
	        Articles = articles;
	    }

}
