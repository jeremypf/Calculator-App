package com.jeremyf.calculator1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	EditText output;
	TextView output2;
	boolean positive = true;
	double num1 = 0;
	double num2 = 0;
	double num3 = 0;
	char sign = '0';
	boolean signPressed = false;
	boolean runComplexCalc = false;
	char complexSign = '0';
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		output =(EditText) findViewById(R.id.Output);
		output2 = (TextView) findViewById(R.id.Output2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	//String text = output.getText().toString();
	
	public void pressNum(String num){
		
		if(output.getText().toString().equals("Error")){//clears calculator
			clear();
		}
		
		String current = output.getText().toString();
		if(current.equals("0")){
			output.setText(num);
		}else if (current.equals("-0")){
			output.setText("-" + num);
		}else if(signPressed){
			if(sign=='0'){
				output2.setText("");
			}
			signPressed = false;
			output.setText(num);
			if(runComplexCalc){
				complexCalc();
			}
			addOutput2();
		}else{
			if(current.contains(".")){
				if((!positive && current.length()<19)|| (positive && current.length()<18)){
					output.setText(current + num);
				}
			}else if((!positive && current.length()<18)|| (positive && current.length()<17)){
				output.setText(current + num);
			}
		}
	}
	
	public void press0(View view){
		pressNum("0");
	}
	public void press1(View view){
		pressNum("1");
	}
	public void press2(View view){
		pressNum("2");
	}
	public void press3(View view){
		pressNum("3");
	}
	public void press4(View view){
		pressNum("4");
	}
	public void press5(View view){
		pressNum("5");
	}
	public void press6(View view){
		pressNum("6");
	}
	public void press7(View view){
		pressNum("7");
	}
	public void press8(View view){
		pressNum("8");
	}
	public void press9(View view){
		pressNum("9");
	}
	
	public void pressDec(View view){
		if(output.getText().toString().equals("Error")){//clears calculator
			clear();
		}
		
		String current = output.getText().toString();
		if(signPressed){
			signPressed = false;
			output.setText("0.");
			if(runComplexCalc){
				complexCalc();
			}
			addOutput2();
		}else if(!current.contains(".")){
			output.setText(current + ".");
		}
	}
	
	public void changeSign(View view){
		if(output.getText().toString().equals("Error")){//clears calculator
			clear();
		}
		
		/*if(signPressed){
			signPressed = false;
			output.setText("-0");
			positive = false;
			if(runComplexCalc){
				complexCalc();
			}
			addOutput2();
		}else{*/
			String current = output.getText().toString();
			if(current.charAt(0)=='-'){
				output.setText(current.substring(1));
				positive = true;
			}else{
				output.setText("-" + current);
				positive = false;
			}
		//}
	}
	
	public void fix(View view){
		if(!signPressed){
			String current = output.getText().toString();
			if(current.length()==1){
				output.setText("0");
			}else if(current.length()==2 && !positive){
				output.setText("-0");
			}else{
				output.setText(current.substring(0,current.length()-1));
			}
		}
	}
	
	
	public void add(View view){//
		pressSign();
		sign = '+';
	}
	public void subtract(View view){
		pressSign();
		sign = '-';
	}
	public void multiply(View view){
		pressSign();
		sign = '*';
	}
	public void divide(View view){
		pressSign();
		sign = '/';
	}
	
	public void pressSign() {
		if(output.getText().toString().equals("Error")){//clears calculator
			clear();
		}
		
		if (signPressed && sign != '0') {
			sign = '1';
		}
		signPressed = true;
		if (sign == '0') {
			num1 = Double.parseDouble(output.getText().toString());
		} else if(sign == '1'){
			
		} else {
			num2 = Double.parseDouble(output.getText().toString());
			runComplexCalc = true;
			complexSign = sign;
		}
	}
	
	public void runClear(View view){
		clear();
	}
	
	public void clear(){
		output.setText("0");
		output2.setText("");
		sign = '0';
		complexSign = '0';
		positive = true;
		num1 = 0;
		num2 = 0;
		num3 = 0;
		signPressed = false;
		runComplexCalc = false;
	}
	
	public void calculate(View view) {
		num2 = Double.parseDouble(output.getText().toString());
		
		if(output.getText().toString().equals("Error")){//clears calculator
			clear();
		}
		
		/*if(signPressed && sign!='0'){
			output.setText("Error");
		}else */if (sign!='0'){
			switch (sign) {
			case '+':
				num3 = num1 + num2;
				break;
			case '-':
				num3 = num1 - num2;
				break;
			case '*':
				num3 = num1 * num2;
				break;
			case '/':
				num3 = num1 / num2;
				break;
			}
			
			String answer = String.valueOf(num3);
			String num2String = String.valueOf(num2);
			
			if((num2String.substring(num2String.length()-2, num2String.length())).equals(".0")){
				num2String = num2String.substring(0,num2String.length()-2);
			}
			
			if((answer.substring(answer.length()-2, answer.length())).equals(".0")){
				answer = answer.substring(0,answer.length()-2);
			}
			
			output.setText(answer);
			
			if(num2 >= 0){
				output2.setText(output2.getText().toString() + " " + num2String + " =");
			}else{
				output2.setText(output2.getText().toString() + " (" + num2String + ") =");				
			}
			num1 = Double.parseDouble(output.getText().toString());
			sign = '0';
			signPressed = true;
		}
		
		
		
	}
	
	public void complexCalc(){//runs when equation has more than two numbers
		switch (complexSign) {
		case '+':
			num1 = num1 + num2;
			break;
		case '-':
			num1 = num1 - num2;
			break;
		case '*':
			num1 = num1 * num2;
			break;
		case '/':
			num1 = num1 / num2;
			break;
		}
		runComplexCalc = false;
	}
	
	public void addOutput2() {
		
		if (sign != '0') {
			String num1String = String.valueOf(num1);
			
			if((num1String.substring(num1String.length()-2, num1String.length())).equals(".0")){
				num1String = num1String.substring(0,num1String.length()-2);
			}
			
			if (num1 >= 0) {
				output2.setText(num1String + " " + sign);
			} else {
				output2.setText("(" + num1String + ") " + sign);
			}
		}

	}

	public void square(View view){
		if(output.getText().toString().equals("Error")){//clears calculator
			clear();
		}
		
		if(signPressed){
			if(sign=='0'){
				num3 = Math.pow(Double.parseDouble(output.getText().toString()),2);
			}
		}else{
			num3 = Math.pow(Double.parseDouble(output.getText().toString()),2);
		}
		
		String num3String = String.valueOf(num3);
		if((num3String.substring(num3String.length()-2, num3String.length())).equals(".0")){
			num3String = num3String.substring(0,num3String.length()-2);
		}
		
		if(sign== '0'){
			if(num3>=0){
				output2.setText(output.getText() + "^2 =");
			}else{
				output2.setText("(" + output.getText() + ")^2 =");
			}
		}
		
		if(signPressed){
			if(sign=='0'){
				output.setText(num3String);
			}
		}else{
			output.setText(num3String);
		}
		signPressed = true;
	}
	
	public void root(View view){
		if(output.getText().toString().equals("Error")){//clears calculator
			clear();
		}
		
		if(signPressed){
			if(sign=='0'){
				num3 = Math.sqrt(Double.parseDouble(output.getText().toString()));
			}
		}else{
			num3 = Math.sqrt(Double.parseDouble(output.getText().toString()));
		}
		
		String num3String = String.valueOf(num3);
		if((num3String.substring(num3String.length()-2, num3String.length())).equals(".0")){
			num3String = num3String.substring(0,num3String.length()-2);
		}
		
		if(sign== '0'){
			output2.setText("(sqrt)" + output.getText() +" =");
		}
		
		if(num3String == "NaN"){
			output.setText("Error");
		}else{
			if(signPressed){
				if(sign=='0'){
					output.setText(num3String);
				}
			}else{
				output.setText(num3String);
			}		
		}
		signPressed = true;
	}
	
}
