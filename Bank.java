import java.util.Scanner;
import java.sql.*;
public class Bank {
             static int amount;
             static String n;
             static String pw;
             public static void create()
             {
            	 Scanner p=new Scanner(System.in);
             System.out.println("enter the id:");
 			int i=p.nextInt();
 			System.out.println("enter the name:");
 			String n=p.next();
 			System.out.println("create password:");
 			String pw=p.next();
 			System.out.println("enter the phone number:");
 			long ph=p.nextLong();
 			System.out.println("deposite amount:");
 			int d=p.nextInt();
 			try{
 				Class.forName("com.mysql.jdbc.Driver");
 				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","naveen8513");
 				PreparedStatement ps=con.prepareStatement("insert into users(id,name,password,balance,phoneno) values(?,?,?,?,?)");
 				ps.setInt(1, i);
 				ps.setString(2, n);
 				ps.setString(3, pw);
 				ps.setInt(4, d);
 				ps.setLong(5, ph);
 				ps.executeUpdate();
 				con.close();
 				System.out.println("account created succesfully");
 				System.out.println("login your account");
					System.out.println("------------------------------");
 				login();
 				
 			}
 			catch(Exception e)
 			{
 				System.out.println(e);
 			}
 		}
 		public static void login()
 		{
 			Scanner p=new Scanner(System.in);
 			System.out.println("enter name:");
 			n=p.next();
 			System.out.println("enter password:");
 			pw=p.next();
 			try{
 				Class.forName("com.mysql.jdbc.Driver");
 				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","naveen8513");
 				PreparedStatement ps=con.prepareStatement("select * from users where name=? and password=?");
 				ps.setString(1,n);
 				ps.setString(2,pw);
 				ResultSet rs=ps.executeQuery();
 				while(rs.next())
 				{
 					//String s1=rs.getString(2);
 					//String s2=rs.getString(3);
 					if(n.equals(rs.getString(2))&& pw.equals(rs.getString(3)))
 					{
 						System.out.println("login successfully");
						show();
 						}
 					else
 					{
 						
							System.out.println("incorrect details  login again");
	 						login();
 					
 					}
 				}
 				
 			}
 			catch(Exception e)
 			{
 				System.out.println(e);
 				
 			}
 			

 	}
             public static void show()
             {
            	 Scanner p=new Scanner(System.in);
            System.out.println("Choose an option");
 			System.out.println("1.deposite");
 			System.out.println("2.withdraw");
 			System.out.println("3.balance enquary");
 			System.out.println("4.change password");
 			System.out.println("5.forget password");
 			System.out.println("6.Exit");
 			int b=p.nextInt();
 			if(b==1)
 			{
 				deposite();
 			}
 			if(b==2)
 			{
 				withdraw();
 			}
 			if(b==3)
 			{
 				balance();
 			}
 			if(b==4)
 			{
 				change();
 			}
 			if(b==5)
 			{
 				forget();
 			}
 			if(b==6)
 			{
 				System.exit(0);
 			}
             }
             public static void deposite()
             {
            	 Scanner p=new Scanner(System.in);
 				System.out.println("enter deposite amount:");
 				int da=p.nextInt();
 				try{
 					Class.forName("com.mysql.jdbc.Driver");
 					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","naveen8513");
 					PreparedStatement ps=con.prepareStatement("select * from users where name=? and password=?");
 					ps.setString(1,n);
 					ps.setString(2, pw);
 				    ResultSet rs=ps.executeQuery();
 				    while(rs.next()){
 				    amount=da+rs.getInt(5);
 				    }
 				    ps=con.prepareStatement("update users set balance=? where name=? and password=?");
 				    ps.setInt(1, amount);
 				    ps.setString(2,n);
 				    ps.setString(3, pw);
 				    ps.executeUpdate();
 				    con.close();
 				    System.out.println("deposite successfully");
 				    
 					
 				}
 				catch(Exception e)
 				{
 					System.out.println(e);
 				}
 				System.out.println("your balance:"+amount);
 				
 			}
             public static void withdraw()
             {
            	 Scanner p=new Scanner(System.in);
            	 System.out.println("enter withdraw amount:");
            	 int wd=p.nextInt();
            	 try
            	 {
            		 Class.forName("com.mysql.jdbc.Driver");
            		 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","naveen8513");
            		 PreparedStatement ps=con.prepareStatement("select * from users where name=? and password=?");
            		 ps.setString(1,n);
            		 ps.setString(2,pw);
            		 ResultSet rs=ps.executeQuery();
            		 while(rs.next())
            		 {
            			 if(wd>rs.getInt(5))
            			 {
            				 
            				 System.out.println("insufficient balance");
            				 show();
            			 }
            			 else
            			 {
            				 amount=rs.getInt(5)-wd;
            				 System.out.println("collect your cash");
            			 }
            		 }
            		 ps=con.prepareStatement("update users set balance=? where name=? and password=?");
            		 ps.setInt(1, amount);
            		 ps.setString(2,n);
  				    ps.setString(3, pw);
            		 ps.executeUpdate();
            		 System.out.println("withdraw successfully");
            		 con.close();
            		 System.out.println("your balance:"+amount);
   
            	 }
            	 catch(Exception e)
            	 {
            		 System.out.println(e);
            	 }
             }
             public static void balance()
             {
            	 try
            	 {
            		 Class.forName("com.mysql.jdbc.Driver");
            		 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","naveen8513");
            		 PreparedStatement ps=con.prepareStatement("select * from users where name=? and password=?");
            		 ps.setString(1,n);
  				    ps.setString(2, pw);
            		 ResultSet rs=ps.executeQuery();
            		 while(rs.next())
            		 {
            			 System.out.println("your balance:"+rs.getInt(5));
            		 }
            		 con.close();
            	 }
            	 catch(Exception e)
            	 {
            		 System.out.println(e);
            	 }
             }
             public static void change()
             {
            	 Scanner p=new Scanner(System.in);
            	 System.out.println("enter old password:");
            	 String old=p.next();
            	 System.out.println("enter new password:");
            	 String np=p.next();
            	 try
            	 {
            	 Class.forName("com.mysql.jdbc.Driver");
            	 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","naveen8513");
            	 PreparedStatement ps=con.prepareStatement("select * from users where name=? and password=?");
            	 ps.setString(1, n);
            	 ps.setString(2, pw);
            	 ResultSet rs=ps.executeQuery();
            	 while(rs.next())
            	 {
            		 if(old.equals(rs.getString(3)))
            		 {
            			 ps=con.prepareStatement("update users set password=? where name=? and password=?");
            			 ps.setString(1,np);
            			 ps.setString(2,n);
      				    ps.setString(3, pw);
            			 ps.executeUpdate();
            			 System.out.println("password changed successfully");
            			 login();
            		 }
            		 else
            		 {
            			 System.out.println("incorrected password.   try again");
            			 change();
            		 }
            	 }
            	 }
            	 catch(Exception e)
            	 {
            		 System.out.println(e);
            	 }
            	 
            	 
             }
             public static void forget()
             {
            	 Scanner p=new Scanner(System.in);
            	 System.out.println("enter your name:");
            	 String name=p.next();
            	 System.out.println("enter your bank account number:");
            	 int ac=p.nextInt();
            	 try
            	 {
            		 Class.forName("com.mysql.jdbc.Driver");
            		 Connection con=DriverManager.getConnection("jdbc:mysql://local:3306/bank","root","naveen8513");
            		 PreparedStatement ps=con.prepareStatement("select * from users where name=? and account=?");
            		 ps.setString(1,name);
            		 ps.setInt(2,ac);
            		 ResultSet rs=ps.executeQuery();
            		 while(rs.next())
            		 {
            			 if(name.equals(rs.getString(2))&&ac==rs.getInt(4))
            			 {
            				 Scanner pm=new Scanner(System.in);
            				 System.out.println("set the password:");
            				 String pass=pm.next();
            				 System.out.println("conform the password:");
            				 String npass=pm.next();
            				 if(pass.equals(npass))
            				 {
            					 PreparedStatement pl=con.prepareStatement("update users password=? where name=? and account=?");
            					 pl.setString(1, npass);
            					 pl.setString(2, name);
            					 pl.setInt(3, ac);
            					 pl.executeUpdate();
            					 System.out.println("then forgot your password.Remember it");
            					 login();
            				 }
            				 else
            				 {
            					System.out.println(" doesn't match"); 
            				 }
            				 
            			 }
            		 }
            	 }
            	 catch(Exception e)
            	 {
            		 System.out.println(e);
            	 }
             }
     
 			

	public static void main(String[] args) {
		Scanner p=new Scanner(System.in);
		System.out.println("WELCOME TO SBI BANK");
		System.out.println("1.Creat an account");
		System.out.println("2.Account login");
		int a=p.nextInt();
		if(a==1)
		{
			create();
		}
		if(a==2)
		{
			login();
		}
	}
	}