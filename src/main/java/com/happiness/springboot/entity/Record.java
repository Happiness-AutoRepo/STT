package com.happiness.springboot.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="records")
public class Record {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name = "userId")
	private int userId;

	@Column(name="long_short")
	private Boolean longTrade;

	@Column(name="symbol")
	private String symbol;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="open_date")
	private LocalDate openDate;

	@Column(name="entry_price")
	private Double entry;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="close_date")
	private LocalDate closeDate;

	@Column(name="exit_price")
	private Double exit;

	@Column(name="gain")
	private Double gain;

	public Record() {
		
	}

	public Record(Boolean longTrade, String symbol, LocalDate openDate, Double entry, LocalDate closeDate, Double exit, Double gain) {
		this.longTrade = longTrade;
		this.symbol = symbol;
		this.openDate = openDate;
		this.entry = entry;
		this.closeDate = closeDate;
		this.exit = exit;
		this.gain = gain;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

	public Boolean getLongTrade() {
		return longTrade;
	}

	public void setLongTrade(Boolean longTrade) {
		this.longTrade = longTrade;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public LocalDate getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}

	public Double getEntry() {
		return entry;
	}

	public void setEntry(Double entry) {
		this.entry = entry;
	}

	public LocalDate getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(LocalDate closeDate) {
		this.closeDate = closeDate;
	}

	public Double getExit() {
		return exit;
	}

	public void setExit(Double exit) {
		this.exit = exit;
	}

	public Double getGain() {
		return gain;
	}

	public void setGain(Double gain) {
		this.gain = gain;
	}

    @Override
	public String toString() {
		return "Record{" +
				"id=" + id +
				", longTrade=" + longTrade +
				", symbol='" + symbol + '\'' +
				", openDate=" + openDate +
				", entry=" + entry +
				", closeDate=" + closeDate +
				", exit=" + exit +
				", gain=" + gain +
				'}';
	}
}











