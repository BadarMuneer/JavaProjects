import java.util.*;
import java.io.*;
import java.sql.*;
class Design
{

    public Connection connection;
    public Statement statement;
    public ResultSet resultSet;
    public File filepath;
    public BufferedReader reader;
    public BufferedWriter writer;
    public FileWriter filewriter;
    public FileReader filereader;
    public Scanner input;
    public ProcessBuilder clscr;
    public Console console=System.console();
    public String password;
    public String delimiter;
    public String str;
    public String[] array;
    public int n;

    public Design()
    {
        try {
            //clearScreen();
            array = new String[4];
            //console=System.console();
            filepath = new File("E:\\JavaProjects\\JavaProjects\\check.txt");
            System.out.println("length="+filepath.length());
            filereader = new FileReader(filepath);
            filewriter = new FileWriter(filepath,true);
            reader = new BufferedReader(filereader);
            writer = new BufferedWriter(filewriter);
            input = new Scanner(System.in);
            delimiter = ",";
            clscr=new ProcessBuilder("cmd","/c","cls");
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e.getMessage());

        }
    }
    public void logOut() //Deletion is Failed
    {
        try
        {
            BufferedWriter bufferedWriter=new BufferedWriter(filewriter);
            if(filepath.exists())
                bufferedWriter.write("");
            bufferedWriter.close();
        }
        catch (Exception e)
        {
            System.out.println("Exception: "+e.getMessage());
        }
    }
    public void deleteAppointment()
    {

        try
        {
            connection=DriverManager.getConnection("jdbc:ucanaccess://E://JavaProjects//PatientDoctor.mdb");
            statement=connection.createStatement();
            statement.executeQuery("SELECT * FROM PatientDoctor");
            resultSet.next();
            n=resultSet.getInt(1);
            statement.execute("DELETE FROM PatientDoctor WHERE Number="+n);
            resultSet=statement.executeQuery("SELECT * FROM PatientDoctor");
            System.out.println("Deleted Successfully...");
            input.nextLine();
            input.nextLine();
            connection.close();
        }
        catch (Exception e)
        {
            System.out.println("Exception: "+e.getMessage());
            input.nextLine();
            input.nextLine();
        }
    }
    public void appointment()
    {
        input.nextLine();
        System.out.print("Enter Your CNIC NO: ");
        array[0]=input.nextLine(); //CNIC
        System.out.print("Enter Your Name: ");
        array[1]=input.nextLine(); //Name
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection=DriverManager.getConnection("jdbc:ucanaccess://E://JavaProjects//Doctor.mdb");
            statement=connection.createStatement();
            resultSet=statement.executeQuery("SELECT * FROM Doctor");
            System.out.println("Doctor"+"\t\t\t"+"Days and Time");
            while(resultSet.next())
            {
                //I am here
                System.out.println(resultSet.getString(2)+"\t"+resultSet.getString(3));
            }
            System.out.print("Book Appointment with(Name): ");
            array[2]=input.nextLine();
            connection.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
          //  input.nextLine();
        }
        try
        {
            connection=DriverManager.getConnection("jdbc:ucanaccess://E:JavaProjects//PatientDoctor.mdb");
            statement=connection.createStatement();
            resultSet=statement.executeQuery("SELECT * FROM PatientDoctor");
            while (resultSet.next())
            {
                n=resultSet.getInt(1);
            }

            System.out.println("n="+n);
            statement.execute("INSERT INTO PatientDoctor VALUES('"+(n+1)+"','"+array[0]+"','"+array[1]+"','"+array[2]+"')");
            System.out.println("Appointment Booked");
            System.out.print("Press Enter...");
            input.nextLine();
            connection.close();
        }
        catch (SQLException throwables)
        {
            System.out.print("Exception: "+throwables.getMessage());
            input.nextLine();
        }
    }
    public int readData()
    {
        while(true)
        {
            try
            {
                clearScreen();
            }
            catch (Exception e){}
            design("ADMIN");
            System.out.println("\t\t\t\t1.See All Appointments");
            System.out.println("\t\t\t\t2.See Single Appointment");
            System.out.println("\t\t\t\t3.Go back");
            design("ADMIN");
            System.out.print("SELECT:");
            int choice = input.nextInt();
            boolean resultMatch = false;
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                connection = DriverManager.getConnection("jdbc:ucanaccess://E://JavaProjects//PatientDoctor.mdb");
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM PatientDoctor");
                switch (choice)
                {
                    case 1:
                        try
                        {
                            System.out.println("Number"+"\t"+"CNIC" + "\t\t\t" + "Patient Name" + "\t\t" + "Appointment with");
                            while (resultSet.next())
                            {
                                System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t\t" + resultSet.getString(3) + "\t\t" + resultSet.getString(4) );
                            }
                            System.out.println("Pres Enter..");
                            input.nextLine();
                            input.nextLine();
                        } catch (Exception e)
                        {
                            System.out.println("Exception: " + e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.print("Enter the Patients CNIC(14-digits): ");
                        input.nextLine();
                        str = input.nextLine();

                        while (!resultMatch) {
                            try {
                                resultSet.next();
                                array[0] = resultSet.getString(1);
                                array[1] = resultSet.getString(2);
                                array[2] = resultSet.getString(3);
                                for (int i = 0; i < array[0].length(); i++) {
                                    if (array[0].charAt(i) == str.charAt(i))
                                        resultMatch = true;
                                    else
                                        {
                                        resultMatch = false;
                                        break;
                                    }
                                }
                            }
                            catch (Exception e)
                            {
                                System.out.println("Patient with " + str + " Not Found");
                                System.out.println("Press Enter..");
                                input.nextLine();
                                break;
                            }
                        }
                        if (resultMatch)
                        {
                            System.out.println("Number"+"\t"+"CNIC" + "\t\t\t" + "Patient Name" + "\t" + "Appointment with");
                            System.out.println(array[0] + "\t" + array[1] + "\t\t" + array[2]+"\t"+array[3]);
                            System.out.println("Press Enter..");
                            input.nextLine();
                        }
                        break;
                    case 3:
                        connection.close();
                        return 0;
                }
            }
            catch (Exception e)
            {
                System.out.println("Exception: " + e.getMessage());
            }
        }
    }

     public void clearScreen()
     {
         try
         {
             clscr.inheritIO().start().waitFor();
         }
         catch(Exception e)
         {
             System.out.println("Exception"+e);
         }
     }
     public void userSelection()
     {
         int i,j;
         System.out.println();
         for(i=1; i<=2; i++)
         {
             System.out.print("\n\t\t\t");
             for(j=1; j<=30; j++)
                 System.out.print("=");
         }
         System.out.println("\n");
         System.out.println("\t\t\t1. Admin");
         System.out.println("\t\t\t2. Doctor");
         System.out.println("\t\t\t3. Patient");
         for(i=1; i<=2; i++)
         {
             System.out.print("\n\t\t\t");
             for(j=1; j<=30; j++)
             {
                 System.out.print("=");
             }
         }
         System.out.print("\nSELECT: ");

     }
     public boolean checkFirstTime()
     {
         try
         {
             if(filepath.length()==0)
             {
                 return true;
             }
             else if(filepath.length()>0)
                 return false;
         }
         catch(Exception e)
         {
             System.out.println(e);
         }

         return false;
     }
     public void design(String title)
     {
         int i,j;
         System.out.println("\n\n");
         for(i=1; i<=2; i++)
         {
             System.out.print("\t\t\t\t");
             for(j=1; j<=30; j++)
             {
                 System.out.print("=");
             }
             System.out.println();
         }
         System.out.print("\t\t\t\t\t   ");
         System.out.println(title);
         for(i=1; i<=2; i++)
         {
             System.out.print("\t\t\t\t");
             for(j=1; j<=30; j++)
             {
                 System.out.print("=");
             }
             System.out.println();
         }
     }
     public int verifyCredentials()
     {
         try
         {
             str=reader.readLine();
             reader.close();
         }
         catch(Exception e){}
         try
         {
             array = str.split(delimiter);
         }
         catch (NullPointerException e)
         {

         }
         switch(array[0])
         {
             case "Admin":
                 return 1;
         }

         return 0;
     }
 }
 class Admin extends Design
 {
     public void adminTasks()
     {
         String userName=new String();
         char[] pass=new char[10];
         str="Admin";
         System.out.print("Enter User Name: ");
         userName=input.nextLine();
         System.out.print(userName+", Enter Your Password: ");
         Console console1=System.console();
         pass=console1.readPassword();
         password = String.valueOf(pass);

         try
         {
             str=str+delimiter+userName+delimiter+password;
             writer.write(str);
             writer.close();
         }
         catch(Exception e)
         {}
     }
     public void exit()
     {
         System.exit(0);
     }
     public void mainScreen()
     {
         boolean correctChoice=false;
         while(true)
         {
             clearScreen();
             design("ADMIN");
             System.out.println("\n\t\t\t\t1.Check Appointment");
             System.out.println("\t\t\t\t2.Book Appointment");
             System.out.println("\t\t\t\t3.Delete Appointment");
             System.out.println("\t\t\t\t4.Exit");
             System.out.println("\t\t\t\t5.Log Out");
             design("ADMIN");
             System.out.print("SELECT:");
             int choice = input.nextInt();
             correctChoice=false;
                 switch (choice)
                 {
                     case 1:
                         checkAppointment();
                         correctChoice = true;
                         break;
                     case 2:
                         bookAppointment();
                         correctChoice = true;
                         break;
                     case 3:
                         deleteAppointment();
                         correctChoice = true;
                         break;
                     case 4:
                         exit();
                         break;
                     case 5:
                         logOut();
                         break;
                     default:
                         System.out.println("Please Select from the Given Choices");
                         System.out.println("Press Enter and choose again...");
                         input.nextLine();
                         input.nextLine();
                 }
         }



     }
     public void checkAppointment()
     {
       readData();
     }
     public void bookAppointment()
     {
         appointment();
     }
 }
 class Doctor extends Design
 {
     public void doctorTasks()
     {
         while (true)
         {

     design("DOCTOR");
         System.out.println("\t\t\t\t1.See All Appointments");

     design("DOCTOR");

     int choice = input.nextInt();
         if(choice==1)

     readData();
         else

     {
         System.out.println("Choose Correctly..");
         input.nextLine();
     }
     }
     }
 }
 class Patient extends Design
 {
     public void patientTasks()
     {
         design("PATIENT");
         System.out.println("\t\t\t\t1.Book Appointment");
         design("PATIENT");
         int choice=input.nextInt();
         if(choice==1)
             appointment();
         else {
             System.out.println("Choose Correctly");
             input.nextLine();
         }
     }
 }
    public class Hospital
    {
        public static void main(String[] args)
        {
            int choice;
            Scanner input = new Scanner(System.in);
            Admin admin = new Admin();
            Doctor doctor = new Doctor();
            Patient patient = new Patient();
            Design design = new Design();
            while(true)
            {
                System.out.println("control returned in main()");
                input.nextLine();
                if (!design.checkFirstTime())
                {
                    switch (design.verifyCredentials())
                    {
                        case 1:
                            admin.clearScreen();
                            admin.mainScreen();
                            break;
                    }
                }
                else
                    {
                    design.userSelection();
                    choice = input.nextInt();
                    switch (choice)
                    {
                        case 1:
                            admin.adminTasks();
                            admin.clearScreen();
                            admin.mainScreen();
                            break;
                        case 2:
                            doctor.doctorTasks();
                            break;
                        case 3:
                            patient.patientTasks();
                            break;
                        default:
                            System.out.println("Please Select From the given Options");
                    }
                }
            }
        }
}
