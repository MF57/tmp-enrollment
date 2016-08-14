package org.tmp.enrollment.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mf57 on 14.08.2016.
 */
@RestController
public class TestController {

    @Autowired
    TestService service;

    @RequestMapping(value = "/api/tests", method = RequestMethod.POST)
    public void createTest(@RequestBody TestRequest request) {
        System.out.println("I'm in controller");
        service.addNewTestEntry(request.getName(), request.getMessage());
    }

    @RequestMapping(value = "/api/tests", method = RequestMethod.GET)
    public List<TestRequest> getAllTests() {
        return service.getAll().stream()
                .map(test -> new TestRequest(test.getName(), test.getMessage()))
                .collect(Collectors.toList());
    }
}
