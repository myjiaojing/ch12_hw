package geym.java.training.ch12.hw.db.bean;

public class ParticipleBean {
    private int id;
    private String word;
    private int fileid;
    
    public ParticipleBean(String word, int fileid) {
        super();
        this.word = word;
        this.fileid = fileid;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public int getFileid() {
        return fileid;
    }
    public void setFileid(int fileid) {
        this.fileid = fileid;
    }
    
}
