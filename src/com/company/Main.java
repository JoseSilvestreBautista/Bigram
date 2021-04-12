package com.company;

import java.io.IOException;


public class Main {


    public static void main(String[] args) throws IOException {
        BagOfWords bow = new BagOfWords();
        bow.loadFile();
        bow.printWordsByValue();
    }
}