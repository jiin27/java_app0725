package org.sp.app0725.calendar;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.JPanel;

import util.StringManager;

//날짜 셀 정의하기 
public class NumCell extends Cell{
	//메인프레임 diaryMain에 Calendar 객체가 존재하기 때문에, 그 객체를 접근하려고
	DiaryMain diaryMain;
	JPanel iconBox; //아이콘들이 배치될 패널
	
	public NumCell(DiaryMain diaryMain, Color color, int width, int height) {
		super(color, width, height);
		
		this.diaryMain=diaryMain;
		iconBox = new JPanel();
		iconBox.setBackground(Color.PINK);
		add(iconBox);
		
		//라벨의 텍스트 크기 조정
		la_title.setFont(new Font("돋움", Font.BOLD, 20));
		
		//마우스 이벤트 연결
		this.addMouseListener(new MouseAdapter() {
			//클릭
			public void mouseClicked(MouseEvent e) {
				int yy=diaryMain.cal.get(Calendar.YEAR);
				int mm=diaryMain.cal.get(Calendar.MONTH);
				int n=Integer.parseInt(la_title.getText());
				
				//System.out.println(yy+"년 "+(mm+1)+"월 "+n+"일 클릭");
				diaryMain.popup.showPop(NumCell.this, yy+"년 "+StringManager.getNumString(mm+1)+"월 "+StringManager.getNumString(n)+"일");
			}
		});
	}
}
