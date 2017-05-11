package com.example;


import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaCompiler;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class Schema {


    @Autowired
    UserRepository userRepository;

    private DataFetcher<UserRepository.User> userDataFetcher = environment -> userRepository.getUser(environment.getArgument("id"));

    public GraphQLSchema getSchema() {
        try {
            SchemaCompiler schemaCompiler = new SchemaCompiler();
            ClassPathResource classPathResource = new ClassPathResource("schema.graphql");
            TypeDefinitionRegistry compiledSchema = schemaCompiler.compile(classPathResource.getFile());

            SchemaGenerator schemaGenerator = new SchemaGenerator();
            return schemaGenerator.makeExecutableSchema(compiledSchema, buildRuntimeWiring());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("QueryType", typeWiring -> typeWiring.typeName("QueryType")
                        .dataFetcher("user", userDataFetcher))
                .build();
    }

}
