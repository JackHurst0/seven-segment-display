package uk.co.jackhurst.sevensegmentdemo

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.TextView
import uk.co.jackhurst.seven_segment_display.SevenSegmentView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bigSegments = findViewById<SevenSegmentView>(R.id.segment_digits)
        val segments = findViewById<SevenSegmentView>(R.id.segment_digits_red)
        val smallSegments = findViewById<SevenSegmentView>(R.id.segment_digits_small)
        val mirrorText = findViewById<TextView>(R.id.digitMirror)
        segments.digits = 23

        val animator = ValueAnimator.ofInt(0, 500)
        animator.duration = 50000
        animator.addUpdateListener { num ->
            bigSegments.digits = num.animatedValue as Int
            segments.digits = (num.animatedValue as Int) / 10
        }
        animator.interpolator = LinearInterpolator()
        animator.repeatCount = ValueAnimator.INFINITE
        animator.start()

        mirrorText.text = smallSegments.digits.toString()
    }
}