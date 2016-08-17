package com.riskfactors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class RiskFactors {
    Dictionary dictionary;
    List<User> users;
    public RiskFactors(List<User> users, Dictionary dictionary){
        this.users = users;
        this.dictionary = dictionary;
    }
    public static void main(String[] args) { //takes 2 arguments: path to user_data, path to dictionary text file
        FileParser fileParser = new FileParser();
        RiskFactors riskFactors = new RiskFactors(fileParser.createUsersFromFile(args[0]), new Dictionary(args[1]));
        riskFactors.buildFeatures();
        System.out.println();
    }
    protected void buildFeatures(){
        List<Feature> featureList = new ArrayList<>();
        featureList.add(new ValidWordFeature(users, dictionary));
        featureList.add(new SharedContactFeature(users));
        calculateFeatures(featureList);
        publishToDB(featureList);
    }
    protected static void calculateFeatures(List<Feature> featureList){
        for(Feature feature: featureList){
            feature.createFeature();
        }
    }
    protected void publishToDB(List<Feature> featureList){
        try{

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/riskfactors", "", "");
            for(Feature feature: featureList){
                feature.publishToDB(connection);
            }
        }catch (Exception exc) {
            exc.printStackTrace();
        }

    }
}
