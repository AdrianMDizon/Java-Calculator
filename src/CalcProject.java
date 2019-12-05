import javax.swing.JFrame;

public class CalcProject extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator myCalc = new Calculator();
		myCalc.setVisible(true);
		myCalc.setTitle("Calculator");
		myCalc.pack();
		myCalc.setSize(700,600);
		myCalc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
