package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.io.BufferedReader;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class BagOfWords {

  //stop words: word to ignore
  private final List<String> StopWords = Arrays.asList(

      new String[]{"the", "a", "in", "it", "be", "by", "of", "if", "is"}

  );
  //File handling variables for the reading file
  private File textFile = new File("src/com/company/sports.txt");

  //maps for handling word(tokens)
  private Map<String, Integer> bag = new HashMap<>();
  private NavigableMap<String, Integer> allWordsByKey = new TreeMap<>();

  protected void loadFile() {
/**
 * Loads the text file into a sorted (by key) tree map
 */

    // Line reader holds each line
    String line = null;

    try {
      BufferedReader reader = new BufferedReader(new FileReader(textFile));
      while ((line = reader.readLine()) != null) {
        createTreeMap(line);
      }
      reader.close();
    } catch (IOException io) {
      io.printStackTrace();
    }
    buildBag();
  }

  private void createTreeMap(String currentLine) {

    /**Builds allWordsByKey Map based on line
     * @param currLine = current line to process from file
     */

    String[] allTokens = currentLine.trim().split("\\s+");
    for (String token : allTokens) {
      if (!StopWords.contains(token)) {
        allWordsByKey.merge(token, 1, Integer::sum);

      }
    }
  }

  protected void printAllWordsAlphabetically() {
/**
 * Print TreeMap of Words
 */
    allWordsByKey.forEach((key, value) -> System.out.println(key + ", " + value));
  }

  private void buildBag() {
/**
 *
 */

    bag = allWordsByKey.entrySet().stream()
        .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
            (e1, e2) -> e1, LinkedHashMap::new));
  }

  protected void printWordsByValue() {
    /**
     * print TreeMap of Words
     */
    bag.forEach((key, value) -> System.out.println(key + "," + value));

  }

}
