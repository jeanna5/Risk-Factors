package com.riskfactors;


import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class Dictionary {
    HashSet<String> allWords = new HashSet<>();

    public Dictionary(String fileName) {
        storeWords(fileName);
    }
    //unfinished: currently uses a hashtable, will use a trie to lower memory usage
    protected void storeWords(String fileName){
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNext()){
                allWords.add(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected boolean contains(String word){
        return allWords.contains(word);
    }
}
