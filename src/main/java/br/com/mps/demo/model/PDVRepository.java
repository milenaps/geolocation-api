package br.com.mps.demo.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PDVRepository extends MongoRepository<PDV, Long>, QuerydslPredicateExecutor<PDV> {

}
