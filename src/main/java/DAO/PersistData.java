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

    public List<TableData>  getData(){
        String sql = this.sqlGenerator.selectStatement();
        ResultSet rs;
        List<TableData> tablesData = new ArrayList<>();
        try {
            pst = con.prepareStatement(sql);
            pst = this.statementFill(pst,this.sqlGenerator.generateHashMap());
            System.out.println(pst);
            rs = pst.executeQuery();
            System.out.println(pst.getFetchSize());
            while (rs.next()){
                tablesData.add(this.getResultSetData(rs,this.sqlGenerator.generateHashMap()));
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

    public boolean delete(){
        String sql = this.sqlGenerator.deleteStatement();
        boolean isSuccess = false ;
        List<TableData> tablesData = new ArrayList<>();
        try {
            pst = con.prepareStatement(sql);
            pst = this.statementFill(pst,this.sqlGenerator.generateHashMap());
            System.out.println(pst);
            if(pst.executeUpdate() > 1){
                isSuccess = true ;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return isSuccess;
    }

    public boolean update(){
        String sql = this.sqlGenerator.updateStatement();
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
        int colIndex = 1;
        try {
            System.out.println(pst.getParameterMetaData().getParameterCount()+"<<<<");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        for(Map.Entry<String, Object> entry: data.entrySet()) {
            try {
                if(entry.getValue() != null && entry.getValue() != ""){
                    pst.setObject(colIndex, entry.getValue());
                    colIndex++;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }


        return pst;
    }

    private TableData getResultSetData(ResultSet rs,HashMap<String,Object> data){
        //@TODO tratar os demais valores
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
                }else if(ReflectionTable.getTypeField(tableData,entry.getKey()).equals(Integer.class)){
                    ReflectionTable.setValueField(tableData,entry.getKey(),rs.getInt(colIndex));
                }else if(entry.getValue() instanceof Date){
                    ReflectionTable.setValueField(tableData,entry.getKey(),rs.getDate(colIndex));
                }else if(entry.getValue() instanceof Float){
                    ReflectionTable.setValueField(tableData,entry.getKey(),rs.getFloat(colIndex));
                }else if(entry.getValue() instanceof Boolean){
                    ReflectionTable.setValueField(tableData,entry.getKey(),rs.getBoolean(colIndex));
                }else if(entry.getValue() instanceof Time){
                    ReflectionTable.setValueField(tableData,entry.getKey(),rs.getTime(colIndex));
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
