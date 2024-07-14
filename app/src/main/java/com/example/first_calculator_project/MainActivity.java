package com.example.first_calculator_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button plus, minus, divide, multiply, zero, one, two, three, four, five, six, seven, eight, nine, dot, equalto, cancel;
    TextView numbering;
    String currentInput = "";
    String operator = "";
    double firstOperand = 0.0;
    boolean operatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numbering = findViewById(R.id.numbering);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        divide = findViewById(R.id.divide);
        multiply = findViewById(R.id.multiply);
        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        dot = findViewById(R.id.dot);
        equalto = findViewById(R.id.equalto);
        cancel = findViewById(R.id.cancel);

        zero.setOnClickListener(new NumberClickListener("0"));
        one.setOnClickListener(new NumberClickListener("1"));
        two.setOnClickListener(new NumberClickListener("2"));
        three.setOnClickListener(new NumberClickListener("3"));
        four.setOnClickListener(new NumberClickListener("4"));
        five.setOnClickListener(new NumberClickListener("5"));
        six.setOnClickListener(new NumberClickListener("6"));
        seven.setOnClickListener(new NumberClickListener("7"));
        eight.setOnClickListener(new NumberClickListener("8"));
        nine.setOnClickListener(new NumberClickListener("9"));
        dot.setOnClickListener(new NumberClickListener("."));

        plus.setOnClickListener(new OperationClickListener("+"));
        minus.setOnClickListener(new OperationClickListener("-"));
        multiply.setOnClickListener(new OperationClickListener("*"));
        divide.setOnClickListener(new OperationClickListener("/"));
        equalto.setOnClickListener(new EqualClickListener());

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbering.setText("");
                currentInput = "";
                operator = "";
                firstOperand = 0.0;
                operatorPressed = false;
            }
        });
    }

    private class NumberClickListener implements View.OnClickListener {
        private String value;

        public NumberClickListener(String value) {
            this.value = value;
        }

        @Override
        public void onClick(View v) {
            try {
                if (operatorPressed) {
                    currentInput = value;
                    operatorPressed = false;
                } else {
                    currentInput += value;
                }
                numbering.append(value);
            } catch (Exception e) {
                numbering.setText("Error");
            }
        }
    }

    private class OperationClickListener implements View.OnClickListener {
        private String op;

        public OperationClickListener(String op) {
            this.op = op;
        }

        @Override
        public void onClick(View v) {
            try {
                if (!currentInput.isEmpty()) {
                    firstOperand = Double.parseDouble(currentInput);
                    operator = op;
                    operatorPressed = true;
                    numbering.append(" " + op + " ");
                }
            } catch (NumberFormatException e) {
                numbering.setText("Invalid Number");
            } catch (Exception e) {
                numbering.setText("Error");
            }
        }
    }

    private class EqualClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                if (!currentInput.isEmpty() && !operator.isEmpty()) {
                    double secondOperand = Double.parseDouble(currentInput);
                    double result = 0.0;

                    switch (operator) {
                        case "+":
                            result = firstOperand + secondOperand;
                            break;
                        case "-":
                            result = firstOperand - secondOperand;
                            break;
                        case "*":
                            result = firstOperand * secondOperand;
                            break;
                        case "/":
                            if (secondOperand != 0) {
                                result = firstOperand / secondOperand;
                            } else {
                                numbering.setText("Error");
                                return;
                            }
                            break;
                        default:
                            numbering.setText("Error");
                            return;
                    }

                    if (result == (int) result) {
                        numbering.setText(String.valueOf((int) result));
                    } else {
                        numbering.setText(String.valueOf(result));
                    }

                    currentInput = String.valueOf(result);
                    operator = "";
                    operatorPressed = false;
                }
            } catch (NumberFormatException e) {
                numbering.setText("Invalid Number");
            } catch (Exception e) {
                numbering.setText("Error");
            }
        }
    }
}
