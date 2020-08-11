package com.example.demo.editor;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "article")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private int id;
	@Column
	private String title;
	@Column
	private String author;
	@Column
	private String timeStamp;
	@Column(name = "last_updated")
	private String updatedTimeStamp ="";
	@Column
	private String summary;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTimeStamp() {

		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getUpdatedTimeStamp() {
		return updatedTimeStamp;
	}

	public void setUpdatedTimeStamp(String updatedTimeStamp) {
		this.updatedTimeStamp = updatedTimeStamp;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	// create and parse date.
	public void createTimeStamp() {
		String pattern = "MMM dd yyyy K:mma";
		SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
		String date = dateFormatter.format(new Date());

		if (this.timeStamp == null) {
			this.setTimeStamp(date);
		} else {
			this.setUpdatedTimeStamp("updated :"+date);
		}
	}

}
