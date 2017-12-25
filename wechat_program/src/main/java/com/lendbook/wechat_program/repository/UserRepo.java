package com.lendbook.wechat_program.repository;

import com.lendbook.wechat_program.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
}
