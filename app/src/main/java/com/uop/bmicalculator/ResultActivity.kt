package com.uop.bmicalculator

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.uop.bmicalculator.databinding.ActivityResultBinding
import com.uop.bmicalculator.models.BMICategory
import com.uop.bmicalculator.models.Person

/**
 * Result Activity - Displays BMI calculation results
 * Shows BMI value, category, and color-coded health recommendations
 */
class ResultActivity : AppCompatActivity() {

    // View Binding for type-safe view access
    private lateinit var binding: ActivityResultBinding

    // Person object created from Intent extras
    private lateinit var person: Person

    companion object {
        // Intent extra keys
        const val EXTRA_WEIGHT = "extra_weight"
        const val EXTRA_HEIGHT = "extra_height"
        const val EXTRA_AGE = "extra_age"
        const val EXTRA_GENDER = "extra_gender"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_your_result)

        // Retrieve Person data from Intent and create Person object
        createPersonFromIntent()

        // Display results using the Person object
        displayResults()

        // Setup button listeners
        setupButtons()
    }

    /**
     * Create Person object from Intent extras using OOP principles
     */
    private fun createPersonFromIntent() {
        val weight = intent.getDoubleExtra(EXTRA_WEIGHT, 0.0)
        val height = intent.getDoubleExtra(EXTRA_HEIGHT, 0.0)
        val age = intent.getIntExtra(EXTRA_AGE, 0)
        val genderName = intent.getStringExtra(EXTRA_GENDER) ?: "OTHER"

        val gender = Person.Gender.valueOf(genderName)

        // Create Person object
        person = Person(weight, height, age, gender)
    }

    /**
     * Display BMI calculation results with color coding
     * Uses when clause to determine colors based on BMI category
     */
    private fun displayResults() {
        // Calculate BMI using Person object method
        val bmiValue = person.calculateBMI()

        // Get BMI category using Person object method
        val category = person.getBMICategory()

        // Display BMI value
        binding.tvBMIValue.text = String.format("%.1f", bmiValue)

        // Display category name
        binding.tvCategory.text = category.displayName

        // Display health status
        val status = if (person.isHealthyWeight()) {
            getString(R.string.status_healthy)
        } else {
            getString(R.string.status_needs_attention)
        }
        binding.tvStatus.text = status

        // Get ideal weight range
        val idealRange = person.getIdealWeightRange()
        binding.tvIdealWeight.text = getString(
            R.string.format_ideal_range,
            idealRange.first,
            idealRange.second
        )

        // Display recommendation
        binding.tvRecommendation.text = category.getRecommendation()

        // Display person details
        displayPersonDetails()

        // Apply color coding using when clause
        applyColorCoding(category)
    }

    /**
     * Display person details in the card
     */
    private fun displayPersonDetails() {
        val details = buildString {
            append(getString(R.string.format_weight, person.weight))
            append("\n")
            append(getString(R.string.format_height, person.height))
            append("\n")
            append(getString(R.string.format_age, person.age))
            append("\n")
            append(getString(R.string.format_gender, person.gender.displayName))
        }
        binding.tvPersonDetails.text = details
    }

    /**
     * Apply color coding to UI elements based on BMI category
     * Uses when clause to determine colors
     * Red for underweight/overweight/obese, Green for normal
     */
    private fun applyColorCoding(category: BMICategory) {
        // Determine color based on category using when clause
        val color = when (category) {
            BMICategory.UNDERWEIGHT -> ContextCompat.getColor(this, R.color.bmi_underweight)
            BMICategory.NORMAL -> ContextCompat.getColor(this, R.color.bmi_normal)
            BMICategory.OVERWEIGHT -> ContextCompat.getColor(this, R.color.bmi_overweight)
            BMICategory.OBESE -> ContextCompat.getColor(this, R.color.bmi_obese)
        }

        // Apply color to BMI value
        binding.tvBMIValue.setTextColor(color)

        // Apply color to category text
        binding.tvCategory.setTextColor(color)

        // Apply color to status based on health
        val statusColor = when {
            person.isHealthyWeight() -> ContextCompat.getColor(this, R.color.status_healthy)
            category == BMICategory.OBESE -> ContextCompat.getColor(this, R.color.status_danger)
            else -> ContextCompat.getColor(this, R.color.status_warning)
        }
        binding.tvStatus.setTextColor(statusColor)

        // Optional: Change card background tint
        val cardTintColor = when (category) {
            BMICategory.NORMAL -> ContextCompat.getColor(this, R.color.bmi_normal)
            BMICategory.UNDERWEIGHT, BMICategory.OVERWEIGHT -> ContextCompat.getColor(this, R.color.bmi_overweight)
            BMICategory.OBESE -> ContextCompat.getColor(this, R.color.bmi_obese)
        }

        // Apply subtle tint to result card (with transparency)
        val transparentColor = Color.argb(
            30,
            Color.red(cardTintColor),
            Color.green(cardTintColor),
            Color.blue(cardTintColor)
        )
        binding.cardBMIResult.setCardBackgroundColor(transparentColor)
    }

    /**
     * Setup button click listeners
     */
    private fun setupButtons() {
        binding.btnRecalculate.setOnClickListener {
            // Go back to MainActivity
            finish()
        }
    }

    /**
     * Handle up button navigation
     */
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
