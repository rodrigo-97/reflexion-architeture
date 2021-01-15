package models;

import reflection.ReflectionTable;
import annotations.FieldTable;
import annotations.Table;

import java.util.ArrayList;

@Table(name = "character")
public class Character extends TableData{

    @FieldTable(columnName = "id", isPk = true)
    private Integer id;

    @FieldTable(columnName = "name")
    private String name;

    @FieldTable(columnName = "age")
    private Integer age;

    @FieldTable(columnName = "anime_id", isFk = true)
    private Integer animeId;

    /* Methods */
    public Character() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAnimeId() {
        return animeId;
    }

    public void setAnimeId(Integer animeId) {
        this.animeId = animeId;
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
        com.google.gson.Gson gson = new com.google.gson.Gson();
        return gson.toJson(this);
    }
}

