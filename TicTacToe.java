package Game;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class TicTacToe extends JFrame implements ActionListener
{
	
	public static int Board_Size=3;  //Board size in TicTacToe is always 3*3
	public static enum GameStatus  // Different variable but belongs to same category
	{
		Incomplete,XWins,ZWins,Tie;
	}
	
	private JButton[][] buttons=new JButton[Board_Size][Board_Size];     //Inbuilt class used to use functionalities of button....3*3 matrix of button
	boolean crossTurn=true;   //whenever i click on any button forst time a cross will be displayed on button
	
	public TicTacToe()    //Constructor
	{
		super.setTitle("TicTacToe");
		super.setSize(800,800);
		super.setResizable(false);
		GridLayout grid=new GridLayout(Board_Size,Board_Size);  //Set layout in grid format (0,0) (0,1) and so on
		super.setLayout(grid);    // now i have set it
		
		Font font =new Font("Comic Sans",1,150);  //1 means bold
		for(int row=0;row<Board_Size;row++)   // Create JButton
		{
			for(int col=0;col<Board_Size;col++)
			{
				JButton button=new JButton("");
				buttons[row][col]=button;           //Add button to that box
				button.setFont(font);
				button.addActionListener(this);   // We have attached a listener to every button so if any action happens on a object all buttons are notified so action performed on a button will be called
				super.add(button);     //Add it in a grid manner
			}
		}
		
		
		
		
		
		super.setVisible(true);      
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton clickedButton=(JButton)e.getSource();              // Find the button that is being clicked
		makeMove(clickedButton);
		GameStatus gs=this.getGameStatus();            //Output of game at this particular moment
		if(gs==GameStatus.Incomplete)
			return;
		declareWinner(gs);
		int choice= JOptionPane.showConfirmDialog(this, "Do you want to restart");
		if(choice==JOptionPane.YES_OPTION)
		{
			for(int row=0;row<Board_Size;row++)   // Create JButton
			{
				for(int col=0;col<Board_Size;col++)
				{
					buttons[row][col].setText("");
					
				}
			}
			crossTurn=true;
		}
		else
			super.dispose();
	}
	private void makeMove(JButton clickedButton)
	{
		String btntext=clickedButton.getText();       //Get the text written on that button
		if(btntext.length()>0)
			JOptionPane.showMessageDialog(this,"Invalid Move");   //Show message for invalid move
		else
		{
			if(crossTurn)
				clickedButton.setText("X");
			else
				clickedButton.setText("0");
			crossTurn=!crossTurn;
		}
	}
	
	private GameStatus getGameStatus()
	{
		String text1="",text2="";
		int row=0,col=0;
		//Check for tet inside rows
		row=0;
		while(row<Board_Size)
		{
			col=0;
			while(col<Board_Size-1)
			{
				text1=buttons[row][col].getText();
				text2=buttons[row][col+1].getText();
				if(!text1.equals(text2) || text1.length()==0)
					break;
				col++;
			}
			if(col==Board_Size-1)
			{
				if(text1.equals("X"))
					return GameStatus.XWins;
				else
					return GameStatus.ZWins;
						
			}
			row++;
			
		}
		
		//Text Inside columns
		col=0;
		while(col<Board_Size)
		{
			row=0;
			while(col<Board_Size-1)
			{
				text1=buttons[row][col].getText();
				text2=buttons[row+1][col].getText();
				if(!text1.equals(text2) || text1.length()==0)
					break;
				row++;
			}
			if(row==Board_Size-1)
			{
				if(text1.equals("X"))
					return GameStatus.XWins;
				else
					return GameStatus.ZWins;
						
			}
			col++;
		}
		
		
		//Test in 1st Diagonal
		row=0;
		col=0;
		while(row<Board_Size-1)
		{
			text1=buttons[row][col].getText();
			text2=buttons[row+1][col+1].getText();
			if(!text1.equals(text2) || text1.length()==0)
				break;
			row++;
			col++;
			if(row==Board_Size-1)
			{
				if(text1.equals("X"))
					return GameStatus.XWins;
				else
					return GameStatus.ZWins;
						
			}
		}
		
		
		//Test in 2nd Diagonal
				row=Board_Size-1;
				col=0;
				while(row>0)
				{
					text1=buttons[row][col].getText();
					text2=buttons[row-1][col+1].getText();
					if(!text1.equals(text2) || text1.length()==0)
						break;
					row--;
					col++;
					if(row==0)
					{
						if(text1.equals("X"))
							return GameStatus.XWins;
						else
							return GameStatus.ZWins;
								
					}
				}
		
		String txt="";
		for(row=0;row<Board_Size;row++)   // Create JButton
		{
			for(col=0;col<Board_Size;col++)
			{
				txt=buttons[row][col].getText();
				if(txt.length()==0)
					return GameStatus.Incomplete;
				
			}
		}
		return GameStatus.Tie;
	}
	
	private void declareWinner(GameStatus gs)
	{
		if(gs==GameStatus.XWins)
			JOptionPane.showMessageDialog(this,"X Wins");
		else if(gs==GameStatus.ZWins)
			JOptionPane.showMessageDialog(this,"Z Wins");
		else
			JOptionPane.showMessageDialog(this,"Tie");
			
	}
}
