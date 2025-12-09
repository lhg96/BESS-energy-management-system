package org.hils.gui.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class BatterySummeryPanel extends JPanel {
	private int id	= 1;
	private int w = 100;
	private int h = 200;

	private double soc = 50;

	private Color backColor = new Color(252, 252, 252);
	private Color cellColor = new Color(210, 210, 210);
	private Color cellLineColor = new Color(180, 180, 180);
	private Color socColor = new Color(0, 255, 0);
	private Color fontColor = new Color(0, 0, 0);
	
	public BatterySummeryPanel(int id, double soc) {
		this.id = id;
		this.soc = soc;
		this.setPreferredSize(new Dimension(w, h));
	}
			
	public BatterySummeryPanel(int id, double soc,int w, int h) {
		this.id = id;
		this.soc = soc;
		this.w = w; 
		this.h = h;
		this.setPreferredSize(new Dimension(w, h));
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D graphic2d = (Graphics2D) g;
		graphic2d.setFont(new Font( "Arial", Font.BOLD, 14 ));
		FontMetrics metrics = graphic2d.getFontMetrics(g.getFont());
		String socM = soc+"";
		int fontW = metrics.stringWidth(socM);
		int fontH = metrics.getHeight();
		
		w = this.getWidth();
		h = this.getHeight()-fontH;
		
		int wgap  = w / 10;
		int hgap  = h / 10;
		int bW 	  = w - (wgap * 2);
		int bH 	  = h - (hgap * 2);
		int cellX = wgap * 3 / 2;
		int cellY = hgap + hgap / 3;
		int cellW = w - (wgap * 3);
		int cellH = h - ((hgap + hgap / 3) * 2);
		int socH  = (int) ((cellH*soc)/100);
		int socY  = cellY+cellH-socH;

		graphic2d.setColor(backColor);
		graphic2d.fillRect(w / 2 - wgap, hgap / 2, wgap * 2, hgap / 2); // top
		graphic2d.fillRect(wgap, hgap, bW, bH); // body

		graphic2d.setColor(cellColor);
		graphic2d.fillRect(wgap * 2, hgap * 3 / 2, w - (wgap * 4), h - (hgap * 3));// cell

		graphic2d.setColor(cellLineColor);
		graphic2d.drawRect(cellX, cellY, cellW, cellH);// cell
		
		graphic2d.setColor(socColor);
		graphic2d.fillRect(cellX, socY,cellW , socH);// cell
		graphic2d.setColor(fontColor);		
		
		
		
		graphic2d.drawString(socM, w/2 - fontW/2, h-(hgap*2));
		graphic2d.setColor(Color.CYAN);
		String idM = "#"+id;
		fontW = metrics.stringWidth(idM);
		
		graphic2d.drawString(idM, w/2 - fontW/2, this.getHeight()-fontH);
		
		
	}
	
	// Main method for independent execution
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> {
			javax.swing.JFrame frame = new javax.swing.JFrame("Battery Summary Panel");
			frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
			
			BatterySummeryPanel panel = new BatterySummeryPanel(1, 75.0);
			frame.add(panel);
			
			frame.setSize(800, 600);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
	}
}
