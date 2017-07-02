package geym.java.training.ch12.hw;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import geym.java.training.ch12.hw.db.bean.FileDbBean;
import geym.java.training.ch12.hw.db.bean.ParticipleBean;
import geym.java.training.ch12.hw.parter.IParticiple;
import geym.java.training.ch12.hw.reader.ContentReaderManager;
import geym.java.training.ch12.hw.reader.IContentReader;

@Component
public class FileContentIndexTask {
    @Autowired
    ContentReaderManager crm;
    @Autowired
    IParticiple parter;

    public ContentReaderManager getCrm() {
        return crm;
    }

    public void setCrm(ContentReaderManager crm) {
        this.crm = crm;
    }

    public IParticiple getParter() {
        return parter;
    }

    public void setParter(IParticiple parter) {
        this.parter = parter;
    }

    protected String getSuffix(String filename) {
        int i = filename.lastIndexOf('.');
        if(i<0 || i+1>=filename.length())return null;
        return filename.substring(i+1);
    }

    public List<ParticipleBean> index(FileDbBean fileDbBean) {
        List<ParticipleBean> re = new ArrayList<ParticipleBean>();
        String suffix = getSuffix(fileDbBean.getFilename());
        if(suffix==null)return null;
        IContentReader reader = crm.getReader(suffix);
        if(reader==null){
            return null;
        }
        String content = reader.readContent(fileDbBean.getAbsolutePath());
        List<String> listWords = parter.split(content);
        for (String word : listWords) {
            ParticipleBean pb = new ParticipleBean(word, fileDbBean.getId());
            re.add(pb);
        }
        return re;
    }
}
