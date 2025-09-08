package com.lianji.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lianji.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    // 继承了 BaseMapper 之后，您就自动拥有了
    // insert, delete, update, selectById, selectOne, selectList 等方法。
    // 对于简单的登录查询，我们甚至不需要在这里添加任何自定义方法。


}
