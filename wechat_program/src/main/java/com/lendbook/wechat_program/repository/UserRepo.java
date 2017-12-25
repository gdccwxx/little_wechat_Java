package com.lendbook.wechat_program.repository;

import com.lendbook.wechat_program.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
<<<<<<< HEAD

    //查找用户
    public User findByWechat(String wechat);
=======
    public User findByWechat (String wechat);
>>>>>>> master
}
