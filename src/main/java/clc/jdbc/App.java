package clc.jdbc;

import clc.jdbc.Iservice.IUserInfoService;
import clc.jdbc.entity.UserInfo;
import clc.jdbc.serviceimpl.UserInfoServiceImpl;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )throws Exception
    {
        IUserInfoService userInfoService=new UserInfoServiceImpl();
        UserInfo userInfo=userInfoService.queryUserInfoById(1);
        System.out.println("oidquery:   "+userInfo.toString());

        String sql="select * from userinfo";
        List<UserInfo> userInfos=userInfoService.queryUserInfo(sql);
        System.out.println("\nsql query:");
        for(UserInfo user:userInfos){
            System.out.println(user.toString());
        }

        String paramSql="select name from userinfo where id=?";
        Object[]params=new Object[]{1};
        List<UserInfo>users=userInfoService.queryUserInfo(paramSql,params);
        System.out.println("\nparamsSql Query:");
        for(UserInfo user:users){
            System.out.println(user.toString());
        }
    }
}
