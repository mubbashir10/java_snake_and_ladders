//importing classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.Arrays.*;
import java.util.*;

//client
public class GamePlay extends JFrame implements MouseListener{
	
	//components
	private BufferedImage bimg; 
	private JLabel board;
	private JLabel green;
	private JLabel red;
	private JLabel blue;
	private JLabel yellow;
	private JLabel playerName1;
	private JLabel playerName2;
	private JLabel playerName3;
	private JLabel playerName4;
	private Button dice1;
	private Button dice2;
	private Button dice3;
	private Button dice4;
	private JLabel turn;
	private JLabel currentPlayer;
	private JLabel value;
	private JLabel number;
	
	//movements
	private int player = 1;
	private int[] position;
	private int min = 1;
	private int max = 6;
	private int diceVal;
	private String[] coGreen;
	private String[] coRed;
	private String[] coBlue;
	private String[] coYellow;
	private String[] tmpCoGreen;
	private String[] tmpCoRed;
	private String[] tmpCoBlue;
	private String[] tmpCoYellow;
	private int currPosGreen = 0;
	private int currPosRed = 0;
	private int currPosBlue = 0;
	private int currPosYellow = 0;
	private Boolean poisonGreen = false;
	private Boolean poisonRed = false;
	private Boolean poisonBlue = false;
	private Boolean poisonYellow = false;
	private int tmpPositionGreen;
	private int tmpPositionRed;
	private int tmpPositionBlue;
	private int tmpPositionYellow;
	private GamePlay g;
	private int playerCount;


