package geym.java.training.ch12.hw;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class FileBean {
    private String filename;
    private String absolutePath;
    
    public FileBean(String filename, String absolutePath) {
        super();
        this.filename = filename;
        this.absolutePath = absolutePath;
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

    public void open() {
        Desktop desk=Desktop.getDesktop();  
        try {
            desk.open(new File(absolutePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
