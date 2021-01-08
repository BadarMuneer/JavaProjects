import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner input=new Scanner(System.in);
        Library studentInLibrary=new Student();
        Library bookInLibrary=new Book();
        int optionSelected;
        int optionSelectedFromMoreOptions;
        boolean isGoBackSelected=false;
        while(true)
        {
            studentInLibrary.mainScreen();  //clears screen and show the main menu
            optionSelected=input.nextInt(); //Choice of user
            switch (optionSelected)
            {
                case 1: //In,Out
                    int rollNo;
                    System.out.printf("%s","Enter Roll No:");
                    rollNo=input.nextInt();
                    studentInLibrary.inAndOut(rollNo);
                    input.nextLine();
                    break;
                case 2: //Books
                    do {
                        bookInLibrary.displayMoreOptions();
                        optionSelectedFromMoreOptions=input.nextInt();
                        switch (optionSelectedFromMoreOptions)
                        {
                            case 1://Search book
                                System.out.printf("\n%s","Enter Book Name:");
                                input.nextLine();
                                String nameOfBook=input.nextLine();//get the name of book to search for
                                bookInLibrary.search(nameOfBook);
                                input.nextLine();
                                break;
                            case 2:
                                bookInLibrary.search();
                                input.nextLine();
                                input.nextLine();
                                break;
                            case 3:
                                try
                                {
                                    bookInLibrary.updateStatus("Yes");
                                    input.nextLine();
                                    input.nextLine();
                                }catch (IllegalArgumentException e)
                                {
                                    System.out.printf("%s",e.getMessage());
                                }
                                break;
                            case 4:
                                bookInLibrary.insertNewRecord();
                                input.nextLine();
                                input.nextLine();
                                break;
                            case 5:
                                isGoBackSelected=true;
                        }

                    }while(!isGoBackSelected);
                    break;
                case 3: //Students
                    isGoBackSelected=false;
                    do {
                        studentInLibrary.displayMoreOptions();
                        optionSelectedFromMoreOptions=input.nextInt();
                        switch (optionSelectedFromMoreOptions)
                        {
                            case 1:
                                studentInLibrary.insertNewRecord();
                                break;
                            case 2:
                                studentInLibrary.updateStatus("No");
                                input.nextLine();
                                input.nextLine();
                                break;
                            case 3:
                                studentInLibrary.search();
                                input.nextLine();
                                input.nextLine();
                                break;
                            case 4:
                                isGoBackSelected=true;
                                break;
                            default:
                                System.out.println("Select Valid Option..!!");
                                input.nextLine();
                                input.nextLine();
                        }
                    }while(!isGoBackSelected);
                    break;
            }
        }
    }
}
