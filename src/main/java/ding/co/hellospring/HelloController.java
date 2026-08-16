package ding.co.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HelloController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Map<Long, User> users = new HashMap<>();

    private Long nextId = 1L;
    private final Object idLock = new Object();

    @PostMapping("/users")
    public User createUser(
            @RequestBody
            User newUser
    ) {
        String sql = "INSERT INTO users (id, name, age) VALUES (?, ?, ?)";

        Long finalId;
        synchronized (idLock) {
            Long nextId = jdbcTemplate.queryForObject("SELECT COALESCE(MAX(id), 0) + 1 FROM users", Long.class);
            if (nextId == null) {
                nextId = 1L;
            }
            finalId = nextId;
            jdbcTemplate.update(connection -> {
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setLong(1, finalId);
                pstmt.setString(2, newUser.getName());
                pstmt.setInt(3, newUser.getAge());
                return pstmt;
            });

            newUser.setId(finalId);
            return newUser;
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Boot";
    }

    @GetMapping("/user")
    public User getUser() {
        User user = new User("Dingco", 30);
        return user;
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            User user1 = new User();
            user1.setId(rs.getLong("id"));
            user1.setName(rs.getString("name"));
            user1.setAge(rs.getInt("age"));
            return user1;
        }, id);
    }

    @GetMapping("/users")
    public List<User> getUserList() {
        return users.values().stream().toList();
    }
}
