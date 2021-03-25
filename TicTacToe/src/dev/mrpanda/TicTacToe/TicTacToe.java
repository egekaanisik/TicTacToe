package dev.mrpanda.TicTacToe;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class TicTacToe {
	public static JFrame f = new JFrame("TicTacToe // Created by MrPandaDev");
	public static Image frameIcon = Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/icon.png"));
	public static JPanel menu = new JPanel();
	public static void intro() {
		menu.removeAll();
		ImageIcon welcomeImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/welcome.png")));
		JLabel welcome = new JLabel(welcomeImg);
		welcome.setBounds(50,20,600,200);
		
		ImageIcon playImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/pbutton.png")));
		JButton play = new JButton(playImg);
		play.setBounds(50,240,600,100);
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
				    	singleMulti();
				    }
				});
			}
		});
		
		JLabel creator = new JLabel("Created by MrPandaDev");
		creator.setBounds(10,350,150,15);
		creator.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN,10));
		creator.setForeground(Color.GRAY);
		creator.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://mrpanda.dev"));
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		menu.add(creator);
		menu.add(welcome);
		menu.add(play);
		
		menu.revalidate();
		menu.repaint();
	}
	
	public static int timeLimit = 0;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void timeSet() {
		menu.removeAll();
		
		ImageIcon backImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/back.png")));
		JLabel back = new JLabel(backImg);
		back.setBounds(20,20,30,30);
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
				    	singleMulti();
				    }
				});
			}
		});
		
		ImageIcon limitImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/limit.png")));
		JLabel limit = new JLabel(limitImg);
		limit.setBounds(50,20,600,150);
		
		
		JSlider slide = new JSlider(0,60,timeLimit);
		slide.setBounds(25,160,650,70);
		slide.setBackground(Color.DARK_GRAY);
		slide.setForeground(Color.LIGHT_GRAY);
		slide.setMinorTickSpacing(10);
		slide.setMajorTickSpacing(10);
		slide.setPaintTicks(true);
		slide.setPaintLabels(true);
		slide.setSnapToTicks(true);
		
		JLabel zero = new JLabel("No Limit");
		zero.setBackground(Color.DARK_GRAY);
		zero.setForeground(Color.LIGHT_GRAY);
		zero.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,16));
		
		JLabel ten = new JLabel("10s");
		ten.setBackground(Color.DARK_GRAY);
		ten.setForeground(Color.LIGHT_GRAY);
		ten.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,16));
		
		JLabel twenty = new JLabel("20s");
		twenty.setBackground(Color.DARK_GRAY);
		twenty.setForeground(Color.LIGHT_GRAY);
		twenty.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,16));
		
		JLabel thirty = new JLabel("30s");
		thirty.setBackground(Color.DARK_GRAY);
		thirty.setForeground(Color.LIGHT_GRAY);
		thirty.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,16));
		
		JLabel forty = new JLabel("40s");
		forty.setBackground(Color.DARK_GRAY);
		forty.setForeground(Color.LIGHT_GRAY);
		forty.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,16));
		
		JLabel fifty = new JLabel("50s");
		fifty.setBackground(Color.DARK_GRAY);
		fifty.setForeground(Color.LIGHT_GRAY);
		fifty.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,16));
		
		JLabel sixty = new JLabel("60s");
		sixty.setBackground(Color.DARK_GRAY);
		sixty.setForeground(Color.LIGHT_GRAY);
		sixty.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,16));
		
		Hashtable labels = new Hashtable();
		labels.put(0, zero);
		labels.put(10, ten);
		labels.put(20, twenty);
		labels.put(30, thirty);
		labels.put(40, forty);
		labels.put(50, fifty);
		labels.put(60, sixty);
		slide.setLabelTable(labels);
		
		ImageIcon playImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/sbutton.png")));
		JButton play = new JButton(playImg);
		play.setBounds(50,260,600,80);
		play.setDisabledIcon(playImg);
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timeLimit = slide.getValue();
				
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
				    	nameX();
				    }
				});
			}
		});
		
		menu.add(back);
		menu.add(limit);
		menu.add(slide);
		menu.add(play);
		
		menu.revalidate();
		menu.repaint();
	}
	
	public static String nameX, nameO;
	public static boolean isAI;
	public static void singleMulti() {
		menu.removeAll();
		
		nameX = null;
		nameO = null;
		timeLimit = 0;
		
		ImageIcon backImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/back.png")));
		JLabel back = new JLabel(backImg);
		back.setBounds(20,20,30,30);
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
				    	intro();
				    }
				});
			}
		});
		
		ImageIcon countImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/playerCount.png")));
		JLabel count = new JLabel(countImg);
		count.setBounds(50,45,600,50);
		
		ImageIcon singleImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/single.png")));
		JButton single  = new JButton(singleImg);
		single.setBounds(50,130,280,200);
		single.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		    	isAI = true;
		    	nameO = "Computer";
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
				    	timeSet();
				    }
				});
			}
		});
		
		ImageIcon multiImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/multi.png")));
		JButton multi  = new JButton(multiImg);
		multi.setBounds(370,130,280,200);
		multi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isAI = false;
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
				    	timeSet();
				    }
				});
			}
		});
		
		menu.add(back);
		menu.add(count);
		menu.add(single);
		menu.add(multi);
		
		menu.revalidate();
		menu.repaint();
	}
	
	public static void nameX() {
		menu.removeAll();
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		
		ImageIcon backImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/back.png")));
		JLabel back = new JLabel(backImg);
		back.setBounds(20,20,30,30);
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
				    	timeSet();
				    }
				});
			}
		});
		
		ImageIcon playerImg;
		
		if(isAI == true)
			playerImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/nameSingle.png")));
		else
			playerImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/nameX.png")));
		
		JLabel player = new JLabel(playerImg);
		player.setBounds(50,20,600,150);
		
		JTextField name = new JTextField();
		name.setBounds(50,180,600,70);
		name.setBackground(Color.DARK_GRAY);
		name.setForeground(Color.LIGHT_GRAY);
		name.setHorizontalAlignment(JTextField.CENTER);
		name.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,36));
		name.setBorder(border);
		name.setDocument(new JTextFieldLimit(11));
		name.setText(nameX);
		
		ImageIcon playImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/sbutton.png")));
		JButton play = new JButton(playImg);
		play.setBounds(50,260,600,80);
		play.setDisabledIcon(playImg);
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = name.getText();
				
				if(input.length() == 0 || input.isBlank()) {
					name.setDocument(new JTextFieldLimit(23));
					name.setText("Please enter your name.");
					name.setEditable(false);
					play.setEnabled(false);
					
					ActionListener listener = new ActionListener(){
				        public void actionPerformed(ActionEvent event){
				            name.setText("");
				            name.setEditable(true);
				            play.setEnabled(true);
				            name.setDocument(new JTextFieldLimit(11));
				        }
				    };
				    
					Timer timer = new Timer(500, listener);
				    timer.setRepeats(false);
				    timer.start();
				    
				    return;
				}
				
				String[] nameRaw = input.split(" ");
				String[] nameArray = Arrays.stream(nameRaw)
		                .filter(value ->
		                        value != null && value.length() > 0
		                )
		                .toArray(size -> new String[size]);
				
				String nameStr = "";
				for(int i = 0; i < nameArray.length; i++) {
					if(i == nameArray.length - 1)
						nameStr += nameArray[i];
					else
						nameStr += nameArray[i] + " ";
				}
				
				if(isAI == true) {
					if (nameStr.equals(nameO)) {
						name.setDocument(new JTextFieldLimit(28));
						name.setText("Please use a different name.");
						name.setEditable(false);
						play.setEnabled(false);
						
						ActionListener listener = new ActionListener(){
					        public void actionPerformed(ActionEvent event){
					            name.setText("");
					            name.setEditable(true);
					            play.setEnabled(true);
					            name.setDocument(new JTextFieldLimit(11));
					        }
					    };
					    
						Timer timer = new Timer(500, listener);
					    timer.setRepeats(false);
					    timer.start();
					    
					    return;
					}
					
					nameX = nameStr;
				} else
					nameX = nameStr;
				
				
				if(isAI == true) {
					SwingUtilities.invokeLater(new Runnable() {
					    @Override
					    public void run() {
					    	game();
					    }
					});
				} else {
					SwingUtilities.invokeLater(new Runnable() {
					    @Override
					    public void run() {
					    	nameO();
					    }
					});
				}
			}
		});
		
		menu.add(back);
		menu.add(player);
		menu.add(name);
		menu.add(play);
		
		menu.revalidate();
		menu.repaint();
	}
	
	public static void nameO() {
		menu.removeAll();
		nameO = null;
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		
		ImageIcon playerImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/nameO.png")));
		JLabel player = new JLabel(playerImg);
		player.setBounds(50,20,600,150);
		
		ImageIcon backImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/back.png")));
		JLabel back = new JLabel(backImg);
		back.setBounds(20,20,30,30);
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
				    	nameX();
				    }
				});
			}
		});
		
		JTextField name = new JTextField();
		name.setBounds(50,180,600,70);
		name.setBackground(Color.DARK_GRAY);
		name.setForeground(Color.LIGHT_GRAY);
		name.setHorizontalAlignment(JTextField.CENTER);
		name.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,36));
		name.setBorder(border);
		name.setDocument(new JTextFieldLimit(11));
		name.setText(nameO);
		
		ImageIcon playImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/sbutton.png")));
		JButton play = new JButton(playImg);
		play.setBounds(50,260,600,80);
		play.setDisabledIcon(playImg);
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = name.getText();
				
				if(input.length() == 0 || input.isBlank()) {
					name.setDocument(new JTextFieldLimit(23));
					name.setText("Please enter your name.");
					name.setEditable(false);
					play.setEnabled(false);
					
					ActionListener listener = new ActionListener(){
				        public void actionPerformed(ActionEvent event){
				            name.setText("");
				            name.setEditable(true);
				            play.setEnabled(true);
				            name.setDocument(new JTextFieldLimit(11));
				        }
				    };
				    
					Timer timer = new Timer(500, listener);
				    timer.setRepeats(false);
				    timer.start();
				    
				    return;
				}
				
				String[] nameRaw = input.split(" ");
				String[] nameArray = Arrays.stream(nameRaw)
		                .filter(value ->
		                        value != null && value.length() > 0
		                )
		                .toArray(size -> new String[size]);
				
				String nameStr = "";
				for(int i = 0; i < nameArray.length; i++) {
					if(i == nameArray.length - 1)
						nameStr += nameArray[i];
					else
						nameStr += nameArray[i] + " ";
				}
				
				if (nameStr.equals(nameX)) {
					name.setDocument(new JTextFieldLimit(28));
					name.setText("Please use a different name.");
					name.setEditable(false);
					play.setEnabled(false);
					
					ActionListener listener = new ActionListener(){
				        public void actionPerformed(ActionEvent event){
				            name.setText("");
				            name.setEditable(true);
				            play.setEnabled(true);
				            name.setDocument(new JTextFieldLimit(11));
				        }
				    };
				    
					Timer timer = new Timer(500, listener);
				    timer.setRepeats(false);
				    timer.start();
				    
				    return;
				}
				
				nameO = nameStr;
				game();
			}
		});
		
		menu.add(back);
		menu.add(player);
		menu.add(name);
		menu.add(play);
		
		menu.revalidate();
		menu.repaint();
	}
	
	public static int[] listeners = {1,2,3,4,5,6,7,8,9}, listenersC = listeners.clone();
	public static char[][] canv = new char[3][3], canvC = Arrays.stream(canv).map(char[]::clone).toArray(char[][]::new);
	public static ImageIcon x = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/x.png"))),
							o = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/o.png"))),
							alphaX = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/alphaX.png"))),
							alphaO = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/alphaO.png"))),
							scores = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/scores.png"))),
							canvImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/canvas.png"))),
							turnImgX = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/turnX.png"))),
							turnImgO = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/turnO.png"))),
							wait = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/wait.png"))),
							turnImgP = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/turnP.png")));
	public static int pointX = 0, pointO = 0, notEmp = 0, timeL = timeLimit;
	public static JLabel one = new JLabel(), two = new JLabel(), three = new JLabel(), four = new JLabel(), five = new JLabel(), six = new JLabel(), seven = new JLabel(),
						 eight = new JLabel(),  nine = new JLabel(), canvas = new JLabel(canvImg), scoreboard = new JLabel(scores), turn = new JLabel(turnImgX),
						 ptX = new JLabel(String.valueOf(pointX),SwingConstants.CENTER), ptO = new JLabel(String.valueOf(pointO),SwingConstants.CENTER),
						 timeInfo = new JLabel("Time Left:", SwingConstants.CENTER), playerX = new JLabel(nameX,SwingConstants.CENTER),playerO = new JLabel(nameO,SwingConstants.CENTER);
	public static final MouseAdapter oneX = hover('x',getCell(1),1), oneO = hover('o',getCell(1),1), twoX = hover('x',getCell(2),2), twoO = hover('o',getCell(2),2), threeX = hover('x',getCell(3),3),
							   threeO = hover('o',getCell(3),3), fourX = hover('x',getCell(4),4), fourO = hover('o',getCell(4),4), fiveX = hover('x',getCell(5),5), fiveO = hover('o',getCell(5),5),
							   sixX = hover('x',getCell(6),6), sixO = hover('o',getCell(6),6), sevenX = hover('x',getCell(7),7), sevenO = hover('o',getCell(7),7), eightX = hover('x',getCell(8),8),
							   eightO = hover('o',getCell(8),8), nineX = hover('x',getCell(9),9), nineO = hover('o',getCell(9),9);
	public static final MouseAdapter mlisten[] = {oneX,twoX,threeX,fourX,fiveX,sixX,sevenX,eightX,nineX,oneO,twoO,threeO,fourO,fiveO,sixO,sevenO,eightO,nineO};
	public static final MouseAdapter oneCX = click('x',getCell(1),1), oneCO = click('o',getCell(1),1), twoCX = click('x',getCell(2),2), twoCO = click('o',getCell(2),2),
			   threeCX = click('x',getCell(3),3), threeCO = click('o',getCell(3),3), fourCX = click('x',getCell(4),4), fourCO = click('o',getCell(4),4),
			   fiveCX = click('x',getCell(5),5), fiveCO = click('o',getCell(5),5), sixCX = click('x',getCell(6),6), sixCO = click('o',getCell(6),6),
			   sevenCX = click('x',getCell(7),7), sevenCO = click('o',getCell(7),7), eightCX = click('x',getCell(8),8), eightCO = click('o',getCell(8),8),
			   nineCX = click('x',getCell(9),9), nineCO = click('o',getCell(9),9);
	public static final MouseAdapter mclisten[] = {oneCX,twoCX,threeCX,fourCX,fiveCX,sixCX,sevenCX,eightCX,nineCX,oneCO,twoCO,threeCO,fourCO,fiveCO,sixCO,sevenCO,eightCO,nineCO};
	public static JLayeredPane gamePanel = new JLayeredPane();
	public static Timer timeX, timeO, timeEx, countdown;
	public static JProgressBar progress;
	
	public static void timerInit() {
		timeX = timer('x');
		timeO = timer('o');
		timeEx = timer('e');
		countdown = countdown();
		progress = new JProgressBar(0, timeLimit);
	}
	
	public static void game() {
		if(!(notEmp > 0)) {
			gamePanel.setLayout(null);
			gamePanel.setOpaque(true);
			gamePanel.setBackground(Color.DARK_GRAY);
			
			timerInit();
			
			f.pack();
			f.setIconImage(frameIcon);
			f.setSize(1285, 910);
			f.getContentPane().remove(menu);
			f.getContentPane().add(gamePanel);
			f.revalidate();
			f.repaint();
			f.setVisible(true);
			f.setResizable(false);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		notEmp = 0;
		listeners = listenersC.clone();
		canv = Arrays.stream(canvC).map(char[]::clone).toArray(char[][]::new);
		
		if(timeLimit != 0) {
			timeInfo.setVisible(true);
			progress.setVisible(true);
		}
		
		for(int i = 1; i < 10; i++) {
			getCell(i).setIcon(null);
		}
		
		SwingUtilities.invokeLater(new Runnable() {
		    @Override
		    public void run() {
		    	turn('x');
		    }
		});
	}
	
	public static void turn(char symbol){
		gamePanel.removeAll();
		if (timeLimit != 0) {
			countdown.stop();
			timeL = timeLimit;
			progress.setString(String.valueOf(getTimestamp(timeL)));
			progress.setValue(timeL);
		
			if(symbol == 'x')
				timeO.stop();
			else
				timeX.stop();
			
			if(isAI == true && symbol == 'o') {
				progress.setVisible(false);
				timeInfo.setVisible(false);
			} else {
				progress.setVisible(true);
				timeInfo.setVisible(true);
			}
		}
		
		canvas.setBounds(50,50,770,770);
		
		ptX.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		ptX.setForeground(Color.LIGHT_GRAY);
		ptX.setBounds(1160,722,50,46);
		
		ptO.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		ptO.setForeground(Color.LIGHT_GRAY);
		ptO.setBounds(1160,774,50,46);
		
		if(symbol == 'x') {
			if(isAI == true)
				turn.setIcon(turnImgP);
			else
				turn.setIcon(turnImgX);
		} else {
			if(isAI == true)
				turn.setIcon(wait);
			else
				turn.setIcon(turnImgO);
		}
		
		turn.setBounds(920,50,300,770);
		
		scoreboard.setIcon(scores);
		scoreboard.setBounds(930,682,280,138);
	    
		playerX.setText(nameX);
		playerX.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		playerX.setForeground(Color.LIGHT_GRAY);
		playerX.setBounds(974,722,180,46);
		
		playerO.setText(nameO);
		playerO.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		playerO.setForeground(Color.LIGHT_GRAY);
		playerO.setBounds(974,774,180,46);
		
		one.setBounds(70,70,200,200);
		two.setBounds(335,70,200,200);
		three.setBounds(600,70,200,200);
		four.setBounds(70,335,200,200);
		five.setBounds(335,335,200,200);
		six.setBounds(600,335,200,200);
		seven.setBounds(70,600,200,200);
		eight.setBounds(335,600,200,200);
		nine.setBounds(600,600,200,200);
		
		if(timeLimit != 0) {
			if(symbol == 'x') {
				timeX.start();
				countdown.start();
			} else if(isAI == false) {
				timeO.start();
				countdown.start();
			}
			
			timeInfo.setBounds(920,50,300,50);
			timeInfo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 36));
			timeInfo.setForeground(Color.LIGHT_GRAY);
			
			progress.setBounds(920,110,300,50);
			progress.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 36));
			progress.setBackground(new Color(39,39,39));
			progress.setForeground(Color.LIGHT_GRAY);
			progress.setStringPainted(true);
			progress.setUI(new BasicProgressBarUI() {
				protected Color getSelectionBackground() { return Color.LIGHT_GRAY; }
				protected Color getSelectionForeground() { return Color.DARK_GRAY; }
			});
		}
		
		if(symbol == 'x') {
			if(canv[0][0] == canv[0][1] && canv[0][1] == canv[0][2] && canv[0][2] == 'o') {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
						win(1,'o');
				    }
				});
				return;
			} else if(canv[1][0] == canv[1][1] && canv[1][1] == canv[1][2] && canv[1][2] == 'o') {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
				    	win(2,'o');
				    }
				});
				return;
			} else if(canv[2][0] == canv[2][1] && canv[2][1] == canv[2][2] && canv[2][2] == 'o') {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
						win(3,'o');
				    }
				});
				return;
			} else if(canv[0][0] == canv[1][0] && canv[1][0] == canv[2][0] && canv[2][0] == 'o') {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
						win(4,'o');
				    }
				});
				return;
			} else if(canv[0][1] == canv[1][1] && canv[1][1] == canv[2][1] && canv[2][1] == 'o') {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
						win(5,'o');
				    }
				});
				return;
			} else if(canv[0][2] == canv[1][2] && canv[1][2] == canv[2][2] && canv[2][2] == 'o') {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
						win(6,'o');
				    }
				});
				return;
			} else if(canv[0][0] == canv[1][1] && canv[1][1] == canv[2][2] && canv[2][2] == 'o') {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
						win(7,'o');
				    }
				});
				return;
			} else if(canv[0][2] == canv[1][1] && canv[1][1] == canv[2][0] && canv[2][0] == 'o') {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
						win(8,'o');
				    }
				});
				return;
			} else if(notEmp == 9) {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
						win(9,' ');
				    }
				});
				return;
			}
		} else {
			if(canv[0][0] == canv[0][1] && canv[0][1] == canv[0][2] && canv[0][2] == 'x') {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
						win(1,'x');
				    }
				});
				return;
			} else if(canv[1][0] == canv[1][1] && canv[1][1] == canv[1][2] && canv[1][2] == 'x') {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
						win(2,'x');
				    }
				});
				return;
			} else if(canv[2][0] == canv[2][1] && canv[2][1] == canv[2][2] && canv[2][2] == 'x') {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
						win(3,'x');
				    }
				});
				return;
			} else if(canv[0][0] == canv[1][0] && canv[1][0] == canv[2][0] && canv[2][0] == 'x') {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
						win(4,'x');
				    }
				});
				return;
			} else if(canv[0][1] == canv[1][1] && canv[1][1] == canv[2][1] && canv[2][1] == 'x') {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
						win(5,'x');
				    }
				});
				return;
			} else if(canv[0][2] == canv[1][2] && canv[1][2] == canv[2][2] && canv[2][2] == 'x') {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
						win(6,'x');
				    }
				});
				return;
			} else if(canv[0][0] == canv[1][1] && canv[1][1] == canv[2][2] && canv[2][2] == 'x') {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
						win(7,'x');
				    }
				});
				return;
			} else if(canv[0][2] == canv[1][1] && canv[1][1] == canv[2][0] && canv[2][0] == 'x') {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
						win(8,'x');
				    }
				});
				return;
			} else if(notEmp == 9) {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
						win(9,' ');
				    }
				});
				return;
			}
		}
		
		if(symbol == 'o' && isAI == true) {
			for(int i = 1; i < 10; i++) {
				getCell(i).removeMouseListener(mlisten[i-1]);
				getCell(i).removeMouseListener(mlisten[i+8]);
				getCell(i).removeMouseListener(mclisten[i-1]);
				getCell(i).removeMouseListener(mclisten[i+8]);
			}
			
			AI();
		} else {
			for(int i = 1; i < 10; i++) {
				getCell(i).removeMouseListener(mlisten[i-1]);
				getCell(i).removeMouseListener(mlisten[i+8]);
				getCell(i).removeMouseListener(mclisten[i-1]);
				getCell(i).removeMouseListener(mclisten[i+8]);
			
				if(listeners[i-1] != -1 && listeners[i-1] != -2) {
					if(symbol == 'x') {
						getCell(i).addMouseListener(mlisten[i-1]);
						getCell(i).addMouseListener(mclisten[i-1]);
					} else {
						getCell(i).addMouseListener(mlisten[i+8]);
						getCell(i).addMouseListener(mclisten[i+8]);
					}
				}
			}
		}
		
		gamePanel.add(progress,2,0);
		gamePanel.add(timeInfo,2,0);
		gamePanel.add(canvas,1,0);
		gamePanel.add(playerX,1,0);
		gamePanel.add(playerO,1,0);
		gamePanel.add(ptX,1,0);
		gamePanel.add(ptO,1,0);
		gamePanel.add(turn,1,0);
		gamePanel.add(scoreboard,1,0);
		gamePanel.add(one,1,0);
		gamePanel.add(two,1,0);
		gamePanel.add(three,1,0);
		gamePanel.add(four,1,0);
		gamePanel.add(five,1,0);
		gamePanel.add(six,1,0);
		gamePanel.add(seven,1,0);
		gamePanel.add(eight,1,0);
		gamePanel.add(nine,1,0);
		
		gamePanel.revalidate();
		gamePanel.repaint();
	}
	
	public static JLabel getCell(int i) {
		switch(i) {
		case 1 : return one;
		case 2 : return two;
		case 3 : return three;
		case 4 : return four; 
		case 5 : return five;
		case 6 : return six;
		case 7 : return seven;
		case 8 : return eight;
		case 9 : return nine;
		default : return null;
		}
	}
	
	public static MouseAdapter click(char turn, JLabel cell,int cellNum) {
		MouseAdapter click = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				canv[numToRow(cellNum)][numToColumn(cellNum)] = turn;

				if(turn == 'x') {
					listeners[cellNum - 1] = -1;
					getCell(cellNum).setIcon(x);
					notEmp++;
					SwingUtilities.invokeLater(new Runnable() {
					    @Override
					    public void run() {
					    	turn('o');
					    }
					});
				} else {
					listeners[cellNum - 1] = -2;
					getCell(cellNum).setIcon(o);
					notEmp++;
					SwingUtilities.invokeLater(new Runnable() {
					    @Override
					    public void run() {
					    	turn('x');
					    }
					});
				}
			}
		
		};
		
		return click;
	}
	
	public static MouseAdapter hover(char turn, JLabel cell, int cellNum) {
		MouseAdapter mouse = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(turn == 'x')
					cell.setIcon(alphaX);
				else
					cell.setIcon(alphaO);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cell.setIcon(null);
			}
		};
		
		return mouse;
	}
	
	public static void AI() {
		if(canv[0][0] == 'o' && canv[0][1] == 'o' && canv[0][2] != 'x') {
			putOForAI(3);
		} else if(canv[0][1] == 'o' && canv[0][2] == 'o' && canv[0][0] != 'x') {
			putOForAI(1);
		} else if(canv[0][0] == 'o' && canv[0][2] == 'o' && canv[0][1] != 'x') {
			putOForAI(2);
		} else if(canv[1][0] == 'o' && canv[1][1] == 'o' && canv[1][2] != 'x') {
			putOForAI(6);
		} else if(canv[1][1] == 'o' && canv[1][2] == 'o' && canv[1][0] != 'x') {
			putOForAI(4);
		} else if(canv[1][0] == 'o' && canv[1][2] == 'o' && canv[1][1] != 'x') {
			putOForAI(5);
		} else if(canv[2][0] == 'o' && canv[2][1] == 'o' && canv[2][2] != 'x') {
			putOForAI(9);
		} else if(canv[2][1] == 'o' && canv[2][2] == 'o' && canv[2][0] != 'x') {
			putOForAI(7);
		} else if(canv[2][0] == 'o' && canv[2][2] == 'o' && canv[2][1] != 'x') {
			putOForAI(8);
		} else if(canv[0][0] == 'o' && canv[1][0] == 'o' && canv[2][0] != 'x') {
			putOForAI(7);
		} else if(canv[1][0] == 'o' && canv[2][0] == 'o' && canv[0][0] != 'x') {
			putOForAI(1);
		} else if(canv[0][0] == 'o' && canv[2][0] == 'o' && canv[1][0] != 'x') {
			putOForAI(4);
		} else if(canv[0][1] == 'o' && canv[1][1] == 'o' && canv[2][1] != 'x') {
			putOForAI(8);
		} else if(canv[1][1] == 'o' && canv[2][1] == 'o' && canv[0][1] != 'x') {
			putOForAI(2);
		} else if(canv[0][1] == 'o' && canv[2][1] == 'o' && canv[1][1] != 'x') {
			putOForAI(5);
		} else if(canv[0][2] == 'o' && canv[1][2] == 'o' && canv[2][2] != 'x') {
			putOForAI(9);
		} else if(canv[1][2] == 'o' && canv[2][2] == 'o' && canv[0][2] != 'x') {
			putOForAI(3);
		} else if(canv[0][2] == 'o' && canv[2][2] == 'o' && canv[1][2] != 'x') {
			putOForAI(6);
		} else if(canv[0][0] == 'o' && canv[1][1] == 'o' && canv[2][2] != 'x') {
			putOForAI(9);
		} else if(canv[1][1] == 'o' && canv[2][2] == 'o' && canv[0][0] != 'x') {
			putOForAI(1);
		} else if(canv[0][0] == 'o' && canv[2][2] == 'o' && canv[1][1] != 'x') {
			putOForAI(5);
		} else if(canv[0][2] == 'o' && canv[1][1] == 'o' && canv[2][0] != 'x') {
			putOForAI(7);
		} else if(canv[1][1] == 'o' && canv[2][0] == 'o' && canv[0][2] != 'x') {
			putOForAI(3);
		} else if(canv[0][2] == 'o' && canv[2][0] == 'o' && canv[1][1] != 'x') {
			putOForAI(5);
		} else if(canv[0][0] == 'x' && canv[0][1] == 'x' && canv[0][2] != 'o') {
			putOForAI(3);
		} else if(canv[0][1] == 'x' && canv[0][2] == 'x' && canv[0][0] != 'o') {
			putOForAI(1);
		} else if(canv[0][0] == 'x' && canv[0][2] == 'x' && canv[0][1] != 'o') {
			putOForAI(2);
		} else if(canv[1][0] == 'x' && canv[1][1] == 'x' && canv[1][2] != 'o') {
			putOForAI(6);
		} else if(canv[1][1] == 'x' && canv[1][2] == 'x' && canv[1][0] != 'o') {
			putOForAI(4);
		} else if(canv[1][0] == 'x' && canv[1][2] == 'x' && canv[1][1] != 'o') {
			putOForAI(5);
		} else if(canv[2][0] == 'x' && canv[2][1] == 'x' && canv[2][2] != 'o') {
			putOForAI(9);
		} else if(canv[2][1] == 'x' && canv[2][2] == 'x' && canv[2][0] != 'o') {
			putOForAI(7);
		} else if(canv[2][0] == 'x' && canv[2][2] == 'x' && canv[2][1] != 'o') {
			putOForAI(8);
		} else if(canv[0][0] == 'x' && canv[1][0] == 'x' && canv[2][0] != 'o') {
			putOForAI(7);
		} else if(canv[1][0] == 'x' && canv[2][0] == 'x' && canv[0][0] != 'o') {
			putOForAI(1);
		} else if(canv[0][0] == 'x' && canv[2][0] == 'x' && canv[1][0] != 'o') {
			putOForAI(4);
		} else if(canv[0][1] == 'x' && canv[1][1] == 'x' && canv[2][1] != 'o') {
			putOForAI(8);
		} else if(canv[1][1] == 'x' && canv[2][1] == 'x' && canv[0][1] != 'o') {
			putOForAI(2);
		} else if(canv[0][1] == 'x' && canv[2][1] == 'x' && canv[1][1] != 'o') {
			putOForAI(5);
		} else if(canv[0][2] == 'x' && canv[1][2] == 'x' && canv[2][2] != 'o') {
			putOForAI(9);
		} else if(canv[1][2] == 'x' && canv[2][2] == 'x' && canv[0][2] != 'o') {
			putOForAI(3);
		} else if(canv[0][2] == 'x' && canv[2][2] == 'x' && canv[1][2] != 'o') {
			putOForAI(6);
		} else if(canv[0][0] == 'x' && canv[1][1] == 'x' && canv[2][2] != 'o') {
			putOForAI(9);
		} else if(canv[1][1] == 'x' && canv[2][2] == 'x' && canv[0][0] != 'o') {
			putOForAI(1);
		} else if(canv[0][0] == 'x' && canv[2][2] == 'x' && canv[1][1] != 'o') {
			putOForAI(5);
		} else if(canv[0][2] == 'x' && canv[1][1] == 'x' && canv[2][0] != 'o') {
			putOForAI(7);
		} else if(canv[1][1] == 'x' && canv[2][0] == 'x' && canv[0][2] != 'o') {
			putOForAI(3);
		} else if(canv[0][2] == 'x' && canv[2][0] == 'x' && canv[1][1] != 'o') {
			putOForAI(5);
		} else if(canv[1][1] != 'o' && canv[1][1] != 'x') {
			putOForAI(5);
		} else if(canv[0][1] == 'x' && canv[1][0] == 'x' && (canv[0][0] != 'x' && canv[0][0] != 'o')) {
			putOForAI(1);
		} else if(canv[0][1] == 'x' && canv[1][2] == 'x' && (canv[0][2] != 'x' && canv[0][2] != 'o')) {
			putOForAI(3);
		} else if(canv[1][0] == 'x' && canv[2][1] == 'x' && (canv[2][0] != 'x' && canv[2][0] != 'o')) {
			putOForAI(7);
		} else if(canv[1][2] == 'x' && canv[2][1] == 'x' && (canv[2][2] != 'x' && canv[2][2] != 'o')) {
			putOForAI(9);
		} else if(canv[1][1] == 'x' && ((canv[0][0] != 'x' && canv[0][0] != 'o')
				|| (canv[0][2] != 'x' && canv[0][2] != 'o') || (canv[2][0] != 'x' && canv[2][0] != 'o')
				|| (canv[2][2] != 'x' && canv[2][2] != 'o'))) {
			List<Integer> empties = new ArrayList<Integer>();
			for(int i = 0; i < 4; i++) {
				if(i == 0) {
					if(canv[0][0] != 'x' && canv[0][0] != 'o') 
						empties.add(1);
				} else if(i == 1) {
					if(canv[0][2] != 'x' && canv[0][2] != 'o')
						empties.add(3);
				} else if(i == 2) {
					if(canv[2][0] != 'x' && canv[2][0] != 'o')
						empties.add(7);
				} else if(i == 3) {
					if(canv[2][2] != 'x' && canv[2][2] != 'o')
						empties.add(9);
				}
			}
			
			putOForAI(empties.get(((int)(Math.random() * empties.size()))));
		} else if(canv[1][1] == 'o' && ((canv[0][1] != 'x' && canv[0][1] != 'o')
				|| (canv[1][0] != 'x' && canv[1][0] != 'o') || (canv[2][1] != 'x' && canv[2][1] != 'o')
				|| (canv[1][2] != 'x' && canv[1][2] != 'o'))) {
			
			List<Integer> empties = new ArrayList<Integer>();
			for(int i = 0; i < 4; i++) {
				if(i == 0) {
					if(canv[0][1] != 'x' && canv[0][1] != 'o') 
						empties.add(2);
				} else if(i == 1) {
					if(canv[1][0] != 'x' && canv[1][0] != 'o')
						empties.add(4);
				} else if(i == 2) {
					if(canv[1][2] != 'x' && canv[1][2] != 'o')
						empties.add(6);
				} else if(i == 3) {
					if(canv[2][1] != 'x' && canv[2][1] != 'o')
						empties.add(8);
				}
			}
			
			putOForAI(empties.get(((int)(Math.random() * empties.size()))));
		} else if(notEmp != 9){
			int[] empties = new int[9 - notEmp];
			int in = 0;
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					if(canv[i][j] != 'x' && canv[i][j] != 'o') {
						empties[in] = getCellNum(i,j);
						in++;
					}
				}
			}
			
			int index = (int)(Math.random() * empties.length);
			putOForAI(empties[index]);
		}
	}
	
	public static void putOForAI(int cell) {
		listeners[cell - 1] = -2;
		notEmp++;
		canv[numToRow(cell)][numToColumn(cell)] = 'o';
		
		int timeForAlpha = (int)(Math.random() * 2001) + 1000;
		int timeToPut = timeForAlpha + (int)(Math.random() * 501) + 400;
		
		ActionListener alpha = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				getCell(cell).setIcon(alphaO);
			}
		};
			
	    Timer t = new Timer(timeForAlpha, alpha);
	    t.setRepeats(false);
	    t.start();
	    
	    ActionListener putO = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				getCell(cell).setIcon(o);
				
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
				    	turn('x');
				    }
				});
			}
		};
			
	    Timer t1 = new Timer(timeToPut, putO);
	    t1.setRepeats(false);
	    t1.start();
	}
	
	public static void win(int type, char winner) {
		gamePanel.removeAll();
		
		ImageIcon playAgain = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/playAgain.png")));
		ImageIcon yesButton = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/yesbutton.png")));
		ImageIcon noButton = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/nobutton.png")));
		
		if(winner == 'x') {
			if(isAI == true)
				turn.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/winP.png"))));
			else
				turn.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/winX.png"))));
			pointX++;
			if(timeLimit != 0)
				timeO.stop();
		} else if (winner == 'o') {
			if(isAI == true)
				turn.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/winComp.png"))));
			else
				turn.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/winO.png"))));
			pointO++;
			if(timeLimit != 0)
				timeX.stop();
		} else {
			turn.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/tie.png"))));
			if(timeLimit != 0) {
				timeO.stop();
				timeX.stop();
			}
		}
		
		if(timeLimit != 0) {
			countdown.stop();
			timeInfo.setVisible(false);
			progress.setVisible(false);
		}
		
		ImageIcon winImg;
		
		switch(type) {
		case 1: winImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/winPos1.png"))); break;
		case 2: winImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/winPos2.png"))); break;
		case 3: winImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/winPos3.png"))); break;
		case 4: winImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/winPos4.png"))); break;
		case 5: winImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/winPos5.png"))); break;
		case 6: winImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/winPos6.png"))); break;
		case 7: winImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/winPos7.png"))); break;
		case 8: winImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TicTacToe.class.getResource("resources/winPos8.png"))); break;
		default: winImg = null;
		}
		
		JLabel win = new JLabel(winImg);
		win.setBounds(50,50,770,770);
		
		for(int i = 1; i < 10; i++) {
			getCell(i).removeMouseListener(mlisten[i-1]);
			getCell(i).removeMouseListener(mlisten[i+8]);
			getCell(i).removeMouseListener(mclisten[i-1]);
			getCell(i).removeMouseListener(mclisten[i+8]);
		}
    	
    	JButton no = new JButton(noButton);
    	no.setBounds(1090,476,130,50);
    	no.setVisible(false);
    	no.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			System.exit(0);
    		}
    	});
    	
    	JButton yes = new JButton(yesButton);
    	yes.setBounds(920,476,130,50);
    	yes.setVisible(false);
    	yes.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			win.setIcon(null);
    			yes.setVisible(false);
    			no.setVisible(false);
    			
    			if(timeLimit != 0)
    				timeEx.stop();
    			
    			game();
    		}
    	});
		
		JLabel plusOne = new JLabel("+1",SwingConstants.CENTER);
		plusOne.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		plusOne.setForeground(Color.GREEN);
		
		if(winner == 'x')
			plusOne.setBounds(1190,722,50,46);
		else if(winner == 'o')
			plusOne.setBounds(1190,774,50,46);
		
		ActionListener hide = new ActionListener(){
	        public void actionPerformed(ActionEvent event){
	            win.setVisible(false);
	        }
	    };
	    
	    ActionListener show = new ActionListener(){
	        public void actionPerformed(ActionEvent event){
	            win.setVisible(true);
	        }
	    };
	    
	    Timer timer = new Timer(1000, hide);
	    timer.setRepeats(false);
	    timer.start();
	    
	    Timer timer2 = new Timer(2000, show);
	    timer2.setRepeats(false);
	    timer2.start();
	    
	    Timer timer3 = new Timer(3000, hide);
	    timer3.setRepeats(false);
	    timer3.start();
	    
	    Timer timer4 = new Timer(4000, show);
	    timer4.setRepeats(false);
	    timer4.start();
	    
		ActionListener pt = new ActionListener(){
	        public void actionPerformed(ActionEvent event){
	        	plusOne.setVisible(false);
	        	
	        	if(timeLimit != 0) {
	        		timeInfo.setVisible(true);
	        		progress.setVisible(true);
	        		countdown.start();
	        		timeEx.start();
	        	}
	        	
	        	if(winner == 'x')
	        		ptX.setText(String.valueOf(pointX));
	        	else if(winner == 'o')
	        		ptO.setText(String.valueOf(pointO));
	        	
	        	turn.setIcon(playAgain);
	        	yes.setVisible(true);
	        	no.setVisible(true);
	        }
	    };
	    
	    Timer ptT = new Timer(4000, pt);
	    ptT.setRepeats(false);
	    ptT.start();
	    
	    gamePanel.add(progress,2,0);
		gamePanel.add(timeInfo,2,0);
		gamePanel.add(canvas,1,0);
		gamePanel.add(playerX,1,0);
		gamePanel.add(playerO,1,0);
		gamePanel.add(ptX,1,0);
		gamePanel.add(ptO,1,0);
		gamePanel.add(turn,1,0);
		gamePanel.add(scoreboard,1,0);
		gamePanel.add(one,1,0);
		gamePanel.add(two,1,0);
		gamePanel.add(three,1,0);
		gamePanel.add(four,1,0);
		gamePanel.add(five,1,0);
		gamePanel.add(six,1,0);
		gamePanel.add(seven,1,0);
		gamePanel.add(eight,1,0);
		gamePanel.add(nine,1,0);
		gamePanel.add(win,2,0);
		gamePanel.add(plusOne,2,0);
		gamePanel.add(yes,2,0);
		gamePanel.add(no,2,0);
		
		gamePanel.revalidate();
		gamePanel.repaint();
	}
	
	public static Timer timer(char turn) {
		ActionListener turnSkip = new ActionListener(){
	        public void actionPerformed(ActionEvent event){
	            if(turn == 'x') {
	            	turn('o');
	            } else if(turn == 'o'){
	            	turn('x');
	            } else
	            	System.exit(0);
	        }
	    };
	    
        Timer t = new Timer(timeLimit * 1000, turnSkip);
	    t.setRepeats(false);
	    
	    return t;
	}
	
	public static Timer countdown() {
		ActionListener timeLeft = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				timeL -= 1;
				progress.setValue(timeL);
				progress.setString(String.valueOf(getTimestamp(timeL)));
			}
		};
			
	    Timer t = new Timer(1000, timeLeft);
	    t.setRepeats(true);
	    
	    return t;
	}
	
	public static int numToRow(int cell) {
		int row = 0;
		
		switch (cell) {
		case 1: row = 0; break;
		case 2: row = 0; break;
		case 3: row = 0; break;
		case 4: row = 1; break;
		case 5: row = 1; break;
		case 6: row = 1; break;
		case 7: row = 2; break;
		case 8: row = 2; break;
		case 9: row = 2; break;
		}
		
		return row;
	}
	
	public static int numToColumn(int cell) {
		int column = 0;
		
		switch (cell) {
		case 1: column = 0; break;
		case 2: column = 1; break;
		case 3: column = 2; break; 
		case 4: column = 0; break;
		case 5: column = 1; break;
		case 6: column = 2; break;
		case 7: column = 0; break;
		case 8: column = 1; break;
		case 9: column = 2; break;
		}
		
		return column;
	}
	
	public static int getCellNum(int i, int j) {
		if(i == 0 && j == 0)
			return 1;
		else if(i == 0 && j == 1)
			return 2;
		else if(i == 0 && j == 2)
			return 3;
		else if(i == 1 && j == 0)
			return 4;
		else if(i == 1 && j == 1)
			return 5;
		else if(i == 1 && j == 2)
			return 6;
		else if(i == 2 && j == 0)
			return 7;
		else if(i == 2 && j == 1)
			return 8;
		else if(i == 2 && j == 2)
			return 9;
		else
			return -1;
	}
	
	public static String getTimestamp(long seconds) {
		int minutes = (int) (seconds / 60);
		seconds = (int) (seconds % 60);

		return String.format("%02d:%02d", minutes, seconds);
	}
	
	public static void main(String[] args) {
		menu.setLayout(null);
		menu.setBackground(Color.DARK_GRAY);
		
		SwingUtilities.invokeLater(new Runnable() {
		    @Override
		    public void run() {
		    	intro();
		    }
		});
		
		f.pack();
		f.setIconImage(frameIcon);
		f.setSize(715, 410);
		f.getContentPane().add(menu);
		f.setVisible(true);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
