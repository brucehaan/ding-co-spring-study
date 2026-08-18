package ding.co.hellospring.repository;

import ding.co.hellospring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    /*
    1. AND 조건 : 이름이 같고(AND) 나이가 같은 사람
    SQL : ... WHERE name = ? AND age = ?
     */
    List<User> findByNameAndAge(String name, int age);

    /*
    2. OR 조건 : 이름이 같거나(OR) 나이가 많은 사람
    SQL : ... WHERE name = ? OR age > ?
     */
    List<User> findByNameOrAgeGreaterThan(String name, int age);

    /*
    3. 정렬(OrderBy) : 나이 기준으로 내림차순(Desc) 정렬
    SQL : ... ORDER BY age DESC
     */
    List<User> findByAgeOrderByAgeDesc(int age);

    /*
    4. 제한(Limit) : 상위 3명만 가져오기
    SQL : ... LIMIT 3
     */
    List<User> findTop3ByAgeGreaterThan(int age);

}
