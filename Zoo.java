package javacodegeeks;

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

