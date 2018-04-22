package clc.jdbc.serviceimpl;

import clc.jdbc.Idao.UserInfoDao;
import clc.jdbc.Iservice.IUserInfoService;
import clc.jdbc.daoImpl.UserInfoDaoImpl;
import clc.jdbc.entity.UserInfo;

import java.util.List;

public class UserInfoServiceImpl implements IUserInfoService {
    UserInfoDao dao=new UserInfoDaoImpl();
    @Override
    public List<UserInfo> queryUserInfo(String sql) throws Exception {
        return dao.queryUserInfo(sql);
    }

    @Override
    public List<UserInfo> queryUserInfo(String sql, Object[] params) throws Exception {
        return dao.query(sql,params);
    }

    @Override
    public UserInfo queryUserInfoById(Integer id) throws Exception {
        return dao.queryUserInfoById(id);
    }
}
