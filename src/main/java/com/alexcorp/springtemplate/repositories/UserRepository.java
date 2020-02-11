package com.alexcorp.springtemplate.repositories;

import com.alexcorp.springtemplate.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

}
