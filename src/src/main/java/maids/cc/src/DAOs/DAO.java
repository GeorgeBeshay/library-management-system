package maids.cc.src.DAOs;

import maids.cc.src.DMOs.Identifiable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

public class DAO <T extends Identifiable> {
    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<T> rowMapper;
    private String relationName;
    private Class<T> clazz;

    public DAO() {}

    public DAO(JdbcTemplate jdbcTemplate, String relationName, Class<T> clazz) {
        if (jdbcTemplate == null || clazz == null) {
            throw new IllegalArgumentException("Abstract DAO attributes must be not null.");
        }
        if (Objects.equals(relationName, "")) {
            throw new IllegalArgumentException("Abstract DAO relationName can't be an empty string.");
        }

        this.jdbcTemplate = jdbcTemplate;
        this.relationName = relationName;
        this.clazz = clazz;
        this.rowMapper = new BeanPropertyRowMapper<>(clazz);
        this.rowMapper.setPrimitivesDefaultedForNullValue(true);
    }

    // Getters

    public Class<T> getClazz() {
        return clazz;
    }

    public String getRelationName() {
        return relationName;
    }

    public BeanPropertyRowMapper<T> getRowMapper() {
        return rowMapper;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }


    // ------------------ CREATE OPERATIONS ------------------

    @Transactional
    public int create(T entityObject) {
        if (entityObject == null) {
            throw new IllegalArgumentException("Entity object to create can't be null");
        }

        try {
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName(relationName).usingGeneratedKeyColumns("id");
            BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(entityObject);
            return simpleJdbcInsert.executeAndReturnKey(parameterSource).intValue();

        } catch (Exception e) {
            return -1;
        }

    }

    // ------------------ READ OPERATIONS ------------------

    @Transactional
    public List<T> readAll() {
        try {
            String sql = "SELECT * FROM " + relationName;
            return jdbcTemplate.query(sql, rowMapper);

        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public List<T> readById(int id) {
        try {
            String sql = "SELECT * FROM " + relationName + " WHERE id = ?";
            return jdbcTemplate.query(sql, rowMapper, id);

        } catch (Exception e) {
            return null;
        }
    }

    // ------------------ UPDATE OPERATIONS ------------------
    // Can't be done in the abstract DAO, because we don't know the exact list of attributes.

    // ------------------ DELETE OPERATIONS ------------------

    @Transactional
    public boolean delete(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("Id can't be less than 1.");
        }

        try {
            String sql = "DELETE FROM " + relationName + " WHERE id = ?";
            return jdbcTemplate.update(sql, id) == 1;   // number of rows affected must be exactly 1
        } catch (Exception e) {
            return false;
        }
    }

}
