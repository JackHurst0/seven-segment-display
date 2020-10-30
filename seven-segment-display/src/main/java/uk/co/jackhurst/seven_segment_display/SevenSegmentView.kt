package uk.co.jackhurst.seven_segment_display

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import java.lang.IllegalStateException

class SevenSegmentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var digits = 0
    private var digitString = "0"
    private var onPaint: Paint? = null
    private var offPaint: Paint? = null
    private var onColor = Color.BLACK
    private var offColor = Color.TRANSPARENT
    private var zeroPadding = 0

    private var segmentLength = 120f
    private var segmentThickness = 20f
    private var digitSpacing = 20f

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.SevenSegmentView)
        segmentThickness =
            attributes.getDimension(R.styleable.SevenSegmentView_segmentThickness, 20f)
        segmentLength = attributes.getDimension(R.styleable.SevenSegmentView_segmentLength, 120f)
        digitSpacing = attributes.getDimension(R.styleable.SevenSegmentView_digitSpacing, 20f)
        onColor = attributes.getColor(R.styleable.SevenSegmentView_onColor, Color.BLACK)
        offColor = attributes.getColor(R.styleable.SevenSegmentView_offColor, Color.TRANSPARENT)
        zeroPadding = attributes.getInt(R.styleable.SevenSegmentView_minDisplay, 0)
        digits = attributes.getInt(R.styleable.SevenSegmentView_digits, 0)
        digitString = digits.toString().padStart(zeroPadding, '0')

    }

    // Todo should probably let use specify wrap content match parent etc too.
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(
            (segmentLength * digitString.length + digitSpacing * (digitString.length - 1)).toInt(),
            (segmentLength * 2).toInt()
        )
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            digitString.forEachIndexed { index, c ->
                val number = when (c) {
                    '0' -> Number.Zero()
                    '1' -> Number.One()
                    '2' -> Number.Two()
                    '3' -> Number.Three()
                    '4' -> Number.Four()
                    '5' -> Number.Five()
                    '6' -> Number.Six()
                    '7' -> Number.Seven()
                    '8' -> Number.Eight()
                    '9' -> Number.Nine()
                    else -> Number.Undefined()
                }
                drawNumber(canvas, number, index)
            }
        }
    }

    fun setDigits(newDigits: Int) {
        val oldDigits = digitString
        digits = newDigits
        digitString = digits.toString().padStart(zeroPadding, '0')
        if (oldDigits != digitString) {
            requestLayout()
        } else {
            invalidate()
        }
    }

    private fun drawNumber(canvas: Canvas, number: Number, index: Int) {
        for (segment in number.segments) {
            drawSegment(canvas, segment, (segmentLength + digitSpacing) * index)
        }
    }

    /**
     * Draw a segment of the display. A horizontal segment looks like <=====>
     * param [startX]: The x of the top left of the minimal boundingRectangle around
     *                 the segment shape
     * param [startX]: The y of the top left of the minimal boundingRectangle around
     *                 the segment shape
     * param [length]: When horizontal, the length from the leftmost part of the
     *                 shape to the rightmost part of the shape. When vertical the
     *                 length from the topmost part of the shape to the bottommost
     *                 part of the shape
     * param [thickness]: When horizontal, the length from the topmost part of the
     *                    shape to the bottommost part of the shape. When vertical the
     *                    length from the leftmost part of the shape to the rightmost
     *                    part of the shape
     * param [verticalOrientation]: If true the segment is vertical, otherwise it's horizontal
     */
    private fun drawSegment(
        canvas: Canvas,
        startX: Float,
        startY: Float,
        length: Float,
        thickness: Float,
        verticalOrientation: Boolean,
        on: Boolean
    ) {
        // The extremity of the shape should be like a triangle with a 90 degree tip. We know this
        // triangle has base equal to [thickness]. Using Pythagoras or trig to work out you want
        // the triangle height to be half the base width (which is [thickness])

        val path = Path()
        val cornerTriangleHeight = thickness * 0.5f
        if (verticalOrientation) {
            path.moveTo(startX + thickness / 2, startY)
            path.lineTo(startX + thickness, startY + cornerTriangleHeight)
            path.lineTo(startX + thickness, startY + length - cornerTriangleHeight)
            path.lineTo(startX + thickness / 2, startY + length)
            path.lineTo(startX, startY + length - cornerTriangleHeight)
            path.lineTo(startX, startY + cornerTriangleHeight)
            path.lineTo(startX + thickness / 2, startY)
        } else {
            path.moveTo(startX, startY + thickness / 2)
            path.lineTo(startX + cornerTriangleHeight, startY + thickness)
            path.lineTo(startX + length - cornerTriangleHeight, startY + thickness)
            path.lineTo(startX + length, startY + thickness / 2)
            path.lineTo(startX + length - cornerTriangleHeight, startY)
            path.lineTo(startX + cornerTriangleHeight, startY)
            path.lineTo(startX, startY + thickness / 2)
        }

        path.close()
        canvas.drawPath(
            path, if (on) {
                getOnPaint()
            } else {
                getOffPaint()
            }
        )
    }

    private fun drawSegment(canvas: Canvas, segment: Segment, offset: Float) {
        val startX = offset + if (segment.orientation == Orientation.HORIZONTAL) {
            segmentThickness / 2f
        } else {
            when (segment.positionHorizontal) {
                PositionHorizontal.START -> 0f
                PositionHorizontal.END -> segmentLength - segmentThickness
            }
        }

        val startY = if (segment.orientation == Orientation.VERTICAL) {
            when (segment.positionVertical) {
                PositionVertical.TOP -> segmentThickness / 2f
                PositionVertical.MIDDLE -> segmentLength
                PositionVertical.BOTTOM -> throw IllegalStateException(
                    "Cannot have vertical segment at bottom"
                )
            }
        } else {
            when (segment.positionVertical) {
                PositionVertical.TOP -> 0f
                PositionVertical.MIDDLE -> segmentLength - segmentThickness / 2f
                PositionVertical.BOTTOM -> segmentLength * 2 - segmentThickness
            }
        }
        val segmentLength: Float = if (segment.orientation == Orientation.VERTICAL) {
            segmentLength - segmentThickness / 2
        } else {
            segmentLength - segmentThickness
        }
        drawSegment(
            canvas,
            startX,
            startY,
            segmentLength,
            segmentThickness,
            segment.orientation == Orientation.VERTICAL,
            segment.state == State.ON
        )
    }

    private fun getOnPaint(): Paint {
        if (onPaint == null) {
            onPaint = Paint().apply {
                color = onColor
                strokeWidth = 1f
                style = Paint.Style.FILL
            }
        }
        return onPaint!!
    }

    private fun getOffPaint(): Paint {
        if (offPaint == null) {
            offPaint = Paint().apply {
                color = offColor
                strokeWidth = 1f
                style = Paint.Style.FILL
            }
        }
        return offPaint!!
    }
}