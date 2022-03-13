package com.example.cm_to_inches_converter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cm_to_inches_converter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //creates a global class object that can access all of the child views inside of root.
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.userInputLog.text = " "

    //Sets a click listener when the user presses the calculate button the convertHeight() function is called.
        binding.calculateButton.setOnClickListener {
            convertHeight()
        }
    }

    private fun convertHeight () {
        // Declare a result variable
        var conversionResult:Double

        //initialize userInput as a double
        val userInputDouble = binding.inputConversion.text.toString().toDoubleOrNull()

        // if userInput is a null value it will return an empty string
        if(userInputDouble == null) {
            binding.userInputLog.text = " "
            return
        }
        // Find which radiobutton is checked and return the userinput converted to the desired (inches or centimeters)
        val conversionSelected = when(binding.conversionOptions.checkedRadioButtonId) {
            R.id.inches_to_cm -> "centimeters"
            else -> "inches"
        }
        //perform the correct conversion based on the selected option
        conversionResult = if(conversionSelected == "centimeters") {
            userInputDouble * 2.54

        } else {
            userInputDouble / 2.54
        }
        //check if we should round up the result
        val roundUp = binding.roundToNearest.isChecked

        if(roundUp) {
            conversionResult = kotlin.math.ceil(conversionResult)
        }
        //Format the string
        val formattedResult = "$conversionResult  $conversionSelected"
        //output the result to the TextView at the bottom of our app
        binding.userInputLog.text = getString(R.string.user_input_log, formattedResult)
    }
}