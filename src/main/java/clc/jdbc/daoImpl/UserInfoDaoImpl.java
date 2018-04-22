package clc.jdbc.daoImpl;

import clc.jdbc.Idao.UserInfoDao;
import clc.jdbc.commons.JDBCBaseImpl;
import clc.jdbc.entity.UserInfo;

import java.util.List;

public class UserInfoDaoImpl extends JDBCBaseImpl<UserInfo,Integer> implements UserInfoDao {
    @Override
    public List<UserInfo> queryUserInfo(String sql)throws Exception {
        return super.query(sql);
    }

    @Override
    public List<UserInfo> queryUserInfo(String sql, Object[] params) throws Exception{
        return super.query(sql,params);
    }

    @Override
    public UserInfo queryUserInfoById(Integer id) throws Exception{
        return super.queryById(id);
    }
}
