package com.huuhkisowl.graphql.utilities;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.huuhkisowl.graphql.dataFetchers.*;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import jakarta.annotation.PostConstruct;

import static graphql.GraphQL.newGraphQL;
import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

@Component
public class GraphQlUtility {

	@Value("classpath:graphql/schemas.graphqls")
    private Resource schemaResource;
    
    private AllMoviesDataFetcher allMoviesDataFetcher;
    private MovieDataFetcher movieDataFetcher;
    private GenreDataFetcher genreDataFetcher;
    private ActorDataFetcher actorDataFetcher;
    private DirectorDataFetcher directorDataFetcher;
    private CreateMovieDataFetcher createMovieDataFetcher;
    
    @Autowired
    GraphQlUtility(AllMoviesDataFetcher allMoviesDataFetcher, MovieDataFetcher movieDataFetcher,
    		GenreDataFetcher genreDataFetcher, ActorDataFetcher actorDataFetcher, DirectorDataFetcher directorDataFetcher,
    		CreateMovieDataFetcher createMovieDataFetcher) throws IOException {
        this.allMoviesDataFetcher = allMoviesDataFetcher;
        this.movieDataFetcher = movieDataFetcher;
        this.genreDataFetcher = genreDataFetcher;
        this.actorDataFetcher = actorDataFetcher;
        this.directorDataFetcher = directorDataFetcher;
        this.createMovieDataFetcher = createMovieDataFetcher;
    }
    
    @PostConstruct
    public GraphQL createGraphQlObject() throws IOException {
        File schemas = schemaResource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemas);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        return newGraphQL(schema).build();
    }
    
    public RuntimeWiring buildRuntimeWiring(){
        return newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                    .dataFetcher("movies", allMoviesDataFetcher)
                    .dataFetcher("movie", movieDataFetcher))
                .type("Movie", typeWiring -> typeWiring
                    .dataFetcher("genres", genreDataFetcher)
                    .dataFetcher("actors", actorDataFetcher)
                    .dataFetcher("director", directorDataFetcher))
                .type("Mutation", typeWiring -> typeWiring
                	.dataFetcher("createMovie", createMovieDataFetcher))
                .build();
    }
}
