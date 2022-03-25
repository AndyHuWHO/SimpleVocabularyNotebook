package ui;

import model.VocabList;
import model.Word;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class VocabListWindow implements ListSelectionListener {
    private static final String JSON_STORE = "./data/testGUIVocabList.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;


    private JScrollPane vocabListScrollPane;
    private JFrame vocabListFrame;
    private JList list;
    private DefaultListModel listModel;
    private JPanel navigationPanel;
    private JPanel wordOptionPanel;
    private JButton backButton;
    private JButton saveVocabListButton;
    private JButton loadVocabListButton;

    private JButton viewButton;
    private JButton deleteButton;
    private final JFrame myFrame;
    private VocabList myVocabList;


    public VocabListWindow(NotebookWindow notebookWindow) {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        this.myFrame = notebookWindow.mainFrame;
        this.myVocabList = notebookWindow.myVocabList;
        setupFrame();
        //setVisible(true);
        setupWordOptionPanel();
        vocabListFrame.add(wordOptionPanel);
        setupNavigationPanel();
        vocabListFrame.add(navigationPanel);
        setupScrollPane();
        vocabListFrame.add(vocabListScrollPane);


    }


    private void setupFrame() {
        vocabListFrame = new JFrame();
        vocabListFrame.setTitle("My Vocabulary Notebook");
        vocabListFrame.setSize(600, 500);
        vocabListFrame.setLayout(null);
        vocabListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vocabListFrame.getContentPane().setBackground(new Color(195, 243, 241));
        vocabListFrame.setVisible(true);
    }


    private void setupScrollPane() {
        listModel = new DefaultListModel();
        //listModel.addElement("try");
        renderVocablistToListModel();
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        //list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        vocabListScrollPane = new JScrollPane(list);
        vocabListScrollPane.setBounds(50, 50, 300, 300);
        //vocabListScrollPane.setOpaque(true);
    }

    private void renderVocablistToListModel() {
        for (Word w: myVocabList.getVocabList()) {
            listModel.addElement(w.getName());

        }
    }

    //// set up the Option JPanel for main frame
    private void setupWordOptionPanel() {
        wordOptionPanel = new JPanel();
        //navigationPanel.setBackground(new Color(255, 255, 255));
        wordOptionPanel.setBounds(350, 50, 100, 200);
        wordOptionPanel.setLayout(new GridLayout(2,1));
        viewButton = new JButton("view word");
        viewButton.addActionListener(new WordOptionListener());
        deleteButton = new JButton("delete word");
        deleteButton.addActionListener(new WordOptionListener());
        //viewNoteBookButton.setBackground(new Color(220, 187, 102));

        wordOptionPanel.add(viewButton);
        wordOptionPanel.add(deleteButton);
    }


    //// set up the Navigation JPanel for main frame
    private void setupNavigationPanel() {
        navigationPanel = new JPanel();
        //navigationPanel.setBackground(new Color(255, 255, 255));
        navigationPanel.setBounds(0, 400, 600, 80);
        navigationPanel.setLayout(new GridLayout(1,3));
        backButton = new JButton("Back");
        backButton.setBounds(0, 0, 200, 80);
        saveVocabListButton = new JButton("Save My Notebook");
        saveVocabListButton.setPreferredSize(new Dimension(200,80));
        loadVocabListButton = new JButton("Load My Notebook");
        loadVocabListButton.setPreferredSize(new Dimension(200,80));
        backButton.addActionListener(new NavigationListener());
        saveVocabListButton.addActionListener(new NavigationListener());
        loadVocabListButton.addActionListener(new NavigationListener());
        navigationPanel.add(backButton);
        navigationPanel.add(saveVocabListButton);
        navigationPanel.add(loadVocabListButton);
    }

    // EFFECTS: saves the workroom to file
    private void saveVocabList() {
        try {
            jsonWriter.open();
            jsonWriter.write(myVocabList);
            jsonWriter.close();
            System.out.println("Your Vocabulary List is SAVED to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadVocabList() {
        try {
            this.myVocabList = jsonReader.read();

            System.out.println("Your Vocabulary List is LOADED from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

//    private void setupBackButton() {
//        backButton = new JButton("Back");
//        backButton.setBounds(0, 400, 200, 100);
//        backButton.addActionListener(new NavigationListener());
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == backButton) {
//            this.dispose();
//            myFrame.setVisible(true);
//            System.out.println("I did it");
//        }
//
//    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                deleteButton.setEnabled(false);

            } else {
                deleteButton.setEnabled(true);
            }
        }
    }
    //https://docs.oracle.com/javase/tutorial/uiswing/examples/zipfiles/components-ListDemoProject.zip
    //Code from this method is learned from the file above

    private class NavigationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == backButton) {
                vocabListFrame.dispose();
                myFrame.setVisible(true);
                System.out.println("I did it");
            } else if (e.getSource() == saveVocabListButton) {
                saveVocabList();
            } else if (e.getSource() == loadVocabListButton) {
                loadVocabList();
                saveVocabList();
            }

        }
    }

    private class WordOptionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            if (e.getSource() == viewButton) {
                System.out.println("viewing");

                new WordViewingWindow(myVocabList.findWordByIndex(index));

            } else if (e.getSource() == deleteButton) {
                System.out.println("will delete");

                listModel.remove(index);
                myVocabList.deleteWordByIndex(index);

                int size = listModel.getSize();

                if (size == 0) {
                    deleteButton.setEnabled(false);

                } else {
                    if (index == listModel.getSize()) {
                        index--;
                    }

                    list.setSelectedIndex(index);
                    list.ensureIndexIsVisible(index);
                }
            }
        }
    }
    //https://docs.oracle.com/javase/tutorial/uiswing/examples/zipfiles/components-ListDemoProject.zip
    //Code from this method is learned from the file above
}
