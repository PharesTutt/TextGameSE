//import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
//import javax.swing.border.*;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics2D;
//import java.awt.GraphicsDevice;
//import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game implements ComponentListener
{
	JFrame window;// all variables
	Container con;
	Clip audio;
	JPanel titleNamePanel,PicturePanel,ScenePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel, voiceOptionsPanel, itemBagPanel;
	JLabel titleNameLabel,PictureLabel,SceneLabel,hpLabel, hpLabelNumber, weaponLabel, weaponLabelName;
	JButton startButton,loadButton,exitButton, choice1,choice2,choice3,choice4;
	JTextArea mainTextArea, voiceOptions, itemBag;
	int WinCondition = 0, HP, MHP;
	String weapon,Position,monsterName, titleMusic, gameMusic, encounterMusic;
	Random random = new Random();
	String text,imagePath,PPath;
	ImageIcon image;
	int windowWidth,windowHeight, image_w,image_h;
	int i;
	
	//GraphicsDevice gDevice;
	
	TitleScreenHandler tsHandler = new TitleScreenHandler();
	ChoiceHandler choiceHandler = new ChoiceHandler();
	
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 45);
	Font normalFont = new Font("Times New Roman", Font.PLAIN, 25);
	Font textFont = new Font("Times New Roman", Font.PLAIN, 15);
	
	/////////////////////////////////////////////////////////////
	sideWork sw=new sideWork();
	String scene="/Users/tutt/eclipse-workspace/TextGame3/resources/TextFiles/Scenetest.txt";
	public static void main(String[] args) //main method
	{
		
		new Game();

	}
/////////////////////////////////////////////////////////////////////////////////////////////
	public void setFile(String soundFileName) {
		try {
			File file = new File(soundFileName);
			AudioInputStream sound = AudioSystem.getAudioInputStream(file);
			audio = AudioSystem.getClip();
			audio.open(sound);
			
		}
		catch(Exception e) {
			
		}
	}
	
	public void play() {
		audio.setFramePosition(0);
		audio.start();
	}
	public void stop() {
		audio.stop();
		audio.close();
	}
	@SuppressWarnings("static-access")
	public void loop() {
		audio.loop(audio.LOOP_CONTINUOUSLY);
	}
