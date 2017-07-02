package geym.java.training.ch12.hw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import geym.java.training.ch12.hw.util.FileBeanUtil;

public class FileIndexIO implements IIndexIO {
    private String indexFilePath;

    public String getIndexFilePath() {
        return indexFilePath;
    }

    public void setIndexFilePath(String indexFilePath) {
        this.indexFilePath = indexFilePath;
    }

    public void write(List<FileBean> list) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(indexFilePath);
            for (FileBean file : list) {
                String str = FileBeanUtil.fileBeanToString(file);
                fw.write(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<FileBean> find(String findString) {
        BufferedReader fr = null;
        if (findString == null || findString.trim().equals("")) {
            return null;
        }
        List<FileBean> re = new Vector<FileBean>();
        try {
            fr = new BufferedReader(new FileReader(indexFilePath));
            String line;
            while ((line = fr.readLine()) != null) {
                String[] tokens = line.split("\\t");
                if (tokens.length < 2)
                    continue;
                if (tokens[0].indexOf(findString) >= 0) {
                    re.add(new FileBean(tokens[0], tokens[1]));
                }
            }
            return re;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public void init() {
    }

}
