package DAO;

import models.TableData;
import reflection.ReflectionTable;
import utils.StringUtils;

import java.util.*;

public class SQLGenerator {
    private String tableName;
    private List<String> pkNames ;
    private List<String> atributesNames;
    private List<Object>  atributesValues = new ArrayList();
    private HashMap<String,Object> nameValue = new HashMap<>();
    private TableData tableData;
    private boolean needPk =  true;



    public SQLGenerator(TableData tab){
        this.tableName = tab.getTableName();
        this.atributesNames = tab.getFields();
        this.pkNames = tab.getPks();
        this.tableData  = tab;
        this.generateHashMap();
    }

    public HashMap<String,Object> generateHashMap(){
        Integer index = 0;

        for(String name: this.atributesNames){
            Object value = ReflectionTable.getFieldValue(this.tableData,name);
            if(ReflectionTable.getFieldValue(this.tableData,name) != null && ReflectionTable.getFieldValue(this.tableData,name) != "")
            this.nameValue.put(name,ReflectionTable.getFieldValue(this.tableData,name));
        }
        return  this.nameValue;
    }
    public HashMap<String,Object> generateHashMap(boolean ignoreNullValues){
        Integer index = 0;

        for(String name: this.atributesNames){
            Object value = ReflectionTable.getFieldValue(this.tableData,name);
                this.nameValue.put(name,ReflectionTable.getFieldValue(this.tableData,name));
        }
        return  this.nameValue;
    }


    public String  selectAllStatement(){
        return "SELECT "+this.columnsSyntax()+" FROM "+this.tableName;
    }

    public String  selectStatement(){
        return "SELECT "+this.columnsSyntax()+" FROM "+this.tableName +" "+this.whereSyntax();
    }

    public String  updateStatement(){
        return "UPDATE "+this.tableName+" "+this.updateSintaxe()+" "+whereSyntax() ;
    }

    public String  deleteStatement(){
        return "DELETE FROM "+ this.tableName+" "+whereSyntax();
    }

    public String insertStatement(){
        this.needPk = false;
        List<String> fieldNames =  this.tableData.getFields();
        List<Object> fieldValues =  new ArrayList<>();

        for(String fieldName: fieldNames){
            fieldName = StringUtils.removeUnderscore(fieldName);
            fieldValues.add(ReflectionTable.getFieldValue(tableData, fieldName));
        }

        return "INSERT INTO "+tableData.getTableName() + " " + this.columnsSyntax() +" VALUES ( "+ this.valuesForInsert()+ ")";
    }

    private String whereSyntax() {
        String where= " WHERE 1=1 " ;
        if (this.nameValue != null){
            if(this.nameValue.size()  >= 1) {
                for(Map.Entry<String,Object> entry: this.nameValue.entrySet()) {
                    if(entry.getKey() != null && entry.getKey() != ""){
                        where+=" AND " + entry.getKey()  + "="  + "?" ;
                    }else{
                        System.out.println("Não há valor a ser preenchido");
                    }
                }
            }
        }

        return where;
    }

    private String columnsSyntax(){
        //Alinha o nome dos campos da Sql. (coluna1, coluna2,)
        List<String> fields = new ArrayList<>();
        for(String name :this.nameValue.keySet() ){
          if(name != null && name != "")
              fields.add(name);
        }
        String columns = "";
        if(!this.needPk && pkNames.size() > 1) {
            for(String pk: this.pkNames ){
                    fields.remove(fields.indexOf(pk));
            }
        }

        if(fields.size() < 1){
            for(String fied: tableData.getFields()){
                fields.add(fied);
            }
        }
        columns+= String.join(",",fields);
        return 	columns;
    }

    private String valuesForInsert(){
        List<String> convertedValues = new ArrayList<>();
        for(Map.Entry<String,Object> entry: this.nameValue.entrySet()){
                convertedValues.add("?");

        }
        return String.join(",",convertedValues);
    }

    private String updateSintaxe() {
        String updateSets = " ";
        for(Map.Entry<String,Object> entry: this.nameValue.entrySet()) {
            if(entry.getValue() instanceof Number)
                updateSets+=entry.getKey() +" = "+entry.getValue().toString()+ ", ";
            else
                updateSets+=entry.getKey() +" = '"+entry.getValue().toString()+ "', ";
        }

        return updateSets;
    }


}
