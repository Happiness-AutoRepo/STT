package com.happiness.springboot.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.happiness.springboot.entity.Record;

public interface RecordRepository extends JpaRepository<Record, Integer> {

	List<Record> findAllByOrderByOpenDateAsc();

	List<Record> findBySymbolContainsAndOpenDateContainsAllIgnoreCase(String symbol, LocalDate date);

    List<Record> findBySymbolContainsAllIgnoreCaseOrderByOpenDateAsc(String symbol);

	List<Record> findByOpenDateContainsAllIgnoreCase(LocalDate date);

	List<Record> findByUserIdOrderByOpenDateAsc(int userId);

	Page<Record> findAllByOrderByOpenDateAsc(Pageable pageable);
}
