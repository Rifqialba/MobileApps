package com.example.myprofileapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class KalkulatorFragment extends Fragment {

    private TextView tvDisplay, tvHistory;
    private String currentInput = "0";
    private String currentOperator = "";
    private String previousInput = "";
    private boolean resetOnNextInput = false;

    // Tombol-tombol kalkulator
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private Button btnAdd, btnSubtract, btnMultiply, btnDivide;
    private Button btnEquals, btnClear, btnDecimal, btnBackspace;
    private Button btnPower, btnSqrt;

    public KalkulatorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kalkulator, container, false);

        initViews(view);
        setupButtonListeners();

        return view;
    }

    private void initViews(View view) {
        tvDisplay = view.findViewById(R.id.tv_display);
        tvHistory = view.findViewById(R.id.tv_history);

        // Number buttons
        btn0 = view.findViewById(R.id.btn_0);
        btn1 = view.findViewById(R.id.btn_1);
        btn2 = view.findViewById(R.id.btn_2);
        btn3 = view.findViewById(R.id.btn_3);
        btn4 = view.findViewById(R.id.btn_4);
        btn5 = view.findViewById(R.id.btn_5);
        btn6 = view.findViewById(R.id.btn_6);
        btn7 = view.findViewById(R.id.btn_7);
        btn8 = view.findViewById(R.id.btn_8);
        btn9 = view.findViewById(R.id.btn_9);

        // Operator buttons
        btnAdd = view.findViewById(R.id.btn_add);
        btnSubtract = view.findViewById(R.id.btn_subtract);
        btnMultiply = view.findViewById(R.id.btn_multiply);
        btnDivide = view.findViewById(R.id.btn_divide);
        btnEquals = view.findViewById(R.id.btn_equals);
        btnClear = view.findViewById(R.id.btn_clear);
        btnDecimal = view.findViewById(R.id.btn_decimal);
        btnBackspace = view.findViewById(R.id.btn_backspace);
        btnPower = view.findViewById(R.id.btn_power);
        btnSqrt = view.findViewById(R.id.btn_sqrt);
    }

    private void setupButtonListeners() {
        // Number buttons
        btn0.setOnClickListener(v -> appendNumber("0"));
        btn1.setOnClickListener(v -> appendNumber("1"));
        btn2.setOnClickListener(v -> appendNumber("2"));
        btn3.setOnClickListener(v -> appendNumber("3"));
        btn4.setOnClickListener(v -> appendNumber("4"));
        btn5.setOnClickListener(v -> appendNumber("5"));
        btn6.setOnClickListener(v -> appendNumber("6"));
        btn7.setOnClickListener(v -> appendNumber("7"));
        btn8.setOnClickListener(v -> appendNumber("8"));
        btn9.setOnClickListener(v -> appendNumber("9"));

        // Basic operators
        btnAdd.setOnClickListener(v -> setOperator("+"));
        btnSubtract.setOnClickListener(v -> setOperator("-"));
        btnMultiply.setOnClickListener(v -> setOperator("×"));
        btnDivide.setOnClickListener(v -> setOperator("÷"));

        // Advanced functions
        btnPower.setOnClickListener(v -> calculatePower());
        btnSqrt.setOnClickListener(v -> calculateSquareRoot());

        // Other functions
        btnEquals.setOnClickListener(v -> calculateResult());
        btnClear.setOnClickListener(v -> clearAll());
        btnDecimal.setOnClickListener(v -> appendDecimal());
        btnBackspace.setOnClickListener(v -> backspace());
    }

    private void appendNumber(String number) {
        if (resetOnNextInput) {
            currentInput = "0";
            resetOnNextInput = false;
        }

        if (currentInput.equals("0")) {
            currentInput = number;
        } else {
            currentInput += number;
        }

        updateDisplay();
    }

    private void appendDecimal() {
        if (resetOnNextInput) {
            currentInput = "0";
            resetOnNextInput = false;
        }

        if (!currentInput.contains(".")) {
            currentInput += ".";
        }

        updateDisplay();
    }

    private void setOperator(String operator) {
        if (!currentOperator.isEmpty()) {
            calculateResult();
        }

        previousInput = currentInput;
        currentOperator = operator;
        resetOnNextInput = true;

        tvHistory.setText(previousInput + " " + currentOperator);
    }

    private void calculateResult() {
        if (previousInput.isEmpty() || currentOperator.isEmpty()) {
            return;
        }

        double prev = Double.parseDouble(previousInput);
        double current = Double.parseDouble(currentInput);
        double result = 0;

        try {
            switch (currentOperator) {
                case "+":
                    result = prev + current;
                    break;
                case "-":
                    result = prev - current;
                    break;
                case "×":
                    result = prev * current;
                    break;
                case "÷":
                    if (current == 0) {
                        tvDisplay.setText("Error");
                        return;
                    }
                    result = prev / current;
                    break;
            }

            // Format hasil (hilangkan .0 jika bilangan bulat)
            if (result == (long) result) {
                currentInput = String.valueOf((long) result);
            } else {
                currentInput = String.valueOf(result);
            }

            tvHistory.setText(previousInput + " " + currentOperator + " " + current + " =");
            currentOperator = "";
            previousInput = "";
            resetOnNextInput = true;

            updateDisplay();

        } catch (Exception e) {
            tvDisplay.setText("Error");
        }
    }

    private void calculatePower() {
        try {
            double number = Double.parseDouble(currentInput);
            double result = number * number;

            if (result == (long) result) {
                currentInput = String.valueOf((long) result);
            } else {
                currentInput = String.valueOf(result);
            }

            tvHistory.setText(currentInput + "² =");
            resetOnNextInput = true;
            updateDisplay();

        } catch (Exception e) {
            tvDisplay.setText("Error");
        }
    }

    private void calculateSquareRoot() {
        try {
            double number = Double.parseDouble(currentInput);

            if (number < 0) {
                tvDisplay.setText("Error");
                return;
            }

            double result = Math.sqrt(number);

            if (result == (long) result) {
                currentInput = String.valueOf((long) result);
            } else {
                currentInput = String.valueOf(result);
            }

            tvHistory.setText("√" + number + " =");
            resetOnNextInput = true;
            updateDisplay();

        } catch (Exception e) {
            tvDisplay.setText("Error");
        }
    }

    private void clearAll() {
        currentInput = "0";
        previousInput = "";
        currentOperator = "";
        resetOnNextInput = false;
        tvHistory.setText("");
        updateDisplay();
    }

    private void backspace() {
        if (currentInput.length() > 1) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
        } else {
            currentInput = "0";
        }
        updateDisplay();
    }

    private void updateDisplay() {
        tvDisplay.setText(currentInput);
    }
}