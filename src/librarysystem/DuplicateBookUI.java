package librarysystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import business.Book;
import business.Validation;
import business.ValidationException;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import java.awt.*;

public class DuplicateBookUI extends JPanel {

    private static final long serialVersionUID = 7167511124084521494L;

    DefaultTableModel dtm = new DefaultTableModel(new Object[] {"ISBN", "Title", "Copies"}, 0);
    JLabel errorField;
    Book selectedBook;
    
	public DuplicateBookUI() {
        setSize(480, 640);

        this.setLayout(new GridLayout(5, 1, 10, 10));

        // TextField: isbn
        var jpI = new JPanel();
        jpI.setLayout(new GridLayout(1, 3, 10, 10));
        JTextField isbnField = new JTextField("isbn");
        jpI.add(new JLabel());
        jpI.add(isbnField);
        jpI.add(new JLabel());
        add(jpI);

        // Button: check availability
        var jp0 = new JPanel();
        jp0.setLayout(new FlowLayout());
        JButton checkAvailabilityButton = new JButton("check availability");
        checkAvailabilityButton.addActionListener(evt -> {
        	selectedBook = null;
        	var isbn = isbnField.getText();
        	try {
				Validation.isIsbn(isbn);
			} catch (ValidationException e) {
				errorField.setText(e.getMessage());
				return;
			}
        	
        	DataAccess da = new DataAccessFacade();
        	var bMap = da.readBooksMap();
    		if(!bMap.containsKey(isbn)) {
    			errorField.setText("Book not found!");
    			return;
    		}
    		
    		var b = bMap.get(isbn);
    		selectedBook = b;
    		dtm.addRow(new Object[] {b.getIsbn(), b.getTitle(), b.getCopies().length});
        });
        jp0.add(checkAvailabilityButton);
        add(jp0);

        // Table: details
        JTable detailsTable = new JTable(dtm);
        JScrollPane detailsScrollPane = new JScrollPane(detailsTable);
        add(detailsScrollPane);

        // Button: Copy
        var jp1 = new JPanel();
        jp1.setLayout(new FlowLayout());
        JButton copyButton = new JButton("Copy");
        copyButton.addActionListener(evt -> {
        	if(selectedBook == null) {
        		errorField.setText("No book selected");
        		return;
        	}
        	
        	DataAccess da = new DataAccessFacade();
        	selectedBook.addCopy();
        	da.saveNewBook(selectedBook);
        	errorField.setText("Book copied");
        	dtm.removeRow(0);
        });
        jp1.add(copyButton);
        add(jp1);
        
        errorField = new JLabel("$");
        add(errorField);

        setVisible(true);
    }
	
	public static void main(String[] args) {
		var f = new JFrame();
		f.setSize(480, 640);
		f.add(new DuplicateBookUI());
		f.setVisible(true);
	}
}