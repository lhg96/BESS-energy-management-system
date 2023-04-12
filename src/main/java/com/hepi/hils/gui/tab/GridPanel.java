package com.hepi.hils.gui.tab;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * grid wire
 * 
 * @author hyun keun lim 
 *
 */
public class GridPanel extends JPanel{
	
	int w;
	int h;
	
	int wgap = 10;
	int hgap = 10;	
	int wline = 5; //선 넓이
	
	int fontH;
	
	Color lineColor 	= new Color(200, 200, 200);
	Color rectBColor 	= new Color(150, 150, 150);
	Color fontColor 	= Color.BLACK;
	
	public GridPanel() {		
		this.setPreferredSize(new Dimension(300, 400));
	}
	
	void updateSize(final Graphics g) {
		w = this.getWidth();
		h = this.getHeight();	
		
		wgap  = w/20;
		hgap  = h/20;
		
		g.setFont(new Font( "Arial", Font.BOLD, 14 ));
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		
		//int fontW = metrics.stringWidth(socM);
		fontH = metrics.getHeight();		
		
		
		//System.out.println(w+"/"+h);		
	}
	
	@Override
	public void paint(Graphics g) {
		updateSize(g);
		
		g.setColor(lineColor);
		g.fillRect(wgap, hgap, w-(2*wgap), wline);		
		g.fillRect(w/2-wline/2, hgap, wline, h-hgap);
		
		g.setColor(rectBColor);
		g.fillRoundRect(w/4, h/2, w/2, fontH*4, wline, wline);
		
		g.setColor(lineColor);
		g.fillRoundRect(w/4+wline, h/2+wline, w/2-(wline*2), fontH*4-wline*2, wline, wline);
	}
}
