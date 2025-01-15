package com.brainstation.fantasyfootball.repository;

import com.brainstation.fantasyfootball.model.dto.RoleDto;
import com.brainstation.fantasyfootball.model.dto.UserDto;
import com.brainstation.fantasyfootball.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Shahinur Beg
 * created date: 10/20/2022
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select s from User s where s.email=?1")
    User findByEmail(String email);
    User findByUsername(String username);
    @Query(value = "select * from user s where s.username=?1",nativeQuery = true)
    UserDto findUserByUsername(String username);
    @Query(value = "select r.role from user_role r inner join user u on r.u_id=u.id where u.id=?1 ",nativeQuery = true)
    List<RoleDto> findRoleById(Long userId);

//    List<User> findUsersByEventsId(Long eventId);
}
