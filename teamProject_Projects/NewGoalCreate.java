//////////////////////////////1번.새목표 만들기 (개인,팀) 창/////////////////////////////

package teamProject_Projects;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import teamProject_Frame.TFrame2;

public class NewGoalCreate extends TFrame2{
     
	JPanel titleP;
	JLabel titleL;
	JButton singleRoomCreate, teamRoomCreate;
	
	public NewGoalCreate() {
		
		titleP = new JPanel();
		titleL = new JLabel("새 목표 만들기");
		
		singleRoomCreate = new JButton("");
		singleRoomCreate.setIcon(new ImageIcon("teamProject_Projects\\_goal1.png"));
		singleRoomCreate.setBounds(90, 257, 310, 310);
		add(singleRoomCreate);
		singleRoomCreate.setBorderPainted(false);
		singleRoomCreate.setContentAreaFilled(false);
		singleRoomCreate.setFocusPainted(false);
		
		
		teamRoomCreate = new JButton("");
		singleRoomCreate.addActionListener(this);
		teamRoomCreate.addActionListener(this);
		teamRoomCreate.setIcon(new ImageIcon("teamProject_Projects\\goal2.png"));
		teamRoomCreate.setBounds(590, 257, 310, 310);
		add(teamRoomCreate);
		teamRoomCreate.setBorderPainted(false);
		teamRoomCreate.setContentAreaFilled(false);
		teamRoomCreate.setFocusPainted(false);
	}
	
	@Override
	public void arrange(String id, int x, int y) {
	    getContentPane().add(titleP);
	    titleP.setLayout(null);
	    titleP.setBounds(80, 50, 800, 200);
	    titleP.add(titleL);
	    titleP.setBackground(Color.WHITE);
	    titleL.setBounds(260, 70, 500, 100);
	    titleL.setFont(new Font("나눔스퀘어라운드 ExtraBold",Font.BOLD, 50));
	    
	    		
		//getContentPane().add(singleRoomCreate);		
		//getContentPane().add(teamRoomCreate);
		//singleRoomCreate.setBounds(200, (y/2)-y/6, 150, 150);
		//teamRoomCreate.setBounds(650,(y/2)-y/6 , 150, 150);
        setVisible(true);
        setLocation(x,y);
		validate();
		//repaint();
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		Object obj = e.getSource();
		
		if (obj == singleRoomCreate) {
			new SinglePlanCreate().arrange(id, getX(), getY());
			saveThisPage();
		}else if (obj == teamRoomCreate) {
			new TeamPlanCreate().arrange(id, getX(), getY());
			saveThisPage();
		}
	}
	

	public static void main(String[] args) {
		NewGoalCreate ng =  new NewGoalCreate();
		ng.arrange("", 0,0);
	}
}
