package org.sp.app0725.calendar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

//화면의 구석에 숨겨져 있다가, 마우스 이벤트에 반응하여 본 모습을 드러내는 탭 메뉴 구현
//현재 ㄱ클래스가 이미 다른 클래스를 상속받은 경우, 다중 상속 금지 원칙이 따라 Thread를 재정의 하지 못한다.
//이 땐 Thread run() 메서드만을 정의한 인터페이스를 정의하면 된다.
public class TabMenu extends JPanel implements Runnable{
	double x;
	double y;
	int width;
	int height;
	double a=0.01;
	int targetX; //목표지점
	
	public TabMenu(Color color, double x, double y, int width, int height) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		
		this.setBackground(color);
		this.setPreferredSize(new Dimension(width, height));
		this.setBounds((int)x, (int)y, width, height);
		
		//마우스 이벤트 구현하기
		this.addMouseListener(new MouseAdapter() {
			//마우스 올라가면
			public void mouseEntered(MouseEvent e) {
				//TabMenu.this.setBounds(-5, y, width, height); //내부익명클래스에서 this를 쓰면 내부익명클래스를 가리키게 되는데, 이때 원 클래스명을 명시하고 this.를 쓰면 원클래스를 가리키게 된다.
				targetX=-5;
			}
			
			//마우스 내려가면/
			public void mouseExited(MouseEvent e) {
				//TabMenu.this.setBounds(-80, y, width, height);
				targetX=-80;
			}
		});
	}
	
	//감속도 공식
	public void tick() {
		//x값 = 기존x + 비율계수*(목표x-현재x)
		this.x=(this.x+a*(targetX-this.x));
	}
	
	public void render() { //보여주기 담당
		this.setBounds((int)x, (int)y, width, height);
	}

	public void run() {
		while(true) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tick();
			render();
		}
	}
}
