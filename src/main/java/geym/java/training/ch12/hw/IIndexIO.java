package geym.java.training.ch12.hw;

import java.util.List;

public interface IIndexIO {
    public void init();
    public void write(List<FileBean> list);
    public List<FileBean> find(String findString);
}
