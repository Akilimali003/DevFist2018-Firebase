package cd.gamedesigner.vijey;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.dnd.MouseDragGestureRecognizer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class MediaPlayer {

	private static JFileChooser fileChooser = new JFileChooser();
	//This is the path for the libvlc.dll
	public static void main(String[] args) {
		 NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:/vlc-2.2.1");             
	     Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);             
	     LibXUtil.initialise();
	     
	     final JFrame frame1 = new JFrame();
	     JPanel panel = new JPanel();
	     JLabel label;
	     
	     //configuration de la fenetre
	     frame1.setLocation(340, 230);
		 frame1.setSize(600, 350);
		 frame1.setResizable(false);
		 frame1.setTitle("VGD Media Player.");
		 frame1.add(panel, BorderLayout.CENTER);
		 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 //Confiuration du panel
		 panel.setSize(frame1.getSize());
		 panel.setBackground(Color.decode("0x000000"));
		 //Instanciation du label
		 label = new JLabel(new ImageIcon("src/image/large.jpg"));
		 //Ajouter le label au panel
	     panel.add(label, BorderLayout.CENTER);
		 frame1.setVisible(true);
			
			JMenuBar jMenuBar = new JMenuBar();
			JMenu jMenu = new JMenu("Fichier");
			JMenu jMenuAbout = new JMenu("A Propos");
			
			JMenuItem ouvrir = new JMenuItem("Ouvrir");
			JMenuItem quitter = new JMenuItem("Quitter");
			JMenuItem apropos = new JMenuItem("A propos");
			jMenu.add(ouvrir);
			jMenu.add(quitter);
			jMenuAbout.add(apropos);
			jMenuBar.add(jMenu);
			jMenuBar.add(jMenuAbout);
			
			frame1.add(jMenuBar, BorderLayout.NORTH);
			
			ouvrir.addActionListener(new ActionListener() {
				
				@SuppressWarnings("deprecation")
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// Create a new instance of MediaPlayer which calls the second frame
					//and hide the main Frame
					 MediaPlayer vlc = new MediaPlayer();
					 vlc.getClass();
					 frame1.hide();
				}
			});
			
			quitter.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// Exit the program once "Quitter" option is clicked
					System.exit(1);
				}
			});
			
			apropos.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, "VIJEY GAME DESIGNER MEDIA PLAYER 1.0\n\n" +
							"Q. Kasali, Av. Rukumbuka N°19L\n" +
							"+243 858 999 623, 097 0597 707\n" +
							"akilimalisamuelvijey@gmail.com\n", "A PROPOS", JOptionPane.INFORMATION_MESSAGE);
					
				}
			});
			
	}
	
	private MediaPlayer(){
		//Maximize  the screen
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		screenSize.getClass();
		JFrame frame = new JFrame();
		
//		//Code provisoire
//		JPanel controlsPane = new JPanel();
//		JButton rewindButton = new JButton("Rewind");
//		
//		
//	    final JSlider jSlider = new JSlider();
		
		MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
		
		Canvas c = new Canvas();
		c.setBackground(Color.decode("0x100A06"));
		JPanel panel = new JPanel();
		c.setBounds(100, 500, 1250, 500);
		panel.setLayout(new BorderLayout());
		panel.add(c, BorderLayout.CENTER);
		c.setBounds(100, 50, 1050, 710);
		frame.add(panel, BorderLayout.NORTH);
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(100, 900, 105, 200);
		frame.add(panel1, BorderLayout.SOUTH);
		
		JButton playButton = new JButton("Play");
		
		playButton.setBounds(50, 50, 150, 100);
		panel1.add(playButton);
		
		JButton pauseButton = new JButton("Pause");
		
		pauseButton.setBounds(80, 50, 150, 100);
		
		panel1.add(pauseButton);
		
		//Code provisoire
//		rewindButton.setBounds(100, 50, 150, 100);
//		panel1.add(rewindButton);
		File ourFile;
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.showSaveDialog(null);
		ourFile = fileChooser.getSelectedFile();
		String mediaPath = ourFile.getAbsolutePath();
		
		final EmbeddedMediaPlayer mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
		mediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(c));

	    ImageIcon img = new ImageIcon("src/image/icons8_Blu_Ray_Disc_Player_48.png");
	    frame.setIconImage(img.getImage());
		frame.setLocation(100, 100);
		frame.setSize(1050, 600);
		frame.setTitle(ourFile.getAbsolutePath());
		frame.setIconImage(new ImageIcon("src/image/icons8_Blu_Ray_Disc_Player_48.png").getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		mediaPlayer.playMedia(mediaPath);
		
		//METHODS
		pauseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mediaPlayer.pause();
				
				//or mediaPlayer.pause depending on what works.
				final long time = mediaPlayer.getTime();
				System.out.println(time);
			}
		});
		
		playButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mediaPlayer.play();
				
			}
		});
		
		
//		//Creation d'un JSlider Code provisoire
//		jSlider.addMouseMotionListener(new MouseMotionAdapter() {
//			@Override
//			public void mouseDragged(MouseEvent e){
//				if(jSlider.getValue()/100 < 1){
//					mediaPlayer.setPosition((float)jSlider.getValue()/100);
//				}
//			}
//			
//		});
//		Timer timer = new Timer(100, new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				jSlider.setValue(Math.round(mediaPlayer.getPosition()*100));
//			}
//		});
//		timer.start();
//		
//		rewindButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				
//				
//			}
//		});
	}
	
}
