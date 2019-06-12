package reader.business;

public abstract class Reader 
{
	public String number;//读者编号
	public String password;//读者密码
	public String name;//读者姓名
	public String gender;//读者性别
	public String phonenumber;//联系电话
	public int canBorrowNumber;//可借书数
	public int borrownumber;//已借书数
	
	public Reader(String number,String password,String name,String gender,String phonenumber,int canBorrowNumber,int borrownumber) 
	{
		this.number=number;
		this.name=name;
		this.password=password;
		this.gender=gender;
		this.phonenumber=phonenumber;
		this.canBorrowNumber=canBorrowNumber;
		this.borrownumber=borrownumber;
	}
}
