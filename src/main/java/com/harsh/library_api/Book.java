package com.harsh.library_api;

public class Book {
	private int id;
	private String title;
	private String author;
	
	Book() {} 
	
	Book(int id,String title,String author){
		this.id=id;
		this.title=title;
		this.author=author;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getAuthor() {
		return this.author;
	}
}
