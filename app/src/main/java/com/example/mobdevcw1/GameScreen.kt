package com.example.mobdevcw1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*


class GameScreen : AppCompatActivity() {

    //Initialise to prevent repeated numbers when user clicks back and then clicks new game again
    //Numbers of the left
    var variable1L = 0;
    var variable2L = 0;
    var variable3L = 0;
    var variable4L = 0;

    //Numbers of the right
    var variable1R = 0;
    var variable2R = 0;
    var variable3R = 0;
    var variable4R = 0;

    //Random arithmetic expression for two terms
    var twoTermsExpressionL = " ";
    var twoTermsExpressionR = " ";

    //Random arithmetic expression for three terms
    var threeTermsExpressionL = " ";
    var threeTermsExpressionR = " ";

    //Random arithmetic expression for four terms
    var fourTermsExpressionB = " ";
    var fourTermsExpressionL = " ";


    //Text view to display expressions
    lateinit var leftExpression: TextView;
    lateinit var rightExpression: TextView;
    lateinit var answerDisplay: TextView;

    var gen: Random = Random()

    //Score
    companion object {
        var correct: Int = 0
        var incorrect: Int = 0
    }

    //Answer to expression
    var leftEval: Int = 0;
    var rightEval: Int = 0;


    //Random Operator
    var operators = mutableListOf<String>("+", "-", "*", "/");
    var rdmOperator = operators.size;

    //Random operator for each expression on each side
    // Left side
    var operatorL2Terms = operators[gen.nextInt(rdmOperator)];
    var operatorL3Terms1 = operators[gen.nextInt(rdmOperator)];
    var operatorL3Terms2 = operators[gen.nextInt(rdmOperator)];
    var operatorL4Terms1 = operators[gen.nextInt(rdmOperator)];
    var operatorL4Terms2 = operators[gen.nextInt(rdmOperator)];
    var operatorL4Terms3 = operators[gen.nextInt(rdmOperator)];

    //Right side
    var operatorR2Terms = operators[gen.nextInt(rdmOperator)];
    var operatorR3Terms1 = operators[gen.nextInt(rdmOperator)];
    var operatorR3Terms2 = operators[gen.nextInt(rdmOperator)];
    var operatorR4Terms1 = operators[gen.nextInt(rdmOperator)];
    var operatorR4Terms2 = operators[gen.nextInt(rdmOperator)];
    var operatorR4Terms3 = operators[gen.nextInt(rdmOperator)];

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        leftExpression = findViewById<TextView>(R.id.textView);
        rightExpression = findViewById<TextView>(R.id.textView3);
        answerDisplay = findViewById<TextView>(R.id.textAnswer);

        var greaterThanButton = findViewById<Button>(R.id.greaterButton);
        var equalsToButton = findViewById<Button>(R.id.equalsButton);
        var lessThanButton = findViewById<Button>(R.id.lessButton);

        //Initialise
        initialise();

        //Call function 'randomTermsExpression'
        randomTermsExpression();

        greaterThanButton.setOnClickListener {
            checkGreaterThan();
            randomTermsExpression();
        }


        equalsToButton.setOnClickListener {
            checkEqualsTo();
            initialise();
            randomTermsExpression();


        }

