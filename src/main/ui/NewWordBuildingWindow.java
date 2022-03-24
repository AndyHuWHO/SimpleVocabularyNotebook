package ui;

import model.VocabList;
import model.Word;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewWordBuildingWindow implements ActionListener {
    Word newWord;
    VocabList myVocabList;
    JFrame wordBuildingFrame;
    JPanel centerPanel;
    JTextField definitionField;
    JTextField learningContextField;
    JPanel saveCancelPanel;
    JButton saveButton;
    JButton cancelButton;

    public NewWordBuildingWindow(NotebookWindow notebookWindow) {
        this.newWord = notebookWindow.newWord;
        this.myVocabList = notebookWindow.myVocabList;
        setupWordBuildingFrame();
        setupNavigationPanel();
        setupCenterPanel();

    }

    private void setupWordBuildingFrame() {
        wordBuildingFrame = new JFrame();
        wordBuildingFrame.setTitle("My Vocabulary Notebook");
        wordBuildingFrame.setSize(600, 500);
        wordBuildingFrame.setLayout(new BorderLayout());
        wordBuildingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        wordBuildingFrame.getContentPane().setBackground(new Color(195, 243, 241));
        wordBuildingFrame.setVisible(true);
    }

    //// set up the Navigation JPanel for main frame
    private void setupCenterPanel() {
        definitionField = new JTextField("definition",20);
        //definitionField.setPreferredSize(new Dimension(350, 100));
        definitionField.setBounds(50, 100, 350, 100);
        learningContextField = new JTextField(20);
        //learningContextField.setPreferredSize(new Dimension(350, 100));
        learningContextField.setBounds(50, 250, 350, 100);

        centerPanel = new JPanel();
        //navigationPanel.setBackground(new Color(255, 255, 255));
        centerPanel.setPreferredSize(new Dimension(600,400));
        centerPanel.setLayout(null);
        centerPanel.setBackground(new Color(195, 243, 241));
        centerPanel.add(definitionField);
        centerPanel.add(learningContextField);

        wordBuildingFrame.add(centerPanel,BorderLayout.CENTER);


    }

    //// set up the Navigation JPanel for main frame
    private void setupNavigationPanel() {
        saveCancelPanel = new JPanel();
        //navigationPanel.setBackground(new Color(255, 255, 255));
        saveCancelPanel.setPreferredSize(new Dimension(600,100));
        saveCancelPanel.setLayout(new GridLayout());
        saveButton = new JButton("Save");
        saveButton.setBounds(0, 0, 200, 80);
        cancelButton = new JButton("Cancel");
        saveCancelPanel.add(saveButton);
        saveButton.addActionListener(this);
        saveCancelPanel.add(cancelButton);
        cancelButton.addActionListener(this);
        wordBuildingFrame.add(saveCancelPanel,BorderLayout.SOUTH);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            newWord.editDefinition(definitionField.getText());
            newWord.editLearningContext(learningContextField.getText());
            wordBuildingFrame.dispose();
        }

    }
}