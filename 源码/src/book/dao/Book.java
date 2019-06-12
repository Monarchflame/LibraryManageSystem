package book.dao;

public class Book 
{
	public String bookNumber;//图书编号
	public String bookName;//图书名称
	public String category;//类别
	public String author;//作者
	public String publishingHouse;//出版社
	public String publicationDate;//出版日期
	
	public Book(String bookNumber,String bookName,String category,String author,String publishingHouse,String publicationDate) 
	{
		this.bookNumber=bookNumber;
		this.bookName=bookName;
		this.category=category;
		this.author=author;
		this.publishingHouse=publishingHouse;
		this.publicationDate=publicationDate;
	}
	
	
}
