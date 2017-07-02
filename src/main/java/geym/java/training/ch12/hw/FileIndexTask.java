package geym.java.training.ch12.hw;

import java.io.File;
import java.util.List;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FileIndexTask implements IFileIndex {
    private String dirname;
    private Queue<File> fileQueue;
    private IIndexIO indexIO;
    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    public String getDirname() {
        return dirname;
    }

    public void setDirname(String dirname) {
        this.dirname = dirname;
    }

    public Queue<File> getFileQueue() {
        return fileQueue;
    }

    public void setFileQueue(Queue<File> fileQueue) {
        this.fileQueue = fileQueue;
    }

    public IIndexIO getIndexIO() {
        return indexIO;
    }

    public void setIndexIO(IIndexIO indexIO) {
        this.indexIO = indexIO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see geym.java.training.ch5.hw.IFileIndex#indexDir()
     */
    public List<FileBean> indexDir() {
        List<FileBean> re = new Vector<FileBean>();
        File root = new File(dirname);
        FolderSearchTask task = new FolderSearchTask(root, re);
        forkJoinPool.submit(task);
        task.join();
        indexIO.write(re);
        return re;
    }

    class FolderSearchTask extends RecursiveTask<List<FileBean>> {
        private static final long serialVersionUID = -2150556082854374304L;
        private final File folder;
        private final List<FileBean> re;

        FolderSearchTask(File folder, List<FileBean> re) {
            super();
            this.folder = folder;
            this.re = re;
        }

        @Override
        protected List<FileBean> compute() {
            re.add(new FileBean(folder.getName(), folder.getAbsolutePath()));
            if (folder.isDirectory()) {
                File[] files = folder.listFiles();
                List<FolderSearchTask> tasks = new Vector<FolderSearchTask>();
                for (File file : files) {
                    FolderSearchTask task = new FolderSearchTask(file, re);
                    tasks.add(task);
                    task.fork();
                }
                for (FolderSearchTask task : tasks) {
                    task.join();
                }
            }
            return re;
        }
    }

}
