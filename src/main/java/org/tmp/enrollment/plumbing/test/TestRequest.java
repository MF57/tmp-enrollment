package org.tmp.enrollment.plumbing.test;

/**
 * Created by mf57 on 14.08.2016.
 */
public class TestRequest {
    private String name;
    private String message;

    public TestRequest() {
    }

    public TestRequest(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}