        lessThanButton.setOnClickListener {
            checkLessThan();
            initialise();
            randomTermsExpression();


        }


    }



    fun generateOnetermL() {
        leftExpression.setText(variable1L.toString());
        leftEval = variable1L;
        println(leftEval)
    }

    fun generateTwoTermsL() {
        leftExpression.setText(twoTermsExpressionL);
        leftEval = calulate2terms(variable1L, variable2L, operatorL2Terms)
        println(leftEval)
    }

    fun generateThreeTermsL() {
        leftExpression.setText(threeTermsExpressionL);
        leftEval = calulate3terms(variable1L, variable2L, variable3L, operatorL3Terms1, operatorL3Terms2);
        println(leftEval);

    }

    fun generateFourTermsL() {
        leftExpression.setText(fourTermsExpressionL);
        val first2terms = calulate3terms(variable1L, variable2L, variable3L, operatorL4Terms1, operatorL4Terms2)
        leftEval = calulate2terms(first2terms, variable4L, operatorL4Terms3)
        println(leftEval);

    }

    fun generateOnetermR() {
        rightExpression.setText(variable1R.toString());
        rightEval = variable1R;
        println(rightEval)

    }

    fun generateTwoTermsR() {
        rightExpression.setText(twoTermsExpressionR);
        rightEval = calulate2terms(variable1R, variable2R, operatorR2Terms)
        println(rightEval)

    }

    fun generateThreeTermsR() {
        rightExpression.setText(threeTermsExpressionR);
        rightEval = calulate3terms(variable1R, variable2R, variable3R, operatorR3Terms1, operatorR3Terms2);
        println(rightEval);

    }

    fun generateFourTermsR() {
        rightExpression.setText(fourTermsExpressionB);
        val first2terms = calulate3terms(variable1R, variable2R, variable3R, operatorR4Terms1, operatorR4Terms2)
        rightEval = calulate2terms(first2terms, variable4R, operatorR4Terms3)
        println(rightEval);

    }

    fun randomTermsExpression() {
        var rdmNumL = (1..4).random();
        var rdmNumR = (1..4).random();

        //Left side random term
        if (rdmNumL == 1) {
            generateOnetermL();
        } else if (rdmNumL == 2) {
            generateTwoTermsL();
            generateThreeTermsL();
        } else if (rdmNumL == 3) {
            generateThreeTermsL();
        } else {
            generateFourTermsL();
        }

        //Right side random term
        if (rdmNumR == 1) {
            generateOnetermR();

        } else if (rdmNumR == 2) {
            generateTwoTermsR();
        } else if (rdmNumR == 3) {
            generateThreeTermsR();
        } else {
            generateFourTermsR();
        }
        initialise();
    }

    fun calulate3terms(variable1: Int, variable2: Int, variable3: Int, operator1: String, operator2: String): Int {
        if (operator1.equals("+") && operator2.equals("+")){
            return (variable1 + variable2) + variable3;
        }
        else if (operator1.equals("-") && operator2.equals("+")){
            return (variable1 - variable2) + variable3;
        }
        else if (operator1.equals("+") && operator2.equals("-")){
            return (variable1 + variable2) - variable3;
        }
        else if (operator1.equals("-") && operator2.equals("-")){
            return (variable1 - variable2) - variable3;
        }
        else if (operator1.equals("*") && operator2.equals("*")){
            return (variable1 * variable2) * variable3;
        }
        else if (operator1.equals("*") && operator2.equals("+")){
            return (variable1 * variable2) + variable3;
        }
        else if (operator1.equals("+") && operator2.equals("*")){
            return (variable1 + variable2) * variable3;
        }
        else if (operator1.equals("*") && operator2.equals("-")){
            return (variable1 * variable2) - variable3;
        }
        else if (operator1.equals("-") && operator2.equals("*")){
            return (variable1 - variable2) * variable3;
        }
        else if (operator1.equals("/") && operator2.equals("/")){
            return (variable1 / variable2) / variable3;
        }
        else if (operator1.equals("/") && operator2.equals("+")){
            return (variable1 / variable2) + variable3;
        }
        else if (operator1.equals("+") && operator2.equals("/")){
            return (variable1 + variable2) / variable3;
        }
        else if (operator1.equals("/") && operator2.equals("-")){
            return (variable1 / variable2) - variable3;
        }
        else if (operator1.equals("-") && operator2.equals("/")){
            return (variable1 - variable2) / variable3;
        }
        else if (operator1.equals("/") && operator2.equals("*")){
            return (variable1 + variable2) - variable3;
        }
        else{                                                                               //if (operatorL3Terms1.equals("*") && operatorL3Terms2.equals("/"))
            return (variable1 * variable2) / variable3;
        }

    }

    fun calulate2terms(variable1: Int, variable2: Int,operator1: String): Int{
        if (operator1.equals("+")){
            return variable1 + variable2;
        }
        else if (operator1.equals("-")){
            return variable1 - variable2;
        }
        else if (operator1.equals("*")){
            return variable1 * variable2;
        }
        else{
            return variable1 / variable2;
        }
    }

    fun initialise(){
        //Numbers of the left
        variable1L = (1..20).random();
        variable2L = (1..20).random();
        variable3L = (1..20).random();
        variable4L = (1..20).random();

        //Numbers of the right
        variable1R = (1..20).random();
        variable2R = (1..20).random();
        variable3R = (1..20).random();
        variable4R = (1..20).random();

        //Random arithmetic expression for two terms
        twoTermsExpressionL = variable1L.toString() + operatorL2Terms + variable2L.toString();
        twoTermsExpressionR = variable1R.toString() + operatorR2Terms + variable2R.toString();

        //Random arithmetic expression for three terms
        threeTermsExpressionL = "(" + variable1L.toString() + operatorL3Terms1 + variable2L.toString() + ")" + operatorL3Terms2 + variable3L;
        threeTermsExpressionR = "(" + variable1R.toString() + operatorR3Terms1 + variable2R.toString() + ")" + operatorR3Terms2 + variable3R;

        //Random arithmetic expression for four terms
        fourTermsExpressionL = "(" + "(" + variable1L.toString() + operatorL4Terms1 + variable2L.toString() + ")" + operatorL4Terms2 + variable3L + ")" + operatorL4Terms3 + variable4L;
        fourTermsExpressionB = "(" + "(" + variable1R.toString() + operatorR4Terms1 + variable2R.toString() + ")" + operatorR4Terms2 + variable3R + ")" + operatorR4Terms3 + variable4R;

    }

    fun checkGreaterThan() {
        if(leftEval > rightEval){
            correct ++;
            answerDisplay.setText("CORRECT!");
            answerDisplay.setTextColor(Color.GREEN);
        }
        else{
            incorrect++;
            answerDisplay.setText("INCORRECT!");
            answerDisplay.setTextColor(Color.RED);

        }
    }

    fun checkEqualsTo() {
        if(leftEval == rightEval){
            correct ++;
            answerDisplay.setText("CORRECT!");
            answerDisplay.setTextColor(Color.GREEN);
        }
        else{
            incorrect++;
            answerDisplay.setText("INCORRECT!");
            answerDisplay.setTextColor(Color.RED);

        }
    }

    fun checkLessThan() {
        if(leftEval < rightEval){
            correct ++;
            answerDisplay.setText("CORRECT!");
            answerDisplay.setTextColor(Color.GREEN);
        }
        else{
            incorrect++;
            answerDisplay.setText("INCORRECT!");
            answerDisplay.setTextColor(Color.RED);

        }
    }



}