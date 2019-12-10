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
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

public class Game implements ComponentListener
{
	JFrame window;// all variables
	Container con;
	Clip audio;
	JPanel titleNamePanel,PicturePanel,ScenePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel, voiceOptionsPanel, itemBagPanel;
	JLabel titleNameLabel,PictureLabel,SceneLabel;
	JButton startButton,loadButton,exitButton, choice1,choice2,choice3,choice4;
	JTextArea mainTextArea, voiceOptions;
	String titleMusic, gameMusic, encounterMusic;
	String text,imagePath,PPath,folderName,game1,game2,pastMusic;
	ImageIcon image;
	Image lmage;
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

	String scene=".//resources//TextFiles//"+folderName+"//TitleScreen";
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
		
		game1 = "Cardinal Exploring";
		game2 = "Gumshoe";
	
		
		
	//	GraphicsEnvironment gEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
	//	gDevice = gEnvironment.getDefaultScreenDevice();
		folderName = game1;
		scene=".//resources//TextFiles//"+folderName+"//TitleScreen";
		sw.SetSceneVar(scene);
		System.out.println(sw.MPath);
		titleMusic = sw.MPath;
		gameMusic = ".//resources//Music//Real Idle music.wav";
		encounterMusic = ".//resources//Music//suspense.wav";
		
		
		setFile(sw.MPath);
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
		titleNamePanel.setBounds( 450, 30, 450, 80);
		titleNamePanel.setBackground(Color.black);
		titleNamePanel.setOpaque(true);
		titleNameLabel = new JLabel(folderName);
		titleNameLabel.setForeground(Color.white);
		titleNameLabel.setFont(titleFont);
		
		//titleNameLabel.setHorizontalAlignment(SwingConstants.CENTER); //testing resizing
		
		PicturePanel = new JPanel();
		PicturePanel.setBounds(0, -20, 800, 500);	
		PicturePanel.setBackground(Color.blue);

		PictureLabel = new JLabel();
		imagePath = sw.PPath;
		System.out.println(sw.PPath);
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
		startButton.setActionCommand("start");
		startButton.setOpaque(true);
		startButton.setBorderPainted(false);
		
		loadButton = new JButton("Load");	
		loadButton.setBackground(Color.black);
		loadButton.setForeground(Color.white);
		loadButton.setFont(normalFont);
		loadButton.setFocusPainted(false);
		loadButton.addActionListener(tsHandler);
		loadButton.setActionCommand("load");
		loadButton.setOpaque(true);
		loadButton.setBorderPainted(false);
		
		exitButton = new JButton("Exit");	
		exitButton.setBackground(Color.black);
		exitButton.setForeground(Color.white);
		exitButton.setFont(normalFont);
		exitButton.setFocusPainted(false);
		exitButton.addActionListener(tsHandler);
		exitButton.setActionCommand("exit");
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
		mainTextPanel.setOpaque(true);;
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
		

		
		con.add(mainTextPanel);//,BorderLayout.CENTER);
		
		con.add(choiceButtonPanel);//,BorderLayout.SOUTH);	
		
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
		setFile(sw.MPath);
		play();
		loop(); 
		
		mainTextPanel.setVisible(false);
		choiceButtonPanel.setVisible(false);
		
