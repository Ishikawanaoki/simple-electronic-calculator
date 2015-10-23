package algo2_report;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
interface LibelConstant {
    int LABELWIDTH = 200;
}
class MyLabel extends JLabel {
    public MyLabel(){
	super("0",RIGHT);
	Dimension d = getSize();
	d.width = LibelConstant.LABELWIDTH;
	setSize(d);
    }
    public ActionListener getActionListener(){
	return (event)->setText(event.getActionCommand());
    }
}

