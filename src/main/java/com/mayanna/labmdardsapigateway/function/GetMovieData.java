package com.mayanna.labmdardsapigateway.function;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.mayanna.labmdardsapigateway.daoModels.Movie;
import com.mayanna.labmdardsapigateway.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class GetMovieData implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {


    private UsersRepository usersRepository;


    @Autowired
    public GetMovieData(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public APIGatewayProxyResponseEvent apply(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent) {

        Integer movieId = Integer.parseInt(apiGatewayProxyRequestEvent.getQueryStringParameters().get("movieId"));
        APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent = new APIGatewayProxyResponseEvent();

        Optional<Movie> movie = null;

        try {
            movie = usersRepository.findById(movieId);
        } catch (Exception e) {
            apiGatewayProxyResponseEvent.setBody(" Movie could not be saved due to the following error " + e.getMessage());
        }
        apiGatewayProxyResponseEvent.setBody(movie.isPresent() ? movie.get().toString() : "No Movie found");
        return apiGatewayProxyResponseEvent;
    }
}