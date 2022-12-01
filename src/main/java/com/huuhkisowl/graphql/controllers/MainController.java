package com.huuhkisowl.graphql.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.huuhkisowl.graphql.utilities.GraphQlUtility;

import graphql.ExecutionResult;
import graphql.GraphQL;

@RestController
public class MainController {

	private GraphQL graphQL;
    private GraphQlUtility graphQlUtility;
    
    @Autowired
    MainController(GraphQlUtility graphQlUtility) throws IOException {
        this.graphQlUtility = graphQlUtility;
        graphQL = this.graphQlUtility.createGraphQlObject();
    }
    
    @PostMapping(value = "/graphql")
    public ResponseEntity query(@RequestBody String query){
    	System.out.println("query::"+query);
        ExecutionResult result = graphQL.execute(query);
        //System.out.println("errors::"+result.getErrors());
        return ResponseEntity.ok(result.getData());
    }
}
