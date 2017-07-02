package geym.java.training.ch12.hw;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import geym.java.training.ch12.hw.db.bean.FileDbBean;
import geym.java.training.ch12.hw.db.dao.FileDbDao;

public class MybatisIndexIO implements IIndexIO {
    private SqlSessionFactory sqlSessionFactory;

    
    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void write(List<FileBean> list) {
        SqlSession session=sqlSessionFactory.openSession();
        FileDbDao fileDbDao=session.getMapper(FileDbDao.class);
        for (FileBean file : list) {
            try {
                FileDbBean fileDbBean = FileDbBean.fromFileBean(file);
                fileDbDao.insert(fileDbBean);
                System.out.println(fileDbBean.getId());
            } catch (Exception e) {
                e.printStackTrace();
                session.rollback();
            }
        }
        session.commit();
        session.close();
    }

    public List<FileBean> find(String findString) {
        SqlSession session=sqlSessionFactory.openSession();
        FileDbDao fileDbDao=session.getMapper(FileDbDao.class);
        try {
            List<FileDbBean> list = fileDbDao.findFile(findString);
            List<FileBean> re = new ArrayList<FileBean>(list.size());
            for (FileDbBean fileDbBean : list) {
                re.add(fileDbBean.toFileBean());
            }
            return re;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return null;
    }

    public void init() {
        SqlSession session=sqlSessionFactory.openSession();
        FileDbDao fileDbDao=session.getMapper(FileDbDao.class);
        try {
            fileDbDao.createTable();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
    }

}
