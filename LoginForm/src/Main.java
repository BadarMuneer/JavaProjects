import javax.swing.*;
import java.awt.*;

class Form
{
    private JFrame frame=null;
    private JLabel headingLabel=null;
    private JLabel namePrompt=null;
    private JLabel passwordPrompt=null;
    private JTextField nameTextField=null;
    private JPasswordField passwordTextField=null;
    private JPanel panel=null;
    private Container container=null;
    private JButton button=null;
    public Form()
    {

    }
    public Form(JFrame frame,JLabel namePrompt,JLabel passwordPrompt,JTextField nameTextField,JPasswordField passwordTextField,JLabel headingLabel)
    {
        this.frame=frame;
        this.namePrompt=namePrompt;
        this.passwordPrompt=passwordPrompt;
        this.nameTextField=nameTextField;
        this.passwordTextField=passwordTextField;
        this.headingLabel=headingLabel;
    }
    public void showFrame()
    {
        frame.setTitle("LOGIN Form");
        frame.setSize(400,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container=frame.getContentPane();
        panel=new JPanel();
        button=new JButton("Log in");
        panel.setLayout(null);
        headingLabel.setFont(new Font("Arial",Font.PLAIN,30));
        headingLabel.setText("LOGIN");
        headingLabel.setBounds(140,1,100,100);
        panel.add(headingLabel);
        namePrompt.setText("Name: ");nameTextField.setColumns(20);
        passwordPrompt.setText("Password: ");passwordTextField.setColumns(20);
        namePrompt.setBounds(37,109,50,30);
        nameTextField.setBounds(80,115,130,20);
        panel.add(namePrompt);
        panel.add(nameTextField);
        passwordPrompt.setBounds(37,150,70,30);
        passwordTextField.setBounds(100,156,130,20);
        button.setBounds(37,200,80,30);
        panel.add(passwordPrompt);panel.add(passwordTextField);
        panel.add(button);
        container.add(panel);
        frame.setVisible(true);
    }
}
public class Main
{
    public static void main(String[] args)
    {
        Form form=new Form(new JFrame(),new JLabel(),new JLabel(),new JTextField(),new JPasswordField(),new JLabel());
        form.showFrame();
    }
}
