package com.susmote.HorseRaceLightTextFrame;

import java.awt.*;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class HorseRaceLightTextFrame extends JFrame {

	/**
	 * coding by susmote
	 */
	private static final long serialVersionUID = -2557039448754459770L;
	private Image img = null;
	private HorseRaceLightTextPanel horseRaceLightTextPanel = new HorseRaceLightTextPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HorseRaceLightTextFrame frame = new HorseRaceLightTextFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HorseRaceLightTextFrame() {
		super();
		setTitle("文字跑马灯特效");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 160, 360, 237);
		URL imgUrl = HorseRaceLightTextFrame.class.getResource("/img/image.jpg");
		img = Toolkit.getDefaultToolkit().getImage(imgUrl);
		getContentPane().add(horseRaceLightTextPanel);
		Thread thread = new Thread(horseRaceLightTextPanel);
		thread.start();
	}
	class HorseRaceLightTextPanel extends JPanel implements Runnable{
		/**
		 * coding by susmote
		 */
		private static final long serialVersionUID = -1878663332205131604L;
		String value = "特克斯博客，给你不一样体验";
		char[] drawChar = value.toCharArray();
		int[] x = new int[drawChar.length];
		int y = 100;
		@Override
		public void paint(Graphics g) {
			g.clearRect(0, 0, getWidth(), getHeight());
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			Font font = new Font("华文楷体", Font.BOLD, 20);
			g.setFont(font);
			g.setColor(Color.RED);
			for (int j = drawChar.length - 1; j >= 0; j--){
				g.drawString(drawChar[drawChar.length-1-j]+"", x[j], y);
			}
		}

		@Override
		public void run() {
			boolean flag = false;
			while (true){
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for (int i = drawChar.length - 1; i >= 0; i--){
					if (!flag){
						x[i] = x[i] + 20 * i;
					}else{
						x[i] = x[i] + 20;
					}
					if (x[i] >= 360-20){
						x[i] = 0;
					}
				}
				repaint();
				if (!flag){
					flag = true;
				}
			}
		}
	}

}
