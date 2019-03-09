package main.java.com.yep.pojo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


public class ArticleBean {
    /**
     * total : 1
     * row : [{"id":1,"articleCategoryId":1,"articleTitle":"今日新闻","articleDateTime":"2019-2-22","articleAuthor":"小萨"}]
     */

    private int total;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * id : 1
         * articleCategoryId : 1
         * articleTitle : 今日新闻
         * articleDateTime : 2019-2-22
         * articleAuthor : 小萨
         * articleDescription : 小猪排队跳水沟
         * articleContent : 新闻内容
         * articleCategoryName
         * articleKeyWord
         * articleImageUrl
         */

        private Integer id;

        private Integer articlecategoryid;

        private String articletitle;

        private String articledescription;

        private Date articledatetime;

        private String articleauthor;

        private String articlecontent;
        
        private String articleCategoryName;
        
        private String articlekeyword;
        
        private String articleimageurl;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getArticlecategoryid() {
            return articlecategoryid;
        }

        public void setArticlecategoryid(Integer articlecategoryid) {
            this.articlecategoryid = articlecategoryid;
        }

        public String getArticletitle() {
            return articletitle;
        }

        public void setArticletitle(String articletitle) {
            this.articletitle = articletitle == null ? null : articletitle.trim();
        }

        public String getArticledescription() {
            return articledescription;
        }

        public void setArticledescription(String articledescription) {
            this.articledescription = articledescription == null ? null : articledescription.trim();
        }

        public Date getArticledatetime() {
            return articledatetime;
        }

        public void setArticledatetime(Date articledatetime) {
            this.articledatetime = articledatetime;
        }

        public String getArticleauthor() {
            return articleauthor;
        }

        public void setArticleauthor(String articleauthor) {
            this.articleauthor = articleauthor == null ? null : articleauthor.trim();
        }

        public String getArticlecontent() {
            return articlecontent;
        }

        public void setArticlecontent(String articlecontent) {
            this.articlecontent = articlecontent == null ? null : articlecontent.trim();
        }

        public String getArticleCategoryName() {
            return articleCategoryName;
        }

        public void setArticleCategoryName(String articleCategoryName) {
            this.articleCategoryName = articleCategoryName;
        }

        public String getArticlekeyword() {
            return articlekeyword;
        }

        public void setArticlekeyword(String articlekeyword) {
            this.articlekeyword = articlekeyword;
        }

        public String getArticleimageurl() {
            return articleimageurl;
        }

        public void setArticleimageurl(String articleimageurl) {
            this.articleimageurl = articleimageurl;
        }
        
        
    }
    
}
