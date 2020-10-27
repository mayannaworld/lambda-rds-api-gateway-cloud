package com.mayanna.labmdardsapigateway.service;

import com.mayanna.labmdardsapigateway.daoModels.Movie;
import com.mayanna.labmdardsapigateway.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class MovieService {

    UsersRepository usersRepository;

    @Autowired
    public MovieService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Movie> getAllMovies() {
        return usersRepository.findAll();
    }

    public List<Movie> insertMovies(@RequestBody final List<Movie> movieList) {
        usersRepository.saveAll(movieList);
        return usersRepository.findAll();
    }

}
