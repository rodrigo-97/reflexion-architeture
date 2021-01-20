package DAO;

import models.TableData;

import reflection.ReflectionTable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class PersistData {
    TableData tableData;
    SQLGenerator sqlGenerator;
    PreparedStatement pst;
    private Connection con = Conn.getInstance();
    public PersistData(TableData tab){
        tableData = tab;
        sqlGenerator = new SQLGenerator(tableData);
    }

    public List<TableData> getData(){
        String sql = this.sqlGenerator.selectStatement();
        ResultSet rs;
        List<TableData> tablesData = new ArrayList<>();
        try {
            pst = con.prepareStatement(sql);
            pst = this.statementFill(pst,this.sqlGenerator.generateHashMap());
            System.out.println(pst);
            rs = pst.executeQuery();
            while (rs.next()){
                if(pst.getParameterMetaData().getParameterCount() >= 1){
                    tablesData.add(this.getResultSetData(rs,this.sqlGenerator.generateHashMap()));
                }else{
                    tablesData.add(this.getResultSetData(rs,this.sqlGenerator.generateHashMap(true)));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tablesData;
    }

    public boolean insert(){
        String sql = this.sqlGenerator.insertStatement();
        boolean isSuccess = false ;
        List<TableData> tablesData = new ArrayList<>();
        try {
            pst = con.prepareStatement(sql);
            pst = this.statementFill(pst,this.sqlGenerator.generateHashMap());
            System.out.println(pst);
            if(pst.executeUpdate() >= 1){
                isSuccess = true ;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return isSuccess;
    }

    private PreparedStatement statementFill(PreparedStatement pst, HashMap<String,Object> data){
        boolean typeFound = false;
        int colIndex = 1;
        for(Map.Entry<String, Object> entry: data.entrySet()){
            try {
            if(entry.getValue() instanceof String){
                pst.setString(colIndex,(String) entry.getValue());
                typeFound = true;
            }else if(entry.getValue() instanceof Integer){
                pst.setInt(colIndex,(Integer) entry.getValue());
                typeFound = true;
            }else if(entry.getValue() instanceof Date){
                pst.setDate(colIndex, (java.sql.Date) entry.getValue());
                typeFound = true;
            }else if(entry.getValue() instanceof Float){
                pst.setFloat(colIndex,(Float)entry.getValue());
                typeFound = true;
            }else if(entry.getValue() instanceof Boolean){
                pst.setBoolean(colIndex,(Boolean)entry.getValue());
                typeFound = true;
            }else if(entry.getValue() instanceof Time){
                pst.setTime(colIndex,(Time)entry.getValue());
                typeFound = true;
            } else {
                System.out.println("Tipo para este dado não declarado:"+entry.getKey());
            }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            colIndex++;
        }


        return pst;
    }

    private TableData getResultSetData(ResultSet rs,HashMap<String,Object> data){
        //@TODO tratar os demais valores
        boolean typeFound = false;
        int colIndex = 1;
        TableData tableData = null;
        try {
            Class tableClass =   this.tableData.getClass();
            Constructor<?> cons = tableClass.getConstructor();
            tableData = (TableData) cons.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        for(Map.Entry<String, Object> entry: data.entrySet()){
        System.out.println(ReflectionTable.getTypeField(tableData,entry.getKey()));
            try {
                if(ReflectionTable.getTypeField(tableData,entry.getKey()).equals(String.class)){
                     ReflectionTable.setValueField(tableData,entry.getKey(),rs.getString(colIndex));
                    typeFound = true;
                }else if(ReflectionTable.getTypeField(tableData,entry.getKey()).equals(Integer.class)){
                    ReflectionTable.setValueField(tableData,entry.getKey(),rs.getInt(colIndex));
                    typeFound = true;
                }else if(entry.getValue() instanceof Date){
                    ReflectionTable.setValueField(tableData,entry.getKey(),rs.getDate(colIndex));
                    typeFound = true;
                }else if(entry.getValue() instanceof Float){
                    ReflectionTable.setValueField(tableData,entry.getKey(),rs.getFloat(colIndex));
                    typeFound = true;
                }else if(entry.getValue() instanceof Boolean){
                    ReflectionTable.setValueField(tableData,entry.getKey(),rs.getBoolean(colIndex));
                    typeFound = true;
                }else if(entry.getValue() instanceof Time){
                    ReflectionTable.setValueField(tableData,entry.getKey(),rs.getTime(colIndex));
                    typeFound = true;
                } else {
                    System.out.println("Tipo para este dado não declarado(Obter dados)"+entry.getKey());
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            colIndex++;
        }


        return tableData;
    }
}
