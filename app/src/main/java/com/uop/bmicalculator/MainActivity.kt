package com.uop.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.uop.bmicalculator.databinding.ActivityMainBinding
import com.uop.bmicalculator.models.Person

/**
 * Main Activity - Entry point of the BMI Calculator app
 * Handles user input and creates Person object for BMI calculation
 */
class MainActivity : AppCompatActivity() {

    // View Binding for type-safe view access
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup UI components
        setupGenderDropdown()
        setupCalculateButton()
    }

    /**
     * Setup the gender dropdown with predefined options
     */
    private fun setupGenderDropdown() {
        val genderOptions = resources.getStringArray(R.array.gender_options)
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, genderOptions)
        binding.actvGender.setAdapter(adapter)
    }

    /**
     * Setup the Calculate button click listener
     */
    private fun setupCalculateButton() {
        binding.btnCalculate.setOnClickListener {
            calculateBMI()
        }
    }

    /**
     * Main calculation method - validates input and creates Person object
     */
    private fun calculateBMI() {
        // Clear previous errors
        clearErrors()
        // Get input values
        val weightStr = binding.etWeight.text.toString().trim()
        val heightStr = binding.etHeight.text.toString().trim()
        val ageStr = binding.etAge.text.toString().trim()
        val genderStr = binding.actvGender.text.toString().trim()
        // Validate inputs
        if (!validateInputs(weightStr, heightStr, ageStr, genderStr)) {
            return
        }
        // Parse values
        val weight = weightStr.toDoubleOrNull() ?: 0.0
        val height = heightStr.toDoubleOrNull() ?: 0.0
        val age = ageStr.toIntOrNull() ?: 0
        val gender = parseGender(genderStr)

        // Additional validation for value ranges
        if (!validateRanges(weight, height, age)) {
            return
        }
        // Create Person object using OOP principles
        val person = Person(weight, height, age, gender)
        // Verify person data is valid
        if (!person.isValidData()) {
            Toast.makeText(this, "Invalid data entered", Toast.LENGTH_SHORT).show()
            return
        }
        // Navigate to Result Activity with Person data
        navigateToResult(person)
    }

    /**
     * Validate that all required inputs are filled
     */
    private fun validateInputs(
        weight: String,
        height: String,
        age: String,
        gender: String
    ): Boolean {
        var isValid = true

        if (weight.isEmpty()) {
            binding.tilWeight.error = getString(R.string.error_empty_weight)
            isValid = false
        }

        if (height.isEmpty()) {
            binding.tilHeight.error = getString(R.string.error_empty_height)
            isValid = false
        }

        if (age.isEmpty()) {
            binding.tilAge.error = getString(R.string.error_empty_age)
            isValid = false
        }

        if (gender.isEmpty() || gender == "Select Gender") {
            binding.tilGender.error = getString(R.string.error_select_gender)
            isValid = false
        }

        return isValid
    }

    /**
     * Validate that numeric values are within acceptable ranges
     */
    private fun validateRanges(weight: Double, height: Double, age: Int): Boolean {
        var isValid = true

        // Validate weight (10-300 kg)
        if (weight < 10 || weight > 300) {
            binding.tilWeight.error = getString(R.string.error_invalid_weight)
            isValid = false
        }

        // Validate height (50-250 cm)
        if (height < 50 || height > 250) {
            binding.tilHeight.error = getString(R.string.error_invalid_height)
            isValid = false
        }

        // Validate age (1-120 years)
        if (age < 1 || age > 120) {
            binding.tilAge.error = getString(R.string.error_invalid_age)
            isValid = false
        }

        return isValid
    }

    /**
     * Parse gender string to Person.Gender enum
     */
    private fun parseGender(genderStr: String): Person.Gender {
        return when (genderStr) {
            "Male" -> Person.Gender.MALE
            "Female" -> Person.Gender.FEMALE
            else -> Person.Gender.OTHER
        }
    }

    /**
     * Clear all error messages from input fields
     */
    private fun clearErrors() {
        binding.tilWeight.error = null
        binding.tilHeight.error = null
        binding.tilAge.error = null
        binding.tilGender.error = null
    }

    /**
     * Navigate to Result Activity with Person data
     * Uses Intent extras to pass data between activities
     */
    private fun navigateToResult(person: Person) {
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra(ResultActivity.EXTRA_WEIGHT, person.weight)
            putExtra(ResultActivity.EXTRA_HEIGHT, person.height)
            putExtra(ResultActivity.EXTRA_AGE, person.age)
            putExtra(ResultActivity.EXTRA_GENDER, person.gender.name)
        }
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        // Clear inputs when returning to main activity
        // Uncomment the line below if you want to clear inputs on resume
        // clearInputs()
    }

    /**
     * Clear all input fields
     */
    private fun clearInputs() {
        binding.etWeight.text?.clear()
        binding.etHeight.text?.clear()
        binding.etAge.text?.clear()
        binding.actvGender.text?.clear()
        clearErrors()
    }
}
