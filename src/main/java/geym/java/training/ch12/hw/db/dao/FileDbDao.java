package geym.java.training.ch12.hw.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import geym.java.training.ch12.hw.db.bean.FileDbBean;

public interface FileDbDao {
    @Select("SELECT * FROM files WHERE filename like '%${filename}%'")
    List<FileDbBean> findFile(@Param("filename") String name);
    
    @Select("SELECT * FROM files WHERE id = #{id}")
    FileDbBean findFileById(@Param("id") int id);
    
    @Insert("insert into files(filename,absolutePath,updateTime) values(#{filename},#{absolutePath},#{updateTime})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(FileDbBean file);
    
    @Insert("Create table IF NOT EXISTS files"
            + "(id int AUTO_INCREMENT PRIMARY KEY," 
            + "filename varchar(100)," 
            + "absolutePath varchar(500),"
            + "updateTime bigint);"
            + "Create index IF NOT EXISTS filename_index on files(filename);")
    public void createTable();
    
    @Insert("TRUNCATE TABLE files")
    public void truncate();
}
