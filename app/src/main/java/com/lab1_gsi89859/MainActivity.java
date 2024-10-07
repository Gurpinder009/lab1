package com.lab1_gsi89859;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import org.mariuszgromada.math.mxparser.Expression;


public class MainActivity extends AppCompatActivity {
    private EditText displayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        displayText = findViewById(R.id.displayText);
        displayText.setShowSoftInputOnFocus(false);

        displayText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display_text).equals(displayText.getText().toString())) {
                    displayText.setText("");
                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void updateText(String strToAdd) {
        String oldString = displayText.getText().toString();
        int cursorPos = displayText.getSelectionStart();
        String leftStr = oldString.substring(0, cursorPos);
        String rightStr = oldString.substring(cursorPos);
        if (getString(R.string.display_text).equals(displayText.getText().toString())) {
            displayText.setText(strToAdd);
            displayText.setSelection(cursorPos + 1);

        } else {
            displayText.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            displayText.setSelection(cursorPos + 1);
        }

    }


    public void zeroBtn(View view) {
        updateText("0");

    }


    public void plusMinusBtn(View view) {
        updateText("-");
    }

    public void pointBtn(View view) {
        updateText(".");
    }


    public void equalsBtn(View view) {
        Log.i("info", getString(R.string.display_text));
        if (!displayText.getText().toString().equals(getString(R.string.display_text))) {

            String userExp = displayText.getText().toString();
            userExp = userExp.replaceAll("÷", "/");
            userExp = userExp.replaceAll("×", "*");
            Expression exp = new Expression(userExp);
            String result = String.valueOf(exp.calculate());
            displayText.setText(result);
            displayText.setSelection(result.length());
        } else {
            displayText.setText("0");
            displayText.setSelection(1);
        }

    }

    public void plusBtn(View view) {
        updateText("+");
    }


    public void threeBtn(View view) {
        updateText("3");

    }

    public void twoBtn(View view) {
        updateText("2");


    }

    public void oneBtn(View view) {
        updateText("1");

    }

    public void minusBtn(View view) {
        updateText("-");
    }

    public void sixBtn(View view) {
        updateText("6");
    }

    public void fiveBtn(View view) {
        updateText("5");
    }

    public void fourBtn(View view) {
        updateText("4");
    }

    public void multiplyBtn(View view) {
        updateText("×");
    }

    public void nineBtn(View view) {
        updateText("9");
    }


    public void eightBtn(View view) {
        updateText("8");
    }

    public void sevenBtn(View view) {
        updateText("7");
    }


    public void divideBtn(View view) {
        updateText("÷");
    }

    public void exponentBtn(View view) {
        updateText("^");
    }


    public void parenthesisBtn(View view) {
        int cursorPos = displayText.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = displayText.getText().length();
        for (int i = 0; i < cursorPos; i++) {
            if (displayText.getText().toString().charAt(i) == '(') {
                openPar += 1;
            }
            if (displayText.getText().toString().charAt(i) == ')') {
                closedPar += 1;
            }

        }
        if (openPar == closedPar || displayText.getText().toString().substring(textLen - 1).equals("(")) {
            updateText("(");
        } else if (closedPar < openPar && !displayText.getText().toString().substring(textLen - 1).equals("(")) {
            updateText(")");
        }
        displayText.setSelection(cursorPos + 1);

    }

    public void clearBtn(View view) {
        displayText.setText("");
    }

    public void backspaceBtn(View view) {
        int cursorPosition = displayText.getSelectionStart();
        int textLen = displayText.getText().length();
        if (cursorPosition != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) displayText.getText();
            selection.replace(cursorPosition - 1, cursorPosition, "");
            displayText.setText(selection);
            displayText.setSelection(cursorPosition - 1);
        }
    }


}