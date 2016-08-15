package com.riskfactors;

import java.sql.Connection;

public abstract class Feature {
    String dbTableName;
    protected abstract void createFeature();
    protected abstract void publishToDB(Connection conn);
    protected void setTableName(String name){
        this.dbTableName = name;
    }
    protected String getTableName(){
        return this.dbTableName;
    }
}
