package com.mayanna.labmdardsapigateway.repository;

import com.mayanna.labmdardsapigateway.daoModels.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Movie, Integer> {
}
