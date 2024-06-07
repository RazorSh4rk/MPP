package librarysystem;

import business.ControllerInterface;
import business.SystemController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import business.ControllerInterface;
import business.SystemController;

public class AllBooks extends JPanel {
    private static final long serialVersionUID = 1L;
    ControllerInterface ci = new SystemController();

    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel middlePanel;
    private TextArea textArea;
    public JPanel getMainPanel() {
        return mainPanel;
    }


    //Singleton class
    public AllBooks() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        defineTopPanel();
        defineMiddlePanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.setVisible(true);
    }

    public void defineTopPanel() {
        topPanel = new JPanel();
        JLabel AllIDsLabel = new JLabel("All Book IDs");
        Util.adjustLabelFont(AllIDsLabel, Util.DARK_BLUE, true);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(AllIDsLabel);
    }

    public void defineMiddlePanel() {
        middlePanel = new JPanel();
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 25, 25);
        middlePanel.setLayout(fl);
        textArea = new TextArea(8, 20);
        populateTextArea();
        middlePanel.add(textArea);

    }


    private void setData(String data) {
        textArea.setText(data);
    }

	public void populateTextArea() {
        //populate
        List<String> ids = ci.allBookIds();
        Collections.sort(ids);
        StringBuilder sb = new StringBuilder();
        for (String s : ids) {
            sb.append(s + "\n");
        }
        setData(sb.toString());
    }
}

