package com.devsuperior.uri2609.repositories;

import com.devsuperior.uri2609.projections.CategorySumProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2609.entities.Category;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(nativeQuery = true, value = "SELECT categories.name, SUM(products.amount) from products " +
            "INNER JOIN categories ON products.id_categories = categories.id " +
            "GROUP BY categories.name")
    List<CategorySumProjection> searchSQL();

}