	//constructor
	GamePlay(int playerCount){

		//assigning object
		this.playerCount = playerCount;
		
		//creating elements
		playerName1 = new JLabel("Player 1");
		playerName1.setBounds(690,80,500,30);
		playerName1.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
    	playerName1.setForeground(Color.decode("#45d11f"));
    	playerName2 = new JLabel("Player 2");
		playerName2.setBounds(690,130,500,30);
		playerName2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
    	playerName2.setForeground(Color.decode("#e00d0d"));
    	playerName3 = new JLabel("Player 3");
		playerName3.setBounds(690,180,500,30);
		playerName3.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
    	playerName3.setForeground(Color.decode("#2e7ee8"));
    	playerName4 = new JLabel("Player 4");
		playerName4.setBounds(690,230,500,30);
		playerName4.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
    	playerName4.setForeground(Color.decode("#eadf15"));
    	dice1 = new Button("Roll Dice");
    	dice1.addMouseListener(this);
		dice1.setBounds(690,450,100,30);
		dice2 = new Button("Roll Dice");
    	dice2.addMouseListener(this);
		dice2.setBounds(-100,-100,0,0);
		dice3 = new Button("Roll Dice");
    	dice3.addMouseListener(this);
		dice3.setBounds(-100,-100,0,0);
		dice4 = new Button("Roll Dice");
    	dice4.addMouseListener(this);
		dice4.setBounds(-100,-100,0,0);
		turn = new JLabel("Turn: ");
		turn.setBounds(20,450,500,30);
		turn.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
    	turn.setForeground(Color.decode("#666666"));
    	currentPlayer = new JLabel("Player 1");
		currentPlayer.setBounds(75,450,500,30);
		currentPlayer.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
    	currentPlayer.setForeground(Color.decode("#666666"));
		value = new JLabel("Dice Value (of previous player): ");
		value.setBounds(200,450,500,30);
		value.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
    	value.setForeground(Color.decode("#666666"));
		number = new JLabel("0");
		number.setBounds(510,450,500,30);
		number.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
    	number.setForeground(Color.decode("#666666"));
		
		//drawing images
		try{

			bimg = ImageIO.read(new File("resources/board.jpg"));
			board = new JLabel(new ImageIcon(bimg));
			board.setBounds(20,-15,650,500);

			bimg = ImageIO.read(new File("resources/green_piece.png"));
			green = new JLabel(new ImageIcon(bimg));
			green.setBounds(-290,110,650,500);

			bimg = ImageIO.read(new File("resources/red_piece.png"));
			red = new JLabel(new ImageIcon(bimg));
			red.setBounds(-290,130,650,500);

			bimg = ImageIO.read(new File("resources/blue_piece.png"));
			blue = new JLabel(new ImageIcon(bimg));
			blue.setBounds(-290,150,650,500);

			bimg = ImageIO.read(new File("resources/yellow_piece.png"));
			yellow = new JLabel(new ImageIcon(bimg));
			yellow.setBounds(-290,170,650,500);
		}
		catch(Exception e){
			e.printStackTrace();
		}

		//adding elements
		add(dice1);
		add(dice2);
		add(dice3);
		add(dice4);
		add(playerName1);
		add(playerName2);
		if(playerCount>3){
			add(playerName3);
		}
		if(playerCount>2){
			add(playerName4);
		}
		add(turn);
		add(currentPlayer);
		add(value);
		add(number);
		if(playerCount>3){
			add(yellow);
		}
		if(playerCount>2){
			add(blue);
		}
		add(red);
		add(green);
		add(board);

		
		//position array
		position = new int[32];
		for(int i = 0; i<32; i++){
			position[i] = i;
			if(i==4)
				position[i] = 12;
			if(i==7)
				position[i] = 9;
			if(i==11)
				position[i] = 25;
			if(i==14)
				position[i] = 2;
			if(i==28)
				position[i] = 18;
			if(i==22)
				position[i] = 5;
		}

              
		//cordinates 
		coGreen = new String[32];
		coRed = new String[32];
		coBlue = new String[32];
		coYellow = new String[32];

		//row 1
		coGreen[0] = "-290,110";
		coGreen[1] = "-210,110";
		coGreen[2] = "-130,110";
		coGreen[3] = "-50,110";
		coGreen[4] = "30,110";
		coGreen[5] = "110,110";
		coGreen[6] = "190,110";
		coGreen[7] = "270,110";

		coRed[0] = "-290,130";
		coRed[1] = "-210,130";
		coRed[2] = "-130,130";
		coRed[3] = "-50,130";
		coRed[4] = "30,130";
		coRed[5] = "110,130";
		coRed[6] = "190,130";
		coRed[7] = "270,130";
		
		coBlue[0] = "-290,150";
		coBlue[1] = "-210,150";
		coBlue[2] = "-130,150";
		coBlue[3] = "-50,150";
		coBlue[4] = "30,150";
		coBlue[5] = "110,150";
		coBlue[6] = "190,150";
		coBlue[7] = "270,150";

		coYellow[0] = "-290,170";
		coYellow[1] = "-210,170";
		coYellow[2] = "-130,170";
		coYellow[3] = "-50,170";
		coYellow[4] = "30,170";
		coYellow[5] = "110,170";
		coYellow[6] = "190,170";
		coYellow[7] = "270,170";

		//row 2
		coGreen[8] = "270,0";
		coGreen[9] = "190,0";
		coGreen[10] = "110,0";
		coGreen[11] = "100,0";
		coGreen[12] = "-50,0";
		coGreen[13] = "-100,0";
		coGreen[14] = "-210,0";
		coGreen[15] = "-290,0";

		coRed[8] = "270,20";
		coRed[9] = "190,20";
		coRed[10] = "110,20";
		coRed[11] = "100,20";
		coRed[12] = "-50,20";
		coRed[13] = "-100,20";
		coRed[14] = "-210,20";
		coRed[15] = "-290,20";

		coBlue[8] = "270,40";
		coBlue[9] = "190,40";
		coBlue[10] = "110,40";
		coBlue[11] = "100,40";
		coBlue[12] = "-50,40";
		coBlue[13] = "-100,40";
		coBlue[14] = "-210,40";
		coBlue[15] = "-290,40";

		coYellow[8] = "270,60";
		coYellow[9] = "190,60";
		coYellow[10] = "110,60";
		coYellow[11] = "100,60";
		coYellow[12] = "-50,60";
		coYellow[13] = "-100,60";
		coYellow[14] = "-210,60";
		coYellow[15] = "-290,60";


		//row 3
		coGreen[16] = "-290,-100";
		coGreen[17] = "-210,-100";
		coGreen[18] = "-130,-100";
		coGreen[19] = "-50,-100";
		coGreen[20] = "30,-100";
		coGreen[21] = "110,-100";
		coGreen[22] = "190,-100";
		coGreen[23] = "270,-100";

		coRed[16] = "-290,-80";
		coRed[17] = "-210,-80";
		coRed[18] = "-130,-80";
		coRed[19] = "-50,-80";
		coRed[20] = "30,-80";
		coRed[21] = "110,-80";
		coRed[22] = "190,-80";
		coRed[23] = "270,-80";

		coBlue[16] = "-290,-60";
		coBlue[17] = "-210,-60";
		coBlue[18] = "-130,-60";
		coBlue[19] = "-50,-60";
		coBlue[20] = "30,-60";
		coBlue[21] = "110,-60";
		coBlue[22] = "190,-60";
		coBlue[23] = "270,-60";

		coYellow[16] = "-290,-40";
		coYellow[17] = "-210,-40";
		coYellow[18] = "-130,-40";
		coYellow[19] = "-50,-40";
		coYellow[20] = "30,-40";
		coYellow[21] = "110,-40";
		coYellow[22] = "190,-40";
		coYellow[23] = "270,-40";

	
		//row4
		coGreen[24] = "270,-200";
		coGreen[25] = "190,-200";
		coGreen[26] = "110,-200";
		coGreen[27] = "100,-200";
		coGreen[28] = "-50,-200";
		coGreen[29] = "-100,-200";
		coGreen[30] = "-210,-200";
		coGreen[31] = "-290,-200";

		coRed[24] = "270,-180";
		coRed[25] = "190,-180";
		coRed[26] = "110,-180";
		coRed[27] = "100,-180";
		coRed[28] = "-50,-180";
		coRed[29] = "-100,-180";
		coRed[30] = "-210,-180";
		coRed[31] = "-290,-180";

		coBlue[24] = "270,-160";
		coBlue[25] = "190,-160";
		coBlue[26] = "110,-160";
		coBlue[27] = "100,-160";
		coBlue[28] = "-50,-160";
		coBlue[29] = "-100,-160";
		coBlue[30] = "-210,-160";
		coBlue[31] = "-290,-160";

		coYellow[24] = "270,-140";
		coYellow[25] = "190,-140";
		coYellow[26] = "110,-140";
		coYellow[27] = "100,-140";
		coYellow[28] = "-50,-140";
		coYellow[29] = "-100,-140";
		coYellow[30] = "-210,-140";
		coYellow[31] = "-290,-140";


		

	}

