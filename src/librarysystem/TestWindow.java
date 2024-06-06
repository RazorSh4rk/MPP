package librarysystem;

import business.LoginException;
import business.SystemController;

import javax.swing.*;
import java.awt.*;

public class TestWindow extends JFrame implements LibWindow {
    public static final TestWindow INSTANCE = new TestWindow();

    private boolean isInitialized = false;

    private JPanel mainPanel;
    private JPanel upperHalf;
    private JPanel middleHalf;
    private JPanel lowerHalf;
    private JPanel container;

    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel lowerPanel;
    private JPanel leftTextPanel;
    private JPanel rightTextPanel;

    private JTextField memberNo;
    private JTextField lastName;
    private JTextField firstName;
    private JTextField phoneNumber;
    private JTextField state;
    private JTextField city;
    private JTextField zip;
    private JTextField street;
    private JLabel label;
    private JButton submitButton;




    public boolean isInitialized() {
        return isInitialized;
    }
    public void isInitialized(boolean val) {
        isInitialized = val;
    }
    private JTextField messageBar = new JTextField();
    public void clear() {
        messageBar.setText("");
    }

    /* This class is a singleton */
    private TestWindow () {}

    public void init() {
        mainPanel = new JPanel();
        defineUpperHalf();
        defineMiddleHalf();
        defineLowerHalf();
        BorderLayout bl = new BorderLayout();
        bl.setVgap(30);
        mainPanel.setLayout(bl);

        mainPanel.add(upperHalf, BorderLayout.NORTH);
        mainPanel.add(middleHalf, BorderLayout.CENTER);
        mainPanel.add(lowerHalf, BorderLayout.SOUTH);
        getContentPane().add(mainPanel);
        isInitialized(true);
        setSize(800, 200); // fixed frame size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    private void defineUpperHalf() {

        upperHalf = new JPanel();
        upperHalf.setLayout(new BorderLayout());
        defineTopPanel();
        defineMiddlePanel();
        defineLowerPanel();
        upperHalf.add(topPanel, BorderLayout.NORTH);
        upperHalf.add(middlePanel, BorderLayout.CENTER);
        upperHalf.add(lowerPanel, BorderLayout.SOUTH);

    }
    private void defineMiddleHalf() {
        middleHalf = new JPanel();
        middleHalf.setLayout(new BorderLayout());
        JSeparator s = new JSeparator();
        s.setOrientation(SwingConstants.HORIZONTAL);
        //middleHalf.add(Box.createRigidArea(new Dimension(0,50)));
        middleHalf.add(s, BorderLayout.SOUTH);

    }
    private void defineLowerHalf() {

        lowerHalf = new JPanel();
        lowerHalf.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton backButton = new JButton("<= Back to Main");
        addBackButtonListener(backButton);
        lowerHalf.add(backButton);

    }
    private void defineTopPanel() {
        topPanel = new JPanel();
        JPanel intPanel = new JPanel(new BorderLayout());
        intPanel.add(Box.createRigidArea(new Dimension(0,20)), BorderLayout.NORTH);
        JLabel loginLabel = new JLabel("Add New Member");
        Util.adjustLabelFont(loginLabel, Color.BLUE.darker(), true);
        intPanel.add(loginLabel, BorderLayout.CENTER);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(intPanel);

    }

private void defineMiddlePanel() {
    middlePanel = new JPanel();
    middlePanel.setLayout(new BorderLayout());
    defineLeftTextPanel();
    defineRightTextPanel();
    middlePanel.add(leftTextPanel, BorderLayout.NORTH);
    middlePanel.add(rightTextPanel, BorderLayout.SOUTH);
}


    private void defineLowerPanel() {
        lowerPanel = new JPanel();
        submitButton = new JButton("Submit");
        addSubmitButtonListener(submitButton);
        lowerPanel.add(submitButton);
    }


    private void defineLeftTextPanel() {
        leftTextPanel = new JPanel();
        leftTextPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

        JPanel leftLine = new JPanel();
        leftLine.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

        memberNo = new JTextField(10);
        JLabel memberNoLabel = new JLabel("Member No");
        memberNoLabel.setFont(Util.makeSmallFont(memberNoLabel.getFont()));
        leftLine.add(memberNoLabel);
        leftLine.add(memberNo);

        firstName = new JTextField(10);
        JLabel firstNameLabel = new JLabel("First Name");
        firstNameLabel.setFont(Util.makeSmallFont(firstNameLabel.getFont()));
        leftLine.add(firstNameLabel);
        leftLine.add(firstName);

        lastName = new JTextField(10);
        JLabel lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setFont(Util.makeSmallFont(lastNameLabel.getFont()));
        leftLine.add(lastNameLabel);
        leftLine.add(lastName);

        phoneNumber = new JTextField(10);
        JLabel phoneNumberLabel = new JLabel("Phone Number");
        phoneNumberLabel.setFont(Util.makeSmallFont(phoneNumberLabel.getFont()));
        leftLine.add(phoneNumberLabel);
        leftLine.add(phoneNumber);

        leftTextPanel.add(leftLine);
    }

    private void defineRightTextPanel() {
        rightTextPanel = new JPanel();
        rightTextPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

        JPanel rightLine = new JPanel();
        rightLine.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

        state = new JTextField(10);
        JLabel stateLabel = new JLabel("State");
        stateLabel.setFont(Util.makeSmallFont(stateLabel.getFont()));
        rightLine.add(stateLabel);
        rightLine.add(state);

        city = new JTextField(10);
        JLabel cityLabel = new JLabel("City");
        cityLabel.setFont(Util.makeSmallFont(cityLabel.getFont()));
        rightLine.add(cityLabel);
        rightLine.add(city);

        street = new JTextField(10);
        JLabel streetLabel = new JLabel("Street");
        streetLabel.setFont(Util.makeSmallFont(streetLabel.getFont()));
        rightLine.add(streetLabel);
        rightLine.add(street);

        zip = new JTextField(10);
        JLabel zipLabel = new JLabel("Zip");
        zipLabel.setFont(Util.makeSmallFont(zipLabel.getFont()));
        rightLine.add(zipLabel);
        rightLine.add(zip);

        rightTextPanel.add(rightLine);
    }




    private void addBackButtonListener(JButton butn) {
        butn.addActionListener(evt -> {
            LibrarySystem.hideAllWindows();
            LibrarySystem.INSTANCE.setVisible(true);
        });
    }

    private void addSubmitButtonListener(JButton butn) {
        butn.addActionListener(evt -> {

           if(!(memberNo.getText().isEmpty() ||
                   lastName.getText().isEmpty() ||
                   firstName.getText().isEmpty() ||
                   state.getText().isEmpty() ||
                   street.getText().isEmpty() ||
                   zip.getText().isEmpty() ||
                   phoneNumber.getText().isEmpty() ||
                   city.getText().isEmpty()))
            {
            try {
                SystemController sc = new SystemController();
                sc.addMember(memberNo.getText(), firstName.getText(), lastName.getText(), phoneNumber.getText(), state.getText(), city.getText(), street.getText(), zip.getText());
                JOptionPane.showMessageDialog(TestWindow.this, "Member id added");
            } catch(Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            } else JOptionPane.showMessageDialog(TestWindow.this, "Enter all credentials");
});
    }



}