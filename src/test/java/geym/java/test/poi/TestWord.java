package geym.java.test.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;

public class TestWord {
    @Test
    public void test2003(){
        File file = null;
        WordExtractor extractor = null;
        try
        {

            file = new File("E:\\txt\\5.垃圾收集器和内存分配.doc");
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            HWPFDocument document = new HWPFDocument(fis);
            extractor = new WordExtractor(document);
            String[] fileData = extractor.getParagraphText();
            for (int i = 0; i < fileData.length; i++)
            {
                if (fileData[i] != null)
                    System.out.println(fileData[i]);
            }
        }
        catch (Exception exep)
        {
            exep.printStackTrace();
        }finally{
            if(extractor!=null)
                try {
                    extractor.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    
    @Test
    public void test2010(){
        File file = null;
        XWPFWordExtractor extractor = null;
        try
        {
            FileInputStream fis = new FileInputStream("E:\\txt\\初识JVM.docx");
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
            extractor = new XWPFWordExtractor(xdoc);
            System.out.println(extractor.getText());
        }
        catch (Exception exep)
        {
            exep.printStackTrace();
        }finally{
            if(extractor!=null)
                try {
                    extractor.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
