//importing classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

//client
public class SnakeMania extends JFrame implements MouseListener{
	
	//variable references
	private JLabel welcome;
	private JLabel slogan;
	private JButton start;
	private JComboBox<String> option;
	private BufferedImage bimg; 
	private JLabel snakeImg;
	private GamePlay gp;
	private int players;
	
	//constructor
	SnakeMania(){
		
		//creating elements
		welcome = new JLabel("Welcome to SnakeMania");
		welcome.setBounds(190,50,500,50);
		welcome.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
    	welcome.setForeground(Color.decode("#45d11f"));
		slogan = new JLabel("select the number of desired player, then click start!");
		slogan.setBounds(210,100,500,50);
		slogan.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
    	slogan.setForeground(Color.decode("#666666"));
    	String[] choices = { "2 Players","3 Players", "4 Players"};
		option  = new JComboBox<String>(choices);
		option.setBounds(300,180,200,50);
		start = new JButton("Start Game");
		start.setBounds(330,300,150,50);
		start.addMouseListener(this);
		  
		
		//drawing images
		try{

			bimg = ImageIO.read(new File("resources/menu.png"));
			snakeImg = new JLabel(new ImageIcon(bimg));
			snakeImg.setBounds(550,200,300,300);
		}
		catch(Exception e){
			e.printStackTrace();
		}

		//adding elements
		add(welcome);
		add(slogan);
		add(option);
		add(snakeImg);
		add(start);
	}

	//main method
	public static void main(String [] args){

		SnakeMania menu = new SnakeMania();
		menu.setLayout(null);
		menu.setBounds(100,100,850,520);
		menu.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		menu.setVisible(true);

	}

	//unit test
	public boolean checkPlayers(int players){

		this.players = players;

		if (players == 4) {
			return true;
		} 
		else 
			return false;
		
	}
	
	//mouse click action
	public void mouseClicked(MouseEvent e){
		
		//making object of Object class
		Object actionSource = e.getSource();

		//start button
		if (actionSource==start){

			//getting player count
			String x = String.valueOf(option.getSelectedItem());
			String[] tmp = x.split(" ");
			int count = Integer.parseInt(tmp[0]);

			//starting game
			gp = new GamePlay(count);
			gp.setLayout(null);
			gp.setBounds(100,100,850,520);
			gp.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			gp.getContentPane().setBackground(Color.WHITE);
			gp.setVisible(true);

			//closing menu
			this.setVisible(false);
			this.dispose();
			this.setVisible(false);

		} 

	}

	//null body implementation
	public void mousePressed(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) {}

}