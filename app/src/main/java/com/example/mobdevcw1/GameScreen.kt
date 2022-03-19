package com.example.mobdevcw1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.*

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

    //Random Operator
    var operators = mutableListOf<String>("+", "-", "*", "/");
    var rdmOperator = operators.size;

    //Text view to display expressions
    lateinit var leftExpression : TextView;
    lateinit var rightExpression : TextView;

    var gen: Random = Random()


class GameScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        leftExpression = findViewById<TextView>(R.id.textView);
        rightExpression = findViewById<TextView>(R.id.textView3);


        //Numbers of the left
        variable1L = (1..21).random();
        variable2L = (1..21).random();
        variable3L = (1..21).random();
        variable4L = (1..21).random();

        //Numbers of the right
        variable1R = (1..21).random();
        variable2R = (1..21).random();
        variable3R = (1..21).random();
        variable4R = (1..21).random();




        //Random arithmetic expression for two terms
        twoTermsExpressionL = variable1L.toString() + operators[gen.nextInt(rdmOperator)] + variable2L.toString();
        twoTermsExpressionR = variable1R.toString() + operators[gen.nextInt(rdmOperator)] + variable2R.toString();

        //Random arithmetic expression for three terms
        threeTermsExpressionL = "(" + variable1L.toString() + operators[gen.nextInt(rdmOperator)] + variable2L.toString() + ")" + operators[gen.nextInt(rdmOperator)] + variable3L;
        threeTermsExpressionR = "(" + variable1R.toString() + operators[gen.nextInt(rdmOperator)] + variable2R.toString() + ")" + operators[gen.nextInt(rdmOperator)] + variable3R;

        //Random arithmetic expression for four terms
        fourTermsExpressionL = "("+"(" + variable1L.toString() + operators[gen.nextInt(rdmOperator)] + variable2L.toString() + ")" + operators[gen.nextInt(rdmOperator)] + variable3L + ")" + operators[gen.nextInt(rdmOperator)] + variable4L;
        fourTermsExpressionB = "("+"(" + variable1R.toString() + operators[gen.nextInt(rdmOperator)] + variable2R.toString() + ")" + operators[gen.nextInt(rdmOperator)] + variable3R + ")" + operators[gen.nextInt(rdmOperator)] + variable4R;

        //Call function 'randomTermsExpression'
        randomTermsExpression();

    }

    fun generateOnetermL(){
        leftExpression.setText(variable1L.toString());

    }

    fun generateTwoTermsL(){
        leftExpression.setText(twoTermsExpressionL);

    }

    fun generateThreeTermsL(){
        leftExpression.setText(threeTermsExpressionL);

    }

    fun generateFourTermsL(){
        leftExpression.setText(fourTermsExpressionL);

    }

    fun generateOnetermR(){
        rightExpression.setText(variable1R.toString());

    }

    fun generateTwoTermsR(){
        rightExpression.setText(twoTermsExpressionR);

    }

    fun generateThreeTermsR(){
        rightExpression.setText(threeTermsExpressionR);

    }

    fun generateFourTermsR(){
        rightExpression.setText(fourTermsExpressionB);

    }

    fun randomTermsExpression(){
        var rdmNumL = (1..4).random();
        var rdmNumR = (1..4).random();

        //Left side random term
        if(rdmNumL == 1){
            generateOnetermL();

        }
        else if (rdmNumL == 2){
            generateTwoTermsL();
            generateThreeTermsL();
        }
        else if (rdmNumL == 3){
            generateThreeTermsL();
        }
        else{
            generateFourTermsL();
        }

        //Right side random term
        if(rdmNumR == 1){
            generateOnetermR();

        }
        else if (rdmNumR == 2){
            generateTwoTermsR();
        }
        else if (rdmNumR == 3){
            generateThreeTermsR();
        }
        else{
            generateFourTermsR();
        }

    }


}