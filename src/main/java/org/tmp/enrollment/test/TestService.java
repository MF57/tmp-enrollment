package org.tmp.enrollment.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mf57 on 14.08.2016.
 */
@Service
public class TestService {

    @Autowired
    private TestRepository repository;

    public void addNewTestEntry(String name, String message) {
        System.out.println("I'm in service");
        TestEntry entry = new TestEntry(name, message);
        repository.save(entry);
    }

    public List<TestEntry> getAll() {
        return repository.findAll();
    }
}
