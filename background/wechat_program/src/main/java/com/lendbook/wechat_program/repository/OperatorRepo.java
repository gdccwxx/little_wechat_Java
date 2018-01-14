package com.lendbook.wechat_program.repository;

import com.lendbook.wechat_program.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperatorRepo extends JpaRepository<Operator,String> {
}
