package com.lemparty.data;

import com.lemparty.entity.PasswordUser;
import com.lemparty.entity.User;
import com.lemparty.entity.User;
import com.lemparty.entity.User;
import com.lemparty.exception.InvalidUserException;
import com.lemparty.exception.UserExistsException;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters.*;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Updates.*;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public class MongoUserDAO {
    MongoCollection<User> collection;

    public MongoUserDAO(String url){
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().register(
                        "com.lemparty.entity.User",
                        "com.lemparty.entity.Profile",
                        "com.lemparty.entity.User"
                ).automatic(true).build()));

        //"mongodb://localhost:27017"
        MongoClient mongoClient = MongoClients.create(url);
        MongoDatabase database = mongoClient.getDatabase("lemparty-cook");
        collection = database.getCollection("userservice", User.class).withCodecRegistry(pojoCodecRegistry);
    }

    public User create(PasswordUser user) throws UserExistsException {

        User existingUser = getUserByEmail(user.getEmail());

        if(existingUser == null){
            collection.insertOne(user);
            return user;
        } else{
            System.out.println("User already exists for "+user.getEmail());
            throw new UserExistsException(user.getEmail());
        }

    }

    public User getUserByEmail(String email){
        User obtainedUser = collection.find(eq("email", email)).first();
        System.out.println("Obtained User is not Null: "+String.valueOf(obtainedUser != null));
        //System.out.println("User: "+String.valueOf(obtainedUser.getFirstname()+" "+obtainedUser.getEmail()));

        return obtainedUser;
    }

    public boolean update(User user) throws InvalidUserException {

        User existingUser = getUserByEmail(user.getEmail());

        if(existingUser != null){
            collection.findOneAndReplace(and(eq("email", user.getEmail())), user);
            return true;
        } else{
            throw new InvalidUserException(user.getEmail());
        }

    }

}
