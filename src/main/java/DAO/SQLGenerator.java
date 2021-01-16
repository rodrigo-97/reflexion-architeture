package DAO;

import annotations.Table;
import models.TableData;
import reflection.ReflectionTable;

import java.util.ArrayList;
import java.util.List;

public class SQLGenerator {
    private String tableName;
    private List<String> pkNames = new ArrayList<>();
    private List<String> atributesNames =  new ArrayList();
    private List<Object>  atributesValues = new ArrayList();
    private TableData tableData;



    public SQLGenerator(TableData tab){
        this.tableName = tab.getTableName();
        this.atributesNames = tab.getFields();
        this.pkNames = tab.getPks();
        this.tableData  = tab;
        // this.atributesValues = tab.
    }

    public String  selectStatement(){
        return "SELECT "+this.colunsSixtaxe(this.atributesNames)+" FROM "+this.tableName;
    }

    public String  selectStatement(List<String> params){
        return "SELECT "+this.colunsSixtaxe(this.atributesNames)+" FROM "+this.tableName;
    }

    public String  updateStatement(){
        return null;
    }

    public String  deleteStatement(){
        return null;
    }

    public String insertStatement(){
        List<String> fieldNames =  this.tableData.getFields();
        for(String fieldName: fieldNames){
            ReflectionTable.(tab,fieldName);
        }
        return null;
    }
//
//    private String whereSintaxe() {
//        String where =  "";
//        if(objetoBd != null) {
//            List<Object>  values  = objetoBd.myFieldValues();
//            where+= " where ";
//            for(Object value: values) {
//                if(value !=null && !value.equals(""))
//                    where+=" and " + value.toString();
//            }
//        }
//        return where;
//    }

    private String colunsSixtaxe(List<String> fields){
        //Alinha o nome dos campos da Sql. (coluna1, coluna2,)
        String columns = "";
        if(!this.needPk()) {
            fields.remove(fields.indexOf(this.pkNames.get(0)));
        }
        columns+= String.join(",",fields);
        return 	columns;
    }

    private String updateSintaxe(List<String> fields, List<Object> values) {
        String updateSets = " ";
        for(Object value:values) {
            updateSets+=fields.get(values.indexOf(value)) +" = "+value.toString()+ ", ";
        }

        return updateSets;
    }

    private boolean needPk() {
        return true;
    }
}