		PicturePanel.setVisible(true);
		titleNamePanel.setVisible(false);
		startButtonPanel.setVisible(false);
		titleNamePanel.setVisible(true);
		startButtonPanel.setVisible(true);

	}
	public void createGameScreen() //update window to display main game screen
	{	
		
		titleNamePanel.setVisible(false);
		startButtonPanel.setVisible(false);
		
		choiceButtonPanel.setVisible(true);
		mainTextPanel.setVisible(true);

		scene = ".//resources//TextFiles//"+folderName+"//scene1";
		SceneSetup();
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
		pastMusic = sw.MPath;
		sw.SetSceneVar(scene);
		if(sw.MPath.equals("") || sw.MPath.equals(pastMusic)) {}
		else {				
			stop();
			setFile(sw.MPath);
			loop();
				
			}
		if(!sw.PPath.equals("")) {
		imagePath = sw.PPath;//set scene1a.jpg and folder to a variable
		image = new ImageIcon(imagePath);
		PictureLabel.setIcon(resize(image,window.getWidth(),window.getHeight()));
		PicturePanel.revalidate();
		PicturePanel.repaint();
		}
		choice1.setText(sw.B1Path);//change button text and functions to variables.
		choice2.setText(sw.B2Path);
		choice3.setText(sw.B3Path);
		choice4.setText(sw.B4Path);
		mainTextPanel.revalidate();
		mainTextPanel.repaint();
		choiceButtonPanel.setVisible(false);// Used in order to get button choices to pop back up after repaint of picture.
		choiceButtonPanel.setVisible(true);

		//set text equal to what the received input is
		text = sw.TPath;
		prepareText();

	}
	
	public int intRound(int x,double y) {
		int result;
		result = (int)Math.round(x*y);
		
	return result;
	}
	public void ButtonFunction(String Function) {
		try {
		if(Function.equals("changeTitle")) {
			BackToTitle();
		}
		else if(Function.equals("Exit")) {
			System.exit(0);
		}
		else if(Function.equals("")) {

		}// change buttonFunction V to SceneSetup
		else { 
			scene = ".//resources//TextFiles//"+folderName+"//"+Function;
			SceneSetup();
		
		}
		}catch(Exception e) {	
		}
		
	}
	public void changeTitle() {
		
		if(folderName.equals(game1))
		folderName = game2;
		else if(folderName.equals(game2))
		folderName = game1;
		
		
		
		scene=".//resources//TextFiles//"+folderName+"//TitleScreen";
		sw.SetSceneVar(scene);
	
		titleNameLabel.setText(folderName);
		
		imagePath = sw.PPath;//set scene1a.jpg and folder to a variable
		image = new ImageIcon(imagePath);
		PictureLabel.setIcon(resize(image,window.getWidth(),window.getHeight()));
		PicturePanel.revalidate();
		PicturePanel.repaint();	
		
		Title();

	}
	public void BackToTitle() {
			
		scene=".//resources//TextFiles//"+folderName+"//TitleScreen";
		sw.SetSceneVar(scene);
	
		titleNameLabel.setText(folderName);
		
		imagePath = sw.PPath;//set scene1a.jpg and folder to a variable
		image = new ImageIcon(imagePath);
		PictureLabel.setIcon(resize(image,window.getWidth(),window.getHeight()));
		PicturePanel.revalidate();
		PicturePanel.repaint();	
		
		Title();

	}


	
	public class TitleScreenHandler implements ActionListener
	{
		
		public void actionPerformed(ActionEvent event) 
		{
			
			
			String yourChoice = event.getActionCommand();
			
			switch(yourChoice){
			case"start":createGameScreen();break;
			case"load":changeTitle();break;
			case"exit":System.exit(0);break;
			}
			
		}
		
	}
	public class ChoiceHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event) 
		{
			
			String yourChoice = event.getActionCommand();
				
			switch(yourChoice){
			case"c1":ButtonFunction(sw.B1Function);break;
			case"c2":ButtonFunction(sw.B2Function);break;
			case"c3":ButtonFunction(sw.B3Function);break;
			case"c4":ButtonFunction(sw.B4Function);break;
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
		
	//Next few lines of code manipulate component Bounds and repaint the picture.
		titleNamePanel.setBounds(intRound(windowWidth,0.55555), intRound(windowHeight,0.06), intRound(windowWidth,0.4270), intRound(windowHeight,0.16));
		titleNamePanel.revalidate();
		PicturePanel.setBounds(0, -(intRound(windowHeight,0.04)), intRound(windowWidth,1), intRound(windowHeight,1));	
		PicturePanel.revalidate();
		
		image_w = windowWidth;
		image_h = windowHeight;
		
		//Commented out code here is old method of repaint that was ineffective.
		//Image im = image.getImage().getScaledInstance(image_w, image_h,Image.SCALE_DEFAULT);
		//image = new ImageIcon(im);
		//PictureLabel.setIcon(image);
		PictureLabel.setIcon(resize(image,image_w,image_h));
		PictureLabel.revalidate();
		PicturePanel.revalidate();

		//Bound setting for components below.
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

