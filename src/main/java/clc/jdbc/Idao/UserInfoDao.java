package clc.jdbc.Idao;

import clc.jdbc.entity.UserInfo;

import java.util.List;

public interface UserInfoDao extends IJDBCBase<UserInfo,Integer>{
    List<UserInfo> queryUserInfo(String sql)throws Exception;
    List<UserInfo> queryUserInfo(String sql,Object[]params)throws Exception;
    UserInfo queryUserInfoById(Integer id)throws Exception;
}
