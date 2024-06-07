package librarysystem;

import business.ControllerInterface;
import business.SystemController;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.util.Collections;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class AllMembers extends JPanel {
    ControllerInterface ci = new SystemController();
    public JPanel getMainPanel() {
        return mainPanel;
    }
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel middlePanel;
    private TextArea textArea;

    public AllMembers() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        defineTopPanel();
        defineMiddlePanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
    }

    public void defineTopPanel() {
        topPanel = new JPanel();
        JLabel AllIDsLabel = new JLabel("All Member IDs");
        Util.adjustLabelFont(AllIDsLabel, Util.DARK_BLUE, true);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(AllIDsLabel);
    }

    public void defineMiddlePanel() {
        middlePanel = new JPanel();
        textArea = new TextArea(8,20);
        populateTextArea();
        middlePanel.add(textArea);
    }

    private void setData(String data) {
        textArea.setText(data);
    }

    public void populateTextArea() {
        //populate
        List<String> ids = ci.allMemberIds();
        Collections.sort(ids);
        StringBuilder sb = new StringBuilder();
        for(String s: ids) {
            sb.append(s + "\n");
        }
        setData(sb.toString());
    }
}



