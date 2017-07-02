package geym.java.test.ik;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;
import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;

import junit.framework.TestCase;

public class TestIk extends TestCase {
    @Test
    public void test() throws IOException{
        String str = "火影忍者漫画";  
        StringReader reader = new StringReader(str);  
        IKSegmentation ik = new IKSegmentation(reader, false);// 当为true时，分词器进行最大词长切分  
        Lexeme lexeme = null;  
        while ((lexeme = ik.next()) != null){  
            System.out.println(lexeme.getLexemeText());  
        }  
    }
}
