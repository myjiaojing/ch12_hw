package geym.java.training.ch12.hw.db.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import geym.java.training.ch12.hw.db.bean.ParticipleBean;

public interface ParticipleDao {
    @Select("SELECT fileid FROM invertedIndex WHERE word = #{keyword}")
    Set<Integer> find(@Param("keyword") String keyword);
    
    @Insert("insert into invertedIndex(word,fileid) values(#{word},#{fileid})")
    void insert(ParticipleBean Participle);
    
    @Select("select count(*) from invertedIndex  where word=#{word} and fileid=#{fileid}")
    int countByWordAndFileId(ParticipleBean Participle);
    
    @Insert("Create table IF NOT EXISTS invertedIndex"
            + "(id int AUTO_INCREMENT PRIMARY KEY," 
            + "word varchar(100)," 
            + "fileid int);"
            + "Create UNIQUE index IF NOT EXISTS word_fileid_index on invertedIndex(word,fileid);")
    public void createTable();
    
    @Insert("TRUNCATE TABLE invertedIndex")
    public void truncate();
}
