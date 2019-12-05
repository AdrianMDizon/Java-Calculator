
public class Arithmetic {

	public static String add(int num1, int num2, boolean hexOn, boolean binOn, boolean octOn) {
		int result = 0;
		String numConv, first, second;
		
		first = Integer.toString(num1);
		second = Integer.toString(num2);
		
		first = Conversions.toDec(first, hexOn, binOn, octOn);
		second = Conversions.toDec(second, hexOn, binOn, octOn);
		
		num1 = Integer.parseInt(first);
		num2 = Integer.parseInt(second);
		
		result = num1 + num2;
		
		if(binOn == true)
			numConv = Integer.toBinaryString(result);
		else if (hexOn == true)
			numConv = Integer.toHexString(result);
		else if(octOn == true)
			numConv = Integer.toOctalString(result);
		else
			numConv = Integer.toString(result);
		
		return numConv;
	}

	public static String subtract(int num1, int num2, boolean hexOn, boolean binOn, boolean octOn) {
		int result = 0;
		String numConv, first, second;
		
		first = Integer.toString(num1);
		second = Integer.toString(num2);
		
		first = Conversions.toDec(first, hexOn, binOn, octOn);
		second = Conversions.toDec(second, hexOn, binOn, octOn);
		
		num1 = Integer.parseInt(first);
		num2 = Integer.parseInt(second);
		
		result = num1 - num2;
		
		if(binOn == true)
			numConv = Integer.toBinaryString(result);
		else if (hexOn == true)
			numConv = Integer.toHexString(result);
		else if(octOn == true)
			numConv = Integer.toOctalString(result);
		else
			numConv = Integer.toString(result);
		
		return numConv;
	}
	
	public static String multiply(int num1, int num2, boolean hexOn, boolean binOn, boolean octOn) {
		int result = 0;
		String numConv, first, second;
		
		first = Integer.toString(num1);
		second = Integer.toString(num2);
		
		first = Conversions.toDec(first, hexOn, binOn, octOn);
		second = Conversions.toDec(second, hexOn, binOn, octOn);
		
		num1 = Integer.parseInt(first);
		num2 = Integer.parseInt(second);
		
		result = num1 * num2;
		
		if(binOn == true)
			numConv = Integer.toBinaryString(result);
		else if (hexOn == true)
			numConv = Integer.toHexString(result);
		else if(octOn == true)
			numConv = Integer.toOctalString(result);
		else
			numConv = Integer.toString(result);
		
		return numConv;
	}
	
	public static String divide(int num1, int num2, boolean hexOn, boolean binOn, boolean octOn) {
		int result = 0;
		String numConv, first, second;
		
		first = Integer.toString(num1);
		second = Integer.toString(num2);
		
		first = Conversions.toDec(first, hexOn, binOn, octOn);
		second = Conversions.toDec(second, hexOn, binOn, octOn);
		
		num1 = Integer.parseInt(first);
		num2 = Integer.parseInt(second);
		
		result = num1 / num2;
		
		if(binOn == true)
			numConv = Integer.toBinaryString(result);
		else if (hexOn == true)
			numConv = Integer.toHexString(result);
		else if(octOn == true)
			numConv = Integer.toOctalString(result);
		else
			numConv = Integer.toString(result);
		
		return numConv;
	}
	
	public static String mod(int num1, int num2, boolean hexOn, boolean binOn, boolean octOn) {
		int result = 0;
		String numConv, first, second;
		
		first = Integer.toString(num1);
		second = Integer.toString(num2);
		
		first = Conversions.toDec(first, hexOn, binOn, octOn);
		second = Conversions.toDec(second, hexOn, binOn, octOn);
		
		num1 = Integer.parseInt(first);
		num2 = Integer.parseInt(second);
		
		result = num1 % num2;
		
		if(binOn == true)
			numConv = Integer.toBinaryString(result);
		else if (hexOn == true)
			numConv = Integer.toHexString(result);
		else if(octOn == true)
			numConv = Integer.toOctalString(result);
		else
			numConv = Integer.toString(result);
		
		return numConv;
	}
	
	public static String equals(int num1, int num2, boolean hexOn, boolean binOn, boolean octOn, boolean adding, boolean subtracting, boolean multiplying, boolean dividing, boolean modulating) {
		String numConv;
		
		if (adding)
			numConv = add(num1, num2, hexOn, binOn, octOn);
		else if (subtracting)
			numConv = subtract(num1, num2, hexOn, binOn, octOn);
		else if (multiplying)
			numConv = multiply(num1, num2, hexOn, binOn, octOn);
		else if (dividing)
			numConv = divide(num1, num2, hexOn, binOn, octOn);
		else
			numConv = mod(num1, num2, hexOn, binOn, octOn);

		return numConv;
		
	}
}
