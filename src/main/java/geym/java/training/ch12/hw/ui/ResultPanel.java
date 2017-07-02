package geym.java.training.ch12.hw.ui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import geym.java.training.ch12.hw.FileBean;

public class ResultPanel extends JPanel {
    MainFrame parent;
    DefaultListModel<FileBean> listModel = new DefaultListModel<FileBean>();
    ListCellRenderer<FileBean> render = new FileBeanListCellRenderer();
    final JList<FileBean> list = new JList<FileBean>(listModel);
    JScrollPane scrollPane = new JScrollPane();

    public ResultPanel() {
        this.setLayout(new BorderLayout());
        list.setCellRenderer(render);
        scrollPane.setViewportView(list);
        add(scrollPane, BorderLayout.CENTER);

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (list.getSelectedIndex() != -1) {
                    if (e.getClickCount() == 2) {
                       // System.out.println((list.getSelectedValue()));
                        list.getSelectedValue().open();
                    }
                }
            }
        });

    }
}
