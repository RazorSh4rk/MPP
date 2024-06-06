package librarysystem;

import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;

public class LibraryCheckoutUI extends JPanel {

	JLabel errorField = new JLabel("$");
	
    public LibraryCheckoutUI() {
        //super("Checkout");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 640);
        //setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top panel with labels and text fields
        JPanel topPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        topPanel.add(new JLabel("Member ID"));
        var mID = new JTextField("");
        topPanel.add(mID);
        topPanel.add(new JLabel("ISBN"));
        var ISBN = new JTextField("");
        topPanel.add(ISBN);
        
        var okB = new JButton("OK");
        okB.addActionListener(evt -> {
        	var memberID = mID.getText();
        	var isbn = ISBN.getText();
        	
        	errorField.setText(memberID + " " + isbn);
        });
        topPanel.add(okB);

        // Error message panel
        JPanel errorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        errorPanel.add(errorField);

        // Table panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JLabel("Book:"), BorderLayout.NORTH);
        JTable table = new JTable(5, 4); // Example table size
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Add panels to the content pane
        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(errorPanel, BorderLayout.SOUTH);
        contentPane.add(tablePanel, BorderLayout.EAST);

        add(contentPane);
        setVisible(true);
    }
}