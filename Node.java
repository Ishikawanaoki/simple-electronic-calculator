package algo2_report;
import java.math.BigDecimal;
import java.math.RoundingMode;
abstract class Node {
	abstract public void show();
	abstract public void rpn();
	abstract public void setOp(char c);
	abstract public void addLeft(Node n);
	abstract public void addRight(Node n);
	abstract public BigDecimal getValue();
}
class Num extends Node {
	public Num(BigDecimal n){
		value = n;
	}
	private BigDecimal value;
	@Override public void setOp(char c){}
	@Override public void addLeft(Node n){}
	@Override public void addRight(Node n){}
	@Override public void show(){
		System.out.print(value);
	}
	@Override public void rpn(){
		System.out.print(value);
	}
	@Override public BigDecimal getValue(){
		return value;
	}
}
class Op extends Node{
	public static Node connectToLeft(Node n){
		final Op result = new Op();
		result.left = n;
		return result;
	}
	public Op(){
		//計算の丸めこみの桁設定
		ans = new BigDecimal(0.00000);
	}
	private char op;
	private Node left;
	private Node right;
	@Override public void setOp(char c){
		op = c;
	}
	@Override public void addLeft(Node n){
		left = n;
	}
	@Override public void addRight(Node n){
		right = n;
	}
	@Override public void show(){
		System.out.print("(");
		if(left!=null) left.show();
		System.out.print(op);
		if(right!=null) right.show();
		System.out.print(")");
	}
	@Override public void rpn(){
		if(left!=null) left.rpn();
		blank();
		if(right!=null)	right.rpn();
		blank();
		System.out.print(op);
		blank();
	}
	private BigDecimal ans;
	private final char opList[] = {'+','-','*','/'};
	@Override public BigDecimal getValue(){
		if(left!=null && right!=null){
			ans = left.getValue();
			if(op == opList[0])
				ans = ans.add(right.getValue());
			else if(op == opList[1])
				ans = ans.subtract(right.getValue());
			else if(op == opList[2])
				ans = ans.multiply(right.getValue());
			else if(op == opList[3]){
				ans = ans.divide(right.getValue(), 54,  RoundingMode.HALF_UP); 
				ans = ans.add(new BigDecimal("1E-54"));
			}
		}
		return ans;
	}
	private void blank(){
		System.out.print(" ");
	}
}

