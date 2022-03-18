package com.example.mobdevcw1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.*

    //Numbers of the left
    var variable1A = (1..21).random();
    var variable2A = (1..21).random();
    var variable3A = (1..21).random();
    var variable4A = (1..21).random();

    //Numbers of the right
    var variable1B = (1..21).random();
    var variable2B = (1..21).random();
    var variable3B = (1..21).random();
    var variable4B = (1..21).random();

    var gen: Random = Random()

    //Random Operator
    var operators = mutableListOf<String>("+", "-", "*", "/");
    var rdmOperator = operators.size;

    //Random arithmetic expression for two terms
    var twoTermsExpressionA = variable1A.toString() + operators[gen.nextInt(rdmOperator)] + variable2A.toString();
    var twoTermsExpressionB = variable1B.toString() + operators[gen.nextInt(rdmOperator)] + variable2B.toString();

    //Random arithmetic expression for three terms
    var threeTermsExpressionA = "(" + variable1A.toString() + operators[gen.nextInt(rdmOperator)] + variable2A.toString() + ")" + operators[gen.nextInt(rdmOperator)] + variable3A;
    var threeTermsExpressionB = "(" + variable1B.toString() + operators[gen.nextInt(rdmOperator)] + variable2B.toString() + ")" + operators[gen.nextInt(rdmOperator)] + variable3B;

    //Random arithmetic expression for four terms
    var fourTermsExpressionA = "("+"(" + variable1A.toString() + operators[gen.nextInt(rdmOperator)] + variable2A.toString() + ")" + operators[gen.nextInt(rdmOperator)] + variable3A + ")" + operators[gen.nextInt(rdmOperator)] + variable4A;
    var fourTermsExpressionB = "("+"(" + variable1B.toString() + operators[gen.nextInt(rdmOperator)] + variable2B.toString() + ")" + operators[gen.nextInt(rdmOperator)] + variable3B + ")" + operators[gen.nextInt(rdmOperator)] + variable4B;

    //Text view to display expressions
    lateinit var leftExpression : TextView;
    lateinit var rightExpression : TextView;

    //


class GameScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        leftExpression = findViewById<TextView>(R.id.textView);
        rightExpression = findViewById<TextView>(R.id.textView3);



        generateFourTermsA();
        generateFourTermsB();
    }

    fun generateOnetermA(){
        leftExpression.setText(variable1A.toString());

    }

    fun generateTwoTermsA(){
        leftExpression.setText(twoTermsExpressionA);

    }

    fun generateThreeTermsA(){
        leftExpression.setText(threeTermsExpressionA);

    }

    fun generateFourTermsA(){
        leftExpression.setText(fourTermsExpressionA);

    }

    fun generateOnetermB(){
        rightExpression.setText(variable1B.toString());

    }

    fun generateTwoTermsB(){
        rightExpression.setText(twoTermsExpressionB);

    }

    fun generateThreeTermsB(){
        rightExpression.setText(threeTermsExpressionB);

    }

    fun generateFourTermsB(){
        rightExpression.setText(fourTermsExpressionB);

    }


}