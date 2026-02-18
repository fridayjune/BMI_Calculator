package com.uop.bmicalculator.models

/**
 * Person class representing an individual with health-related properties
 * Implements BMI calculation functionality using OOP principles
 */
class Person(
    val weight: Double,      // Weight in kilograms
    val height: Double,      // Height in centimeters
    val age: Int,           // Age in years
    val gender: Gender      // Gender of the person
) {
    // Private property to store calculated BMI
    private var bmiValue: Double? = null
    private var category: BMICategory? = null

    /**
     * Enum class for gender options
     */
    enum class Gender(val displayName: String) {
        MALE("Male"),
        FEMALE("Female"),
        OTHER("Other");

        companion object {
            fun fromString(value: String): Gender {
                return when (value.lowercase()) {
                    "male" -> MALE
                    "female" -> FEMALE
                    else -> OTHER
                }
            }
        }
    }

    /**
     * Calculate BMI using the formula: BMI = weight (kg) / (height (m))^2
     * @return BMI value rounded to 2 decimal places
     */
    fun calculateBMI(): Double {
        if (bmiValue == null) {
            // Convert height from cm to meters
            val heightInMeters = height / 100.0

            // Calculate BMI
            bmiValue = weight / (heightInMeters * heightInMeters)
        }

        // Round to 2 decimal places
        return String.format("%.2f", bmiValue).toDouble()
    }

    /**
     * Get the BMI category for this person
     * @return BMICategory enum value
     */
    fun getBMICategory(): BMICategory {
        if (category == null) {
            val bmi = calculateBMI()
            category = when {
                bmi < 18.5 -> BMICategory.UNDERWEIGHT
                bmi < 25.0 -> BMICategory.NORMAL
                bmi < 30.0 -> BMICategory.OVERWEIGHT
                else -> BMICategory.OBESE
            }
        }
        return category!!
    }

    /**
     * Get health status based on BMI category
     * @return true if BMI is in normal range, false otherwise
     */
    fun isHealthyWeight(): Boolean {
        return getBMICategory() == BMICategory.NORMAL
    }

    /**
     * Get ideal weight range for this person's height
     * Based on BMI range 18.5 - 24.9
     * @return Pair of (minimum weight, maximum weight)
     */
    fun getIdealWeightRange(): Pair<Double, Double> {
        val heightInMeters = height / 100.0
        val minWeight = 18.5 * (heightInMeters * heightInMeters)
        val maxWeight = 24.9 * (heightInMeters * heightInMeters)

        return Pair(
            String.format("%.1f", minWeight).toDouble(),
            String.format("%.1f", maxWeight).toDouble()
        )
    }

    /**
     * Get detailed health information
     * @return String with comprehensive health details
     */
    fun getHealthInfo(): String {
        val bmi = calculateBMI()
        val category = getBMICategory()
        val idealRange = getIdealWeightRange()

        return buildString {
            append("BMI: $bmi\n")
            append("Category: ${category.displayName}\n")
            append("Status: ${if (isHealthyWeight()) "Healthy" else "Needs Attention"}\n")
            append("Ideal Weight Range: ${idealRange.first} - ${idealRange.second} kg\n")
            append("\nRecommendation:\n${category.getRecommendation()}")
        }
    }

    /**
     * Validate if the person's data is valid for BMI calculation
     * @return true if data is valid, false otherwise
     */
    fun isValidData(): Boolean {
        return weight > 0 && height > 0 && age > 0
    }

    override fun toString(): String {
        return "Person(weight=$weight kg, height=$height cm, age=$age, gender=${gender.displayName})"
    }
}
