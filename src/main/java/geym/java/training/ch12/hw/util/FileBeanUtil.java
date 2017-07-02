package geym.java.training.ch12.hw.util;

import geym.java.training.ch12.hw.FileBean;

public class FileBeanUtil {
    public static String fileBeanToString(FileBean file){
        StringBuffer sb=new StringBuffer();
        sb.append(file.getFilename()).append("\t").append(file.getAbsolutePath()).append("\n");
        return sb.toString();
    }
}
