package geym.java.training.ch12.hw.db.bean;

import geym.java.training.ch12.hw.FileBean;

public class FileDbBean {
    private int id;
    private String filename;
    private String absolutePath;
    private long updateTime;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getAbsolutePath() {
        return absolutePath;
    }
    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }
    public long getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
   
    public static FileDbBean fromFileBean(FileBean file){
        FileDbBean re=new FileDbBean();
        re.setFilename(file.getFilename());
        re.setAbsolutePath(file.getAbsolutePath());
        re.setUpdateTime(System.currentTimeMillis());
        return re;
    }
    
    public FileBean toFileBean(){
        FileBean re=new FileBean(getFilename(),getAbsolutePath());
        return re;
    }
}
