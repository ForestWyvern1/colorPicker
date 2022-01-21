package ru.a1exs.colorpicker

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import ru.a1exs.colorpicker.databinding.ColorPickerBinding

@SuppressLint("ClickableViewAccessibility", "SetTextI18n")
class ColorPicker @JvmOverloads constructor(
    private val ctx: Context,
    private val attributeSet: AttributeSet?,
    private val defAttrStyle: Int = 0
) : ConstraintLayout(ctx, attributeSet, defAttrStyle) {

    private val binding = ColorPickerBinding.inflate(LayoutInflater.from(ctx), this)
    lateinit var onColorChange : (pixel : Int) -> Unit

    init {
        setBackgroundResource(R.drawable.color_picker_background)
        val attrs = ctx.obtainStyledAttributes(attributeSet, R.styleable.ColorPicker)

        var userText : String? = ""
        userText = attrs.getString(R.styleable.ColorPicker_text)

        binding.userText.text = userText

        binding.palette.isDrawingCacheEnabled = true
        binding.palette.buildDrawingCache(true)

        binding.palette.setOnTouchListener { v, event ->

            val bitmap = binding.palette.drawingCache

            if (event.action == MotionEvent.ACTION_MOVE || event.action == MotionEvent.ACTION_DOWN) {
                if (event.x >= 0 && event.y >= 0 && event.x < bitmap.width && event.y < bitmap.height) {

                    binding.marker.apply {
                        visibility = View.VISIBLE
                        x = event.x + 20
                        y = event.y + 280
                    }

                    val pixel = bitmap.getPixel(event.x.toInt(), event.y.toInt())
                    onColorChange(pixel)

                    binding.textColor.setTextColor(pixel)
                    binding.textColor.text = "HEX code of color is : ${Integer.toHexString(pixel).toUpperCase()}"

                }
            }

            return@setOnTouchListener true
        }


    }

}