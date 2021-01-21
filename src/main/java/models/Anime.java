package models;

import com.google.gson.Gson;
import reflection.ReflectionTable;
import annotations.FieldTable;
import annotations.Table;

import java.util.ArrayList;

@Table(name = "anime")
public class Anime extends TableData{

    @FieldTable(columnName = "id")
    private String id;

    @FieldTable(columnName = "name")
    private String name;

    @FieldTable(columnName = "num_episodes")
    private Integer numEpisodes;

    /* Methods */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Anime(String id, String name, Integer numEpisodes) {
        this.id = id;
        this.name = name;
        this.numEpisodes = numEpisodes;
    }

    public Anime() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumEpisodes() {
        return numEpisodes;
    }

    public void setNumEpisodes(Integer numEpisodes) {
        this.numEpisodes = numEpisodes;
    }

    @Override
    public String getTableName() {
        return ReflectionTable.getTableName(this);
    }

    @Override
    public ArrayList<String> getFields() {
        return ReflectionTable.getFields(this);
    }

    @Override
    public ArrayList<String> getPks() {
        return ReflectionTable.getPks(this);
    }

    @Override
    public ArrayList<String> getFks() {
        return ReflectionTable.getFks(this);
    }

    @Override
    public ArrayList<String> getTypeFields() {
        return ReflectionTable.getTypeFields(this);
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        String response = "{anime: " + new Gson().toJson(this) + "}";
        return (response);
    }
}
