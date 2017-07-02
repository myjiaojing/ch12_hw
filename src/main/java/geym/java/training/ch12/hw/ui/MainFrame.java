package geym.java.training.ch12.hw.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame{

    private static final long serialVersionUID = 1069007770252961297L;
    
    protected SearchBar searchBar=new SearchBar();
    protected ResultPanel resultPanel=new ResultPanel();
    
    public MainFrame(String name){
        super(name);
        this.setLayout(new BorderLayout());
        searchBar.parent=this;
        resultPanel.parent=this;
        add(searchBar,BorderLayout.NORTH);
        add(resultPanel,BorderLayout.CENTER);
    }

}
