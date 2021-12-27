package io.jpe.starter.interceptor;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExecutionTimeRepository extends PagingAndSortingRepository <ExecutionTimeRecord, Integer> {

}
