package main.java.com.yep.pojo;

public class ColumnInfo {
    private Integer columnId;

    private String columnName;

    private Integer columnLevel;

    private Integer columnProperty;

    private String columnDescript;

    private String columnKeyword;

    private Integer contentTemplate;

    private Integer coverTemplate;
    
    private Integer columnParentId;

    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public Integer getColumnLevel() {
        return columnLevel;
    }

    public void setColumnLevel(Integer columnLevel) {
        this.columnLevel = columnLevel;
    }

    public Integer getColumnProperty() {
        return columnProperty;
    }

    public void setColumnProperty(Integer columnProperty) {
        this.columnProperty = columnProperty;
    }

    public String getColumnDescript() {
        return columnDescript;
    }

    public void setColumnDescript(String columnDescript) {
        this.columnDescript = columnDescript == null ? null : columnDescript.trim();
    }

    public String getColumnKeyword() {
        return columnKeyword;
    }

    public void setColumnKeyword(String columnKeyword) {
        this.columnKeyword = columnKeyword == null ? null : columnKeyword.trim();
    }

    public Integer getContentTemplate() {
        return contentTemplate;
    }

    public void setContentTemplate(Integer contentTemplate) {
        this.contentTemplate = contentTemplate;
    }

    public Integer getCoverTemplate() {
        return coverTemplate;
    }

    public void setCoverTemplate(Integer coverTemplate) {
        this.coverTemplate = coverTemplate;
    }
    
    
    public Integer getColumnParentId() {
        return columnParentId;
    }

    public void setColumnParentId(Integer columnParentId) {
        this.columnParentId = columnParentId;
    }

    public String toString(){
        return columnId+columnName+columnLevel+columnLevel+columnProperty+columnDescript+columnKeyword+contentTemplate+coverTemplate;
    }
}