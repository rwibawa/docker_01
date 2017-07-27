/********************************************************************************
 * Copyright (c) 2015-2016 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package com.navisow.zoo;

import org.bson.Document;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

public class IntegrationTest {
    private static MongoClient client;

    @BeforeClass
    public static void start() {
        client = new MongoClient("192.168.99.100");
        MongoDatabase db = client.getDatabase("mydb");
        db.createCollection("animals");
    }

    @Test
    public void testMongoInsert() {
        Zoo zoo = new Zoo(client);
        zoo.addAnimal("lion", "mammal");
        FindIterable it = client.getDatabase("mydb").getCollection("animals")
            .find(new Document("name", "lion"));
        Assert.assertNotNull(it.first());
    }

    @AfterClass
    public static void stop() {
        client.close();
    }
}

