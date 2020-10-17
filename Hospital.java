import java.util.*;
import java.io.*;
class Design
{
	private File filepath;
	private BufferedReader reader;
	private BufferedWriter writer;
	private FileWriter filewriter;
	private FileReader filereader;
	private Scanner input;
	Design()
	{
		try
		{
			filepath=new File("E:\\JavaProjects\\JavaProjects\\check.txt");
			filereader=new FileReader(filepath);
			filewriter=new FileWriter(filepath);
			reader=new BufferedReader(filereader);
			writer=new BufferedWriter(filewriter);
			input=new Scanner(System.in);
		}
		catch(Exception e)
		{

		}
	}
	public void userSelection()
	{
	    System.out.println();	
		for(int i=1; i<=2; i++)
		{
			System.out.print("\n\t\t\t");
			for(int j=1; j<=30; j++)
				System.out.print("=");
		}
		System.out.println("\n");
		System.out.println("\t\t\t1. Admin");
		System.out.println("\t\t\t2. Doctor");
		System.out.println("\t\t\t3. Patient");
		for(int i=1; i<=2; i++)
		{
			System.out.print("\n\t\t\t");
			for(int j=1; j<=30; j++)
			{
				System.out.print("=");
			}
		}
		System.out.print("\nSELECT: ");

	}
	public void checkFirstTimeOpening()
	{
		if(filepath.exists())
		{

		}
		else
		{
			userSelection();  //Asks for the user type
		}
	}
	public void design(String title)
	{
		System.out.println("\n\n");
		for(int i=1; i<=2; i++)
		{
			System.out.print("\t\t\t\t");
			for(int j=1; j<=30; j++)
			{
				System.out.print("=");
			}
			System.out.println();
		}
		System.out.print("\t\t\t\t\t   ");
		System.out.println(title);
		for(int i=1; i<=2; i++)
		{
			System.out.print("\t\t\t\t");
			for(int j=1; j<=30; j++)
			{
				System.out.print("=");
			}
			System.out.println();
		}
	}
}
class Admin extends Design
{
	public void adminTasks()
	{

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
		design.checkFirstTimeOpening();
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
			System.out.println("Please Choose from the Give choices");
		}
	}
}