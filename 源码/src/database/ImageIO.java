package database;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ImageIO 
{	
	/**
	 * 二进制转图片
	 *
	 * @param imgByte 图片二进制数据
	 */
	public static ImageIcon saveToImgByBytes(byte [] imgByte)
	{
		ImageIcon imageIcon=null;
		if (imgByte !=  null && imgByte.length >  0 )
		{           
			imageIcon=new ImageIcon(imgByte);
		}
		 return imageIcon;
	}
	
	
	/**
	 * 根据地址获得数据的字节流
	 *
	 * @param path 图片路径
	 * @return 图片二进制数据
	 */
	public static byte[] getImageFromLocalByPath(String path) 
	{
		try 
		{
			File imageFile=new File(path);
			InputStream inStream = new FileInputStream(imageFile);
			byte[] btImg = readInputStream(inStream);// 得到图片的二进制数据
			return btImg;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 从输入流中获取数据
	 *
	 * @param inStream 输入流
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	private static byte[] readInputStream(InputStream inStream) throws IOException 
	{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[10240];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) 
		{
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
	/**
      * 将16进制字符串转换为byte[]
     * 
     * @param str 16进制字符串
     * @return 
     * 	null失败
     * 	byte[]成功
     */
    public static byte[] HnumberToBytes(String str) 
    {
        if(str == null || str.trim().equals("")) 
        {
            return null;
        }
        byte[] bytes = new byte[str.length() / 2];
        for(int i = 0; i < str.length() / 2; i++) 
        {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }
        return bytes;
    }
    
    /**
	 * 存图片
	 *
	 * @param imgByte 图片二进制数据
	 * @param imgPath 图片的保存路径
	 * @param imgName 图片的名称
	 * @return 
	 * 		1：保存正常
	 * 		0：保存失败
	 */
    public static int saveToImgByBytes_imgByte(byte [] imgByte,String imgPath,String imgName)
	{		          
		 int stateInt =  1 ; 	 
		 if (imgByte !=  null && imgByte.length >  0 )
		 {           
			 try 
			 { 
				 // 将字符串转换成二进制，用于显示图片                   
				 // 将上面生成的图片格式字符串 imgStr，还原成图片显示   
				 InputStream in =  new ByteArrayInputStream(imgByte);          
				 File file= new File(imgPath,imgName);  //可以是任何图片格式.jpg,.png等 
				 FileOutputStream fos= new FileOutputStream(file);         
				 byte [] b = new byte [ 1024 ];             
				 int nRead =  0 ;    
				 while ((nRead = in.read(b)) != - 1 ) 
				 { 
					 fos.write(b,  0 , nRead);           
				 } 
                
				 fos.flush(); 
				 fos.close();             
				 in.close();          
			 }  
			 catch (Exception e) 
			 {           
				 stateInt =  0 ;            
				 e.printStackTrace();          
			 }  
			 finally 
			 { 			 } 
        
		 } 
     
		 return stateInt; 
	}
    
	/**
	 * 面板显示头像
	 * @number 读者编号
	 * @avatar_label 头像
	 * @avatar_button 头像按钮
	 * 上面二者取一
	 * */
	public static void readerAvatarFromDatabase(String number,JLabel avatar_label,JButton avatar_button,String readersCategory)
	{
		ConnectDatabase condata=new ConnectDatabase();
		String sql="";
		if (readersCategory.equals("student")) {
			sql="select 头像 from student where 读者编号 = ?";
		} else if (readersCategory.equals("teacher")) {
			sql="select 头像 from teacher where 读者编号 = ?";
		}
		
		String []paras = {number.trim()};
		ResultSet rs=condata.queryExecute(sql,paras);
		byte[] imgByte = new byte[1024];
		try 
		{
			if(rs.next())//只有一个数据
			{
				String string = rs.getString(1);
				if(string==null)//没有头像，安排默认的
				{
					String imgString="FFD8FFE000104A46494600010102001C001C0000FFDB00430006040506050406060506070706080A100A0A09090A140E0F0C1017141818171416161A1D251F1A1B231C1616202C20232627292A29191F2D302D283025282928FFC0000B0800C800C801011100FFC4001B00010002030101000000000000000000000001060204050703FFC4003C100001040200020605090705000000000000010203110405062112133141516123337181C114223242435291B1D11524345362A1E11625727383FFDA0008010100003F00F7D209209040028124512451241240144D020900000000000000000006407B80000000000000000240000001A599B4C2C3B49F2188FF00BA9CD4E44DC598ADB486191FE6BC8D7FF5825FF06B5FF23EF0F1763AAFA682467B399D5C3DC60E6524390C47AFD577253A0000000499188A140006B67E6C1830ACB90F444EE4EF5295B7E23CACC556C178F07822F35F6A9C45B55B55200075B59BECCC1546ABBAD8BEEB97F252EFAAD963ECA1EB207734ED8D7B50DD001918832000001A9B4CE8B5D88F9E65F26A78A9E71B2CE9F6192B34EEBF04EE44354120020FB61E54D873B26C79158F4FEE7A36936716CF111ECE5237948DF053A000001950A142850A0B489CFB0F39E22D92EC73D7A0BE8235A8D3E2726BC850A142850A15E42BC8DED3673B5D9CC992D59D9237C50F4C85CD96263D8B6C725A2F89950A142850A33142850A1471B8B33171352F462D492FA34F89E76000000017AE0BCC59F5EFC77ADBE15E5EC52C6288A2680268000514AE3C9AF371A0EE6C7D3F7AAFF82AF42850A14280A02851DFE099BAADCF577CA58D53E25F850A1428519D0A02850A3CFB8D17FDF5E8BDD1B7F238200000001D4E1A5ADEE279BA8F4AA144D0A1445197B87B87B87B87B8142E38895BB84757AC8917E0578000003DC01D8E128BADDEE3D27D1B7FE087A3000033A14280A145538F313A58D8F929F66BD05F6294A00000000B6700E2AAE46464AA72627569ED52E94050A140CE850A142851ABB2C46E6E1CB8EFEC912AFC14F2BC8824827921992A48D6950F9D0A1429450A1428519318AE7A35896AAB489E27A868F07F676B2283ED3B64F6A9D0A142850A144D0A142850A1455B8C74CB3B3E5D8ADB91A9E91A9DE9E25200000028B6F076995D2267E436913D522F7AF8973A142850A1428CE8000015653789386555EFC9D737CDD127C0A7AB558E54545454ED45EE00002AD7916AE1DE1A7CCE66467B559176A46BDAEF69766351A888C4A44E4889DC6400001950A04513428511428E66D74587B24B9A3E84BFCC6F252A79FC279B02AAE3AB3219E5C94E3646BF320F5D8D3B3CD5AB46BF456EA96CFBC385953FA9C795FEC6A9D8C2E15CFC854599198ECFEAEDFC0B4EA78770F5D4EE8ACB2FF324F821D8A142850A14280A33A142850A142850A142850A02850A142850A142850A32A142850A14281069656DB0316FAFCB89153B916D7FB1CCC8E2CD7477D5F5B27B128D37F1A43F678B22FB5C7C9DC6BCFF0082E5FF0061F4671A44BF4F0DE9EC71B50F17E03BD647347EEB3A58DBCD6E47ABCB8D17C245E87E674515152D151517BD0914050001950A142850A3573B371B0A3EB32A66469E655B65C629CDB8107FE927E85733B6D9D9ABFBC6448A9F751693F0346850A142850A36313372711D78F3C91F922F22C5AEE309E2A6E74292A7DE6F252D5ADDB616C53F779915FDED5E4A742850A14285195022893195CD8E35748E463139AAAAF242A3BBE2C4674E1D6A22AF675ABD9EE429F3CF3654AAF9E47C8F5EF553E44804120004519B1CE8DE8E62AB1E9DE859B4BC573C1D08B3D1658BB3ACFAE9FA976C3CA8732149B1E549235EF43ED40106742851A9B1CD835F8CB3E43E989D89DEBE4879DEF37B91B49296E3C74FA31A2FE6720000000000037359B2C8D6E475B8EFAF16F729E8DA2DC41B586E3F993A7D2897B53FC1D4A02890696D76106B711D3E42D77353BDCA79A6DB653ECF29669D797D56A76350D20000000280000A3EB8B912E264326C772B646F62A1E91C3DB98B6D8DCE9992DFA71FC50EC02791F2CA9E2C58249E75E846D4B553CC379B4976B98B249CA34E51B3C10E750A0000050000005007DF0B2A5C2C964F8EEA91A7A7E97651ECF0DB34748EEC91BE0A6F991E7DC67B6F95657C8E077A0897E72A7D671590000000000000003A9C3BB476AF3D1F7E81FCA44F23D42391B2C69246A8AC725A29C9E28D97ECDD5BD58BE9E4F98CFD4F32E6AB6BDBE22850A142850A14280A0280A1400A1428517BE04D8ACF8D26148AAAE879B57FA4FFD9";
					imgByte=HnumberToBytes(imgString);//16进制转二进制
					ImageIcon icon=ImageIO.saveToImgByBytes(imgByte);
					
					if(avatar_label!=null)
					{
						icon.setImage(icon.getImage().getScaledInstance(130, 130,Image.SCALE_DEFAULT));//图片自适应
						avatar_label.setIcon(icon);
					}
					if(avatar_button!=null)
					{
						icon.setImage(icon.getImage().getScaledInstance(100, 100,Image.SCALE_DEFAULT));//图片自适应
						avatar_button.setIcon(icon);
					}
					if (readersCategory.equals("student")) {
						sql="update student set 头像= '"+imgByte+"' where 读者编号=?";
					} else if (readersCategory.equals("teacher")) {
						sql="update teacher set 头像= '"+imgByte+"' where 读者编号=?";
					}
					
					boolean ju=condata.cudExecute(sql, paras);
					if (!ju) 
					{
						System.out.println("默认头像安排失败");
					}
				}
				else 
				{
					imgByte=HnumberToBytes(string);
					ImageIcon icon=ImageIO.saveToImgByBytes(imgByte);
					if(avatar_label!=null)
					{
						icon.setImage(icon.getImage().getScaledInstance(130, 130,Image.SCALE_DEFAULT));//图片自适应
						avatar_label.setIcon(icon);
					}
					if(avatar_button!=null)
					{
						icon.setImage(icon.getImage().getScaledInstance(100, 100,Image.SCALE_DEFAULT));//图片自适应
						avatar_button.setIcon(icon);
					}
				}
			}
			else 
			{
				System.out.println("该用户不存在");
			}
		} 
		catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
	}
	/*
	 * 把图片存储到数据库
	 */
	public static void setAvatarToDatabase(String number,String filepath,JLabel avatar_label,JButton avatar_button,String readersCategory)
	{
		ConnectDatabase condata=new ConnectDatabase();
		
		String []paras = {number.trim()};
		String sql="";
		if (readersCategory.equals("student")) {
			sql="update student set 头像= (SELECT BulkColumn FROM Openrowset( Bulk '"+filepath+"', Single_Blob) as img) where 读者编号=?";
		} else if (readersCategory.equals("teacher")) {
			sql="update teacher set 头像= (SELECT BulkColumn FROM Openrowset( Bulk '"+filepath+"', Single_Blob) as img) where 读者编号=?";
		}		
		//String []paras = {filepath,number.trim()};
		//String sql="EXEC insertavatar @filepath=?, "+"@number=?";
		condata.queryExecute(sql,paras);
	}
}
