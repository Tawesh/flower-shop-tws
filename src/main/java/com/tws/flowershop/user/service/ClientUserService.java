package com.tws.flowershop.user.service;

import com.tws.flowershop.comment.utils.TokenUtils;
import com.tws.flowershop.user.entity.ClientUserEntity;
import com.tws.flowershop.user.entity.TokenEntity;
import com.tws.flowershop.user.mapper.ClientUserMapper;
import com.tws.flowershop.user.service.impl.ClientUserImpl;
import com.tws.flowershop.comment.utils.DateTimeUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author TaoWeiShu
 * @date: 2023/10/7  19:44
 */
@Service
public class ClientUserService {

    @Resource
    private ClientUserMapper clientUserMapper;

    @Resource
    private ClientUserImpl clientUser;

    @Resource
    private DateTimeUtil dateTimeUtil;

    @Resource
    private TokenUtils tokenUtils;
    /**
     * 搜索用户
     *
     * @param account  账号
     * @param password 密码
     * @return
     */

    @Resource
    private TokenService tokenService;

    public ClientUserImpl searchUser(String account, String password) {
        ClientUserEntity clientUserEntity = clientUserMapper.searchUser(account, password);
        System.out.println(clientUserEntity);
        String timestamp = dateTimeUtil.getTimestamp();
        if (clientUserEntity.getAccount() != null) {
            clientUser.setMsg("success");
            clientUser.setUserId(clientUserEntity.getId());
            //查询用户的一般信息
            //生成token
            Map<String, String> token = tokenUtils.setToken(clientUserEntity.getId(), clientUserEntity.getAccount(), clientUserEntity.getUserName());
            //将token存到数据库
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setToken(token.get("token"));
            tokenEntity.setKey(token.get("key"));
            tokenService.addToken(tokenEntity);
            //后端加密原则 9token9
            String newToken = "ITSFLOWER" + token.get("token") + "REWOLFSTI";
            clientUser.setToken(newToken);
            clientUser.setTimestamp(timestamp);
            clientUser.setUsername(clientUserEntity.getUserName());
            clientUser.setAccount(clientUserEntity.getAccount());
        } else {
            clientUser.setMsg("fail");
            clientUser.setUsername(null);
            clientUser.setToken(null);
            clientUser.setUserId(null);
            clientUser.setTimestamp(timestamp);
            clientUser.setAccount(null);

        }
        return clientUser;
    }

    /**
     * 注册用户
     *
     * @param
     * @return
     */
    public boolean toRegister(ClientUserEntity clientUserEntity) {
        try {
            //注册账号密码表
            int i = clientUserMapper.insertUser(clientUserEntity);
            return true;
        } catch (Exception exception) {
            System.out.println("注册数据时出现错误" + exception);
            return false;
        }

    }

    /**
     * 判断用户是否存在
     *
     * @param account 账号
     * @return true/false
     */
    public boolean userIsExists(String account) {
        long l = clientUserMapper.userNum(account);
        System.out.println("查询到的用户数" + l);
        return l > 0;
    }

    /**
     * 重制密码
     *
     * @param newPwd 新密码
     * @param acc    账号
     * @return boolean
     */
    public boolean forgetPassword(String newPwd, String acc) {
        try {
            return clientUserMapper.userForgetPwd(newPwd, acc) == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean idIsExists(String userId) {
        return clientUserMapper.idNum(userId) == 1;
    }

    //管理员使用
    public List<ClientUserEntity> adminFindAll() {
        try {
            return clientUserMapper.adminFindAll();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean adminDeleteUser(String id) {
        try {
            int i = clientUserMapper.deleteUserById(id);
            return true;
        } catch (Exception e) {
            System.out.println("管理员删除用户时出现异常" + e);
            return false;
        }
    }

    public boolean adminEditUser(ClientUserEntity clientUser) {
        try {
            int i = clientUserMapper.updateUser(clientUser);
            return true;
        } catch (Exception e) {
            System.out.println("管理员编辑用户时发生异常" + e);
            return false;
        }
    }

    public ClientUserEntity adminSelById(String id) {
        try {
            return clientUserMapper.adminSelectById(id);
        } catch (Exception e) {
            System.out.println("管理员查单个用户时出现异常+" + e);
            return null;
        }
    }

    public List<ClientUserEntity> adminSelByAcc(String acc) {
        try {
            ArrayList<ClientUserEntity> userEntities = new ArrayList<>();
            userEntities.add(clientUserMapper.adminSelectByAcc(acc));
            return userEntities;
        } catch (Exception e) {
            System.out.println("管理员查单个用户时出现异常+" + e);
            return null;
        }
    }
}
