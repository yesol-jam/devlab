package com.yesol.devlab.repository;

import com.yesol.devlab.vo.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
}
