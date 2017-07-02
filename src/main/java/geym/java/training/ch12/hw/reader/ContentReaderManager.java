package geym.java.training.ch12.hw.reader;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class ContentReaderManager {
    /**
     * inject from spring
     */
    @Resource(name = "readers")
    private Map<String,IContentReader> readers;
    
    public IContentReader getReader(String suffix){
        return readers.get(suffix);
    }
}
