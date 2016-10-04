package org.tmp.enrollment.plumbing.test;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by mf57 on 14.08.2016.
 */
public interface TestRepository extends MongoRepository<TestEntry, ObjectId> {
}
