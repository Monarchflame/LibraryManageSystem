package manager.business;

import book.dao.Book;
import database.Model;
import reader.dao.Student;

public interface Interface_manager 
{
	//对图书操作：
	/*
	 * 找一本书，找到返回它所有属性
	 * @param bookName 书名*/
	public Model searchBook(String bookName);
	/*
	 * 输出所有图书属性*/
	public void outEveryBook();
	/*
	 * 删除图书
	 * @param bookNumber 图书编号*/
	public boolean deleteBook(String bookNumber);
	/*
	 * 添加图书*/
	public boolean addBook(Book book,int total);
	/*
	 * 修改图书*/
	public Model alterBook(String oldBookNumber,Book newBook);
	/*
	 * 在原有基础上更改图书数目,+-bookChange
	 * @param bookName 书名
	 * @param bookChange 变更个数*/
	public boolean asBook(String bookNumber,int bookChange);
	/*
	 * 处理还书
	 * @param bookNumber 图书编号
	 * @param readerNumber 读者编号
	 * */
	public void returnBook(String bookNumber,String readerNumber);
	
	//对读者操作
	/*
	 * 查找读者信息
	 * @param readerNumber 读者编号
	 * @param readerCategary 读者类型*/
	public Model searchReader(String readerNumber,String readerCategary);
	/*
	 * 缴纳罚金
	 * @param readerNumber 读者编号
	 * @param readerCategary 读者类型
	 */
	public boolean payFine(String readerNumber,String readerCategary);
}
