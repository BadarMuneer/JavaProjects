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
	public Design()
	{
		try
		{
			clscr=new ProcessBuilder("cmd","/c","cls");
			clscr.inheritIO().start().waitFor();
			filepath=new File("check.txt");
			filereader=new FileReader(filepath);
			filewriter=new FileWriter(filepath);
			reader=new BufferedReader(filereader);
			writer=new BufferedWriter(filewriter);
			input=new Scanner(System.in);
			//System.out.println("Hello");
			console=System.console();
		}
		catch(Exception e)
		{
			System.out.println("Exception: "+e.getMessage());

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
			if(filepath.length()>0)
				{
					return true;
				}
			else
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
	public void verifyCredentials()
	{

	}
}
class Admin extends Design
{
	public void adminTasks()
	{
		String userName=new String();
		try
		{
			filepath.createNewFile();
		}
		catch(Exception e) { }
		System.out.print("Enter User Name: ");
		userName=input.nextLine();
		System.out.print(userName+", Enter Your Password: ");
		char[] pass=console.readPassword();
		password=String.valueOf(pass);
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

		if(design.checkFirstTimeOpening())
		{
			design.verifyCredentials();
		}
		else
		{
			design.userSelection();
			choice=input.nextInt();
			switch(choice)
			{
				case 1:
				admin.adminTasks();
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