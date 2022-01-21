package ru.a1exs.colorpicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.a1exs.colorpicker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.colorPicker.onColorChange = {pixel ->
            binding.backView.setBackgroundColor(pixel)
            window.statusBarColor = pixel
        }

    }
}