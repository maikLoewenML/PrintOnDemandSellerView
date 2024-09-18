package com.example.printondemandsellerview.amazonMerchOnDemand;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmazonMerchOnDemandRepository extends CrudRepository <AmazonMerchOnDemand, Long> {
}
