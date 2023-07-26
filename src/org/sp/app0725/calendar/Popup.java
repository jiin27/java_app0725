package org.sp.app0725.calendar;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

//날짜 셀을 클릭하면, 상세 내용을 입력받을 수 있는 팝업창
public class Popup extends JFrame{
	JLabel la_header; //날짜 제목
	JTextArea area;
	JPanel p_icon; //아이콘이 배치될 패널
	String[] path= {"res/rain.png", "res/sunny.png", "res/snow.png", "res/thunder.png"};
	ArrayList<JLabel> la_icon; //아이콘 이미지를 담을 라벨들
	JButton bt;
	JLabel la_selected; //유저가 선택한 라벨
	NumCell numCell; //저장 버튼을 누를 때 어떤 셀을 대상으로 아이콘을 반영할지를 알기 위함.
	int index; //사용자가 선택한 라벨의 index, 
	
	public Popup() {
		la_header = new JLabel("날짜");
		area = new JTextArea();
		p_icon = new JPanel();
		la_icon=new ArrayList<JLabel>();
		bt = new JButton("저장");
		
		//스타일
		la_header.setFont(new Font("돋움", Font.BOLD, 20));
		area.setPreferredSize(new Dimension(380, 150));
		p_icon.setPreferredSize(new Dimension(380, 100));
		
		setLayout(new FlowLayout());
		
		add(la_header);
		add(area);
		add(p_icon);
		createIcon();
		add(bt);
		
		setBounds(400, 200, 400, 400);
		//setVisible(true);
		setLocationRelativeTo(null);
		
		//저장버튼과 리스너 연결
		//재정의할 메서드가 1개일 경우 굳이 클래스까지 만들 필요가 없다. -> 람다 표현식
		bt.addActionListener((e)->{
			save();
		});
		
	}
	
	//유저가 선택한 아이콘과 area의 값을 날짜 셀에 저장하자(NumCell 에 반영)
	//아이콘은 NumCell의 iconBox 패널에 부착하자. 나중에 아이콘을 지울 때 iconBox 패널의 하위 자식들을 전부 제거하는 방식이 편하므로.
	public void save() {
		//라벨을 생성하여 부착하기
		URL url=ClassLoader.getSystemResource(path[index]);
		try {
			BufferedImage buffImg=ImageIO.read(url);
			Image image=buffImg;
			image=image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			JLabel la_icon=new JLabel(new ImageIcon(image));
			numCell.iconBox.add(la_icon);
			numCell.updateUI(); //컴포넌트 새로 고침
			this.setVisible(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	//아이콘 생성
	public void createIcon() {
		for(int i=0; i<path.length; i++) { //ArrayList 는 크기가 지정되지 않았기 때문에 크기가 지정된 path[]의 length를 이용한다.
			URL url=ClassLoader.getSystemResource(path[i]);
			try {
				BufferedImage buffImg=ImageIO.read(url);
				Image image=buffImg;
				image=image.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
				JLabel la=new JLabel(new ImageIcon(image));
				la_icon.add(la); //리스트에 생성된 라벨 넣기
				p_icon.add(la);
				
				la.addMouseListener(new MouseAdapter() { //*내부익명클래스에서 전역변수가 아닌 지역 변수를 이용하려면 final, 상수로 선언돼 있어야만 한다.
					public void mouseClicked(MouseEvent e) {
						la_selected=(JLabel)e.getSource(); 
						index=la_icon.indexOf(la_selected);
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	//원도우 보이면서 날짜 출력, 선택된 셀에 아이콘 등 적용
	public void showPop(NumCell numCell, String header) {
		this.setVisible(true); //보익[
		la_header.setText(header);
		
		//셀에 아이콘 적용하기
		this.numCell=numCell;
		//numCell.iconBox.add(la_icon[])
	}
}
