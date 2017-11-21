package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /// 메소드 이름을 규칙에 맞게만해 주어도 Spring Data JPA 가 알아서 implementation을 제공한다.
    public List<User> findAllByOrderByUserNoAsc();
    public List<User> findByUserName(String userName);

    /// Named Param 처리
    @Query(value = "SELECT * FROM tbl_user t WHERE t.user_name = :name", nativeQuery = true)
    public List<User> sqlFindName(@Param("name") String name);

    /// Param 처리
    @Query("SELECT t FROM User t WHERE t.userName = ?1")
    public List<User> jpqlFindName(String userName);

    /// Like 처리
    @Query("SELECT t FROM User t WHERE t.userName like %?1%")
    public List<User> jpqlFindNameEx(String userName);

    /// EntityName 처리
    @Query("SELECT t FROM #{#entityName} t WHERE t.userName like %?1%")
    public List<User> jpqlFindNameEx2(String userName);
}