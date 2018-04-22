package clc.jdbc.Idao;

import java.util.List;

public interface IJDBCBase<T,ID> {
    List<T> query(String sql)throws Exception;
    public List<T> query(String sql,Object[] params)throws Exception;
    public T queryById(ID id) throws Exception;
}
