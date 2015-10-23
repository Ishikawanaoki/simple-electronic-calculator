package algo2_report;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

import javax.swing.*;
interface KeyboardConstant {
	final char[] keys = new char[]
			{'$','/','*','-','7','8','9','+','4','5','6','1','2','3','=','0','.'};
	long INTERVAL = 100;
}
public class setKey extends InputStream {
	final private uniquePanel panel= new uniquePanel("計算機");
	public setKey(){
		super();
		for(char c : KeyboardConstant.keys){
			JButton button = new JButton(String.valueOf(c));
			button.addActionListener((event)->
					queue.addLast((int)(event.getActionCommand().charAt(0))));	
			panel.add(button);
		}
	}
	public JPanel getPanel(){
		return panel.p;
	}
	public ActionListener getCloseAction(){
		return (event)->queue.addLast(-1);
	}
	private LinkedList<Integer> queue= new LinkedList<Integer>();
	@Override
	public int read() throws IOException {
		try {
			while(queue.isEmpty()){
				Thread.sleep(KeyboardConstant.INTERVAL);
			}
		}catch(InterruptedException e){
		}
		return queue.remove();
	}
	@Override
	public int available() throws IOException {
		return 0;
	}
	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		if(len==0) return 0;
		int c = read();
		if(c==-1) return -1;
		b[off]=(byte) c;
		return 1;
	}
}
class uniquePanel extends JFrame{
	public JPanel p = new JPanel();
	private GridBagConstraints gbc = new GridBagConstraints();
	private GridBagLayout layout = new GridBagLayout(); 		
	uniquePanel(String title){
		super();
		setBounds( 10, 10, 300, 200);	
		p.setLayout(layout); 
		gbc.fill = GridBagConstraints.BOTH;
	}
	
	private int cursol_x = 0;
	private int cursol_y = 0;
	void add(JButton b){
		setGridBag1(gbc);
		layout.setConstraints(b, gbc);
		p.add(b);
	}
	void setGridBag1(GridBagConstraints gbc){
		//カーソル位置の移動
		//x=4で改行
		if( (cursol_x >= 4) || ( cursol_x==3 && cursol_y==2 ) ){
			cursol_x = 0;
			cursol_y++;
		}
		//'+'と'='の場合設定
		if( (cursol_x==3 && cursol_y==1) || (cursol_x==3 && cursol_y==3) )
			setGridBag2(gbc);
		//'.'の場合設定
		else if(cursol_x==1 && cursol_y==4)
			setGridBag3(gbc);
		else{
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
		}
		gbc.gridx = cursol_x++;
		gbc.gridy = cursol_y;
		////'.'の場合 直後有効
		if(cursol_x==2 && cursol_y==4){
			cursol_x=0;
			cursol_y++;
		}
	}
	void setGridBag2(GridBagConstraints gbc){ 
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
	}
	void setGridBag3(GridBagConstraints gbc){
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
	}
}
