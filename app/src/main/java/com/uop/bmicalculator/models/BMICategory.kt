package com.uop.bmicalculator.models

/**
 * Enum class representing different BMI categories
 * Based on WHO BMI classification standards
 */
enum class BMICategory(val displayName: String, val range: String) {
    UNDERWEIGHT("Underweight", "BMI < 18.5"),
    NORMAL("Normal Weight", "BMI 18.5 - 24.9"),
    OVERWEIGHT("Overweight", "BMI 25 - 29.9"),
    OBESE("Obese", "BMI ≥ 30");

    /**
     * Determines if this category is healthy
     */
    fun isHealthy(): Boolean {
        return this == NORMAL
    }

    /**
     * Get recommendation message for this category
     */
    fun getRecommendation(): String {
        return when (this) {
            UNDERWEIGHT -> "You may need to gain weight. Consult a healthcare professional for advice."
            NORMAL -> "Great! You have a healthy weight. Keep maintaining your lifestyle."
            OVERWEIGHT -> "You may need to lose some weight. Consider a balanced diet and regular exercise."
            OBESE -> "You should consult a healthcare professional for a personalized health plan."
        }
    }

    companion object {
        /**
         * Categorize BMI value into appropriate category
         */
        fun fromBMI(bmi: Double): BMICategory {
            return when {
                bmi < 18.5 -> UNDERWEIGHT
                bmi < 25.0 -> NORMAL
                bmi < 30.0 -> OVERWEIGHT
                else -> OBESE
            }
        }
    }
}
