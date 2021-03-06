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
        boolean onlyIdFullField = true;
        for(String name: this.atributesNames){
            Object value = ReflectionTable.getFieldValue(this.tableData,name);
            if(ReflectionTable.getFieldValue(this.tableData,name) != null && ReflectionTable.getFieldValue(this.tableData,name) != ""){
                this.nameValue.put(name,ReflectionTable.getFieldValue(this.tableData,name));
                onlyIdFullField =  false;
            }
        }

        for(String name: this.pkNames){
            Object value = ReflectionTable.getFieldValue(this.tableData,name);
            if(ReflectionTable.getFieldValue(this.tableData,name) != null && ReflectionTable.getFieldValue(this.tableData,name) != "")
                this.nameValue.put(name,ReflectionTable.getFieldValue(this.tableData,name));
        }

        if(nameValue.size() < 1  || onlyIdFullField ){
            for(String name: this.atributesNames){
                Object value = ReflectionTable.getFieldValue(this.tableData,name);
                    this.nameValue.put(name,ReflectionTable.getFieldValue(this.tableData,name));
            }
            for(String name: this.pkNames){
                Object value = ReflectionTable.getFieldValue(this.tableData,name);
                    this.nameValue.put(name,ReflectionTable.getFieldValue(this.tableData,name));
            }
        }

        return  this.nameValue;
    }

    public String  selectStatement(){
        String columns = this.columnsSyntax() ;
        String where = this.whereSyntax();
        return "SELECT "+columns+" FROM "+this.tableName +" "+where;
    }

    public String  updateStatement(){
        return "UPDATE "+this.tableName+" "+this.updateSintaxe()+" "+whereSyntax() ;
    }

    public String  deleteStatement(){
        return "DELETE FROM "+ this.tableName+" "+whereSyntax();
    }

    public String insertStatement(){

        return "INSERT INTO "+tableData.getTableName() + " (" + this.columnsSyntax() +")  VALUES ( "+ this.valuesForInsert()+ ")";
    }


    private String whereSyntax() {
        boolean fillValues = true;
        String where= " WHERE 1=1 " ;
        if (this.nameValue != null){
            if(this.nameValue.size()  >= 1) {
                for(Map.Entry<String,Object> entry: this.nameValue.entrySet()) {
                    if(entry.getValue() != null && entry.getValue() != ""){
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
        for(Map.Entry<String,Object> entry :this.nameValue.entrySet() ){
          if(entry.getKey() != null && entry.getKey()!= "" ){
              fields.add(entry.getKey());
          }
        }

        String columns = "";

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

    private String updateSintaxe(){
        List<String> fields = new ArrayList<>();
        int index = 0;
        for(Map.Entry<String,Object> entry: this.nameValue.entrySet()) {
                fields.add(entry.getKey()+"="+"?");

        }
        return "SET "+String.join(", ",fields);
    }


}
