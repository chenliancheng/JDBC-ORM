package clc.jdbc.Iservice;

import clc.jdbc.entity.UserInfo;

import java.util.List;

public interface IUserInfoService {
    List<UserInfo> queryUserInfo(String sql)throws Exception;
    List<UserInfo> queryUserInfo(String sql,Object[]params)throws Exception;
    UserInfo queryUserInfoById(Integer id)throws Exception;
}
