package com.sixpmpricetracker.repository;

import com.sixpmpricetracker.model.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductHistoryRepository extends JpaRepository<ProductHistory, Integer> {
}
