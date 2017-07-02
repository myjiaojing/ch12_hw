package geym.java.training.ch12.hw;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.h2.store.fs.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import geym.java.training.ch12.hw.db.bean.FileDbBean;
import geym.java.training.ch12.hw.db.bean.ParticipleBean;
import geym.java.training.ch12.hw.db.dao.FileDbDao;
import geym.java.training.ch12.hw.db.dao.ParticipleDao;
import geym.java.training.ch12.hw.parter.IParticiple;

@Component("dbIndexIO")
public class FullTextIndexIO implements IIndexIO {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private FileContentIndexTask fileContenttask;
    @Autowired
    private IParticiple parter;

    public FileContentIndexTask getFileContenttask() {
        return fileContenttask;
    }

    public void setFileContenttask(FileContentIndexTask fileContenttask) {
        this.fileContenttask = fileContenttask;
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void write(List<FileBean> list) {
        SqlSession session = sqlSessionFactory.openSession();
        FileDbDao fileDbDao = session.getMapper(FileDbDao.class);
        ParticipleDao participleDao = session.getMapper(ParticipleDao.class);
        try {
            fileDbDao.truncate();
            participleDao.truncate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (FileBean file : list) {
            try {
                if (FileUtils.isDirectory(file.getAbsolutePath())) {
                    continue;
                }
                FileDbBean fileDbBean = FileDbBean.fromFileBean(file);
                fileDbDao.insert(fileDbBean);
                List<ParticipleBean> ps = fileContenttask.index(fileDbBean);
                if (ps == null)
                    continue;
                for (ParticipleBean p : ps) {
                    if (participleDao.countByWordAndFileId(p) == 0) {
                        participleDao.insert(p);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                session.rollback();
            }
        }
        session.commit();
        session.close();
    }

    public List<FileBean> find(String findString) {
        SqlSession session = sqlSessionFactory.openSession();
        FileDbDao fileDbDao = session.getMapper(FileDbDao.class);
        ParticipleDao participleDao = session.getMapper(ParticipleDao.class);
        List<String> findStrings = parter.split(findString);
        try {
            Set<Integer> idList = null;
            for (String str : findStrings) {
                if (idList == null ) {
                    idList = participleDao.find(str);
                } else {
                    idList.retainAll(participleDao.find(str));
                }
            }

            List<FileDbBean> fileDbBeanList = new ArrayList<FileDbBean>(idList.size());
            for (Integer id : idList) {
                fileDbBeanList.add(fileDbDao.findFileById(id));
            }
            List<FileBean> re = new ArrayList<FileBean>(fileDbBeanList.size());
            for (FileDbBean fileDbBean : fileDbBeanList) {
                re.add(fileDbBean.toFileBean());
            }
            return re;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @PostConstruct
    public void init() {
        SqlSession session = sqlSessionFactory.openSession();
        FileDbDao fileDbDao = session.getMapper(FileDbDao.class);
        ParticipleDao participleDao = session.getMapper(ParticipleDao.class);
        try {
            fileDbDao.createTable();
            participleDao.createTable();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
