package com.techelevator.dao;

import com.techelevator.model.meal.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCategoryDao implements CategoryDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCategoryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(String name) {
        String sql = "INSERT INTO category (category_name) VALUES (?) RETURNING category_id;";
        int categoryId = jdbcTemplate.queryForObject(sql, int.class, name);
        return categoryId;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM category ORDER BY category_name;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            categories.add(mapRowToCategory(results));
        }
        return categories;
    }

    @Override
    public List<Category> findAllByRecipeId(int recipeId) {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT c.category_id, c.category_name " +
                "FROM category AS c " +
                "JOIN recipe_category AS rc ON c.category_id = rc.category_id " +
                "WHERE rc.recipe_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recipeId);
        while (results.next()) {
            categories.add(mapRowToCategory(results));
        }
        return categories;
    }

    private Category mapRowToCategory(SqlRowSet rowSet) {
        Category category = new Category();
        category.setId(rowSet.getInt("category_id"));
        category.setName(rowSet.getString("category_name"));
        return category;
    }
}