	//mouse hover actions and effects
	public void mouseClicked(MouseEvent mc){ 

		Object button = mc.getSource();
		Random rn = new Random();
		
		//player 1
		if (button==dice1&&player==1){
			diceVal = rn.nextInt(max - min + 1) + min;
			if(diceVal ==6){
				poisonGreen = false;
			}
			if((currPosGreen + diceVal<32)&&poisonGreen == false){
				if(currPosGreen + diceVal==31){
					this.getContentPane().removeAll();
					JLabel won = new JLabel("Player 1 WON!");
					won.setBounds(190,50,500,50);
					won.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
			    	won.setForeground(Color.decode("#45d11f"));
					this.add(won);
					this.revalidate();
					this.repaint();
					
				}
				tmpPositionGreen = currPosGreen + diceVal;
				currPosGreen = currPosGreen + diceVal;
				if(currPosGreen==14||currPosGreen==22||currPosGreen==28){
					poisonGreen = true;
				}
				currPosGreen =  position[currPosGreen];
				tmpCoGreen = coGreen[currPosGreen].split(",");
				green.setLocation(Integer.parseInt(tmpCoGreen[0]),Integer.parseInt(tmpCoGreen[1]));
			}
			System.out.println("Player:"+player+" Dice:"+diceVal+" New Position:"+currPosGreen+" Co-ord:"+coGreen[currPosGreen]+" Poison: "+poisonGreen);
			number.setText(""+diceVal);
			if((diceVal!=6)&&(tmpPositionGreen!=4&&tmpPositionGreen!=7&&tmpPositionGreen!=11)){
				player=2;
				currentPlayer.setText("Player "+player);
				dice1.setBounds(100,-100,0,0);
				dice2.setBounds(690,450,100,30);
			}
		}

		//player 2
		if (button==dice2&&player==2){
			diceVal = rn.nextInt(max - min + 1) + min;
			if(diceVal ==6){
				poisonRed = false;
			}
			if((currPosRed + diceVal<32)&&poisonRed == false){
				if(currPosRed + diceVal==31){
					this.getContentPane().removeAll();
					JLabel won = new JLabel("Player 2 WON!");
					won.setBounds(190,50,500,50);
					won.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
			    	won.setForeground(Color.decode("#e00d0d"));
					this.add(won);
					this.revalidate();
					this.repaint();
					
				}
				currPosRed = currPosRed + diceVal;
				if(currPosRed==14||currPosRed==22||currPosRed==28){
					poisonRed = true;
				}
				currPosRed =  position[currPosRed];
				tmpCoRed = coRed[currPosRed].split(",");
				red.setLocation(Integer.parseInt(tmpCoRed[0]),Integer.parseInt(tmpCoRed[1]));
			}
			System.out.println("Player:"+player+" Dice:"+diceVal+" New Position:"+currPosRed+" Co-ord:"+coRed[currPosRed]+" Poison: "+poisonRed);
			number.setText(""+diceVal);
			if((diceVal!=6)&&(tmpPositionRed!=4&&tmpPositionRed!=7&&tmpPositionRed!=11)){
				if(playerCount>2){
					player=3;
					currentPlayer.setText("Player "+player);
					dice2.setBounds(100,-100,0,0);
					dice3.setBounds(690,450,100,30);
				}
				else{
					player=1;
					currentPlayer.setText("Player "+player);
					dice2.setBounds(100,-100,0,0);
					dice1.setBounds(690,450,100,30);
				}
			}
		}

		//player 3
		if (button==dice3&&player==3){
			diceVal = rn.nextInt(max - min + 1) + min;
			if(diceVal ==6){
				poisonBlue = false;
			}
			if((currPosBlue + diceVal<32)&&poisonBlue == false){
				if(currPosBlue + diceVal==31){
					this.getContentPane().removeAll();
					JLabel won = new JLabel("Player 3 WON!");
					won.setBounds(190,50,500,50);
					won.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
			    	won.setForeground(Color.decode("#2e7ee8"));
					this.add(won);
					this.revalidate();
					this.repaint();
					
				}
				tmpPositionBlue = currPosBlue + diceVal;
				currPosBlue = currPosBlue + diceVal;
				if(currPosBlue==14||currPosBlue==22||currPosBlue==28){
					poisonBlue = true;
				}
				currPosBlue =  position[currPosBlue];
				tmpCoBlue = coBlue[currPosBlue].split(",");
				blue.setLocation(Integer.parseInt(tmpCoBlue[0]),Integer.parseInt(tmpCoBlue[1]));
			}
			System.out.println("Player:"+player+" Dice:"+diceVal+" New Position:"+currPosBlue+" Co-ord:"+coBlue[currPosBlue]+" Poison: "+poisonBlue);
			number.setText(""+diceVal);
			if((diceVal!=6)&&(tmpPositionBlue!=4&&tmpPositionBlue!=7&&tmpPositionBlue!=11)){
				if(playerCount>3){
					player=4;
					currentPlayer.setText("Player "+player);
					dice3.setBounds(100,-100,0,0);
					dice4.setBounds(690,450,100,30);
				}
				else{
					player=1;
					currentPlayer.setText("Player "+player);
					dice4.setBounds(100,-100,0,0);
					dice1.setBounds(690,450,100,30);
				}
			}
		}

		//player 4
		if (button==dice4&&player==4){
			diceVal = rn.nextInt(max - min + 1) + min;
			if(diceVal ==6){
				poisonYellow = false;
			}
			if((currPosYellow + diceVal<32)&&poisonYellow == false){
				if(currPosYellow + diceVal==31){
					this.getContentPane().removeAll();
					JLabel won = new JLabel("Player 4 WON!");
					won.setBounds(190,50,500,50);
					won.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
			    	won.setForeground(Color.decode("#45d11f"));
					this.add(won);
					this.revalidate();
					this.repaint();
					
				}
				tmpPositionYellow = currPosYellow + diceVal;
				currPosYellow = currPosYellow + diceVal;
				if(currPosYellow==14||currPosYellow==22||currPosYellow==28){
					poisonYellow = true;
				}
				currPosYellow =  position[currPosYellow];
				tmpCoYellow = coYellow[currPosYellow].split(",");
				yellow.setLocation(Integer.parseInt(tmpCoYellow[0]),Integer.parseInt(tmpCoYellow[1]));
			}
			System.out.println("Player:"+player+" Dice:"+diceVal+" New Position:"+currPosYellow+" Co-ord:"+coYellow[currPosYellow]+" Poison: "+poisonYellow);
			number.setText(""+diceVal);
			if((diceVal!=6)&&(tmpPositionYellow!=4&&tmpPositionYellow!=7&&tmpPositionYellow!=11)){
				if(playerCount>3){
					player=1;
					currentPlayer.setText("Player "+player);
					dice4.setBounds(100,-100,0,0);
					dice1.setBounds(690,450,100,30);
				}
			}
			
		}
			
	}

	public void mousePressed(MouseEvent me) { }
	public void mouseReleased(MouseEvent me) { }
	public void mouseExited(MouseEvent me) { }
	public void mouseEntered(MouseEvent me) { }

}