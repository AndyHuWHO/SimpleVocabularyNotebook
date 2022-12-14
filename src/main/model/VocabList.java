package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//VocabList is a list of Words
public class VocabList implements Writeable {
    private ArrayList<Word> vocabList;

    //A new empty list of the word entries
    public VocabList() {
        this.vocabList = new ArrayList<>();
    }


    //MODIFIES: this
    //EFFECTS: add a new word entry into VocabList
    public void addWord(Word word) {
        vocabList.add(word);
        EventLog.getInstance().logEvent(new Event("Added word: " + word.getName()));
    }


    //MODIFIES: this
    //EFFECTS: add a given list of Words to the end of the vocabList and return the new VocabList
    public VocabList addAListOfWords(ArrayList<Word> words) {
        vocabList.addAll(words);
        return this;
    }


    //MODIFIES: this
    //EFFECTS: remove a word of the given name from the vocab list if the word is in the list and return true
    //         if there is no word of the given name in the list, return false
    public boolean deleteWordByName(String word) {
        for (Word v : vocabList) {
            if (v.getName().equals(word)) {
                vocabList.remove(v);
                return true;
            }
        }
        return false;
    }


    //MODIFIES: this
    //EFFECTS: remove a word by its index in the list + 1  from the vocab list if the word is in the list
    // and return the word, if there is no word of the given index in the list, return null
    public Word deleteWordByIndex(String index) {
        for (Word v : vocabList) {
            if (Integer.toString(vocabList.indexOf(v) + 1).equals(index)) {
                vocabList.remove(v);
                return v;
            }
        }
        return null;
    }


    //MODIFIES: this
    //EFFECTS: remove a word by its index in the list + 1  from the vocab list if the word is in the list
    // and return the word, if there is no word of the given index in the list, return null
    public void deleteWordByIndex(int index) {
        EventLog.getInstance().logEvent(new Event("Deleted word: "
                + this.findWordByIndex(index).getName()));
        vocabList.remove(index);

    }


    //EFFECTS: get a word of the given name from the vocab list
    public Word findWordByName(String word) {
        for (Word v : vocabList) {
            if (v.getName().equals(word)) {
                return v;
            }
        }
        return null;
    }

    //EFFECTS: get a word by its index in the list + 1 from the vocab list
    public Word findWordByIndex(String index) {
        for (Word v : vocabList) {
            if (Integer.toString(vocabList.indexOf(v) + 1).equals(index)) {
                return v;
            }
        }
        return null;
    }


    //EFFECTS: get a word by its index in the list from the vocab list
    public Word findWordByIndex(int index) {
        for (Word v : vocabList) {
            if (vocabList.indexOf(v) == (index)) {
                return v;
            }
        }
        return null;
    }


    //EFFECTS: returns the vocabList which is ArrayList<Vocab>
    public ArrayList<Word> getVocabList() {
        return vocabList;
    }

    // EFFECTS: to check if the vocab list is empty, return true if yes and false otherwise
    public boolean isEmpty() {
        return vocabList.isEmpty();
    }

    // EFFECTS: to get the number of vocab entries in the vocab list
    public int getSize() {
        return vocabList.size();
    }

    // EFFECTS: to check if a specific vocab is in the vocab list, return true if yes and false otherwise
    public boolean containsWord(Word v) {
        return vocabList.contains(v);
    }

    //EFFECTS: to make a list of the words' names in the vocab list
    public String toListVocabulary() {
        String result = "Below is your vocab list:";
        for (Word v : vocabList) {
            result = result + "\n" + (vocabList.indexOf(v) + 1) + ". " + v.getName();
        }
        return "****************************\n" + result + "\n****************************";
    }


    // EFFECTS: returns an unmodifiable list of thingies in this workroom
    public List<Word> getWords() {
        return Collections.unmodifiableList(vocabList);
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("words", wordsToJson());
        return json;
    }  //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//code used from the link above

    // EFFECTS: returns words in this vocabList as a JSON array
    private JSONArray wordsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Word word : vocabList) {
            jsonArray.put(word.toJson());
        }

        return jsonArray;
    }   //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    //code used from the link above


}
