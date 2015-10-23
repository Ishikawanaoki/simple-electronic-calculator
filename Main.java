package algo2_report;
import java.awt.BorderLayout;
import java.awt.Container;
import java.math.BigDecimal;
class Main {
    public static void main(String[] arg) throws ParseException {
    BigDecimal previous = new BigDecimal(0);
	final MyFrame frame = new MyFrame();
	final setKey setKey = new setKey();
	frame.setCloseActionListener(setKey.getCloseAction());
	final Container container = frame.getContentPane();
	container.add(setKey.getPanel(), BorderLayout.CENTER);
	final MyLabel label = new MyLabel();
	container.add(label,BorderLayout.NORTH);
	frame.setVisible(true);

	while(true){
		Parser parser = new Parser(setKey);
		parser.setActionListener(label.getActionListener());
		try{
			final Node tree = parser.start(previous);
			previous = tree.getValue();
		}catch(ParseException e){
			System.out.println(e);
		}catch(TokenMgrError e){
			System.out.println(e);
		}catch(ArithmeticException e){
			System.out.println("その他の算術的エラー");
			
		}	}
    }
}

