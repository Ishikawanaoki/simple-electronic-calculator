ckage algo2_report;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
interface Constant {
    int WIDTH = 300;
    int HEIGHT = 300;
    String TITLE = "計算器";
}
class MyFrame extends JFrame {
    public void setCloseActionListener(ActionListener a){
	closeActionList.add(a);
    }
    private final LinkedList<ActionListener> closeActionList
	= new LinkedList<ActionListener>();
    class MyWindowListener extends WindowAdapter {
	public MyWindowListener(){
	}
    @Override
	public void windowClosed(WindowEvent e){
	    for(ActionListener listener : closeActionList){
		listener.actionPerformed(
                   new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"close"));
	    }
	}
    }
    public MyFrame(){
        super();    
	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	addWindowListener(new MyWindowListener());
	setSize(Constant.WIDTH,Constant.HEIGHT);
	setTitle(Constant.TITLE);
    }
}
