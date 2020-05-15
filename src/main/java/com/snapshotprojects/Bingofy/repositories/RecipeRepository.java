package com.snapshotprojects.Bingofy.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.snapshotprojects.Bingofy.entities.ApplicationUser;
import com.snapshotprojects.Bingofy.entities.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
		
	@Query("FROM recipe r WHERE user_id = ?1")
	List<Recipe> findRecipeByUserId(Long id);
	
}
