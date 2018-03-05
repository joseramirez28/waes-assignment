package org.jr.waes.dao;

import org.jr.waes.dto.BinaryDataComparison;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interface that describes a MongoDB repository, Spring Boot will infer the
 * needed methods and beans so we just need to Autowire it
 * 
 * @author Jose Ramirez
 *
 */
public interface BinaryDataRepository extends MongoRepository<BinaryDataComparison, Integer> {

}
