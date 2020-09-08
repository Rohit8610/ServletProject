package com.nt.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookBean {
	
	private String bookId;
	private String bookName;
	private String author;
	private String publisher;
	private int quantity;
	private int issued;

}
