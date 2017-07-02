package geym.java.training.ch12.hw.reader;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import geym.java.training.ch12.hw.util.EncodingUtil;

@Component("txtReader")
public class TxtReader implements IContentReader{

    public String readContent(String url) {
        try {
            File file=new File(url);
            return FileUtils.readFileToString(file,EncodingUtil.getFilecharset(file));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
