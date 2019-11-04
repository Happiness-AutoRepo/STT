package com.happiness.springboot.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.happiness.springboot.dao.RecordRepository;
import com.happiness.springboot.entity.Record;
import com.happiness.springboot.transferobject.Alert;
import com.happiness.springboot.transferobject.GlobalQuote;
import com.happiness.springboot.utilities.RestUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecordServiceImpl implements RecordService {

	private Logger logger = Logger.getLogger(getClass().getName());

	private RecordRepository recordRepository;
	private RestUtilities restUtilities;
	
	@Autowired
	public RecordServiceImpl(RecordRepository recordRepository, RestUtilities restUtilities) {
		this.recordRepository = recordRepository;
		this.restUtilities = restUtilities;
	}
	
	@Override
	public List<Record> findAll() {
		return recordRepository.findAllByOrderByOpenDateAsc();
	}

	@Override
	public List<Record> findAllById(int userId) {
		return recordRepository.findByUserIdOrderByOpenDateAsc(userId);
	}

	@Override
	public Record findById(int theId) {
		Optional<Record> result = recordRepository.findById(theId);
		
		Record theEmployee = null;
		
		if (result.isPresent()) {
			theEmployee = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + theId);
		}
		
		return theEmployee;
	}

	@Override
	public void save(Record record) {
		recordRepository.save(record);
	}

	@Override
	public void deleteById(int theId) {
		recordRepository.deleteById(theId);
	}

	@Override
	public List<Record> searchBy(String symbol, LocalDate date) {
		return recordRepository.findBySymbolContainsAndOpenDateContainsAllIgnoreCase(symbol, date);
	}

	@Override
	public List<Record> searchBy(String symbol) {
		return recordRepository.findBySymbolContainsAllIgnoreCaseOrderByOpenDateAsc(symbol);
	}

	@Override
	public List<Record> searchBy(LocalDate date) {
		return recordRepository.findByOpenDateContainsAllIgnoreCase(date);
	}

	@Override
	public Alert getConvertedAlerts(Boolean action, Double alertPrice) {
		Double TVIXspot = restUtilities.getSpotPrice("TVIX");
		Double UVXYspot = restUtilities.getSpotPrice("UVXY");
		Double VXXspot = restUtilities.getSpotPrice("VXX");
		Double SVXYspot = restUtilities.getSpotPrice("SVXY");

		Alert alert = new Alert();
		alert.setUVXY(alertPrice);
		alert.setAction(action);

		if(action) {
			alert.setVXX(Math.round((VXXspot - (UVXYspot - alertPrice)/1.5)*100.0)/100.0);
			alert.setTVIX(Math.round((TVIXspot - (UVXYspot - alertPrice)*4/3)*100.0)/100.0);
			alert.setSVXY(Math.round(((UVXYspot - alertPrice)/3 + SVXYspot)*100.0)/100.0);
		} else {
			alert.setVXX(Math.round((VXXspot + (alertPrice - UVXYspot)/1.5)*100.0)/100.0);
			alert.setTVIX(Math.round((TVIXspot + (alertPrice - UVXYspot)*4/3)*100.0)/100.0);
			alert.setSVXY(Math.round((SVXYspot - (alertPrice - UVXYspot)/3)*100.0)/100.0);
		}

		return alert;
	}

    @Override
    public Page<Record> findAllByOrderByOpenDateAsc(Pageable pageable) {
        return recordRepository.findAllByOrderByOpenDateAsc(pageable);
    }
}






