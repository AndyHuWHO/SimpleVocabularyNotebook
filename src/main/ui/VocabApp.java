package ui;

import model.Vocab;
import model.VocabList;
import java.util.Scanner;


//Vocabulary Notebook Application
public class VocabApp {
    private Vocab newWord;
    private Vocab wordBeingEdited;
    private Vocab wordBeingViewed;
    private VocabList myVocabList;
    private Scanner input;       // what's everything scanner can do??
    boolean keepGoing = true;    //why is this needed??


    // initialize the Vocab App
    public VocabApp() {
        input = new Scanner(System.in);   //what is system.in??
        input.useDelimiter("\n");    // useDelimiter??
        myVocabList = new VocabList();
        mainMenu();    //might want to call it mainMenu??
    }

    // MODIFIES: this  //does it modify this??
    // EFFECTS: processes user input at mainMenu
    private void mainMenu() {

        String mainMenuCommand;

        //init(); //is it needed?

        while (keepGoing) {
            displayMainMenu();
            mainMenuCommand = input.next();
            mainMenuCommand = mainMenuCommand.toLowerCase(); //I might not want this??

            if (mainMenuCommand.equals("q")) {
                keepGoing = false;
            } else {
                processMainMenuCommand(mainMenuCommand);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // is this needed??
    // MODIFIES: this
    // EFFECTS: initializes VocabApp
    //private void init() {
    //    input = new Scanner(System.in);
    //    input.useDelimiter("\n");
    //    myVocabList = new VocabList();
    //}

    // EFFECTS: displays main menu options to user
    private void displayMainMenu() {
        System.out.println("\nEnter your VOCABULARY to create a new entry, or:");
        System.out.println("\tv -> to view your vocab list");
        System.out.println("\tq -> quit");
    }


    // EFFECTS: start building a new vocab entry with given name by
    // asking user to put in definition or do other commands
    private void buildNewWord(String word) {
        newWord = new Vocab(word);
        String definitionPhaseCommand;

        displayAddDefinitionMenu();
        definitionPhaseCommand = input.next();
        definitionPhaseCommand = definitionPhaseCommand.toLowerCase();

        if (definitionPhaseCommand.equals("q")) {
            keepGoing = false;
        } else {
            processDefinitionPhaseCommand(definitionPhaseCommand);
        }

    }

    // EFFECTS: displays add definition menu options to user
    private void displayAddDefinitionMenu() {
        System.out.println("\nPlease enter the DEFINITION for " + newWord.getName() + ", or:");
        System.out.println("\tm -> to main WITHOUT saving the entry");  //why was this line commented out earlier??
        System.out.println("\ts) -> to "
                + "SAVE the entry without content for definition or learning context");
        System.out.println("\tq -> quit");
    }


    //EFFECTS: ask user to put in the learning context for the word or do other commands
    private void learningContextPhase() {
        String learningContextPhaseCommand;

        displayAddLearningContextMenu();
        learningContextPhaseCommand = input.next();
        learningContextPhaseCommand = learningContextPhaseCommand.toLowerCase();

        if (learningContextPhaseCommand.equals("q")) {
            keepGoing = false;
        } else {
            processLearningContextPhaseCommand(learningContextPhaseCommand);
        }
    }


    // EFFECTS: displays edit learning context menu options to user
    private void displayAddLearningContextMenu() {
        System.out.println("\nPlease write down your original LEARNING CONTEXT for " + newWord.getName() + ", or:");
        System.out.println("\tm -> to main WITHOUT saving the entry");  //why was this line commented out?
        System.out.println("\ts) -> to "
                + "Save the entry without content for learning context");
        System.out.println("\tq -> quit");
    }


    //EFFECTS: display vocab names in the vocab list with user options.
    private void viewNotebook() {
        if (myVocabList.isEmpty()) {
            System.out.println("\nThere's no words in your list yet");
        } else {
            printVocabList();
            String vocabListCommand;


            displayViewVocabListMenu();
            vocabListCommand = input.next();
            vocabListCommand = vocabListCommand.toLowerCase();

            if (vocabListCommand.equals("q")) {
                keepGoing = false;
            } else {
                processVocabListCommand(vocabListCommand);
            }

        }
    }


    // EFFECTS: displays view vocabularyList menu options to user
    private void displayViewVocabListMenu() {
        System.out.println("\nPlease type in the word you want to REVIEW, or");
        System.out.println("\tm -> back to main");
        System.out.println("\te -> to edit a word entry");
        System.out.println("\td -> to delete a word entry");
        System.out.println("\tq -> quit");
    }

    //EFFECTS: display vocab entry information with user options.
    private void viewVocabEntryPhase() {
        String vocabListCommand;
        displayViewVocabEntryMenu();
        vocabListCommand = input.next();
        vocabListCommand = vocabListCommand.toLowerCase();

        if (vocabListCommand.equals("q")) {
            keepGoing = false;
        } else {
            processViewVocabEntryCommand(vocabListCommand);
        }

    }


    // EFFECTS: displays view vocab entry menu options to user
    private void displayViewVocabEntryMenu() {
        System.out.println("\tv -> to go back and view your vocab list");
        System.out.println("\tm -> back to main");
        System.out.println("\te -> to edit the current word entry");
        System.out.println("\td -> to delete the current word entry");
        System.out.println("\tq -> quit");
    }


    //REQUIRES: vocabList is not empty
    //EFFECTS: display delete phase options
    private void deleteOneWord() {

        printVocabList();
        String deletePhaseCommand;


        displayDeletePhaseMenu();
        deletePhaseCommand = input.next();
        deletePhaseCommand = deletePhaseCommand.toLowerCase();

        if (deletePhaseCommand.equals("q")) {
            keepGoing = false;
        } else {
            processDeletePhaseCommand(deletePhaseCommand);
        }

    }


    // EFFECTS: displays view delete phase menu options to user
    private void displayDeletePhaseMenu() {
        System.out.println("\nPlease type in the word you want to DELETE, or");
        System.out.println("\tm -> back to main");
        System.out.println("\tv -> back to view your vocab list");
        System.out.println("\tq -> quit");
    }


    //REQUIRES: vocabList is not empty
    //EFFECTS: display edit phase options
    private void editWordEntry() {

        printVocabList();
        String editPhaseCommand;


        displayEditPhaseMenu();
        editPhaseCommand = input.next();
        editPhaseCommand = editPhaseCommand.toLowerCase();

        if (editPhaseCommand.equals("q")) {
            keepGoing = false;
        } else {
            processEditPhaseCommand(editPhaseCommand);
        }

    }


    // EFFECTS: displays view edit entry phase menu options to user
    private void displayEditPhaseMenu() {
        System.out.println("\nPlease type in the word you want to EDIT, or");
        System.out.println("\tm -> back to main");
        System.out.println("\tv -> back to view your vocab list");
        System.out.println("\tq -> quit");
    }


    //REQUIRES: vocabList is not empty
    //EFFECTS: display edit name phase options
    private void editEntryName(Vocab word) {
        wordBeingEdited = word;

        printVocabList();
        String editNamePhaseCommand;


        if (word == null) {
            System.out.println("It's not in the list");

        } else {
            displayEditNamePhaseMenu();
            editNamePhaseCommand = input.next();
            editNamePhaseCommand = editNamePhaseCommand.toLowerCase();

            if (editNamePhaseCommand.equals("q")) {
                keepGoing = false;
            } else {
                processEditNamePhaseCommand(editNamePhaseCommand);
            }
        }

    }


    // EFFECTS: displays view edit entry phase menu options to user
    private void displayEditNamePhaseMenu() {
        System.out.println("\nPlease type in the new word NAME for: " + wordBeingEdited.getName());
        System.out.println("\tm -> back to main");
        System.out.println("\tv -> back to view your vocab list");
        System.out.println("\tq -> quit");
    }


    //REQUIRES: vocabList is not empty
    //EFFECTS: display edit name phase options
    private void editEntryDefinition() {


        printVocabList();
        String editDefinitionPhaseCommand;


        displayEditDefinitionPhaseMenu();
        editDefinitionPhaseCommand = input.next();
        editDefinitionPhaseCommand = editDefinitionPhaseCommand.toLowerCase();

        if (editDefinitionPhaseCommand.equals("q")) {
            keepGoing = false;
        } else {
            processEditDefinitionPhaseCommand(editDefinitionPhaseCommand);
        }

    }


    // EFFECTS: displays view edit entry phase menu options to user
    private void displayEditDefinitionPhaseMenu() {
        System.out.println("\nPlease type in definition for " + wordBeingEdited.getName() + " to add");
        System.out.println("\tm -> back to main");  //last step edit is automatically saved
        System.out.println("\tv -> back to view your vocab list");  //last step edit is automatically saved
        System.out.println("\tq -> quit");
    }


    //REQUIRES: vocabList is not empty
    //EFFECTS: display edit name phase options
    private void editEntryLearningContext() {

        printVocabList();
        String editLearningContextPhaseCommand;


        displayEditLearningContextPhaseMenu();
        editLearningContextPhaseCommand = input.next();
        editLearningContextPhaseCommand = editLearningContextPhaseCommand.toLowerCase();

        if (editLearningContextPhaseCommand.equals("q")) {
            keepGoing = false;
        } else {
            processEditLearningContextPhaseCommand(editLearningContextPhaseCommand);
        }

    }


    // EFFECTS: displays view edit entry phase menu options to user
    private void displayEditLearningContextPhaseMenu() {
        System.out.println("\nPlease type in learning context for " + wordBeingEdited.getName() + " to add");
        System.out.println("\tm -> back to main");  //last step edit is automatically saved
        System.out.println("\tv -> back to view your vocab list");  //last step edit is automatically saved
        System.out.println("\tq -> quit");
    }


    // MODIFIES: this
    // EFFECTS: processes user command at main menu
    private void processMainMenuCommand(String command) {
        if (command.equals("v")) {
            viewNotebook();
        } else {
            buildNewWord(command);
        }
    }


    // MODIFIES: this
    // EFFECTS: processes user command at definition phase
    private void processDefinitionPhaseCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu, word NOT saved");
        } else if (command.equals("s")) {
            myVocabList.addVocab(newWord);
            System.out.println(newWord.getName() + " is SAVED in your notebook "
                    + "with no definition or original learning context");
        } else {
            newWord.editDefinition(command);
            learningContextPhase();

        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command at learning context phase
    private void processLearningContextPhaseCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu, word NOT saved");
        } else if (command.equals("s")) {
            myVocabList.addVocab(newWord);
            System.out.println(newWord.getName() + " is SAVED in your notebook "
                    + "without original learning context");
        } else {
            newWord.editLearningContext(command);
            myVocabList.addVocab(newWord);
            System.out.println(newWord.getName() + " is SAVED with definition and original learning context");

        }
    }        //how did this go back to main automatically



    // MODIFIES: this
    // EFFECTS: processes user command at vocabList viewing phase
    private void processVocabListCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu");
        } else if (command.equals("d")) {
            deleteOneWord();
        } else if (command.equals("e")) {
            editWordEntry();
        } else {
            printVocabEntry(command);
            wordBeingViewed = myVocabList.findVocab(command);
            viewVocabEntryPhase();

        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command at vocab entry viewing phase
    private void processViewVocabEntryCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu");
        } else if (command.equals("v")) {
            viewNotebook();
        } else if (command.equals("d")) {
            myVocabList.getVocabList().remove(wordBeingViewed);
            System.out.println(wordBeingViewed.getName() + " is deleted from your vocab list");
            viewNotebook();
        } else if (command.equals("e")) {
            editEntryName(wordBeingViewed);
        } else {
            System.out.println("invalid command");
            viewVocabEntryPhase();

        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command at delete vocab phase
    private void processDeletePhaseCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu");
        } else if (command.equals("v")) {
            viewNotebook();
        } else {
            if (myVocabList.deleteVocab(command)) {
                System.out.println(command + " is deleted from the list");
            } else {
                System.out.println(command + " is not in the list");
            }
            //myVocabList.deleteVocab(command);
            viewNotebook();

        }
    }


    // MODIFIES: this
    // EFFECTS: processes user command at delete vocab phase
    private void processEditPhaseCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu");
        } else if (command.equals("v")) {
            viewNotebook();
        } else {
            editEntryName(myVocabList.findVocab(command));
            viewNotebook();

        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command at delete vocab phase
    private void processEditNamePhaseCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu");
        } else if (command.equals("v")) {
            viewNotebook();
        } else {
            wordBeingEdited.editName(command);
            System.out.println("name had been edited");
            editEntryDefinition();
            viewNotebook();

        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command at delete vocab phase
    private void processEditDefinitionPhaseCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu");
        } else if (command.equals("v")) {
            viewNotebook();
        } else {
            wordBeingEdited.editDefinition(command);
            System.out.println("definition had been edited");
            editEntryLearningContext();

        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command at delete vocab phase
    private void processEditLearningContextPhaseCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu");
        } else if (command.equals("v")) {
            viewNotebook();
        } else {
            wordBeingEdited.editLearningContext(command);
            System.out.println("learning context had been edited");
            viewNotebook();

        }
    }



    //EFFECTS: print the names of vocabs in the list
    private void printVocabList() {
        System.out.println("Below is your vocab list: ");
        System.out.println(myVocabList.toString());

    }


    //EFFECTS: print the names of vocabs in the list
    public void printVocabEntry(String word) {
        for (Vocab v : myVocabList.getVocabList()) {
            if (v.getName().equals(word)) {
                System.out.println("Word: " + v.getName());
                System.out.println(v.getDefinition());
                System.out.println(v.getLearningContext());
                //viewVocabEntryPhase();
                return;
            }

        }
        System.out.println(word + " is not in the list");
    }



}


