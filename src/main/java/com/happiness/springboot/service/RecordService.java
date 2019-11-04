package com.happiness.springboot.service;

import java.time.LocalDate;
import java.util.List;

import com.happiness.springboot.entity.Record;
import com.happiness.springboot.transferobject.Alert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecordService {

    List<Record> findAll();

    List<Record> findAllById(int userId);

    Record findById(int theId);

    void save(Record record);

    void deleteById(int theId);

    List<Record> searchBy(String symbol, LocalDate date);

    List<Record> searchBy(String symbol);

    List<Record> searchBy(LocalDate date);

    Alert getConvertedAlerts(Boolean action, Double alertPrice);

    Page<Record> findAllByOrderByOpenDateAsc(Pageable pageable);
}
