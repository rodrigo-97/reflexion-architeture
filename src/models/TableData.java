package models;

import java.util.ArrayList;

public abstract class TableData {
    public abstract String getTableName();
    public abstract ArrayList<String> getFields();
    public abstract ArrayList<String> getPks();
    public abstract ArrayList<String> getFks();
}
