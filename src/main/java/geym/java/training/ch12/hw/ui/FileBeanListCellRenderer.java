package geym.java.training.ch12.hw.ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import geym.java.training.ch12.hw.FileBean;

public class FileBeanListCellRenderer extends JLabel implements ListCellRenderer<FileBean>{

    private static final long serialVersionUID = -7487563761051409268L;

    public FileBeanListCellRenderer() {
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList<? extends FileBean> list, FileBean value, int index,
            boolean isSelected, boolean cellHasFocus) {

        setText(value.getAbsolutePath());

        Color background;
        Color foreground;

        // check if this cell represents the current DnD drop location
        JList.DropLocation dropLocation = list.getDropLocation();
        if (dropLocation != null
                && !dropLocation.isInsert()
                && dropLocation.getIndex() == index) {

            background = Color.BLUE;
            foreground = Color.WHITE;

        // check if this cell is selected
        } else if (isSelected) {
            background = Color.RED;
            foreground = Color.WHITE;

        // unselected, and not the DnD drop location
        } else {
            background = Color.WHITE;
            foreground = Color.BLACK;
        };

        setBackground(background);
        setForeground(foreground);

        return this;
    }

}
