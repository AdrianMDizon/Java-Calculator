
public class Conversions {
	
	public static String decToBinary(String num) {
		
		//convert the decimal value into binary, and make a copy 
		int numConv = Integer.parseInt(num);
		String binary = Integer.toBinaryString(numConv);
//		String binaryOriginal = binary;
		
		if(binary.length() > 4) {
			int find = binary.length() - 4;
			
			for(int i = 0 ; i < binary.length() / 4; i++) {
				if(binary.charAt(find) != ' ') {
					String binary1 = binary.substring(0,find);
					String binary2 = binary.substring(find,binary.length());
					binary = binary1 + " " + binary2;
				}
				
				if(find > 4)
					find = find - 4;
			}
		}
//		if(binaryOriginal.length() % 4 != 0) {
//			while (binaryOriginal.length() % 4 != 0) {
//				binary = "0" + binary;
//				binaryOriginal = "0" + binaryOriginal;
//			}
//		}
		return binary;
	}

	public static String toDec(String num, boolean hexOn, boolean binOn, boolean octOn) {
		String dec = "";
		int numConv;
		
		//convert the dec value from num into the appropriate numbering system
		if (hexOn == true)
			numConv = Integer.parseInt(num, 16);
		else if (binOn == true)
			numConv = Integer.parseInt(num, 2);
		else if (octOn == true)
			numConv = Integer.parseInt(num, 8);
		else
			numConv = Integer.parseInt(num);
		
		//place the converted number into a string, then return it
		dec = Integer.toString(numConv);
		return dec;
	}

	public static String formatBinary(String num, String binaryOriginal, boolean hexOn, boolean binOn, boolean octOn) {
		
		int numConv = Integer.parseInt(toDec(num, hexOn, binOn, octOn));
		String binary = decToBinary(Integer.toString(numConv));
		binary = binaryOriginal.substring(0, binaryOriginal.length() - binary.length()) + binary;
		
		if(binary.contains("\n") == false)
			binary = binary.substring(0,40) + "\n" + binary.substring(40, binary.length());
		
		return binary;
	}
}
