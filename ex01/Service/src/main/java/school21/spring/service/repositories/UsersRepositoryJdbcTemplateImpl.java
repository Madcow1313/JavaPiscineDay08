package school21.spring.service.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private JdbcTemplate jdbcTemplate;
    private class MyRowMapper implements RowMapper<User>{

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(rs.getLong(1), rs.getString(2));
            return user;
        }
    }
    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public User findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM models.user WHERE identifier = ?", new MyRowMapper(), id);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM models.user", new MyRowMapper());
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update("INSERT INTO models.user (email) VALUES (?)", entity.getEmail());
        entity.setIdentifier(findByEmail(entity.getEmail()).get().getIdentifier());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("UPDATE models.user SET email = ? WHERE identifier = ?",
                entity.getEmail(), entity.getIdentifier());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM models.user WHERE identifier = ?", id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
       try {
           return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM models.user WHERE email = ?",
                   new MyRowMapper(), email));
       }
       catch (Exception e){}
       return Optional.empty();
    }
}
