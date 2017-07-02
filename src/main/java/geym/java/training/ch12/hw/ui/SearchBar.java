package geym.java.training.ch12.hw.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import geym.java.training.ch12.hw.FileBean;
import geym.java.training.ch12.hw.FileFinderMain;
import geym.java.training.ch12.hw.FileIndexTask;
import geym.java.training.ch12.hw.IIndexIO;

public class SearchBar extends JPanel {
    MainFrame parent;
    JTextField txtSearchString = new JTextField("", 24);
    JButton searchBtn = new JButton("搜索");
    JButton indexBtn = new JButton("索引");
    JDialog waitingDailog = new JDialog();

    public SearchBar() {
        FlowLayout layout = new FlowLayout();
        setLayout(layout);
        layout.setAlignment(FlowLayout.LEFT);
        add(txtSearchString);
        add(searchBtn);
        add(indexBtn);

        waitingDailog.setModal(true);
        waitingDailog.add(new JLabel("等待"));
        waitingDailog.pack();
        waitingDailog.setLocationRelativeTo(parent);

        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.resultPanel.listModel.clear();
                SwingWorker searchWorker = new SwingWorker<List<FileBean>, Void>() {
                    @Override
                    protected List<FileBean> doInBackground() throws Exception {
                        IIndexIO indexIO = FileFinderMain.context.getBean(FileIndexTask.class).getIndexIO();
                        List<FileBean> list = indexIO.find(txtSearchString.getText().trim());
                        return list;
                    }

                    protected void done() {

                        List<FileBean> list;
                        try {
                            list = get();
                            if (list == null)
                                return;
                            for (FileBean file : list) {
                                parent.resultPanel.listModel.addElement(file);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } finally {
                            waitingDailog.setVisible(false);
                        }

                    }

                };
                searchWorker.execute();
                waitingDailog.setVisible(true);
            }
        });

        indexBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    public void run() {
                        FileFinderMain.context.getBean(FileIndexTask.class).indexDir();
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                JOptionPane.showMessageDialog(parent, "索引重建成功");
                            }
                        });

                    }
                }.start();

            }
        });
    }
}
