package reader.business;

import reader.dao.Student;

public interface ReaderFunction_Interface 
{
	/*
	 * 找一本书，找到返回它所有属性
	 * @param bookName 书名*/
	public void SearchBook(String bookName);
	/*
	 * 借书
	 * @param bookNumber 图书编号*/
	public boolean BorrowBook(String bookNumber);
	/*
	 * 还书
	 * @param bookNumber 图书编号*/
	public boolean ReturnBook(String bookNumber);
	/*
	 * 修改读者密码
	 * @param newPassword 新密码*/
	public boolean alterPassword(String newPassword);
	/*
	 * 续借
	 *  bookNumber 图书编号
	 */
	public boolean renew(String bookNumber) ;
}
