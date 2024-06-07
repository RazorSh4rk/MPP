package librarysystem;

import business.SystemController;
import dataaccess.Auth;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class MainWindow extends JFrame {
	private static final LibWindow login = LoginWindow.INSTANCE;

	public MainWindow(String accessRight) {
		super("Leabharlann - [" + accessRight + "]");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640	, 640);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
        
        var menuItems = new ArrayList<String>();
        if(SystemController.currentAuth == Auth.ADMIN) {
        	menuItems.add("Add new book");
        	menuItems.add("Add new member");
        	menuItems.add("Add copy of a book");
        } else if(SystemController.currentAuth == Auth.LIBRARIAN) {
        	menuItems.add("Check out book");
        	menuItems.add("Get user record");
        } else if(SystemController.currentAuth == Auth.BOTH){
        	menuItems.add("Add new book");
        	menuItems.add("Add new member");
        	menuItems.add("Add copy of a book");
        	menuItems.add("Check out book");
        	menuItems.add("Get user record");
        }
        menuItems.add("Get books");
        menuItems.add("Exit");

        String[] m = menuItems.toArray(new String[0]);
        var links = new JList<String>(m);     
        var cards = new JPanel(new CardLayout());
        
        var checkout = new LibraryCheckoutUI();
        cards.add(checkout, "Check out book");

        var newMember= new TestWindow();
        cards.add(newMember, "Add new member");

        var allMembers = new AllMembers();
        cards.add(allMembers.getMainPanel(), "Get user record");

        var allBooks = new AllBooks();
        cards.add(allBooks.getMainPanel(), "Get books");
        
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
        login.init();
        Util.centerFrameOnDesktop(LoginWindow.INSTANCE);
        login.setVisible(true);
	}
}