////////////////////////////////////////////////////////////////////////////////////////////
	
	public Game()//game start method
	{
	//	GraphicsEnvironment gEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
	//	gDevice = gEnvironment.getDefaultScreenDevice();
		
		titleMusic = ".//resources//Music//YouLikeJazz.wav";
		gameMusic = ".//resources//Music//Real Idle music.wav";
		encounterMusic = ".//resources//Music//suspense.wav";
		
		
		setFile(titleMusic);
		play();
		loop();
		
		
		window = new JFrame();								//create default window
		window.setSize(810, 500);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		window.addComponentListener(this);
		window.setResizable(true);
		//window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//window.setUndecorated(true);

		//window.setLayout(new BorderLayout());
		con = window.getContentPane();
		
		
		titleNamePanel = new JPanel();					//Create Title Screen
		titleNamePanel.setBounds( 450, 30, 300, 80);
		titleNamePanel.setBackground(Color.black);
		titleNamePanel.setOpaque(true);
		titleNameLabel = new JLabel("Detective game");
		titleNameLabel.setForeground(Color.white);
		titleNameLabel.setFont(titleFont);
		
		//titleNameLabel.setHorizontalAlignment(SwingConstants.CENTER); //testing resizing
		
		PicturePanel = new JPanel();
		PicturePanel.setBounds(0, -20, 800, 500);	
		PicturePanel.setBackground(Color.blue);

		PictureLabel = new JLabel();
		imagePath = ".//resources//Pictures//Final Title Screen21.jpg";
		image = new ImageIcon(imagePath);
		PictureLabel.setIcon(image);
	    PicturePanel.add(PictureLabel);
		

		
		startButtonPanel = new JPanel();				//Create Start Button
		startButtonPanel.setBounds(500, 125, 200, 100);
		startButtonPanel.setBackground(Color.black);
		startButtonPanel.setOpaque(false);
		
		startButton = new JButton("New Game");	
		startButton.setBackground(Color.black);
		startButton.setForeground(Color.white);
		startButton.setFont(normalFont);
		startButton.setFocusPainted(false);
		startButton.addActionListener(tsHandler);
		startButton.setOpaque(true);
		startButton.setBorderPainted(false);
		
		loadButton = new JButton("Load");	
		loadButton.setBackground(Color.black);
		loadButton.setForeground(Color.white);
		loadButton.setFont(normalFont);
		loadButton.setFocusPainted(false);
	//	loadButton.addActionListener(tsHandler);
		loadButton.setOpaque(true);
		loadButton.setBorderPainted(false);
		
		exitButton = new JButton("Exit");	
		exitButton.setBackground(Color.black);
		exitButton.setForeground(Color.white);
		exitButton.setFont(normalFont);
		exitButton.setFocusPainted(false);
	//	exitButton.addActionListener(tsHandler);
		exitButton.setOpaque(true);
		exitButton.setBorderPainted(false);
		
		startButtonPanel.add(startButton);
		startButtonPanel.add(loadButton);
		startButtonPanel.add(exitButton);
		titleNamePanel.add(titleNameLabel);
		mainTextPanel = new JPanel();
		mainTextPanel.setBounds(0, 313, 494, 150);
		mainTextPanel.setBackground(Color.black);

		
		mainTextArea = new JTextArea("");
		mainTextArea.setBounds(0, 313, 494, 150);
		mainTextArea.setBackground(Color.black);
		mainTextArea.setForeground(Color.white);
		mainTextArea.setFont(textFont);
		mainTextArea.setLineWrap(true);
		mainTextArea.setWrapStyleWord(true);
		mainTextPanel.add(mainTextArea);
////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////	
		choiceButtonPanel = new JPanel();
		choiceButtonPanel.setBounds(494, 312, 300, 150);
		choiceButtonPanel.setBackground(Color.black);
		choiceButtonPanel.setLayout(new GridLayout(4,1));

		
		choice1 = new JButton("choice1");
		choice1.setBackground(Color.white);
		choice1.setForeground(Color.black);
		choice1.setFont(normalFont);
		choice1.setFocusPainted(false);
		choice1.addActionListener(choiceHandler);
		choice1.setActionCommand("c1");
		choice1.setOpaque(true);
		choice1.setBorderPainted(true);
		choiceButtonPanel.add(choice1);
		
		choice2 = new JButton("choice2");
		choice2.setBackground(Color.white);
		choice2.setForeground(Color.black);
		choice2.setFont(normalFont);
		choice2.setFocusPainted(false);
		choice2.addActionListener(choiceHandler);
		choice2.setActionCommand("c2");
		choice2.setOpaque(true);
		choice2.setBorderPainted(true);
		choiceButtonPanel.add(choice2);
		
		choice3 = new JButton("choice3");
		choice3.setBackground(Color.white);
		choice3.setForeground(Color.black);
		choice3.setFont(normalFont);
		choice3.setFocusPainted(false);
		choice3.addActionListener(choiceHandler);
		choice3.setActionCommand("c3");
		choice3.setOpaque(true);
		choice3.setBorderPainted(true);
		choiceButtonPanel.add(choice3);
		
		choice4 = new JButton("choice4");
		choice4.setBackground(Color.white);
		choice4.setForeground(Color.black);
		choice4.setFont(normalFont);
		choice4.setFocusPainted(false);
		choice4.addActionListener(choiceHandler);
		choice4.setActionCommand("c4");
		choice4.setOpaque(true);
		choice4.setBorderPainted(true);
		choiceButtonPanel.add(choice4);
		
		con.add(choiceButtonPanel);//,BorderLayout.SOUTH);
		con.add(mainTextPanel);//,BorderLayout.CENTER);
		con.add(titleNamePanel);//,BorderLayout.CENTER);
		con.add(startButtonPanel);//,BorderLayout.SOUTH);		
		con.add(PicturePanel);


		choiceButtonPanel.setVisible(false);
		mainTextPanel.setVisible(false);

		
		window.setVisible(true);
	}
	public void Title()
	{
		stop();
		setFile(titleMusic);
		play();
		loop(); 
		
		mainTextPanel.setVisible(false);
		choiceButtonPanel.setVisible(false);
		
		titleNamePanel.setVisible(true);
		startButtonPanel.setVisible(true);
		PicturePanel.setVisible(true);
	}
	public void createGameScreen() //update window to display main game screen
	{	
		
		titleNamePanel.setVisible(false);
		startButtonPanel.setVisible(false);
		
		choiceButtonPanel.setVisible(true);
		mainTextPanel.setVisible(true);

		playerSetup();
	}
	public void playerSetup()  // beginner player setup
	{
		WinCondition = 0;
		townGate();
	}
	
	  Timer timer = new Timer(20,new ActionListener()
	{ @Override
	  public void actionPerformed(ActionEvent e)
	  {
		char character[] = text.toCharArray();
		int arrayNumber = character.length;
		
		String addedCharacter = "";
		String blank = "";
		
		addedCharacter = blank + character[i];
		mainTextArea.append(addedCharacter);
		
		i++;
		
		if(i == arrayNumber){
			i = 0;
			timer.stop();
		
		
		}
		
	  }
	});
	public void prepareText()
	{
		i = 0;
		mainTextArea.setText("");
		timer.start();
		
	}
	public void SceneSetup() 
	{
		sw.SetSceneVar(scene);
		prepareText();
		stop();
		setFile(sw.MPath);
		loop();
		Position = "townGate";
		imagePath = sw.PPath;//set scene1a.jpg and folder to a variable
		image = new ImageIcon(imagePath);
		PictureLabel.setIcon(resize(image,window.getWidth(),window.getHeight()));
		PictureLabel.revalidate();
		
		//set text equal to what the received input is
		text = sw.TPath;
		choice1.setText(sw.B1Path);//change button text and functions to variables.
		choice2.setText(sw.B2Path);
		choice3.setText(sw.B3Path);
		choice4.setText(sw.B4Path);
	}
	public void townGate() 
	{
		sw.SetSceneVar(scene);
		prepareText();
		stop();
		setFile(sw.MPath);
		loop();
		Position = "townGate";
		imagePath = sw.PPath;//set scene1a.jpg and folder to a variable
		image = new ImageIcon(imagePath);
		PictureLabel.setIcon(resize(image,window.getWidth(),window.getHeight()));
		PictureLabel.revalidate();
		
		//set text equal to what the received input is
		text = sw.TPath;
		prepareText();
		choice1.setText(sw.B1Path);//change button text and functions to variables.
		choice2.setText(sw.B2Path);
		choice3.setText(sw.B3Path);
		choice4.setText(sw.B4Path);
	}
	public void talkGuard() 
	{
		Position = "GateGuard";
		//mainTextArea.setText("to continue please say north");
		text ="to continue please say north";
		prepareText();
		choice1.setText("Activate voice command");
		choice2.setText("Voice Options");
		choice3.setText("Item Bag");
		choice4.setText("");
	}
	public void CardinalDirection()
	{
		WinCondition ++; ;
		if(WinCondition >= 15)
		{
			GameWin();
		}else 
		{
		int RNG = (random.nextInt(6) + 1);
	
		switch(RNG){
			case 1:openField(); break;
			case 2:Encounter();break;
			case 3:ruins(); break;
			case 4:building();break;
			case 5:lake(); break;
			case 6:store();break;
			}
		}
	}
	public void Direction() 
	{
		int check = 0;
		String result = Position;

/////////////////////////////////////////////////////////////////////////////////////////////		
		switch(result) {
		case "GateGuard":
 			if(Position.equals("Fight")||Position.equals("curFight")) {

			}else {
				CardinalDirection();check++;
			}
 			
		break;
		case "north":
 			if(Position.equals("Fight")||Position.equals("curFight")) {

			}else {
				CardinalDirection();check++;
			}
 			
		break;
		case "east":
 			if(Position.equals("Fight")||Position.equals("curFight")) {

			}else {
				CardinalDirection();check++;
			}
 			
		break;
		case "south":
 			if(Position.equals("Fight")||Position.equals("curFight")) {

			}else {
				CardinalDirection();check++;
			}
 			
		break;
		case "west":
 			if(Position.equals("Fight")||Position.equals("curFight")) {

			}else {
				CardinalDirection();check++;
			}
 			
		break;
		case "Fight":
			if(Position.equals("Fight")||Position.equals("curFight")) {
				damage();
				check++;
			}
			break;
		case "curFight":
			if(Position.equals("Fight")||Position.equals("curFight")) {
				damage();
				check++;
			}
			break;
		case "escape" :
			if(Position.equals("Fight")||Position.equals("curFight")) {
				CardinalDirection();
				stop();
				setFile(gameMusic);
				loop();
				check++;
			}
			break;
		default:
			CardinalDirection();
		}
////////////////////////////////////////////////////////////////////////////////////////////////	
		
		if(check == 1)
		{
			
		}else {
			choice1.setText("Try Again.");
		}
		
	}
	public void GameWin()
	{
		Position = "GameWin";
		//mainTextArea.setText("You Reach Home with oodles of loot from Town.    \n <GAME WIN>");
		text ="You Reach Home with oodles of loot from Town.    \n <GAME WIN>";
		prepareText();
		choice1.setText("Restart?");
		choice2.setText("Exit");
		choice3.setText("");
		choice4.setText("");
	}
	public void openField()
	{
		Position = "openField";
		//mainTextArea.setText("An open field nothing of interest here.");
		text ="An open field nothing of interest here.";
		prepareText();
		choice1.setText("Voice command");
		choice2.setText("Voice Options");
		choice3.setText("Item Bag");
		choice4.setText("");
	}
	public void ruins()
	{
		Position = "ruins";
		//mainTextArea.setText("ruins lie before you indicating \na primordial civilization lay to wastes on these grounds.");
		text ="ruins lie before you indicating \na primordial civilization lay to wastes on these grounds.";
		prepareText();
		choice1.setText("Voice command");
		choice2.setText("Voice Options");
		choice3.setText("Item Bag");
		choice4.setText("");
	}
	public void building()
	{
		Position = "building";
		//mainTextArea.setText("You come across an empty building and find nothing of value.");
		text ="You come across an empty building and find nothing of value.";
		prepareText();
		choice1.setText("Voice command");
		choice2.setText("Voice Options");
		choice3.setText("Item Bag");
		choice4.setText("");
	}
	public void lake()
	{
		Position = "lake";
		//mainTextArea.setText("A wide open lake where many have settled.");
		text ="A wide open lake where many have settled.";
		prepareText();
		choice1.setText("Voice command");
		choice2.setText("Voice Options");
		choice3.setText("Item Bag");
		choice4.setText("");
	}
	public void store()
	{
		Position = "store";
		//mainTextArea.setText("You come across a store that sells all kinds of \nmaterials and weapons. but you see theres a sign on \nthe door [Closed till Further Notice]");
		text ="You come across a store that sells all kinds of \nmaterials and weapons. but you see theres a sign on \nthe door [Closed till Further Notice]";
		prepareText();
		choice1.setText("Voice command");
		choice2.setText("Voice Options");
		choice3.setText("Item Bag");
		choice4.setText("");
	}

	public void Encounter()
	{
		stop();
		setFile(encounterMusic);
		loop();
		Position = "Fight";
		//genMonster();
		monsterName = "zombie";
		MHP = 16;
		//mainTextArea.setText("MonsterHP: " + MHP + "\nyou encounter a " + monsterName + ". \nWhat do you do?");
		text ="MonsterHP: " + MHP + "\nyou encounter a " + monsterName + ". \nWhat do you do?";
		prepareText();
		choice1.setText("Voice command");
		choice2.setText("Voice Options");
		choice3.setText("Item Bag");
		choice4.setText("");
	}
	public void damage(){
		int DP = 3;
		MHP -= DP;
		if (MHP <= 0)
			fightWin();
		else {
		currentFight();
		}
	}
	public void currentFight() {
		if (MHP < 16) {
		//mainTextArea.setText("You deal 3 damage to the " + monsterName+ "." + "\nMonsterHP: " + MHP + "\nMonster: " + monsterName);
			text ="You deal 3 damage to the " + monsterName+ "." + "\nMonsterHP: " + MHP + "\nMonster: " + monsterName;
			prepareText();
		}else {
		//mainTextArea.setText("MonsterHP: " + MHP + "     Monster: " + monsterName);
			text ="MonsterHP: " + MHP + "     Monster: " + monsterName;
			prepareText();
		}
		Position = "curFight";

		choice1.setText("Voice command");
		choice2.setText("Voice Options");
		choice3.setText("Item Bag");
		choice4.setText("");
	}
	public void fightWin() 
	{
		stop();
		setFile(gameMusic);
		loop();
		Position = "win";
		//mainTextArea.setText("You Defeated the " + monsterName + "! \nPlease input a direction.");
		text ="You Defeated the "+ monsterName + "! \nPlease input a direction.";
		prepareText();
		choice1.setText("Voice command");
		choice2.setText("Voice Options");
		choice3.setText("Item Bag");
		choice4.setText("");
		
	}
	public int intRound(int x,double y) {
		int result;
		result = (int)Math.round(x*y);
		
	return result;
	}
	/*public void genMonster() // to be implemented
	{
		
		
	}*/
	/*
	public void voiceOptions()
	{
		if(!voiceOptionsPanel.isVisible())
		voiceOptionsPanel.setVisible(true);
		else {
			voiceOptionsPanel.setVisible(false);
			 }
	}
	public void itemBag()
	{
		if(!itemBagPanel.isVisible())
			itemBagPanel.setVisible(true);
		else {
			itemBagPanel.setVisible(false);
			 }
	}
	*/
	public class TitleScreenHandler implements ActionListener
	{
		
		public void actionPerformed(ActionEvent event) 
		{
			createGameScreen();
		}
		
	}
	public class ChoiceHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event) 
		{
			
			String yourChoice = event.getActionCommand();
			/*	
			switch(yourChoice){
			case"c1":ButtonFunction(B1Function);break;
			case"c2":ButtonFunction(B2Function);break;
			case"c3":ButtonFunction(B3Function);break;
			case"c4":ButtonFunction(B4Function);break;
			}
			*/
			switch(Position){
			case "townGate":
				switch(yourChoice){
				case"c1": talkGuard(); break;
				case"c2":break;
				case"c3":break;
				case"c4":break;
				}
				break;
			case "GateGuard":
				switch(yourChoice) 
				{
				case"c1":Direction();break;
				case"c2":break;
				case"c3":break;
				case"c4":break;
				}
				break;
			case "Fight":
				switch(yourChoice) 
				{
				case"c1":Direction();break;
				case"c2":break;
				case"c3":break;
				case"c4":break;
				}
				break;
			case "curFight":
				switch(yourChoice) 
				{
				case"c1":Direction();break;
				case"c2":break;
				case"c3":break;
				case"c4":break;
				}
				break;
			case "win":
				switch(yourChoice) 
				{
				case"c1":Direction();break;
				case"c2":break;
				case"c3":break;
				case"c4":break;
				}
				break;
			case "openField":
				switch(yourChoice) 
				{
				case"c1":Direction();break;
				case"c2":break;
				case"c3":break;
				case"c4":break;
				}
				break;
			case "lake":
				switch(yourChoice) 
				{
				case"c1":Direction();break;
				case"c2":break;
				case"c3":break;
				case"c4":break;
				}
				break;
			case "store":
				switch(yourChoice) 
				{
				case"c1":Direction();break;
				case"c2":break;
				case"c3":break;
				case"c4":break;
				}
				break;
			case "building":
				switch(yourChoice) 
				{
				case"c1":Direction();break;
				case"c2":break;
				case"c3":break;
				case"c4":break;
				}
				break;
			case "ruins":
				switch(yourChoice) 
				{
				case"c1":Direction();break;
				case"c2":break;
				case"c3":break;
				case"c4":break;
				}
				break;
			case "GameWin":
				switch(yourChoice) 
				{
				case"c1":Title();break;
				case"c2":System.exit(0);break;
				case"c3":break;
				case"c4":break;
				}
				break;
			}
		}
			

	}
	public static ImageIcon resize(ImageIcon target,int Width,int Height) {
		BufferedImage bi = new BufferedImage(Width,Height,BufferedImage.TRANSLUCENT);
		Graphics2D gd = (Graphics2D)bi.createGraphics();
		gd.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
		gd.drawImage(target.getImage(),0,0,Width,Height,null);
		gd.dispose();
		return new ImageIcon(bi);
	}
	
	@Override
	public void componentHidden(ComponentEvent arg0) {
		// only used for abstract method placeholder
		
	}
	@Override
	public void componentMoved(ComponentEvent arg0) {
		// only used for abstract method placeholder
		
	}
	@Override
	public void componentResized(ComponentEvent arg0) {
	//allow the manipulation of the components when the window is resized.
		
		windowWidth = window.getWidth();//Original size: 810
		windowHeight = window.getHeight();//Original size: 500
		
	//	titleFont = new Font("Times New Roman", Font.PLAIN, 45); // experimenting with text size manipulation
	//	normalFont = new Font("Times New Roman", Font.PLAIN, 25);
	//	textFont = new Font("Times New Roman", Font.PLAIN, 15);
		
		titleNamePanel.setBounds(intRound(windowWidth,0.55555), intRound(windowHeight,0.06), intRound(windowWidth,0.370370), intRound(windowHeight,0.16));
		titleNamePanel.revalidate();
		PicturePanel.setBounds(0, -(intRound(windowHeight,0.04)), intRound(windowWidth,1), intRound(windowHeight,1));	
		PicturePanel.revalidate();
		
		image_w = windowWidth;
		image_h = windowHeight;
		
		//Image im = image.getImage().getScaledInstance(image_w, image_h,Image.SCALE_DEFAULT);
		//image = new ImageIcon(im);
		//PictureLabel.setIcon(image);
		PictureLabel.setIcon(resize(image,image_w,image_h));
		PictureLabel.revalidate();
		PicturePanel.revalidate();

		startButtonPanel.setBounds(intRound(windowWidth,0.61728), intRound(windowHeight,0.25), intRound(windowWidth,0.246913), intRound(windowHeight,0.2));
		startButtonPanel.revalidate();
		mainTextPanel.setBounds(0, intRound(windowHeight,0.626), intRound(windowWidth,0.6175), intRound(windowHeight,0.3));
		mainTextPanel.revalidate();
		mainTextArea.setBounds(0, intRound(windowHeight,0.626), intRound(windowWidth,0.6175), intRound(windowHeight,0.3));
		mainTextArea.revalidate();
		choiceButtonPanel.setBounds(intRound(windowWidth,0.6175), intRound(windowHeight,0.626), intRound(windowWidth,0.3703703704), intRound(windowHeight,0.3));
		choiceButtonPanel.revalidate();

	}
	@Override
	public void componentShown(ComponentEvent arg0) {
		// only used for abstract method placeholder
		
	}

}

