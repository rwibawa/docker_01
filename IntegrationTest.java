package javacodegeeks;

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

