package com.estgp.calculator;


// * Autor - Nuno Matos
// * Nº - 17453

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mButtonSum;
    Button mButtonSubtraction;
    Button mButtonDivision;
    Button mButtonMultiply;
    Button mButtonReset;

    EditText mOperand1;
    EditText mOperand2;

    TextView mResult;

    private float operand1 = (float) 0.0;
    private float operand2 = (float) 0.0;

    private float result = (float) 0.0;

    public static String LOG_TAG = "myTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Buttons
        mButtonSum = (Button) findViewById(R.id.btn_sum);
        mButtonSubtraction = (Button) findViewById(R.id.btn_sub);
        mButtonDivision = (Button) findViewById(R.id.btn_div);
        mButtonMultiply = (Button) findViewById(R.id.btn_multi);
        mButtonReset = (Button) findViewById(R.id.btn_reset);

        //EditTexts - Operands
        mOperand1 = (EditText) findViewById(R.id.et_operand1);
        mOperand2 = (EditText) findViewById(R.id.et_operand2);

        //Result
        mResult = (TextView) findViewById(R.id.tv_result);

        //Buttons listeners
        mButtonSum.setOnClickListener(this);
        mButtonSubtraction.setOnClickListener(this);
        mButtonDivision.setOnClickListener(this);
        mButtonMultiply.setOnClickListener(this);
        mButtonReset.setOnClickListener(this);

    }
    //-----------------------------------------------------------------
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putFloat("result", result);
        super.onSaveInstanceState(outState);
    }
    //-----------------------------------------------------------------
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        result = savedInstanceState.getFloat("result");
        if (mResult != null){
            mResult.setText(Float.toString(result));
        }
    }
    //-----------------------------------------------------------------
    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btn_sum:
                if (mOperand1.getText().toString().length() == 0 || mOperand2.getText().toString().length() == 0) {
                    Toast.makeText(this, R.string.empty_fields, Toast.LENGTH_SHORT).show();
                    return;
                }
                sum();
                Log.d(LOG_TAG, "Result = " + sum());
                break;
            case R.id.btn_sub:
                if (mOperand1.getText().toString().length() == 0 || mOperand2.getText().toString().length() == 0) {
                    Toast.makeText(this, R.string.empty_fields, Toast.LENGTH_SHORT).show();
                    return;
                }
                subtraction();
                Log.d(LOG_TAG, "Result = " + subtraction());
                break;
            case R.id.btn_div:
                if (mOperand1.getText().toString().length() == 0 || mOperand2.getText().toString().length() == 0) {
                    Toast.makeText(this, R.string.empty_fields, Toast.LENGTH_SHORT).show();
                    return;
                }
                division();
                Log.d(LOG_TAG, "Result = " + division());
                break;
            case R.id.btn_multi:
                if (mOperand1.getText().toString().length() == 0 || mOperand2.getText().toString().length() == 0) {
                    Toast.makeText(this, R.string.empty_fields, Toast.LENGTH_SHORT).show();
                    return;
                }
                multiply();
                Log.d(LOG_TAG, "Result = " + multiply());
                break;
            case R.id.btn_reset:
                reset();
                Log.d(LOG_TAG, "RESET");
                break;
        }
    }
    //-----------------------------------------------------------------
    //função soma
    public float sum(){
        operand1 = Float.parseFloat(mOperand1.getText().toString());
        operand2 = Float.parseFloat(mOperand2.getText().toString());
        result = operand1 + operand2;
        mResult.setText(String.valueOf(result));
        return result;
    }
    //-----------------------------------------------------------------
    //função subtracção
    public float subtraction(){
        operand1 = Float.parseFloat(mOperand1.getText().toString());
        operand2 = Float.parseFloat(mOperand2.getText().toString());
        result = operand1 - operand2;
        mResult.setText(String.valueOf(result));
        return result;
    }
    //-----------------------------------------------------------------
    //função divisão
    public float division(){
        operand1 = Float.parseFloat(mOperand1.getText().toString());
        operand2 = Float.parseFloat(mOperand2.getText().toString());
        if (operand2 == 0 || operand2 == 0.0){
            Toast.makeText(this, R.string.divide_by_zero, Toast.LENGTH_SHORT).show();
        }
        result = operand1 / operand2;
        mResult.setText(String.valueOf(result));
        return result;
    }
    //-----------------------------------------------------------------
    //função multiplicação
    public float multiply(){
        operand1 = Float.parseFloat(mOperand1.getText().toString());
        operand2 = Float.parseFloat(mOperand2.getText().toString());
        result = operand1 * operand2;
        mResult.setText(String.valueOf(result));
        return result;
    }
    //-----------------------------------------------------------------
    //função reset
    public void reset(){
        mOperand1.getText().clear();
        mOperand2.getText().clear();
        result = (float) 0.0;
        mResult.setText("0.0");
    }
}
