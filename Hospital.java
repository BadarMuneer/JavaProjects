import java.util.*;
class Design
{
	private Output output;
	private Path p;
	private Input input;
	private BufferedReader reader;
	private BufferedWriter writer;
	Design()
	{
		
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

}
class Doctor extends Design
{

}
public class Hospital
{
	public static void main(String[] args) 
	{
		Admin admin=new Admin();
		Doctor doctor=new Doctor();
		
	}
}