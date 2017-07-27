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
import com.mongodb.MongoClient;

public class Zoo {

    private MongoClient client;

    public Zoo(MongoClient client) {
        this.client = client;
    }

    public void addAnimal(String name, String type) {
        Document doc = new Document("name", "lion").append("type", "mammal");
        client.getDatabase("mydb").getCollection("animals").insertOne(doc);
    }
}

