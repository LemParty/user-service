package com.lemparty.config.profile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.lemparty.entity.Profile;
import com.lemparty.entity.User;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.lemparty.data")
//@EnableTransactionManagement
public class GlobalProfile {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.dynamodb.region}")
    private String amazonDynamoDBRegion;

    @Value("${amazon.aws.accesskey}")
    private String awsAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String awsSecretKey;

    @Bean
    public String getSalt(){
        return "vIVZosBTBDhv8adNonr5mjxHq78WXQHNw8DKKqHa1RUkafwUVpcVz3azStz95CMRSW/hExuMOQAxEyL5h0KoBOebAeplJYuaFKKBMLSAcAVaiqvZ+dsRFw8lFK20+Dxck6vNEPnD06KRHt5VAjPSXnjF8M18E99njDTEO9kqwZihBgMPAMW23Flp4IpsyFz8a3R7r3ypG+kwDXP3Xyb1cfgT1Gc0v/2038vgWadLx0z75J98uXrXWygze61FeqqQRyHbUfznAZPLuRsnmeUJGOi0EJmV3vEOQ9HgY2LUBMhlaJqDEOZlilKsM+BXzO7A608KLtYns73rPXPLkFbMxqbUlq3rpXbFciXBAePqIasZsIv8S5STlraY+ty4W1fnReCP4OmwAQxCu4sS8JQMGiF1vsWWqQAKzrsy9o83csUoKkdzBue/olhbAei3tsJX+6P0t5oPii0E2QuKdgouqbGuF1KvjR9+6lle3zxe3NUoaIEAZJGpGRx4RYDOEz6dOxmlfbKZ3hSyq+dH4hejcUL5lWHScMuCeLm0CvJClB8q5WDxvSZF2E/Lj3kdmRddeYGXPLSFehnCZoGYlSfAv2VRRZqezW90zfobhvltquOWtF3tU3h8o/4qPRzK2mMt7uJwCFIzijJlak6wdJfDfwn8woN1POr0DXWMA55Uv8k=";
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(awsAccessKey, awsSecretKey);
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB =
                AmazonDynamoDBClientBuilder
                        .standard()
                        .withEndpointConfiguration(
                            new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, amazonDynamoDBRegion))
                .build();

        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        CreateTableRequest tableRequestUser = dynamoDBMapper
                .generateCreateTableRequest(User.class);
        CreateTableRequest tableRequestProfile = dynamoDBMapper
                .generateCreateTableRequest(Profile.class);

        tableRequestUser.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        tableRequestProfile.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));

        TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequestUser);
        TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequestProfile);

        return amazonDynamoDB;
    }
}
