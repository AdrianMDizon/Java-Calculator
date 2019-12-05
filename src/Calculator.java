import javax.swing.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
	JMenuItem minimize, copy, paste, about;
	JPanel mainPanel, panel1, panel2, panel3;
	JLabel num1Label, num2Label, resLabel;
	JButton addButton, subButton, multButton, divButton, modButton, negButton, clearButton, clearMemButton, sqrtButton, percentButton, fractionButton, equalsButton, 
			quotButton, backspaceButton, zeroButton, oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton, sevenButton, eightButton, nineButton,
			decimal, aButton, bButton, cButton, dButton, eButton, fButton;
	JTextArea numArea, bitArea;
	JRadioButton hex, dec, oct, bin, qword, dword, word, bite;
	JMenuBar menuBar;
	JMenuItem viewMenu, editMenu, helpMenu;
	String num, toBinary, binary, binaryOriginal, copiedNum, copiedBinary, result;
	int num1, num2, numConv, numOriginal = 0;
	boolean savedNum, adding, subtracting, dividing, multiplying, modulating = false,
			hexOn, octOn, binOn = false, decOn = true;
	
	public Calculator() {
		initGUI();
		setMenu();
		
	}
	
	public void initGUI() {
		setTitle("Calculator");
		
		mainPanel = new JPanel(new GridBagLayout());
		
		this.getContentPane().add(mainPanel);
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		numArea = new JTextArea(1,50);	//main text field
		numArea.setEditable(false);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 20;
		gbc.gridheight = 1;
		numArea.setPreferredSize(new Dimension(100,100));
		numArea.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		num = "0";
		numArea.setText(num);
		mainPanel.add(numArea,gbc);
		
		gbc.gridy = 2;
		mainPanel.add(new JSeparator(),gbc);
		
		bitArea = new JTextArea(1,50);	//text field for binary bits
		bitArea.setEditable(false);
		gbc.gridy = 3;
		bitArea.setPreferredSize(new Dimension(100,100));
		bitArea.setFont(new Font("Serif", Font.BOLD, 15));
		binary = "0000 0000 0000 0000 0000 0000 0000 0000 \n"
				+ "0000 0000 0000 0000 0000 0000 0000 0000";
		binaryOriginal = binary;
		bitArea.setText(binary);
		
		mainPanel.add(bitArea,gbc);
		
		//Radio buttons to show the numbering system to be displayed
		hex = new JRadioButton ("Hex");
			hex.addActionListener(this);
		dec = new JRadioButton ("Dec");
			dec.addActionListener(this);
		oct = new JRadioButton ("Oct");
			oct.addActionListener(this);
		bin = new JRadioButton ("Bin");
			bin.addActionListener(this);
		
		
		ButtonGroup numSystem = new ButtonGroup();
		numSystem.add(hex);
		numSystem.add(dec);
		numSystem.add(oct);
		numSystem.add(bin);
		dec.setSelected(true);
		
		Box box1 = Box.createVerticalBox();
		box1.add(hex);
		box1.add(dec);
		box1.add(oct);
		box1.add(bin);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 2;
		gbc.gridheight = 3;
		mainPanel.add(box1, gbc);	//add the first group of radio buttons
		
		gbc.gridy = 9;
		mainPanel.add(new JSeparator(),gbc);
		
		qword = new JRadioButton ("QWord");
		dword = new JRadioButton ("DWord");
		word = new JRadioButton ("Word");
		bite = new JRadioButton ("Byte");
		
		ButtonGroup byteSystem = new ButtonGroup();
		byteSystem.add(qword);
		byteSystem.add(dword);
		byteSystem.add(word);
		byteSystem.add(bite);
		qword.setSelected(true);
		
		Box box2 = Box.createVerticalBox();
		box2.add(qword);
		box2.add(dword);
		box2.add(word);
		box2.add(bite);

		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.gridwidth = 2;
		gbc.gridheight = 3;
		mainPanel.add(box2, gbc);	//add the second group of radio buttons
		
		quotButton = new JButton("Quot");
		gbc.gridx = 3;
		gbc.gridy = 6;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		mainPanel.add(quotButton,gbc);
		
		modButton = new JButton("Mod");
		gbc.gridx = 4;
		modButton.addActionListener(this);
		mainPanel.add(modButton,gbc);
		
		aButton = new JButton("A");
		gbc.gridx = 5;
		mainPanel.add(aButton,gbc);
		aButton.setEnabled(false);
		
		bButton = new JButton("B");
		gbc.gridy = 7;
		mainPanel.add(bButton,gbc);
		bButton.setEnabled(false);
		
		cButton = new JButton("C");
		gbc.gridy = 8;
		mainPanel.add(cButton,gbc);
		cButton.setEnabled(false);
		
		dButton = new JButton("D");
		gbc.gridy = 9;
		mainPanel.add(dButton,gbc);
		dButton.setEnabled(false);
		
		eButton = new JButton("E");
		gbc.gridy = 10;
		mainPanel.add(eButton,gbc);
		eButton.setEnabled(false);
		
		fButton = new JButton("F");
		gbc.gridy = 11;
		mainPanel.add(fButton,gbc);
		fButton.setEnabled(false);
		
		backspaceButton = new JButton ("<-");
		gbc.gridx = 6;
		gbc.gridy = 7;
		backspaceButton.addActionListener(this);
		mainPanel.add(backspaceButton,gbc);
		
		clearMemButton = new JButton ("CE");
		gbc.gridx = 7;
		clearMemButton.addActionListener(this);
		mainPanel.add(clearMemButton,gbc);
		
		clearButton = new JButton ("C");
		gbc.gridx = 8;
		clearButton.addActionListener(this);
		mainPanel.add(clearButton,gbc);
		
		
		sevenButton = new JButton("7");
		gbc.gridx = 6;
		gbc.gridy = 8;
		sevenButton.addActionListener(this);
		mainPanel.add(sevenButton,gbc);
		
		eightButton = new JButton("8");
		gbc.gridx = 7;
		eightButton.addActionListener(this);
		mainPanel.add(eightButton,gbc);
		
		nineButton = new JButton("9");
		gbc.gridx = 8;
		nineButton.addActionListener(this);
		mainPanel.add(nineButton,gbc);
		
		fourButton = new JButton("4");
		gbc.gridx = 6;
		gbc.gridy = 9;
		fourButton.addActionListener(this);
		mainPanel.add(fourButton,gbc);
		
		fiveButton = new JButton("5");
		gbc.gridx = 7;
		fiveButton.addActionListener(this);
		mainPanel.add(fiveButton,gbc);
		
		sixButton = new JButton("6");
		gbc.gridx = 8;
		sixButton.addActionListener(this);
		mainPanel.add(sixButton,gbc);
		
		oneButton = new JButton("1");
		gbc.gridx = 6;
		gbc.gridy = 10;
		oneButton.addActionListener(this);
		mainPanel.add(oneButton,gbc);
		
		twoButton = new JButton("2");
		gbc.gridx = 7;
		twoButton.addActionListener(this);
		mainPanel.add(twoButton,gbc);
		
		threeButton = new JButton ("3");
		gbc.gridx = 8;
		threeButton.addActionListener(this);
		mainPanel.add(threeButton,gbc);
		
		zeroButton = new JButton ("0");
		gbc.gridx = 6;
		gbc.gridy = 11;
		gbc.gridwidth = 2;
		zeroButton.setPreferredSize(new Dimension(150,25));
		zeroButton.addActionListener(this);
		mainPanel.add(zeroButton,gbc);
		
		decimal = new JButton(".");
		gbc.gridx = 8;
		gbc.gridwidth = 1;
		mainPanel.add(decimal,gbc);
		decimal.setEnabled(false);
	
		negButton = new JButton ("±");
		gbc.gridx = 9;
		gbc.gridy = 7;
		negButton.addActionListener(this);
		mainPanel.add(negButton,gbc);
		
		divButton = new JButton("/");
		gbc.gridy = 8;
		divButton.addActionListener(this);
		mainPanel.add(divButton,gbc);
		
		multButton = new JButton("*");
		gbc.gridy = 9;
		multButton.addActionListener(this);
		mainPanel.add(multButton,gbc);
		
		subButton = new JButton("-");
		gbc.gridy = 10;
		subButton.addActionListener(this);
		mainPanel.add(subButton,gbc);
		
		addButton = new JButton("+");
		gbc.gridy = 11;
		addButton.addActionListener(this);
		mainPanel.add(addButton,gbc);
		
		sqrtButton = new JButton("√");
		gbc.gridx = 10;
		gbc.gridy = 7;
		mainPanel.add(sqrtButton,gbc);
		sqrtButton.setEnabled(false);

		percentButton = new JButton ("%");
		gbc.gridy = 8;
		mainPanel.add(percentButton,gbc);
		percentButton.setEnabled(false);
		
		fractionButton = new JButton ("1/x");
		gbc.gridy = 9;
		mainPanel.add(fractionButton,gbc);
		fractionButton.setEnabled(false);
		
		equalsButton = new JButton ("=");
		gbc.gridy = 10;
		gbc.gridheight = 2;
		equalsButton.addActionListener(this);
		equalsButton.setPreferredSize(new Dimension(50,60));
		mainPanel.add(equalsButton,gbc);
		
		this.pack();
		this.setVisible(true);
	}

	public void setMenu() {
		
		JMenuBar menuBar = new JMenuBar();		
		JMenu viewMenu = new JMenu ("View");
		JMenu editMenu = new JMenu ("Edit");
		JMenu helpMenu = new JMenu ("Help");	
		
		viewMenu.setMnemonic(KeyEvent.VK_V);
		menuBar.add(viewMenu);
		
		minimize = new JMenuItem("Hide",KeyEvent.VK_T);
		minimize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		minimize.addActionListener(this);
		viewMenu.add(minimize);
		
		
		
		editMenu.setMnemonic(KeyEvent.VK_E);
		menuBar.add(editMenu);
		
		copy = new JMenuItem("Copy",KeyEvent.VK_C);
		minimize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		copy.addActionListener(this);
		editMenu.add(copy);
		
		paste = new JMenuItem("Paste", KeyEvent.VK_P);
		minimize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
		paste.addActionListener(this);
		editMenu.add(paste);
		
		helpMenu.setMnemonic(KeyEvent.VK_H);
		menuBar.add(helpMenu);
		
		about = new JMenuItem("About",KeyEvent.VK_A);
		minimize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.ALT_MASK));
		about.addActionListener(this);
		helpMenu.add(about);
		
		
		menuBar.add(editMenu);
		menuBar.add(helpMenu);
		this.setJMenuBar(menuBar);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == minimize) {
			this.setState(Frame.ICONIFIED);
		}
		if (e.getSource() == copy) {
			copiedNum = num;
			copiedBinary = binary;
		}
		if (e.getSource() == paste) {
			numArea.setText(copiedNum);
			bitArea.setText(copiedBinary);
		}
		if (e.getSource() == about) {
			openAbout();
		}
		if (e.getSource() == zeroButton) {
			if(num != "0") {
				num += "0";
				numArea.setText(num);
			}
			else
				numArea.setText("0");	

			//convert the current number into binary to display in the 2nd text area
			binary = Conversions.formatBinary(num, binaryOriginal, hexOn, binOn, octOn);
			bitArea.setText(binary);
		}
		if (e.getSource() == oneButton) {
			if(num != "0") {
				num += "1";
				numArea.setText(num);
			}
			else {
				num = "1";
				numArea.setText(num);
			}
			//convert the current number into binary to display in the 2nd text area
			binary = Conversions.formatBinary(num, binaryOriginal, hexOn, binOn, octOn);
			bitArea.setText(binary);
		}
		if (e.getSource() == twoButton) {
			if(num != "0") {
				num += "2";
				numArea.setText(num);
			}
			else {
				num = "2";
				numArea.setText(num);
			}
			//convert the current number into binary to display in the 2nd text area
			binary = Conversions.formatBinary(num, binaryOriginal, hexOn, binOn, octOn);
			bitArea.setText(binary);
		
		}
		if (e.getSource() == threeButton) {
			if(num != "0") {
				num += "3";
				numArea.setText(num);
			}
			else {
				num = "3";
				numArea.setText(num);
			}
			//convert the current number into binary to display in the 2nd text area
			binary = Conversions.formatBinary(num, binaryOriginal, hexOn, binOn, octOn);
			bitArea.setText(binary);
		
		}
		if (e.getSource() == fourButton) {
			if(num != "0") {
				num += "4";
				numArea.setText(num);
			}
			else {
				num = "4";
				numArea.setText(num);
			}
			//convert the current number into binary to display in the 2nd text area
			binary = Conversions.formatBinary(num, binaryOriginal, hexOn, binOn, octOn);
			bitArea.setText(binary);
		
		}
		if (e.getSource() == fiveButton) {
			if(num != "0") {
				num += "5";
				numArea.setText(num);
			}
			else {
				num = "5";
				numArea.setText(num);
			}
			//convert the current number into binary to display in the 2nd text area
			binary = Conversions.formatBinary(num, binaryOriginal, hexOn, binOn, octOn);
			bitArea.setText(binary);
		
		}
		if (e.getSource() == sixButton) {
			if(num != "0") {
				num += "6";
				numArea.setText(num);
			}
			else {
				num = "6";
				numArea.setText(num);
			}
			//convert the current number into binary to display in the 2nd text area
			binary = Conversions.formatBinary(num, binaryOriginal, hexOn, binOn, octOn);
			bitArea.setText(binary);
		}
		if (e.getSource() == sevenButton) {
			if(num != "0") {
				num += "7";
				numArea.setText(num);
			}
			else {
				num = "7";
				numArea.setText(num);
			}
			//convert the current number into binary to display in the 2nd text area
			binary = Conversions.formatBinary(num, binaryOriginal, hexOn, binOn, octOn);
			bitArea.setText(binary);
		
		}
		if (e.getSource() == eightButton) {
			if(num != "0") {
				num += "8";
				numArea.setText(num);
			}
			else {
				num = "8";
				numArea.setText(num);
			}
			//convert the current number into binary to display in the 2nd text area
			binary = Conversions.formatBinary(num, binaryOriginal, hexOn, binOn, octOn);
			bitArea.setText(binary);
		
		}
		if (e.getSource() == nineButton) {
			if(num != "0") {
				num += "9";
				numArea.setText(num);
			}
			else {
				num = "9";
				numArea.setText(num);
			}
			//convert the current number into binary to display in the 2nd text area
			binary = Conversions.formatBinary(num, binaryOriginal, hexOn, binOn, octOn);
			bitArea.setText(binary);
		
		}
		if (e.getSource() == negButton) {
			if (num.contains("-")) {
				num = num.substring(1);
				numArea.setText(num);
			}
			else if (num != "0") {
				num = "-" + num;
				numArea.setText(num);
			}
			//convert the current number into binary to display in the 2nd text area
			binary = Conversions.formatBinary(num, binaryOriginal, hexOn, binOn, octOn);
			bitArea.setText(binary);
		
		}
		if (e.getSource() == clearButton) {
			num = "0";
			binary = binaryOriginal;
			num1 = 0;
			num2 = 0;
			savedNum = false;
			adding = false; subtracting = false; multiplying = false; dividing = false; modulating = false;
			numArea.setText(num);
			bitArea.setText(binary);
		}
		if (e.getSource() == clearMemButton) {
			num = "0";
			binary = binaryOriginal;
			num1 = 0;
			num2 = 0;
			savedNum = false;
			adding = false; subtracting = false; multiplying = false; dividing = false; modulating = false;
			numArea.setText(num);
			bitArea.setText(binary);
		}
		if (e.getSource() == backspaceButton) {
			if(num.length() != 1) {
				num = num.substring(0, num.length() - 1);
				numArea.setText(num);
			}
			else {
				num = "0";
				numArea.setText(num);
			}
			//convert the current number into binary to display in the 2nd text area
			numConv = Integer.parseInt(Conversions.toDec(num, hexOn, binOn, octOn));
			binary = Conversions.decToBinary(Integer.toString(numConv));
			binary = binaryOriginal.substring(0, binaryOriginal.length() - binary.length()) + binary;
			bitArea.setText(binary);bitArea.setText(binary);
		}
		
		if (e.getSource() == addButton) {
			
			//set num2 to be whatever the current number the user entered is. Num1 is the previous number
			
			if (savedNum == false)
				result = numArea.getText();
			else {
				num2 = Integer.parseInt(numArea.getText());
				result = Arithmetic.equals(num1, num2, hexOn, binOn, octOn, adding, subtracting, multiplying, dividing, modulating);
			}
			
			
			//save the result as the new num1, then display the result
			num1 = Integer.parseInt(result);
			savedNum = true;
			adding = true; subtracting = false; multiplying = false; dividing = false; modulating = false;
			numArea.setText(result);
		
			//convert the current number into binary to display in the 2nd text area
			numConv = Integer.parseInt(Conversions.toDec(result, hexOn, binOn, octOn));
			binary = Conversions.decToBinary(Integer.toString(numConv));
			binary = binaryOriginal.substring(0, binaryOriginal.length() - binary.length()) + binary;
			bitArea.setText(binary);
			num = "0";
		}
		
		if (e.getSource() == subButton) {
			//set num2 to be whatever the current number the user entered is. Num1 is the previous number
			if(savedNum == false)
				result = numArea.getText();
			else {
				num2 = Integer.parseInt(numArea.getText());
				result = Arithmetic.equals(num1, num2, hexOn, binOn, octOn, adding, subtracting, multiplying, dividing, modulating);
			}
			
			//save the result as the new num1, then display the result
			num1 = Integer.parseInt(result);
			savedNum = true;
			adding = false; subtracting = true; multiplying = false; dividing = false; modulating = false;
			numArea.setText(result);
			
			//convert the current number into binary to display in the 2nd text area
			numConv = Integer.parseInt(Conversions.toDec(result, hexOn, binOn, octOn));
			binary = Conversions.decToBinary(Integer.toString(numConv));
			binary = binaryOriginal.substring(0, binaryOriginal.length() - binary.length()) + binary;
			bitArea.setText(binary);num = "0";
		}
		
		if (e.getSource() == multButton) {
			if (savedNum == true) {
				//set num2 to be whatever the current number the user entered is. Num1 is the previous number
				num2 = Integer.parseInt(numArea.getText());
				result = Arithmetic.equals(num1, num2, hexOn, binOn, octOn, adding, subtracting, multiplying, dividing, modulating);
			}
			else
				result = num;
			
			//save the result as the new num1, then display the result
			num1 = Integer.parseInt(result);
			savedNum = true;
			adding = false; subtracting = false; multiplying = true; dividing = false; modulating = false;
			numArea.setText(result);
			
			//convert the current number into binary to display in the 2nd text area
			numConv = Integer.parseInt(Conversions.toDec(result, hexOn, binOn, octOn));
			binary = Conversions.decToBinary(Integer.toString(numConv));
			binary = binaryOriginal.substring(0, binaryOriginal.length() - binary.length()) + binary;
			bitArea.setText(binary);
			num = "0";
		}
		
		if (e.getSource() == divButton) {
			//set num2 to be whatever the current number the user entered is. Num1 is the previous number
			num2 = Integer.parseInt(numArea.getText());
			
			if(num2 == 0)
				numArea.setText("Error");
			else if(num1 == 0)
				result = Integer.toString(num2);
			else
				result = Arithmetic.equals(num1, num2, hexOn, binOn, octOn, adding, subtracting, multiplying, dividing, modulating);
		
			//save the result as the new num1, then display the result
			num1 = Integer.parseInt(result);
			savedNum = true;
			adding = false; subtracting = false; multiplying = false; dividing = true; modulating = false;
			numArea.setText(result);

			//convert the current number into binary to display in the 2nd text area
			numConv = Integer.parseInt(Conversions.toDec(result, hexOn, binOn, octOn));
			binary = Conversions.decToBinary(Integer.toString(numConv));
			binary = binaryOriginal.substring(0, binaryOriginal.length() - binary.length()) + binary;
			bitArea.setText(binary);
			num = "0";
		}
		if (e.getSource() == quotButton) {
			//set num2 to be whatever the current number the user entered is. Num1 is the previous number
			num2 = Integer.parseInt(numArea.getText());
			
			if(num2 == 0)
				numArea.setText("Error");
			else if(num1 == 0)
				result = Integer.toString(num2);
			else
				result = Arithmetic.equals(num1, num2, hexOn, binOn, octOn, adding, subtracting, multiplying, dividing, modulating);
		
			//save the result as the new num1, then display the result
			num1 = Integer.parseInt(result);
			savedNum = true;
			adding = false; subtracting = false; multiplying = false; dividing = true; modulating = false;
			numArea.setText(result);

			//convert the current number into binary to display in the 2nd text area
			numConv = Integer.parseInt(Conversions.toDec(result, hexOn, binOn, octOn));
			binary = Conversions.decToBinary(Integer.toString(numConv));
			binary = binaryOriginal.substring(0, binaryOriginal.length() - binary.length()) + binary;
			bitArea.setText(binary);
			num = "0";
		}

		if (e.getSource() == modButton) {
			//set num2 to be whatever the current number the user entered is. Num1 is the previous number
			num2 = Integer.parseInt(numArea.getText());
			
			if(num2 == 0)
				numArea.setText("Error");
			else if (num1 == 0)
				result = Integer.toString(num2);
			else
				result = Arithmetic.equals(num1, num2, hexOn, binOn, octOn, adding, subtracting, multiplying, dividing, modulating);
		
			//save the result as the new num1, then display the result
			num1 = Integer.parseInt(result);
			savedNum = true;
			adding = false; subtracting = false; multiplying = false; dividing = false; modulating = true;
			numArea.setText(result);

			//convert the current number into binary to display in the 2nd text area
			numConv = Integer.parseInt(Conversions.toDec(result, hexOn, binOn, octOn));
			binary = Conversions.decToBinary(Integer.toString(numConv));
			binary = binaryOriginal.substring(0, binaryOriginal.length() - binary.length()) + binary;
			bitArea.setText(binary);
			num = "0";
		}
		if (e.getSource() == equalsButton) {
			//set num2 to be whatever the current number the user entered is. Num1 is the previous number
			num2 = Integer.parseInt(numArea.getText());
			result = Arithmetic.equals(num1, num2, hexOn, binOn, octOn, adding, subtracting, multiplying, dividing, modulating);
			
			//reset the operation flags to false, then display the result
			num1 = Integer.parseInt(result);
			adding = false; subtracting = false; multiplying = false; dividing = false; modulating = false;
			numArea.setText(result);
			
			//convert the current number into binary to display in the 2nd text area
			numConv = Integer.parseInt(Conversions.toDec(result, hexOn, binOn, octOn));
			binary = Conversions.decToBinary(Integer.toString(numConv));
			binary = binaryOriginal.substring(0, binaryOriginal.length() - binary.length()) + binary;
			bitArea.setText(binary);
			num = "0";
		}
		
		if (e.getSource() == dec) {
			numConv = Integer.parseInt(Conversions.toDec(numArea.getText(), hexOn, binOn, octOn));
			
			//turn the system flags off, and enable/re-enable the appropriate buttons
			hexOn = false; binOn = false; octOn = false;
			twoButton.setEnabled(true); threeButton.setEnabled(true); fourButton.setEnabled(true); fiveButton.setEnabled(true); sixButton.setEnabled(true); sevenButton.setEnabled(true); eightButton.setEnabled(true); nineButton.setEnabled(true);
			aButton.setEnabled(false); bButton.setEnabled(false); cButton.setEnabled(false); dButton.setEnabled(false); eButton.setEnabled(false); fButton.setEnabled(false);
		
			//convert the number to a string, save it to num, then place it into the text box
			num = Integer.toString(numConv);
			numArea.setText(num);
			decOn = true;	
			}
		
		if (e.getSource() == hex) {
			numConv = Integer.parseInt(Conversions.toDec(numArea.getText(), hexOn, binOn, octOn));
			
			//turn the system flags off, and enable/re-enable the appropriate buttons
			octOn = false; binOn = false; decOn = false;
			twoButton.setEnabled(true); threeButton.setEnabled(true); fourButton.setEnabled(true); fiveButton.setEnabled(true); sixButton.setEnabled(true); sevenButton.setEnabled(true); eightButton.setEnabled(true); nineButton.setEnabled(true);
			aButton.setEnabled(true); bButton.setEnabled(true); cButton.setEnabled(true); dButton.setEnabled(true); eButton.setEnabled(true); fButton.setEnabled(true);
		
			//convert the number to hex, save it to num, then place it into the text box
			num = Integer.toHexString(numConv);
			numArea.setText(num);
			hexOn = true;

		}
		
		if (e.getSource() == oct) {
			numConv = Integer.parseInt(Conversions.toDec(numArea.getText(), hexOn, binOn, octOn));
			
			//turn the system flags off, and enable/re-enable the appropriate buttons
			hexOn = false; binOn = false; decOn = false;	
			twoButton.setEnabled(true); threeButton.setEnabled(true); fourButton.setEnabled(true); fiveButton.setEnabled(true); sixButton.setEnabled(true); sevenButton.setEnabled(true); eightButton.setEnabled(true); nineButton.setEnabled(false);
			aButton.setEnabled(false); bButton.setEnabled(false); cButton.setEnabled(false); dButton.setEnabled(false); eButton.setEnabled(false); fButton.setEnabled(false);
		
			//convert the number to a binary string, save it to num, then place it into the text box
			num = Integer.toOctalString(numConv);
			numArea.setText(num);
			octOn = true;
			
			}
		
		if (e.getSource() == bin) {
			numConv = Integer.parseInt(Conversions.toDec(numArea.getText(), hexOn, binOn, octOn));
			
			//turn the system flags off, and enable/re-enable the appropriate buttons
			hexOn = false; octOn = false; decOn = false;	
			twoButton.setEnabled(false); threeButton.setEnabled(false); fourButton.setEnabled(false); fiveButton.setEnabled(false); sixButton.setEnabled(false); sevenButton.setEnabled(false); eightButton.setEnabled(false); nineButton.setEnabled(false);
			aButton.setEnabled(false); bButton.setEnabled(false); cButton.setEnabled(false); dButton.setEnabled(false); eButton.setEnabled(false); fButton.setEnabled(false);
			
			//convert the number to a binary string, save it to num, then place it into the text box
			num = Integer.toBinaryString(numConv);
			numArea.setText(num);
			binOn = true;
			
			
	}
}

	public void openAbout() {
		About about = new About();
		about.setVisible(true);
		about.pack();
		about.setSize(700,600);
	}
}
