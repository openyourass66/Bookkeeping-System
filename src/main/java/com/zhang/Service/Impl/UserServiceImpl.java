package com.zhang.Service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhang.Exception.BusinessException;
import com.zhang.Mapper.ConsumptionMapper;
import com.zhang.Mapper.UserMapper;
import com.zhang.Pojo.DTO.UpdatePasswordDTO;
import com.zhang.Pojo.DTO.LoginDTO;
import com.zhang.Pojo.DTO.UserQueryDTO;
import com.zhang.Pojo.Entity.PageResult;
import com.zhang.Pojo.Entity.User;
import com.zhang.Pojo.VO.LoginVO;
import com.zhang.Pojo.VO.UserVO;
import com.zhang.Properties.JwtProperties;
import com.zhang.Service.UserService;
import com.zhang.Utils.JwtUtil;
import com.zhang.Utils.PasswordUtil;
import com.zhang.Utils.RedisUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ConsumptionMapper consumptionMapper;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 分页查询
     * @param userQueryDTO
     * @return
     */
    @Override
    public PageResult<UserVO> page(UserQueryDTO userQueryDTO){
        // 生成缓存键
        String cacheKey = "user:page:" + JSON.toJSONString(userQueryDTO);
        
        // 先从缓存中获取
        Object cachedData = redisUtil.get(cacheKey);
        if (cachedData != null) {
            return JSON.parseObject(cachedData.toString(), PageResult.class);
        }
        
        // 从数据库查询
        LocalDateTime beginDateTime = userQueryDTO.getBeginDate()==null?null:userQueryDTO.getBeginDate().atStartOfDay();
        LocalDateTime endDateTime = userQueryDTO.getEndDate()==null?null:userQueryDTO.getEndDate().atTime(23, 59, 59, 999_999_999);
        userQueryDTO.setBeginDateTime(beginDateTime);
        userQueryDTO.setEndDateTime(endDateTime);
        PageHelper.startPage(userQueryDTO.getPage(),userQueryDTO.getPageSize());
        Page<UserVO> page = userMapper.page(userQueryDTO);
        PageResult<UserVO> result = new PageResult<>(page.getTotal(),page.getResult());
        
        // 存入缓存，设置过期时间为5分钟
        redisUtil.set(cacheKey, JSON.toJSONString(result), 300);
        
        return result;
    }
    /**
     * 登录
     * @param loginDTO
     * @return
     */
    @Override
    public LoginVO login(LoginDTO loginDTO) {
        User u = userMapper.selectByUsername(loginDTO.getUsername());
        if (u != null && PasswordUtil.matches(loginDTO.getPassword(), u.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", u.getId());
            String jwt = JwtUtil.generateJwt(jwtProperties,claims);
            return new LoginVO(u.getId(), u.getUsername(), jwt);
        }
        return null;
    }
    /**
     * 保存用户
     * @param u
     */
    @Override
    public void save(User u){
        if(u.getSelfDescription()== null){
            u.setSelfDescription("这个人很懒，什么都没写");
        }
        // 加密密码
        u.setPassword(PasswordUtil.encodePassword(u.getPassword()));
        u.setCreateTime(LocalDateTime.now());
        u.setUpdateTime(LocalDateTime.now());
        userMapper.insert(u);
        // 清除缓存
        clearUserCache();
    }

    /**
     * 修改密码
     * @param updatePasswordDTO
     */
    @Override
    public void updatePassword(UpdatePasswordDTO updatePasswordDTO) {
        if (updatePasswordDTO.getOldPassword().equals(updatePasswordDTO.getPassword())) {
            throw new BusinessException("新密码不能与旧密码相同");
        }

        User u = selectById(updatePasswordDTO.getId());
        if (u == null) {
            throw new BusinessException("用户不存在");
        }

        if (!PasswordUtil.matches(updatePasswordDTO.getOldPassword(), u.getPassword())) {
            throw new BusinessException("旧密码错误");
        }

        User user = new User();
        user.setId(updatePasswordDTO.getId());
        user.setPassword(updatePasswordDTO.getPassword());
        userMapper.update(user);
        // 清除缓存
        clearUserCache();
    }
    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Override
    public User selectById(Long id){
        // 生成缓存键
        String cacheKey = "user:id:" + id;
        
        // 先从缓存中获取
        Object cachedData = redisUtil.get(cacheKey);
        if (cachedData != null) {
            return JSON.parseObject(cachedData.toString(), User.class);
        }
        
        // 从数据库查询
        User result = userMapper.selectById(id);
        
        // 存入缓存，设置过期时间为5分钟
        if (result != null) {
            redisUtil.set(cacheKey, JSON.toJSONString(result), 300);
        }
        
        return result;
    }
    /**
     * 修改用户
     * @param u
     */
    @Override
    public void update(User u){
        u.setUpdateTime(LocalDateTime.now());
        userMapper.update(u);
        // 清除缓存
        clearUserCache();
        // 清除单个用户缓存
        redisUtil.delete("user:id:" + u.getId());
    }
    /**
     * 删除用户
     * @param id
     */
    @Override
    public void deleteById(Long id){
        Integer count = consumptionMapper.countByUserId(id);
        if(count > 0){
            throw new BusinessException("该用户有消费记录，不能删除");
        }
        userMapper.deleteById(id);
        // 清除缓存
        clearUserCache();
        // 清除单个用户缓存
        redisUtil.delete("user:id:" + id);
    }
    
    /**
     * 清除用户缓存
     */
    private void clearUserCache() {
        // 清除所有用户分页缓存
        redisUtil.deleteByPattern("user:page:*");
        // 清除所有报表缓存
        redisUtil.deleteByPattern("report:*");
    }
}
