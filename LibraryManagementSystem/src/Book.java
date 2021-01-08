import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class Book extends Library
{
    private ResultSet result;
    @Override
    public void displayMoreOptions()
    {
        clrScreen();
        showData();
        insertDesign(2,19,'=');
        System.out.printf("%55s\n\n","1.Search Book");
        System.out.printf("%57s\n\n","2.See All Books");
        System.out.printf("%54s\n\n","3.Issue Book");
        System.out.printf("%52s\n\n","4.Add Book");
        System.out.printf("%51s\n","5.Go back");
        insertDesign(2,19,'=');
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
            result=getStatement().executeQuery("SELECT * FROM Book");
            ResultSetMetaData metaData=result.getMetaData();
            //Design
            designHeadings();
            //Columns Headings
            System.out.printf("%-8s%-30s%-25s%-10s%-10s\n",metaData.getColumnName(1),metaData.getColumnName(2),metaData.getColumnName(3),metaData.getColumnName(4),metaData.getColumnName(5));

            //Design
            designHeadings();
            while(result.next())
            {
                System.out.printf("%-8d%-30s%-25s%-10s%-10s\n",result.getInt("srNo"),result.getString(2),result.getString(3),result.getString(4),result.getString(5));
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    @Override
    public void search(String name)  //Search for particular book
    {
        try
        {
            result=getStatement().executeQuery("SELECT * FROM Book WHERE Book='"+name+"'");
            if (result == null)
            {
                throw new IllegalArgumentException(name + " Does not exists!!");
            }

            else
                {
                    result.next();
                    ResultSetMetaData metaData=result.getMetaData();
                    designHeadings();
                    System.out.printf("%-8s%-30s%-25s%-10s%-10s",metaData.getColumnName(1),metaData.getColumnName(2),metaData.getColumnName(3),metaData.getColumnName(4),metaData.getColumnName(5));
                    designHeadings();
                    System.out.printf("%-8d%-30s%-25s%-10s%-10s\n",result.getInt("srNo"),result.getString(2),result.getString(3),result.getString(4),result.getString(5));
                }
            }
        catch (Exception throwables)
        {
            throwables.printStackTrace();
        }
    }

    //Take details from the person who is issuing the book
    @Override
    public void updateStatus(String status)
    {
        search();
        int n;
        Scanner input=new Scanner(System.in);
        System.out.printf("%s","SELECT:");
        n=input.nextInt();
        try
        {
            result=getStatement().executeQuery("SELECT * FROM Book WHERE srNo="+n);
            result.next();
            if(result.getString("Issued").equals("No"))
            {
                System.out.printf("%s","Enter Roll Number:");
                int rollNumber=input.nextInt();
                ResultSet rs=null;
                rs=getStatement().executeQuery("SELECT * FROM Student WHERE RollNo="+rollNumber);
                if(rs.next())
                {
                    boolean isUpdated=getStatement().execute("UPDATE Student SET Books="+(rs.getInt("Books")+1)+" WHERE RollNo="+rollNumber);
                    if(!isUpdated)
                    {
                        getStatement().execute("UPDATE Book SET Issued='"+status+"',To="+rollNumber+" WHERE srNo="+n);
                        System.out.printf("%s%d%s", "Book Issued To ", rollNumber, " Successfully..");
                    }
                }
                else
                    System.out.println("Roll No Does Not Exists");
            }
            else
                System.out.println("Book is Issued To SomeOne Already");

        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }
    @Override
    public void insertNewRecord()
    {
        Scanner input=new Scanner(System.in);
        String bookName;
        String author;
        System.out.printf("%s","Enter Book Name:");
        bookName=input.nextLine();
        System.out.printf("%s","Enter Author Name:");
        author=input.nextLine();
        try
        {
            getStatement().execute("INSERT INTO Book (Book,Author,Issued) VALUES('"+bookName+"','"+author+"','No')");
            System.out.println("Book Added Successfully...");
        } catch (SQLException throwables)
        {
            System.out.println(throwables.getMessage());
        }
    }
}
