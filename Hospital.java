import java.util.*;
import java.io.*;
class Design
{
	public File filepath;
	public BufferedReader reader;
	public BufferedWriter writer;
	public FileWriter filewriter;
	public FileReader filereader;
	public Scanner input;
	public ProcessBuilder clscr;
	public Console console;
	public String password;
	public String delimiter;
	public String str;
	public String[] array;
	public Design()
	{
		try
		{
			clearScreen();
			array=new String[3]; 
			filepath=new File("E:\\JavaProjects\\JavaProjects\\check.txt");
			filereader=new FileReader(filepath);
			filewriter=new FileWriter(filepath,true);
			reader=new BufferedReader(filereader);
			writer=new BufferedWriter(filewriter);
			input=new Scanner(System.in);
			console=System.console();
			delimiter=",";
		}
		catch(Exception e)
		{
			System.out.println("Exception: "+e.getMessage());

		}
	}

	public void clearScreen()
	{
		clscr=new ProcessBuilder("cmd","/c","cls");
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
	public boolean checkFirstTimeOpening()
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
		}
		catch(Exception e){}
		array=str.split(delimiter);
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
		str="Admin";
		System.out.print("Enter User Name: ");
		userName=input.nextLine();
		System.out.print(userName+", Enter Your Password: ");
		char[] pass=console.readPassword();
		password=String.valueOf(pass);
		try
		{
			str=str+delimiter+userName+delimiter+password;
			writer.write(str);
			writer.close();
	    }
	    catch(Exception e)
	    {}
	}
	public void mainScreen()
	{
		design("ADMIN");

		System.out.println("\n\t\t\t\t1.Check Appointment");
		System.out.println("\t\t\t\t2.Book Appointment");
		System.out.println("\t\t\t\t3.Check Bill");

		design("ADMIN");

	}
}
class Doctor extends Design
{
	public void doctorTasks()
	{

	}
}
class Patient extends Design
{
	public void patientTasks()
	{

	}
}
public class Hospital
{
	public static void main(String[] args) 
	{
		int choice;
		Scanner input=new Scanner(System.in);
		Admin admin=new Admin();
		Doctor doctor=new Doctor();
		Patient patient=new Patient();
		Design design=new Design();
		//admin.mainScreen();
		if(!design.checkFirstTimeOpening())
		{
			switch(design.verifyCredentials())
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
			choice=input.nextInt();
			switch(choice)
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