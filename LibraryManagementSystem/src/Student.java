import java.sql.*;
import java.util.Scanner;

public class Student extends Library
{
    private String name;
    private String rollNo;
    private int classInSchool;
    private String gender;
    private ResultSet resultSet;
    @Override
    public void displayMoreOptions() //further options that are available for to explore student
    {
        clrScreen();
        showData();
        insertDesign(2,25,'=');
        System.out.printf("%55s\n\n","1.Registration");
        System.out.printf("%52s\n\n","2.Submit Book");
        System.out.printf("%58s\n\n","3.Inside Students");
        System.out.printf("%50s\n","4.Go Back");
        insertDesign(2,25,'=');
        System.out.printf("%s","SELECT:");
    }
    public void designHeadings()
    {
        System.out.println();
        for(int i=1; i<=2; i++)
        {
            System.out.printf("%s","=");
            for(int j=1; j<=80; j++)
            {
                System.out.printf("%c",'=');
            }
            System.out.println();
        }
    }

    @Override
    public void search()
    {
        try
        {
            resultSet = getStatement().executeQuery("SELECT * FROM Student WHERE inside='yes'");
            if(resultSet==null)
                throw new Exception("No one is inside");
            else
            {
                ResultSetMetaData metaData=resultSet.getMetaData();
                designHeadings();
                System.out.printf("%-8s%-20s%-8s%-8s%-8s%-8s%s\n",metaData.getColumnName(1),metaData.getColumnName(2),metaData.getColumnName(3),metaData.getColumnName(4),metaData.getColumnName(5),metaData.getColumnName(6),metaData.getColumnName(7));
                designHeadings();
                while(resultSet.next())
                {
                    System.out.printf("%-8d%-20s%-8d%-8s%-8d%-8s%-4d\n", resultSet.getInt("srNo"),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getString(6),resultSet.getInt(7));
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void insertNewRecord()
    {
        Scanner input=new Scanner(System.in);
        System.out.printf("%s","Enter Name:");
        name=input.nextLine();
        System.out.printf("%s","Enter RollNo:");
        rollNo=input.nextLine();
        System.out.printf("%s","Enter Class:");
        classInSchool=input.nextInt();
        input.nextLine();
        System.out.printf("%s","Enter Gender(Male-Female):");
        gender=input.nextLine();

        //Insert the data into database

        try
        {
            getStatement().execute("INSERT INTO Student(Name,RollNo,Gender,Class,Inside) VALUES('"+name+"',"+rollNo+",'"+gender+"',"+classInSchool+",'No')");
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }
    @Override
    public void updateStatus(String status)
    {
        Book b=new Book();
        b.search();  //show All Books
        int n;
        Scanner input=new Scanner(System.in);
        System.out.printf("%s","SELECT:");
        n=input.nextInt();  //get The index of book

        System.out.printf("%s","Enter Roll Number:");
        int rollNumber=input.nextInt();  //get Roll Number who has issued the book
        try
        {
            resultSet=getStatement().executeQuery("SELECT * FROM Book WHERE srNo="+n);
            resultSet.next();
            if((resultSet.getString("Issued").equals("Yes")) && (resultSet.getInt("To")==rollNumber))
            {
                ResultSet rs=null;
                rs=getStatement().executeQuery("SELECT * FROM Student WHERE RollNo="+rollNumber);
                if(rs.next())
                {
                    boolean isUpdated=getStatement().execute("UPDATE Student SET Books="+(rs.getInt("Books")-1)+" WHERE RollNo="+rollNumber);
                    if(!isUpdated)
                    {
                        getStatement().execute("UPDATE Book SET Issued='"+status+"',To="+0+" WHERE srNo="+n);
                        System.out.printf("%s","Book submitted Successfully..");
                    }
                }
                else
                    System.out.println("Roll No Does Not Exists");
            }
            else
                System.out.println("Book is Not Issued");

        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

    }

    @Override
    public void search(String name)
    {

    }
}
