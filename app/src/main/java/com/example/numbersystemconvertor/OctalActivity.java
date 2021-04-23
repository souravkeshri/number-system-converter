package com.example.numbersystemconvertor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class OctalActivity extends AppCompatActivity {

    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btnplus;
    Button btnminus;
    Button btnback;
    Button btnEquals;
    Button btnClear;
    Button btnA;
    Button btnB;
    Button btnC;
    Button btnD;
    Button btnE;
    Button btnF;
    Button buttonMultiplication;
    Button buttonHome;
    TextView textViewBinary;
    TextView textViewOctal;
    TextView textViewDecimal;
    TextView textViewHexaDecimal;
    TextView textViewQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_octal);
        btn0=findViewById(R.id.button0);
        btn1=findViewById(R.id.button1);
        btn2=findViewById(R.id.button2);
        btn3=findViewById(R.id.button3);
        btn4=findViewById(R.id.button4);
        btn5=findViewById(R.id.button5);
        btn6=findViewById(R.id.button6);
        btn7=findViewById(R.id.button7);
        btn8=findViewById(R.id.button8);
        btn9=findViewById(R.id.button9);
        btnplus=findViewById(R.id.buttonplus);
        btnminus=findViewById(R.id.buttonminus);
        btnback=findViewById(R.id.buttonback);
        btnEquals=findViewById(R.id.buttonEquals);
        btnClear=findViewById(R.id.buttonClear);
        btnA=findViewById(R.id.buttonA);
        btnB=findViewById(R.id.buttonB);
        btnC=findViewById(R.id.buttonC);
        btnD=findViewById(R.id.buttonD);
        btnE=findViewById(R.id.buttonE);
        btnF=findViewById(R.id.buttonF);
        buttonMultiplication=findViewById(R.id.buttonMultiplication);
        buttonHome=findViewById(R.id.buttonhome);
        textViewBinary=findViewById(R.id.textViewBinary);
        textViewOctal=findViewById(R.id.textViewOctal);
        textViewDecimal=findViewById(R.id.textViewDecimal);
        textViewHexaDecimal=findViewById(R.id.textViewHexaDecimal);
        textViewQuestion=findViewById(R.id.textViewQuestion);

    }

    public void numberPressed(View v)
    {
        Button b = (Button)v;
        String input=b.getText().toString();
        textViewOctal.setText(textViewOctal.getText().toString()+input);

    }
    public void clearBtn(View v)
    {
        textViewOctal.setText("");
        textViewBinary.setText("Binary :                        ");
        textViewDecimal.setText("Decimal :                      ");
        textViewHexaDecimal.setText("HexaDecimal :                   ");
        textViewQuestion.setVisibility(View.INVISIBLE);
    }
    public void trim(View v)
    {
        String str=textViewOctal.getText().toString();
        if(str!=null && str.length()>1) {
            textViewOctal.setText(str.substring(0, str.length() - 1));
        }
        else
        {
            textViewOctal.setText("");
            textViewBinary.setText("Binary :                        ");
            textViewDecimal.setText("Decimal :                      ");
            textViewHexaDecimal.setText("HexaDecimal :                   ");
            textViewQuestion.setVisibility(View.INVISIBLE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void result(View v)
    {
        String exp=textViewOctal.getText().toString();
        if(exp.equals(""))
        {
            textViewOctal.setText("");
            textViewBinary.setText("Binary :                        ");
            textViewDecimal.setText("Decimal :                      ");
            textViewHexaDecimal.setText("HexaDecimal :                   ");
            textViewQuestion.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(),"Enter Something!!",Toast.LENGTH_LONG).show();
        }
        else {
            ArrayList<String> operatorList = new ArrayList<String>();
            ArrayList<String> operandList = new ArrayList<String>();
            StringTokenizer st = new StringTokenizer(exp, "+-*/", true);
            while (st.hasMoreTokens()) {
                String token = st.nextToken();

                if ("+-/*".contains(token)) {
                    operatorList.add(token);
                } else {
                    operandList.add(token);
                }
            }
            ArrayList<String> operandsInDecimal = new ArrayList<String>();
            for (int i = 0; i < operandList.size(); i++) {
                operandsInDecimal.add(i, Integer.toString(Integer.parseInt(operandList.get(i), 8), 10));
            }

            ArrayList<String> DecExpression = new ArrayList<String>(operandsInDecimal.size());
            ArrayList<String> OctExpression = new ArrayList<String>(operandsInDecimal.size());
            if (operandsInDecimal.size() != (operatorList.size() + 1)) {
                textViewOctal.setText("");
                textViewBinary.setText("Binary :                        ");
                textViewDecimal.setText("Decimal :                      ");
                textViewHexaDecimal.setText("HexaDecimal :                   ");
                textViewQuestion.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),"Invalid Expression!!",Toast.LENGTH_LONG).show();
            } else {

                for (int i = 0, j = 0, k = 0; i < operandsInDecimal.size() + operatorList.size(); i++) {
                    if (i % 2 == 0)
                        DecExpression.add(operandsInDecimal.get(j++));
                    else
                        DecExpression.add(operatorList.get(k++));
                }

                for (int i = 0, j = 0, k = 0; i < operandList.size() + operatorList.size(); i++) {
                    if (i % 2 == 0)
                        OctExpression.add(operandList.get(j++));
                    else
                        OctExpression.add(operatorList.get(k++));
                }

                StringBuilder myStringA = new StringBuilder();
                StringBuilder myStringB = new StringBuilder();
                String expressionDecimal = new String();
                String expressionOctal = new String();
                for (String i : DecExpression) {
                    myStringA.append(i);
                }
                expressionDecimal = myStringA.toString();
                for (String i : OctExpression) {
                    myStringB.append(i);
                }
                expressionOctal = myStringB.toString();


                String DecimalResult = (Integer.toString((int) eval(expressionDecimal)));

                textViewQuestion.setText(expressionOctal);
                textViewQuestion.setVisibility(View.VISIBLE);

                textViewBinary.setText("Binary       :  " + Integer.toString(Integer.parseInt(DecimalResult, 10), 2));
                textViewDecimal.setText("Decimal     :  " + DecimalResult);
                textViewOctal.setText(Integer.toString(Integer.parseInt(DecimalResult, 10), 8));
                textViewHexaDecimal.setText("HexaDecimal :  " + Integer.toString(Integer.parseInt(DecimalResult, 10), 16).toUpperCase());

            }
        }

    }
    public void home(View v)
    {
        Intent intent=new Intent(OctalActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

    public void onBackPressed() {
        Intent intent=new Intent(OctalActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}