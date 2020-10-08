import java.util.*;
interface Design
{
	public void design();
}
class Admin implements Design
{
	public void design()
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
		System.out.print("\t\t\t\t\t");
		System.out.println("BADAR HOSPITAL");
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
public class Hospital
{
	public static void main(String[] args) 
	{
		Admin admin=new Admin();
		admin.design();
	}
}