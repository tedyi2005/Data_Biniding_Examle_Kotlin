package com.example.data_bining_example.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.lifecycle.ViewModelProviders
import com.example.data_bining_example.R
import com.example.data_bining_example.view_model.Popularity
import com.example.data_bining_example.view_model.Simple_View_Model


class MainActivity : AppCompatActivity() {

    // Obtain ViewModel from ViewModelProviders
    private val viewModel by lazy { ViewModelProviders.of(this).get(Simple_View_Model::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateName()
        updateLikes()
    }

    fun onLike(view: View) {
        viewModel.onLike()
        updateLikes()
    }

    /**
     * So much findViewById! We'll fix that with Data Binding.
     */
    private fun updateName() {
        findViewById<TextView>(R.id.plain_name).text = viewModel.name
        findViewById<TextView>(R.id.plain_lastname).text = viewModel.lastName
    }

    private fun updateLikes() {
        findViewById<TextView>(R.id.likes).text = viewModel.likes.toString()
        findViewById<ProgressBar>(R.id.progressBar).progress =
            (viewModel.likes * 100 / 5).coerceAtMost(100)
        val image = findViewById<ImageView>(R.id.imageView)

        val color = getAssociatedColor(viewModel.popularity, this)

        ImageViewCompat.setImageTintList(image, ColorStateList.valueOf(color))

        image.setImageDrawable(getDrawablePopularity(viewModel.popularity, this))
    }

    private fun getAssociatedColor(popularity: Popularity, context: Context): Int {
        return when (popularity) {
            Popularity.NORMAL -> context.theme.obtainStyledAttributes(
                intArrayOf(android.R.attr.colorForeground)
            ).getColor(0, 0x000000)
            Popularity.POPULAR -> ContextCompat.getColor(context,
                R.color.popular
            )
            Popularity.STAR -> ContextCompat.getColor(context,
                R.color.star
            )
        }
    }

    private fun getDrawablePopularity(popularity: Popularity, context: Context): Drawable? {
        return when (popularity) {
            Popularity.NORMAL -> {
                ContextCompat.getDrawable(context,
                    R.drawable.ic_person_black_96dp
                )
            }
            Popularity.POPULAR -> {
                ContextCompat.getDrawable(context,
                    R.drawable.ic_whatshot_black_96dp
                )
            }
            Popularity.STAR -> {
                ContextCompat.getDrawable(context,
                    R.drawable.ic_whatshot_black_96dp
                )
            }
        }
    }
}
