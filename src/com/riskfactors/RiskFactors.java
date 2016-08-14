package com.riskfactors;

import java.util.ArrayList;
import java.util.List;

//my path: "/Users/jeanna.gindi/Documents/user_data"

public class RiskFactors {
    public static void main(String[] args) { //takes path to user_data as an argument
        List<User> users = new ArrayList<>();
        FileParser fileParser = new FileParser();
        users = fileParser.processFiles(args[0]);
        System.out.println("Files parsed :)");
    }
}
