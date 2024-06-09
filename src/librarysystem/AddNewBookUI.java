package librarysystem;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import business.Author;
import business.Book;
import business.LibraryMember;
import business.Validation;
import business.ValidationException;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class AddNewBookUI extends JPanel{
	
	JTextField isbnF, titleF, authorF;
	JSpinner cLenF, copyF;
	JLabel errorField;
	
	public AddNewBookUI() {
		setLayout(new GridLayout(7, 1, 10, 10));
		
		var p0 = new JPanel();
		p0.setLayout(new GridLayout(1, 2, 10, 10));
		p0.add(new JLabel("ISBN"));
		var isbnF = new JTextField();
		p0.add(isbnF);
		add(p0);
		
		var p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 2, 50, 50));
		p1.add(new JLabel("Title"));
		var titleF = new JTextField();
		p1.add(titleF);
		add(p1);
		
		var p2 = new JPanel();
		p2.setLayout(new GridLayout(1, 2, 50, 50));
		p2.add(new JLabel("Author(s)"));
		var authorF = new JTextField();
		p2.add(authorF);
		add(p2);
		
		var p3 = new JPanel();
		p3.setLayout(new GridLayout(1, 2, 50, 50));
		p3.add(new JLabel("Maximum checkout (days)"));
		var cLenF = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));
		p3.add(cLenF);
		add(p3);
		
		var p4 = new JPanel();
		p4.setLayout(new GridLayout(1, 2, 50, 50));
		p4.add(new JLabel("Copies"));
		var copyF = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		p4.add(copyF);
		add(p4);
		
		var p5 = new JPanel();
		p5.setLayout(new FlowLayout());
		var b = new JButton("Add");
		b.addActionListener(evt -> {
			var isbn = isbnF.getText();
			var title = titleF.getText();
			var author = authorF.getText();
			var cLen = (int) cLenF.getValue();
			var copies = (int) copyF.getValue();

			
			try {
				Validation.nonEmpty(title);
				Validation.nonEmpty(author);
				Validation.isIsbn(isbn);
			} catch (ValidationException e) {
				errorField.setText(e.getMessage());
				return;
			}
			
			var aList = Arrays.asList(author.split(", "))
					.stream().map(el -> {
						var n = el.split(" ");
						return new Author(n[0], n[1], "", null, "");
					})
					.collect(Collectors.toList());
			
			DataAccess da = new DataAccessFacade();
			var nB = new Book(isbn, title, cLen, aList);
			da.saveNewBook(nB);
			errorField.setText("Book saved: " + nB.getTitle());
		});
		p5.add(b);
		add(p5);
		
		errorField = new JLabel("$");
		add(errorField);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		var f = new JFrame();
		f.add(new AddNewBookUI());
		f.setVisible(true);
	}
}
