import java.awt.*;//it import the required classes
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.net.*;
import java.util.*;

public class Puzzle8 extends JFrame implements ActionListener,KeyEventPostProcessor
{
	private static Puzzle8 openFrame;
	private static int a[]=new int[9];
	
	JTextField jTextFieldArray[] = new JTextField[9]; 
	String textFieldStrings[] = new String[9];
	
	JButton jButton1=new JButton("Start");
	public static void main(String args[])
	{
		new Puzzle8();
	}
	
	Puzzle8()
	{
		//if (openFrame != null)
		//{
		//	openFrame.dispose();
		//}
		//openFrame = this;
		jButton1.addActionListener(this);
		getContentPane().setLayout(null);
		getContentPane().add(jButton1);
		jButton1.setBounds(30,200, 90, 30);

		for (int i = 0,x=0,y=0; i < jTextFieldArray.length; i++) 
		{	
			textFieldStrings[i] = "TextField" + i; 
			jTextFieldArray[i] = new JTextField(textFieldStrings[i]); 
			jTextFieldArray[i].setBounds(20+x, 20+y, 50, 50);
			jTextFieldArray[i].setEditable(false);
			jTextFieldArray[i].setText("  ");
			getContentPane().add(jTextFieldArray[i]); 
			if(i==2||i==5)
			{
				y+=60;
				x=0;
			}
			else x+=60;
		}
		
			
		setTitle("Puzzle8");
        setSize(225,280);
        setVisible(true);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventPostProcessor(this);
		this.addWindowListener(new WindowAdapter()//for closing the tool when press close button
		{
            public void windowClosing(WindowEvent e)
            {
				System.exit(0);
            }
        });
	}
	
	Puzzle8(int a[])
	{
		jButton1.addActionListener(this);
		getContentPane().setLayout(null);
		getContentPane().add(jButton1);
		jButton1.setBounds(30,200, 90, 30);
		String st;
		for (int i = 0,x=0,y=0; i < jTextFieldArray.length; i++) 
		{
			textFieldStrings[i] = "TextField" + i; 
			jTextFieldArray[i] = new JTextField(textFieldStrings[i]); 
			jTextFieldArray[i].setBounds(20+x, 20+y, 50, 50);
			jTextFieldArray[i].setEditable(false);
			if(a[i]==0) st="  ";
			else	st=String.valueOf(a[i]);
			st="   "+st+"   ";
			jTextFieldArray[i].setText(st);
			switch(a[i])
			{
				case 1: jTextFieldArray[i].setBackground( Color.GREEN ); break;
				case 2: jTextFieldArray[i].setBackground( Color.RED ); break;
				case 3: jTextFieldArray[i].setBackground( Color.PINK ); break;
				case 4: jTextFieldArray[i].setBackground( Color.ORANGE ); break;
				case 5: jTextFieldArray[i].setBackground( Color.YELLOW ); break;
				case 6: jTextFieldArray[i].setBackground( Color.WHITE ); break;
				case 7: jTextFieldArray[i].setBackground( Color.MAGENTA ); break;
				case 8: jTextFieldArray[i].setBackground( Color.BLUE ); break;
			}
			getContentPane().add(jTextFieldArray[i]); 
			if(i==2||i==5)
			{
				y+=60;
				x=0;
			}
			else x+=60;
		} 
			
		setTitle("Puzzle8");
        setSize(225,280);
        setVisible(true);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventPostProcessor(this);
		this.addWindowListener(new WindowAdapter()//for closing the tool when press close button
		{
            public void windowClosing(WindowEvent e)
            {
				System.exit(0);
            }
        });
		if (openFrame != null)
		{
			openFrame.dispose();
		}
		openFrame = this;		
	}
	
	public void actionPerformed(ActionEvent e)//when press enter buuton
	{
		if(e.getSource()== jButton1)
		{
			this.dispose();
			Random generator = new Random();
			boolean already=false;
			for(int i=0,t,n=0;n<9;i++)
			{
				already=false;
				t=generator.nextInt(1000)%9+1;
				for(int j=0;j<=n;j++)
				{
					if(a[j]==t)	already=true;
				}
				if(already==false)	{a[n]=t;n++;}
			}
			for(int i=0;i<9;i++)
			{
				if(a[i] == 9) a[i]=0;
			}
			new Puzzle8(a);
		}
	}
	
	@Override
    public boolean postProcessKeyEvent(KeyEvent ke) 
	{
		int keyCode;
		keyCode = ke.getKeyCode();
		int check_zero=0;
		if (ke.getID() == KeyEvent.KEY_PRESSED)
		{
			switch( keyCode ) 
			{ 
				case KeyEvent.VK_UP:
					for(int i=0;i<9;i++)
						if(a[i]==0)	check_zero=i;
					if(check_zero!=6&&check_zero!=7&&check_zero!=8)
					{
						a[check_zero]=a[check_zero+3];
						a[check_zero+3]=0;
					}
					break;
				case KeyEvent.VK_DOWN:
					for(int i=0;i<9;i++)
						if(a[i]==0)	check_zero=i;
					if(check_zero!=0&&check_zero!=1&&check_zero!=2)
					{
						a[check_zero]=a[check_zero-3];
						a[check_zero-3]=0;
					}
					break;
				case KeyEvent.VK_LEFT:
					for(int i=0;i<9;i++)
						if(a[i]==0)	check_zero=i;
					if(check_zero%3!=2)
					{
						a[check_zero]=a[check_zero+1];
						a[check_zero+1]=0;
					}
					break;
				case KeyEvent.VK_RIGHT :
					for(int i=0;i<9;i++)
						if(a[i]==0)	check_zero=i;
					if(check_zero%3!=0)
					{
						a[check_zero]=a[check_zero-1];
						a[check_zero-1]=0;
					}
					break;
			}
			for(int i=0,j=0;a[i]==i;i++)
			{
				j++;
				if(j==8)
				{
					JOptionPane.showMessageDialog(null,"Congratulations");
					System.exit(0);
				}
			}
			new Puzzle8(a);
			return true;
		}
        // Let keyboard focus manager handle the event further.
        return false;
    }
}