package com.happiness.springboot.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import com.happiness.springboot.utilities.ContangoUtilities;
import com.happiness.springboot.utilities.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.happiness.springboot.entity.Record;
import com.happiness.springboot.service.RecordService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/records")
public class RecordsController {

	private Logger logger = Logger.getLogger(getClass().getName());

	private RecordService recordService;
	private ContangoUtilities contangoUtilities;

	@Autowired
	public RecordsController(RecordService recordService, ContangoUtilities contangoUtilities) {
		this.recordService = recordService;
		this.contangoUtilities = contangoUtilities;
	}

	@GetMapping("/list")
	public String listRecords(Model model, HttpServletRequest request, @PageableDefault(size = 10) Pageable pageable) {

		SecurityUtil.setAuthUserId(request);

		List<Record> records;
		if(request.isUserInRole("ADMIN")) {
			records = recordService.findAll();
		} else {
			records = recordService.findAllById(SecurityUtil.authUserId());
		}

		model.addAttribute("records", records);
		logger.info("Record set: " + records);
		return "operations/list-records";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {

		// create model attribute to bind form data and set userId for the records
		Record record = new Record();
		record.setUserId(SecurityUtil.authUserId());

		model.addAttribute("record", record);

		return "operations/record-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("recordId") int id, Model model) {

		// get the record from the service
		Record record = recordService.findById(id);

		// set record as a model attribute to pre-populate the form
		model.addAttribute("record", record);

		// send over to our form
		return "operations/record-form";
	}


	@PostMapping("/save")
	public String saveRecord(@ModelAttribute("record") @Valid Record record, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "operations/record-form";
		}
		else {
			if(record.getLongTrade()) {
				record.setGain(Math.round(((record.getExit() - record.getEntry()) / record.getEntry()) * 10000.0) / 100.0);
			}else {
				record.setGain(Math.round(((record.getEntry() - record.getExit()) / record.getEntry()) * 10000.0) / 100.0);
			}
			recordService.save(record);

			// use a redirect to prevent duplicate submissions
			return "redirect:/records/list";
		}
	}


	@GetMapping("/delete")
	public String delete(@RequestParam("recordId") int id) {

		// delete the record
		recordService.deleteById(id);

		// redirect to /records/list
		return "redirect:/records/list";

	}

	@GetMapping("/converter")
	public String showConverter(Model model) {
		model.addAttribute("contango", contangoUtilities.getContango());
		return "operations/converter";
	}

	@GetMapping("/converterResult")
	public String showConverterResult(@RequestParam("action") Boolean action, @RequestParam("alertPrice") Double alertPrice, Model model) {

		model.addAttribute("alert", recordService.getConvertedAlerts(action, alertPrice));
		return "operations/converter-result";
	}


	@GetMapping("/search")
	public String search(@RequestParam("symbol") String symbol, @RequestParam("date") String date, Model model) {

		// check inputted symbol and date, if both are empty then just give list of all operations
		if (symbol.trim().isEmpty() && date.trim().isEmpty()) {
			return "redirect:/records/list";
		}
		// else, if only date is empty search by symbol
		else if(!symbol.trim().isEmpty() && date.trim().isEmpty()){
			List<Record> records = recordService.searchBy(symbol);
			model.addAttribute("records", records);
			return "operations/list-records";
		}
		// else, if only symbol is empty search by date
		else if(symbol.trim().isEmpty() && !date.trim().isEmpty()){
			List<Record> records = recordService.searchBy(LocalDate.parse(date));
			model.addAttribute("records", records);
			return "operations/list-records";
		} else {
			List<Record> records = recordService.searchBy(symbol, LocalDate.parse(date));
			model.addAttribute("records", records);
			return "operations/list-records";
		}
	}
}


















