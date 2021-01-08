import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.*;

public abstract class Library {
    private static final ProcessBuilder b;
    private static int maleCount;
    private static int femaleCount;
    private static int totalCount;
    private static Connection connection;
    private static Statement statement;

    Library() {
        maleCount = 0;
        femaleCount = 0;
        totalCount = maleCount + femaleCount;
    }

    static {
        b = new ProcessBuilder("cmd", "/c", "cls");
        try {
            connection = DriverManager.getConnection("jdbc:ucanaccess://E://NewFolder//LibraryManagementSystem//src//libraryDatabase.mdb");
            statement = connection.createStatement();
        } catch (Exception exception) {
            System.out.println("Problem in Accessing Data " + exception.getMessage());
        }
    }

    public static Statement getStatement() {
        return statement;
    }

    public static void setMaleCount(int maleCount) {
        Library.maleCount = maleCount;
    }

    public static void setFemaleCount(int femaleCount) {
        Library.femaleCount = femaleCount;
    }

    public static void setTotalCount(int totalCount) {
        Library.totalCount = totalCount;
    }

    public static int getMaleCount() {
        return maleCount;
    }

    public static int getFemaleCount() {
        return femaleCount;
    }

    public static int getTotalCount() {
        return totalCount;
    }

    public static void clrScreen() {
        try {
            b.inheritIO().start().waitFor();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showData() {
        System.out.printf("%70s\n\n\n", "Welcom to GBHS Library");
        System.out.printf("%-9s%2d\n", "Males: ", getMaleCount());
        System.out.printf("%s%2d\n", "Females: ", getFemaleCount());
        System.out.printf("%-9s%2d\n\n\n", "Total: ", getTotalCount());
    }

    public void mainScreen() {
        clrScreen();
        showData(); //display number of people inside library;
        insertDesign(2, 19, '=');
        System.out.printf("%51s\n\n", "1.In,Out");
        System.out.printf("%50s\n\n", "2.Books");
        System.out.printf("%52s\n", "3.Student");
        insertDesign(2, 19, '=');
        System.out.printf("%s", "SELECT:");
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public void insertDesign(int numberOfLines, int numberOfCharacters, char c) {
        for (int i = 1; i <= numberOfLines; i++) {
            System.out.printf("%40s", "=");
            for (int j = 1; j <= numberOfCharacters; j++) {
                System.out.printf("%c", c);
            }
            System.out.println();
        }
    }
    public static void exitApplication() {
        System.exit(0);
    }

    public void inAndOut(int rollNo) {
        try {
            ResultSet resultSet = getStatement().executeQuery("SELECT * FROM Student WHERE RollNo=" + rollNo);
            if (resultSet.next()) //check roll no selected or not, registered.
            {
                if (resultSet.getString("Inside").equals("Yes"))//if he is already inside
                {
                    getStatement().execute("UPDATE Student SET Inside='No'" + " WHERE RollNo=" + rollNo);
                    if(resultSet.getString("Gender").equals("Male"))
                    {
                        maleCount--;
                    }
                    else
                        femaleCount--;
                    totalCount--;
                } else //if not already inside
                {
                    //
                    getStatement().execute("UPDATE Student SET Inside='Yes'" + " WHERE RollNo=" + rollNo);
                    if(resultSet.getString("Gender").equals("Male"))
                    {
                        maleCount++;
                    }
                    else
                        femaleCount++;
                    totalCount++;
                    //
                }
            } else //roll no is not registered
            {
                System.out.println("RollNo is Not Registered");
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public abstract void displayMoreOptions();

    public abstract void search();

    public abstract void search(String name);

    public abstract void updateStatus(String status);

    public abstract void insertNewRecord();
}
