package geym.java.training.ch12.hw.reader;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;

@Component("docxReader")
public class Word2010Reader implements IContentReader {
    public String readContent(String url) {
        XWPFWordExtractor extractor = null;
        FileInputStream fis = null;
        StringBuffer sb = new StringBuffer();
        try {
            fis = new FileInputStream(url);
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
            extractor = new XWPFWordExtractor(xdoc);
            sb.append(extractor.getText());
        } catch (Exception exep) {
            exep.printStackTrace();
        } finally {
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (extractor != null)
                try {
                    extractor.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return sb.toString();
    }

}
