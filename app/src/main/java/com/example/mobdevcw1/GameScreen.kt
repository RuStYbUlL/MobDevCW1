package com.example.mobdevcw1

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
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

    var twoTermsExpression = " ";

    //Random arithmetic expression for three terms
    var threeTermsExpressionL = " ";
    var threeTermsExpressionR = " ";

    var threeTermsExpression = " ";


    //Random arithmetic expression for four terms
    var fourTermsExpressionR = " ";
    var fourTermsExpressionL = " ";

    var fourTermsExpression = " ";

    var first2terms = 0.0;

    //Text view to display expressions
    lateinit var leftExpression: TextView;
    lateinit var rightExpression: TextView;
    lateinit var answerDisplay: TextView;

    var gen: Random = Random()

    //Score
    companion object {
        var correct: Int = 0
        var incorrect: Int = 0;
//        lateinit var countTime: TextView
    }

    var leftEval: Double = 0.0;
    var rightEval: Double = 0.0;

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

    //Timer
    lateinit var countDownTimer : CountDownTimer;
    var timeLeftInMillieSeconds = 50000L;
    var timerRunning : Boolean = false;
    lateinit var countDownText : TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        leftExpression = findViewById<TextView>(R.id.textView);
        rightExpression = findViewById<TextView>(R.id.textView3);
        answerDisplay = findViewById<TextView>(R.id.textAnswer);

        var greaterThanButton = findViewById<Button>(R.id.greaterButton);
        var equalsToButton = findViewById<Button>(R.id.equalsButton);
        var lessThanButton = findViewById<Button>(R.id.lessButton);

        //Set incorrect and correct to zero when new game starts
        incorrect = 0;
        correct = 0;
        //generate random numbers
        generateRandom();

        //Call function 'randomTermsExpression'
        randomTermsExpression();

        greaterThanButton.setOnClickListener {
            checkGreaterThan();
            randomTermsExpression();
        }

        equalsToButton.setOnClickListener {
            checkEqualsTo();
            generateRandom();
            randomTermsExpression();
        }

        lessThanButton.setOnClickListener {
            checkLessThan();
            generateRandom();
            randomTermsExpression();
        }

        countDownText = findViewById<TextView>(R.id.timerView)
        //Start timer
        startTimer();
    }

    fun startTimer(){
        object : CountDownTimer(timeLeftInMillieSeconds, 1000){
            override fun onTick(p0: Long) {
                timeLeftInMillieSeconds = p0;
                updateTimer();
            }

            override fun onFinish() {
                showResults();
            }
        }.start();
    }

    fun showResults(){
        val resultsIntent = Intent(this, Results::class.java);
        startActivity(resultsIntent);
    }

    fun updateTimer(){
        var seconds : Int= (timeLeftInMillieSeconds/1000).toInt();
        var timeLeftText = "00:";
        if (seconds>=10){
            timeLeftText += seconds;
        }
        else{
            timeLeftText += "0" + seconds;
        }
        countDownText.setText(timeLeftText);
    }

    fun generateOnetermL() {
        leftExpression.setText(variable1L.toString());
        leftEval = variable1L.toDouble();
        println(leftEval)
    }

    fun generateTwoTermsL() {
        leftEval = calulate2terms(variable1L.toDouble(), variable2L.toDouble(), operatorL2Terms)
        twoTermsExpressionL = twoTermsExpression;
        println(leftEval)
        while(!(leftEval <= 100) ||(leftEval % 1 != 0.0)) {
            generateRandom();
            leftEval = calulate2terms(variable1L.toDouble(), variable2L.toDouble(), operatorL2Terms)
            println("integer left = " + leftEval)

        }
        twoTermsExpressionL = twoTermsExpression;
        println(twoTermsExpressionL)
        leftExpression.setText(twoTermsExpressionL);
    }

    fun generateThreeTermsL() {
        leftEval = calulate3terms(variable1L.toDouble(), variable2L.toDouble(), variable3L.toDouble(), operatorL3Terms1, operatorL3Terms2);
        threeTermsExpressionL = threeTermsExpression;
        println(leftEval);
        while(!(leftEval <= 100) || (leftEval % 1 != 0.0000)){
            generateRandom();
            leftEval = calulate3terms(variable1L.toDouble(), variable2L.toDouble(), variable3L.toDouble(), operatorL3Terms1, operatorL3Terms2);
            println("integer left = " + leftEval);  //testing (delete this line)
        }
        threeTermsExpressionL = threeTermsExpression;
        leftExpression.setText(threeTermsExpressionL);
    }

    fun generateFourTermsL() {
        first2terms = calulate3terms(variable1L.toDouble(), variable2L.toDouble(), variable3L.toDouble(), operatorL4Terms1, operatorL4Terms2)
        leftEval = calulate2terms(first2terms, variable4L.toDouble(), operatorL4Terms3)
        fourTermsExpressionL = "(" + "(" + variable1L.toString() + operatorL4Terms1 + variable2L.toString() + ")" + operatorL4Terms2 + variable3L + ")" + operatorL4Terms3 + variable4L;
        println(leftEval);
        while(!(leftEval <= 100) || (leftEval % 1 != 0.0000)){
            generateRandom();
            first2terms = calulate3terms(variable1L.toDouble(), variable2L.toDouble(), variable3L.toDouble(), operatorL4Terms1, operatorL4Terms2)
            leftEval = calulate2terms(first2terms, variable4L.toDouble(), operatorL4Terms3)
            println("integer left = " + leftEval);  //testing (delete this line)
            fourTermsExpressionL = "(" + "(" + variable1L.toString() + operatorL4Terms1 + variable2L.toString() + ")" + operatorL4Terms2 + variable3L + ")" + operatorL4Terms3 + variable4L;
        }
        leftExpression.setText(fourTermsExpressionL);

    }

    fun generateOnetermR() {
        rightExpression.setText(variable1R.toString());
        rightEval = variable1R.toDouble();
        println(rightEval)

    }

    fun generateTwoTermsR() {
        rightEval = calulate2terms(variable1R.toDouble(), variable2R.toDouble(), operatorR2Terms)
        twoTermsExpressionR = twoTermsExpression;
        println(rightEval);
        while(rightEval % 1 != 0.0){  //if resulting value is not an integer
            generateRandom();
            rightEval = calulate2terms(variable1R.toDouble(), variable2R.toDouble(), operatorR2Terms)
            println("integer right = " + rightEval);

        }
        twoTermsExpressionR = twoTermsExpression;
        rightExpression.setText(twoTermsExpressionR);


    }

    fun generateThreeTermsR() {
        rightEval = calulate3terms(variable1R.toDouble(), variable2R.toDouble(), variable3R.toDouble(), operatorR3Terms1, operatorR3Terms2)
        threeTermsExpressionR = threeTermsExpression;
        println(rightEval);
        while(!(rightEval <= 100) || (rightEval % 1 != 0.0000)){
            generateRandom();
            rightEval = calulate3terms(variable1R.toDouble(), variable2R.toDouble(), variable3R.toDouble(), operatorR3Terms1, operatorR3Terms2)
            println("integer right = " + rightEval);  //testing (delete this line)

        }
        threeTermsExpressionR = threeTermsExpression;
        rightExpression.setText(threeTermsExpressionR);


    }

    fun generateFourTermsR() {
        first2terms = calulate3terms(variable1R.toDouble(), variable2R.toDouble(), variable3R.toDouble(), operatorR4Terms1, operatorR4Terms2)
        rightEval = calulate2terms(first2terms, variable4R.toDouble(), operatorR4Terms3);
        fourTermsExpressionR = "(" + "(" + variable1R.toString() + operatorR4Terms1 + variable2R.toString() + ")" + operatorR4Terms2 + variable3R + ")" + operatorR4Terms3 + variable4R;
        println(rightEval);
        while(!(rightEval <= 100) || (rightEval % 1 != 0.0000)){
            generateRandom();
            first2terms = calulate3terms(variable1R.toDouble(), variable2R.toDouble(), variable3R.toDouble(), operatorR4Terms1, operatorR4Terms2)
            rightEval = calulate2terms(first2terms, variable4R.toDouble(), operatorR4Terms3);
            println("integer right = " + leftEval);  //testing (delete this line)
            fourTermsExpressionR = "(" + "(" + variable1R.toString() + operatorR4Terms1 + variable2R.toString() + ")" + operatorR4Terms2 + variable3R + ")" + operatorR4Terms3 + variable4R;
        }
        rightExpression.setText(fourTermsExpressionR);
    }

    fun randomTermsExpression() {
        var rdmNumL = (1..4).random();
        var rdmNumR = (1..4).random();

        //Left side random term
        if (rdmNumL == 1) {
            generateOnetermL();
        } else if (rdmNumL == 2) {
            generateTwoTermsL();
//            generateThreeTermsL();
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
        generateRandom();
    }

    fun calulate3terms(variable1: Double, variable2: Double, variable3: Double, operator1: String, operator2: String): Double {
        if (operator1.equals("+") && operator2.equals("+")){
            threeTermsExpression = "(" + variable1.toInt().toString() + operator1 + variable2.toInt().toString()+ ")" + operator2 + variable3.toInt().toString();  //String expression
            return ((variable1 + variable2) + variable3);
        }
        else if (operator1.equals("-") && operator2.equals("+")){
            threeTermsExpression = "(" + variable1.toInt().toString() + operator1 + variable2.toInt().toString()+ ")" + operator2 + variable3.toInt().toString();
            return ((variable1 - variable2) + variable3);
        }
        else if (operator1.equals("+") && operator2.equals("-")){
            threeTermsExpression = "(" + variable1.toInt().toString() + operator1 + variable2.toInt().toString()+ ")" + operator2 + variable3.toInt().toString();
            return ((variable1 + variable2) - variable3);
        }
        else if (operator1.equals("-") && operator2.equals("-")){
            threeTermsExpression = "(" + variable1.toInt().toString() + operator1 + variable2.toInt().toString()+ ")" + operator2 + variable3.toInt().toString();
            return ((variable1 - variable2) - variable3);
        }
        else if (operator1.equals("*") && operator2.equals("*")){
            threeTermsExpression = "(" + variable1.toInt().toString() + operator1 + variable2.toInt().toString()+ ")" + operator2 + variable3.toInt().toString();
            return ((variable1 * variable2) * variable3);
        }
        else if (operator1.equals("*") && operator2.equals("+")){
            threeTermsExpression = "(" + variable1.toInt().toString() + operator1 + variable2.toInt().toString()+ ")" + operator2 + variable3.toInt().toString();
            return ((variable1 * variable2) + variable3);
        }
        else if (operator1.equals("+") && operator2.equals("*")){
            threeTermsExpression = "(" + variable1.toInt().toString() + operator1 + variable2.toInt().toString()+ ")" + operator2 + variable3.toInt().toString();
            return ((variable1 + variable2) * variable3);
        }
        else if (operator1.equals("*") && operator2.equals("-")){
            threeTermsExpression = "(" + variable1.toInt().toString() + operator1 + variable2.toInt().toString()+ ")" + operator2 + variable3.toInt().toString();
            return ((variable1 * variable2) - variable3);
        }
        else if (operator1.equals("-") && operator2.equals("*")){
            threeTermsExpression = "(" + variable1.toInt().toString() + operator1 + variable2.toInt().toString()+ ")" + operator2 + variable3.toInt().toString();
            return ((variable1 - variable2) * variable3);
        }
        else if (operator1.equals("/") && operator2.equals("/")){
            threeTermsExpression = "(" + variable1.toInt().toString() + operator1 + variable2.toInt().toString()+ ")" + operator2 + variable3.toInt().toString();
            return ((variable1 / variable2) / variable3);
        }
        else if (operator1.equals("/") && operator2.equals("+")){
            threeTermsExpression = "(" + variable1.toInt().toString() + operator1 + variable2.toInt().toString()+ ")" + operator2 + variable3.toInt().toString();
            return ((variable1 / variable2) + variable3)
        }
        else if (operator1.equals("+") && operator2.equals("/")){
            threeTermsExpression = "(" + variable1.toInt().toString() + operator1 + variable2.toInt().toString()+ ")" + operator2 + variable3.toInt().toString();
            return ((variable1 + variable2) / variable3);
        }
        else if (operator1.equals("/") && operator2.equals("-")){
            threeTermsExpression = "(" + variable1.toInt().toString() + operator1 + variable2.toInt().toString()+ ")" + operator2 + variable3.toInt().toString();
            return ((variable1 / variable2) - variable3);
        }
        else if (operator1.equals("-") && operator2.equals("/")){
            threeTermsExpression = "(" + variable1.toInt().toString() + operator1 + variable2.toInt().toString()+ ")" + operator2 + variable3.toInt().toString();
            return ((variable1 - variable2) / variable3);
        }
        else if (operator1.equals("/") && operator2.equals("*")){
            threeTermsExpression = "(" + variable1.toInt().toString() + operator1 + variable2.toInt().toString()+ ")" + operator2 + variable3.toInt().toString();
            return ((variable1 / variable2) * variable3);
        }
        else{
            threeTermsExpression = "(" + variable1.toInt().toString() + operator1 + variable2.toInt().toString()+ ")" + operator2 + variable3.toInt().toString();
            return ((variable1 * variable2) / variable3);
        }

    }

    fun calulate2terms(variable1: Double, variable2: Double,operator1: String): Double{
        if (operator1.equals("+")){
            twoTermsExpression = variable1.toInt().toString() + operator1 + variable2.toInt().toString();
            return (variable1 + variable2);
        }
        else if (operator1.equals("-")){
            twoTermsExpression = variable1.toInt().toString() + operator1 +  variable2.toInt().toString();
            return (variable1 - variable2);
        }
        else if (operator1.equals("*")){
            twoTermsExpression = variable1.toInt().toString() + operator1 +  variable2.toInt().toString();
            return (variable1 * variable2);
        }
        else{
            twoTermsExpression = variable1.toInt().toString() + operator1 +  variable2.toInt().toString();
            return (variable1 / variable2);
        }
    }

    fun generateRandom(){
        //Numbers of the left
        variable1L = (1..100).random();
        variable2L = (1..100).random();
        variable3L = (1..100).random();
        variable4L = (1..100).random();

        //Numbers of the right
        variable1R = (1..20).random();
        variable2R = (1..20).random();
        variable3R = (1..20).random();
        variable4R = (1..20).random();
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