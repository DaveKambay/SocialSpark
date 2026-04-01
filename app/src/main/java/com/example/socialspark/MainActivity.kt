package com.example.socialspark

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // This function runs when the app starts up
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Makes the app content appear behind the status bar for a modern look
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // This block ensures the app content doesn't get hidden under the camera notch or navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- SECTION 1: LINKING UI TO KOTLIN ---
        // We find the components we made in activity_main.xml using their IDs
        val etTimeInput = findViewById<EditText>(R.id.etTimeInput)
        val btnGetSpark = findViewById<Button>(R.id.btnGetSpark)
        val btnReset = findViewById<Button>(R.id.btnReset)
        val tvSuggestion = findViewById<TextView>(R.id.tvSuggestion)

        // --- SECTION 2: THE MAIN LOGIC ---
        // This code runs only when the user clicks the "Find a Spark" button
        btnGetSpark.setOnClickListener {

            // Get the text from the input field, remove extra spaces, and make it lowercase
            val userInput = etTimeInput.text.toString().trim().lowercase()

            // Use Logcat to track what the user is typing (Requirement: Testing/Logs)
            Log.d("SocialSparkLog", "User typed: $userInput")

            // BEGINNER LOGIC: Using if-statements to decide the social suggestion
            // This satisfies the "Social Spark Suggestion Logic" requirement
            val resultMessage = if (userInput == "morning") {
                "☀️ Send a 'Good morning' text to a family member."
            } else if (userInput == "mid-morning") {
                "☕ Reach out to a colleague with a quick 'Thank you'."
            } else if (userInput == "afternoon") {
                "🤳 Share a funny meme or interesting link with a friend."
            } else if (userInput == "afternoon snack time") {
                "🍪 Send a quick 'thinking of you' message."
            } else if (userInput == "dinner") {
                "📞 Call a friend or relative for a 5-minute catch-up."
            } else if (userInput == "night" || userInput == "after dinner") {
                "🌙 Leave a thoughtful comment on a friend's post."
            }

            // --- SECTION 3: ERROR HANDLING ---
            // If the user didn't type anything or typed something we don't recognize
            else if (userInput.isEmpty()) {
                "⚠️ Please enter a time of day to get a suggestion!"
            } else {
                "🤔 I don't recognize that time. Try 'Morning' or 'Afternoon'!"
            }

            // Display the final suggestion on the screen
            tvSuggestion.text = resultMessage
        }

        // --- SECTION 4: RESET FUNCTIONALITY ---
        // This clears the screen so the user can start over (Requirement: Reset Button)
        btnReset.setOnClickListener {
            etTimeInput.text.clear() // Clears the input box
            tvSuggestion.text = "Waiting for your input..." // Resets the instruction text
            Log.d("SocialSparkLog", "The app was reset by the user.")
        }
    }
}