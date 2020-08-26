package com.example.kotlin_practice_app.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatImageView
import com.example.kotlin_practice_app.R
import kotlin.properties.Delegates

class RoundImageView : AppCompatImageView {

    companion object {
        const val CIRCLE = 0
        const val FILLET = 1
        private const val DEFAULT_RADIUS = 8
    }

    private var mShape = CIRCLE
    private var mCoverColor by Delegates.notNull<Int>()
    private var mRadius by Delegates.notNull<Int>()

    private lateinit var mPaint: Paint
    private lateinit var mPath: Path

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView)
        mShape = ta.getInt(R.styleable.RoundImageView_shape, CIRCLE)
        mCoverColor = ta.getColor(
            R.styleable.RoundImageView_cover_color,
            resources.getColor(R.color.colorSurface)
        )
        mRadius = ta.getDimensionPixelOffset(
            R.styleable.RoundImageView_radius,
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                DEFAULT_RADIUS.toFloat(), resources.displayMetrics
            ).toInt()
        )
        ta.recycle()

        mPaint = Paint()
        mPaint.color = mCoverColor
        mPaint.isAntiAlias = true
        mPath = Path()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        when (mShape) {
            CIRCLE -> {
                drawCircleCover(canvas)
            }
            FILLET -> {
                drawFilletCover(canvas)
            }
        }
    }

    private fun drawCircleCover(canvas: Canvas) {
        val radius = width.coerceAtMost(height) / 2
        mPath.addRect(0F, 0F, width.toFloat(), height.toFloat(), Path.Direction.CCW)
        mPath.addCircle(
            width.toFloat() / 2,
            height.toFloat() / 2,
            radius.toFloat(),
            Path.Direction.CW
        )
        canvas.drawPath(mPath, mPaint)
    }

    private fun drawFilletCover(canvas: Canvas) {
        val rectf = RectF(0F, 0F, width.toFloat(), height.toFloat())
        mPath.addRect(rectf, Path.Direction.CCW)
        mPath.addRoundRect(rectf, mRadius.toFloat(), mRadius.toFloat(), Path.Direction.CW)
        canvas.drawPath(mPath, mPaint)
    }
}