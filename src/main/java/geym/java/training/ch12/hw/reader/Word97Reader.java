package geym.java.training.ch12.hw.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.springframework.stereotype.Component;

@Component("docReader")
public class Word97Reader implements IContentReader {
    public String readContent(String url) {
        File file = null;
        WordExtractor extractor = null;
        StringBuffer sb=new StringBuffer();
        try
        {
            file = new File(url);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            HWPFDocument document = new HWPFDocument(fis);
            extractor = new WordExtractor(document);
            String[] fileData = extractor.getParagraphText();
            for (int i = 0; i < fileData.length; i++)
            {
                if (fileData[i] != null)
                    sb.append((fileData[i]));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            if(extractor!=null)
                try {
                    extractor.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return sb.toString();
    }

}
