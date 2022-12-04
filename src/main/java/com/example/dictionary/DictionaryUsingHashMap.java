package com.example.dictionary;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class DictionaryUsingHashMap {

    DbConnection dbConnection = new DbConnection();
    private HashMap<String, String> dictionary;

    public DictionaryUsingHashMap() {

        dictionary = new HashMap<>();
        addListOfWords();
    }

    public boolean addWord(String word, String meaning) {
        dictionary.put(word, meaning);
        return true;
    }

    public String[] getSueggestions(String word) {
        int i = 0;
        String[] suggestions = new String[5];
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            int lastIndex = Math.min(word.length(), entry.getKey().length());
            if (word.compareTo(entry.getKey().substring(0, lastIndex)) == 0) {
                suggestions[i++] = entry.getKey();
            }
            if (i == 4) break;
        }
        return suggestions;
    }

    public String findMeaning(String word) {
        try {

            String query=String.format("SELECT meaning FROM dictionary WHERE meaning='%s'",word);
            ResultSet rs = dbConnection.getQueryTable(query);

            String str = word.substring(0, 1).toUpperCase() + word.substring(1);


            if (!dictionary.containsKey(str)) {
                return "Given word not found";
            } else return dictionary.get(str);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return "Given word not found";

    }




private void addListOfWords(){
addWord("Project","An application to do something");
addWord("WFH","work from home");
addWord("Next tomorrow","the day after tomorrow");
addWord("Onboarding","integrating a new employee in the organization");
addWord("Slow walk","to delay or prevent progress");
addWord("Account","an arrangement with a bank to keep your money there and allow you to take it out when you need to");
addWord("Air","the mixture of gases that surrounds the Earth and that we breathe");
addWord("Attack"," to try to hurt or defeat (mainly referred to as physical violence but can also be used to describe verbal or emotional outbursts)");
addWord("Attention","notice, thought or interest");
addWord("Back","(adverb) in return, into, towards a previous place or condition, or an earlier time; (noun) the part of your body that is opposite to the front, from your shoulder to your bottom");
addWord("Behaviour","the way that someone behaves");
addWord("Country","An area of land that has its own government, army, etc");
addWord("day","a period of 24 hours.");
addWord("Development","the process in which someone or something grows or changes and becomes more advanced");
addWord("Direction","the position towards which someone or something moves or faces");
    }

}
