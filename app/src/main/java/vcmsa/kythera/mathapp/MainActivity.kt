package vcmsa.kythera.mathapp

import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ui elements
        val firstNumber = findViewById<EditText>(R.id.edtFirstNumber)
        val secondNumber = findViewById<EditText>(R.id.edtSecondNumber)
        val addition = findViewById<RadioButton>(R.id.radioAddition)
        val subtraction = findViewById<RadioButton>(R.id.radioSubtraction)
        val multiplication = findViewById<RadioButton>(R.id.radioMultiplication)
        val division = findViewById<RadioButton>(R.id.radioDivision)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val answerButton = findViewById<Button>(R.id.btnAnswer)
        val answerText = findViewById<EditText>(R.id.edtAnswer)
        val resetButton = findViewById<Button>(R.id.btnReset)


        //answer button will give the answer
        answerButton.setOnClickListener {
          val input1 = firstNumber.text.toString()
            val input2 = secondNumber.text.toString()

           // if empty will show toast
            if (input1.isEmpty() || input2.isEmpty()) {
                Toast.makeText(this, "Enter both of the numbers please", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
        }
            // will convert to integers
            try {
                val num1 = input1.toInt()
                val num2 = input2.toInt()
                var result = 0
                var operation = ""


              // the calculations buttons
                //AI Assisted - start
                when {

                    addition.isChecked -> {
                        result = (num1 + num2)
                        operation = "Addition (+)"
                    }

                    subtraction.isChecked -> {
                        result = (num1 - num2)
                        operation = "Subtraction (-)"
                    }

                    multiplication.isChecked -> {
                        result = (num1 * num2)
                        operation = "Multiplication (*)"
                    }

                    division.isChecked -> {
                        if (num2 == 0) {
                            Toast.makeText(this, "This Cannot be divided by Zero", Toast.LENGTH_SHORT).show()
                            return@setOnClickListener
                        }
                        result = (num1 / num2)
                        operation = "Division (/)"
                    }
                }
                //end

                // output of the answer
                // AI generated - start
                answerText.setText(result.toString())
                Toast.makeText(this, "The $operation of $num1 and $num2 is $result", Toast.LENGTH_SHORT).show()
                //end

                //will catch if theres no whole numbers or alphabets in the input
            }catch (_: NumberFormatException) {
                Toast.makeText(this , "Input is invalid, Enter a whole number", Toast.LENGTH_SHORT).show()
            }
        }
        //when given calculations it will reset
        resetButton.setOnClickListener {
            firstNumber.text.clear()
            secondNumber.text.clear()
            radioGroup.clearCheck()
            answerText.text.clear()
            Toast.makeText(this, "Reset done", Toast.LENGTH_SHORT).show()


        }


    }

}