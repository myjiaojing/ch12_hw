package geym.java.training.ch12.hw;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import geym.java.training.ch12.hw.ui.MainFrame;

public class FileFinderMain {
    public static ApplicationContext context;

    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("spring.xml");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    private static void createAndShowGUI() {
        JFrame f = new MainFrame("File Searcher Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500,500);
        //居中
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
