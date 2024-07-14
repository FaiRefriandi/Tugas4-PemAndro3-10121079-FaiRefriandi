package com.froztlass.mephisto

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import coil.load
import com.froztlass.mephisto.api.SuperheroResponse
import com.froztlass.mephisto.viewmodel.SuperheroViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

//---  NAMA : FA'I REFRIANDI
//---  NIM : 10121079
//---  KELAS : PEM-ANDRO 3

class MainActivity : ComponentActivity() {
    private val viewModel: SuperheroViewModel by viewModels()
    private lateinit var progressBar: ProgressBar
    private lateinit var superheroImage: ImageView
    private lateinit var superheroName: TextView
    private lateinit var superheroPublisher: TextView
    private lateinit var superheroGender: TextView
    private lateinit var superheroOccupation: TextView
    private lateinit var intelligenceBar: ProgressBar
    private lateinit var intelligenceValue: TextView
    private lateinit var strengthBar: ProgressBar
    private lateinit var strengthValue: TextView
    private lateinit var speedBar: ProgressBar
    private lateinit var speedValue: TextView
    private lateinit var durabilityBar: ProgressBar
    private lateinit var durabilityValue: TextView
    private lateinit var powerBar: ProgressBar
    private lateinit var powerValue: TextView
    private lateinit var combatBar: ProgressBar
    private lateinit var combatValue: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set status bar and navigation bar to transparent
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        window.navigationBarColor = android.graphics.Color.TRANSPARENT

        // Optional: To make status bar icons and navigation bar icons darker
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController?.isAppearanceLightStatusBars = true
        insetsController?.isAppearanceLightNavigationBars = true

        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        superheroImage = findViewById(R.id.superheroImage)
        superheroName = findViewById(R.id.superheroName)
        superheroPublisher = findViewById(R.id.superheroPublisher)
        superheroGender = findViewById(R.id.superheroGender)
        superheroOccupation = findViewById(R.id.superheroOccupation)
        intelligenceBar = findViewById(R.id.intelligenceBar)
        intelligenceValue = findViewById(R.id.intelligenceValue)
        strengthBar = findViewById(R.id.strengthBar)
        strengthValue = findViewById(R.id.strengthValue)
        speedBar = findViewById(R.id.speedBar)
        speedValue = findViewById(R.id.speedValue)
        durabilityBar = findViewById(R.id.durabilityBar)
        durabilityValue = findViewById(R.id.durabilityValue)
        powerBar = findViewById(R.id.powerBar)
        powerValue = findViewById(R.id.powerValue)
        combatBar = findViewById(R.id.combatBar)
        combatValue = findViewById(R.id.combatValue)

        lifecycleScope.launch {
            viewModel.superhero.collect { superhero ->
                updateUI(superhero)
            }
        }

        viewModel.fetchSuperhero()
    }

    private fun updateUI(superhero: SuperheroResponse?) {
        progressBar.visibility = View.GONE
        if (superhero != null) {
            superheroImage.load(superhero.image.url)
            superheroName.text = superhero.name
            superheroPublisher.text = "Publisher: ${superhero.biography.publisher}"
            superheroGender.text = "Gender: ${superhero.appearance.gender}"
            superheroOccupation.text = "Occupation: ${superhero.work.occupation}"

            // Convert string to int and set progress, value and color
            val intelligence = superhero.powerstats.intelligence.toIntOrNull() ?: 0
            setProgressBarColor(intelligenceBar, intelligence)
            intelligenceValue.text = intelligence.toString()

            val strength = superhero.powerstats.strength.toIntOrNull() ?: 0
            setProgressBarColor(strengthBar, strength)
            strengthValue.text = strength.toString()

            val speed = superhero.powerstats.speed.toIntOrNull() ?: 0
            setProgressBarColor(speedBar, speed)
            speedValue.text = speed.toString()

            val durability = superhero.powerstats.durability.toIntOrNull() ?: 0
            setProgressBarColor(durabilityBar, durability)
            durabilityValue.text = durability.toString()

            val power = superhero.powerstats.power.toIntOrNull() ?: 0
            setProgressBarColor(powerBar, power)
            powerValue.text = power.toString()

            val combat = superhero.powerstats.combat.toIntOrNull() ?: 0
            setProgressBarColor(combatBar, combat)
            combatValue.text = combat.toString()
        }
    }

    private fun setProgressBarColor(progressBar: ProgressBar, value: Int) {
        progressBar.progress = value

        val color = when {
            value <= 33 -> R.color.yellow
            value <= 66 -> R.color.orange
            else -> R.color.red
        }

        progressBar.progressDrawable.setColorFilter(
            ContextCompat.getColor(this, color), PorterDuff.Mode.SRC_IN
        )
    }
}
