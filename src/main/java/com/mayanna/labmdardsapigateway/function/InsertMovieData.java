package com.mayanna.labmdardsapigateway.function;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.mayanna.labmdardsapigateway.daoModels.Movie;
import com.mayanna.labmdardsapigateway.repository.UsersRepository;
import com.mayanna.labmdardsapigateway.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class InsertMovieData implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private UsersRepository usersRepository;


    @Autowired
    public InsertMovieData(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public APIGatewayProxyResponseEvent apply(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent) {

        String apiGatewayRequestBody = apiGatewayProxyRequestEvent.getBody();

        APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent = new APIGatewayProxyResponseEvent();
        Movie savedMovie = null;

        try {
        Movie movie = Utils.convertJsonStringToObject(apiGatewayRequestBody, Movie.class);

             savedMovie = usersRepository.save(movie);
        } catch (Exception e) {
            apiGatewayProxyResponseEvent.setBody(" Movie could not be saved due to the following error " + e.getMessage());
        }

        apiGatewayProxyResponseEvent.setBody(" Successful inserted the movie into the database "+savedMovie);

        return apiGatewayProxyResponseEvent;
    }
}