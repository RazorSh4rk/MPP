package librarysystem;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class MainWindow extends JFrame {
	// admin
	// librarian
	// both
	public MainWindow(String accessRight) {
		super("Leabharlann - [" + accessRight + "]");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640	, 640);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
        
        var menuItems = new ArrayList<String>();
        if(accessRight.equals("admin")) {
        	menuItems.add("Add new book");
        	menuItems.add("Add new member");
        	menuItems.add("Add copy of a book");
        	menuItems.add("exit");
        } else if(accessRight.equals("librarian")) {
        	menuItems.add("Check out book");
        	menuItems.add("Get user record");
        	menuItems.add("exit");
        } else if(accessRight.equals("superuser")){
        	menuItems.add("Add new book");
        	menuItems.add("Add new member");
        	menuItems.add("Add copy of a book");
        	menuItems.add("Check out book");
        	menuItems.add("Get user record");
        	menuItems.add("exit");
        }
        String[] m = menuItems.toArray(new String[menuItems.size()]);
        var links = new JList<String>(m);     
        var cards = new JPanel(new CardLayout());
        
        var checkout = new LibraryCheckoutUI();
        cards.add(checkout, "Check out book");
        
        var jp = new JPanel();
        var jl = new JLabel("new");
        jp.add(jl);
        cards.add(jp, "Add new member");
        
        links.addListSelectionListener(evt -> {
        	var selected = links.getSelectedValue().toString();
        	var cl = (CardLayout) (cards.getLayout());
        	cl.show(cards, selected);
        });
        
        var splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, links, cards);
        splitPane.setDividerLocation(640 - 480);
        add(splitPane, BorderLayout.CENTER);
        
        setVisible(true);
	}

	public static void main(String[] args) {
		new MainWindow("superuser");
	}
}
