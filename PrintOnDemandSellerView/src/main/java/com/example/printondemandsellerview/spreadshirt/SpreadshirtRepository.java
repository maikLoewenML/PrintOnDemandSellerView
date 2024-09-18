package com.example.printondemandsellerview.spreadshirt;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpreadshirtRepository extends CrudRepository <Spreadshirt, Long> {
}
