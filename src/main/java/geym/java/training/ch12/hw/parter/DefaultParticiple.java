package geym.java.training.ch12.hw.parter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;

@Component
public class DefaultParticiple implements IParticiple {
    private Set<String> ignoreWords=new HashSet<String>();

    public DefaultParticiple() {
        try {
            InputStream inStream = DefaultParticiple.class.getResourceAsStream("ignore.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
            int c = 0;
            while ((c = reader.read()) != -1) {
                ignoreWords.add(Character.toString((char) c));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> split(String conent) {
        List<String> re = new ArrayList<String>();
        StringReader reader = new StringReader(conent);
        IKSegmentation ik = new IKSegmentation(reader, false);
        Lexeme lexeme = null;
        try {
            while ((lexeme = ik.next()) != null) {
                String word = lexeme.getLexemeText();
                if(!ignoreWords.contains(word)){
                    re.add(word);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re;
    }
}
