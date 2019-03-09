package main.java.com.yep.pojo;

public class MenuInfo {
    private Integer id;

    private String menuName;

    private Integer menuParentid;

    private Integer menuLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public Integer getMenuParentid() {
        return menuParentid;
    }

    public void setMenuParentid(Integer menuParentid) {
        this.menuParentid = menuParentid;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }
}