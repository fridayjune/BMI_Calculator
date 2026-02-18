# BMI Calculator

A Kotlin-based Android application for calculating Body Mass Index (BMI) with Object-Oriented Programming principles.

## Description
This app allows users to input their height, weight, age, and gender to calculate their BMI and receive personalized health recommendations with color-coded results.

## Features
- ✅ Weight input (kg)
- ✅ Height input (cm)
- ✅ Age and gender selection
- ✅ Instant BMI calculation using OOP principles
- ✅ BMI category classification (Underweight, Normal, Overweight, Obese)
- ✅ Color-coded results (Green for healthy, Orange/Red for attention needed)
- ✅ Ideal weight range calculation
- ✅ Personalized health recommendations
- ✅ Input validation with error messages
- ✅ Clean Material Design UI

## Technologies Used
- **Language:** Kotlin
- **UI Framework:** Material Design Components
- **Architecture:** OOP (Object-Oriented Programming)
- **View Binding:** Enabled for type-safe view access
- **Min SDK:** API 24 (Android 7.0)
- **Target SDK:** API 34 (Android 14)

## OOP Implementation

### Classes and Structure

#### 1. **Person Class** (`Person.kt`)
Encapsulates person data and BMI calculation logic:
- **Properties:** weight, height, age, gender
- **Methods:**
  - `calculateBMI()`: Calculates BMI value
  - `getBMICategory()`: Returns BMI category enum
  - `isHealthyWeight()`: Boolean check for healthy BMI
  - `getIdealWeightRange()`: Calculates ideal weight range
  - `isValidData()`: Validates person data

#### 2. **BMICategory Enum** (`BMICategory.kt`)
Defines BMI categories with associated behavior:
- **Values:** UNDERWEIGHT, NORMAL, OVERWEIGHT, OBESE
- **Methods:**
  - `isHealthy()`: Returns if category is healthy
  - `getRecommendation()`: Returns health advice
  - `fromBMI()`: Static method to categorize BMI value

#### 3. **Gender Enum** (nested in Person class)
Represents gender options:
- **Values:** MALE, FEMALE, OTHER

### When Clause Usage
The app extensively uses Kotlin's `when` clause for:
- BMI categorization based on value ranges
- Color coding based on BMI category
- Input validation
- Gender parsing

## Setup Instructions

### Prerequisites
- Android Studio (Hedgehog 2023.1.1 or later)
- JDK 11 or higher
- Android SDK with API 24-34

### Steps to Run
1. **Open Project in Android Studio:**
   ```
   File → Open → Select BMI_Calculator folder
   ```

2. **Configure SDK Location:**
   - Copy `local.properties.template` to `local.properties`
   - Update `sdk.dir` with your Android SDK path
   - Example: `sdk.dir=C\:\\Users\\YourName\\AppData\\Local\\Android\\sdk`

3. **Sync Gradle:**
   - Wait for automatic Gradle sync
   - Or click "Sync Project with Gradle Files"

4. **Run the App:**
   - Click Run button (▶️) or press Shift+F10
   - Select emulator or connected device

## Project Structure
```
BMI_Calculator/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/uop/bmicalculator/
│   │       │   ├── models/
│   │       │   │   ├── BMICategory.kt      # Enum for BMI categories
│   │       │   │   └── Person.kt           # Person class with BMI logic
│   │       │   ├── MainActivity.kt         # Input screen activity
│   │       │   └── ResultActivity.kt       # Result screen activity
│   │       ├── res/
│   │       │   ├── layout/
│   │       │   │   ├── activity_main.xml   # Main input UI
│   │       │   │   └── activity_result.xml # Result display UI
│   │       │   └── values/
│   │       │       ├── strings.xml         # String resources
│   │       │       ├── colors.xml          # Color definitions
│   │       │       └── themes.xml          # App themes
│   │       └── AndroidManifest.xml         # App manifest
│   ├── build.gradle                        # App-level Gradle config
│   └── proguard-rules.pro                  # ProGuard rules
├── gradle/
│   └── wrapper/
│       └── gradle-wrapper.properties       # Gradle wrapper config
├── build.gradle                            # Project-level Gradle config
├── settings.gradle                         # Project settings
├── gradle.properties                       # Gradle properties
└── README.md                               # This file
```

## BMI Categories
- **Underweight:** BMI < 18.5 (Orange)
- **Normal weight:** BMI 18.5 - 24.9 (Green)
- **Overweight:** BMI 25 - 29.9 (Orange)
- **Obese:** BMI ≥ 30 (Red)

## Screenshots Description

### Main Screen (activity_main.xml)
- Title and subtitle
- Input fields for weight, height, age
- Gender dropdown selector
- Material Design styled inputs with validation
- Calculate BMI button

### Result Screen (activity_result.xml)
- Large BMI value display
- Color-coded category (Green/Orange/Red)
- Health status indicator
- Person details card
- Ideal weight range
- Personalized recommendations
- Recalculate button

## Usage
1. Launch the app
2. Enter your weight in kilograms
3. Enter your height in centimeters
4. Enter your age
5. Select your gender
6. Tap "Calculate BMI"
7. View your results with color-coded health status
8. Read personalized recommendations
9. Tap "Calculate Again" to start over

## Input Validation
- **Weight:** 10-300 kg
- **Height:** 50-250 cm
- **Age:** 1-120 years
- **Gender:** Must be selected

## Development Status
✅ **COMPLETE** - Full Android Studio Kotlin project ready to run!

## Building APK
```bash
# Debug APK
./gradlew assembleDebug

# Release APK (unsigned)
./gradlew assembleRelease
```

## Testing
Run the app on an emulator or physical device to test:
- Input validation
- BMI calculation accuracy
- Color coding for different categories
- Navigation between screens

## License
Educational project for University of People - Mobile App Development

## Author
University of People Student
