package main.java.com.yep.pojo;

public class ResultJson {
    /**
     * 模块编号
     */
    private String code;
    
    /**
     * 返回状态:true成功 false:失败
     */
    private boolean result;
    
    /**
     * 返回信息提示
     */
    private String resultMsg;
    
    
    /**
     * 返回数据
     */
    private Object resultData = "";

    /**
     * 返回后跳的地址
     */
    private String url;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
