package clc.jdbc.commons;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class JDBCBaseImpl<T,ID extends Serializable>{
    private Class classzz;

    public JDBCBaseImpl(){
        Type type=this.getClass().getGenericSuperclass();
        ParameterizedType ptype=(ParameterizedType)type;
        Type[] types=ptype.getActualTypeArguments();
        this.classzz=(Class)types[0];
    }


    public List<T> query(String sql)throws Exception{
        return query(sql,null);
    }

    public List<T> query(String sql,Object[] params)throws Exception{
    Connection conn=DBHelper.getConnection();
    PreparedStatement pst=conn.prepareStatement(sql);
    if(params!=null) {
        for (int i=0;i<params.length;i++){
            pst.setObject(i+1,params[i]);
        }
    }
    ResultSet rs=pst.executeQuery();
    List<T> datas=convertEntity(rs);
    DBHelper.closeResource(conn,pst,rs);
    return datas;
    }

    private String getTableName(){
        return classzz.getSimpleName();
    }

    private String buildOidSql(String tableName){
       return "select * from "+tableName+ " where id=?";
    }

    public T queryById(ID id) throws Exception{
       String oIdSql=buildOidSql(getTableName());
        Connection conn=DBHelper.getConnection();
        PreparedStatement pst=conn.prepareStatement(oIdSql);
        pst.setObject(1,id);
        ResultSet rs=pst.executeQuery();
        T entity= convertEntity(rs).get(0);
        DBHelper.closeResource(conn,pst,rs);
        return entity;
    }

    private List<T> convertEntity(ResultSet rs)throws Exception{
        List<T> datas=new ArrayList<T>();
        Field[] fields=classzz.getDeclaredFields();
        ResultSetMetaData metaData=rs.getMetaData();
        int columnCount=metaData.getColumnCount();
        while(rs.next()){
            Object obj=classzz.getDeclaredConstructor().newInstance();
            for(int i=0;i<columnCount;i++){
                String columnLabel= metaData.getColumnLabel(i+1);
                Object value=rs.getObject(columnLabel);
                for(int fieldIndex=0;fieldIndex<fields.length;fieldIndex++){
                    Field field=fields[fieldIndex];
                    if(columnLabel.equals(field.getName())){
                        field.setAccessible(true);
                        field.set(obj,value);
                        break;
                    }
                }
            }
            datas.add((T)obj);
        }
        return datas;
    }
}
