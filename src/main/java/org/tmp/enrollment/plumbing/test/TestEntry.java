package org.tmp.enrollment.plumbing.test;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * Created by mf57 on 14.08.2016.
 */
public class TestEntry {
    @Id
    private ObjectId id;
    private String name;
    private String message;

    public TestEntry(String name, String message) {
        this.id = ObjectId.get();
        this.name = name;
        this.message = message;
    }


    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
