package androidx.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * 文字标识
 */
public class MarkTipsView extends AppCompatTextView {

    /**
     * 画笔
     */
    private Paint paint;
    /**
     * 宽度
     */
    private int width;
    /**
     * 高度
     */
    private int height;
    /**
     * 三角形边长
     */
    private float triangleSide = dip(10);
    /**
     * 圆角大小
     */
    private float markRadius = dip(8);
    /**
     * 填充颜色
     */
    private int markSolid = Color.parseColor("#F1AA44");
    /**
     * 三角形高度
     */
    private float triangleHeight;


    public MarkTipsView(Context context) {
        super(context);
        initAttributeSet(context, null);
    }

    public MarkTipsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributeSet(context, attrs);
    }

    public MarkTipsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributeSet(context, attrs);
    }

    private void initAttributeSet(Context context, AttributeSet attrs) {
        paint = new Paint();
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MarkTipsView);
            triangleSide = array.getDimension(R.styleable.MarkTipsView_triangleSide, triangleSide);
            markRadius = array.getDimension(R.styleable.MarkTipsView_markRadius, markRadius);
            markSolid = array.getColor(R.styleable.MarkTipsView_markSolid, markSolid);
            array.recycle();
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        triangleHeight = (float) (Math.sin(Math.toRadians(60)) * triangleSide);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int w = widthSpecSize;
        int h = heightSpecSize;
        if (heightSpecMode == MeasureSpec.AT_MOST || heightSpecMode == MeasureSpec.UNSPECIFIED) {
            w = widthSpecSize;
            h = height + (int) triangleHeight;
        }
        setMeasuredDimension(w, h);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), (int) (getPaddingBottom() + triangleHeight));
        setBackgroundDrawable(new BitmapDrawable(getResources(), drawBackgroundBitmap()));
    }

    /**
     * @return 绘制背景Bitmap
     */
    private Bitmap drawBackgroundBitmap() {
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(markSolid);
        //矩形
        Path path = new Path();
        RectF rect = new RectF(0, 0, width, height - triangleHeight);
        path.addRoundRect(rect, markRadius, markRadius, Path.Direction.CCW);
        //三角形
        Path trianglePath = new Path();
        float lineY = height - triangleHeight;
        trianglePath.moveTo(width / 2F - triangleSide / 2F, lineY);
        trianglePath.lineTo(width / 2F, lineY + triangleHeight);
        trianglePath.lineTo(width / 2F + triangleSide / 2F, lineY);
        path.addPath(trianglePath);
        //绘制路径
        canvas.drawPath(path, paint);
        return output;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setText(getText());
    }

    /**
     * @param value px值
     * @return dip值
     */
    private float dip(int value) {
        return Resources.getSystem().getDisplayMetrics().density * value;
    }

    /**
     * @return 三角形边长
     */
    public float getTriangleSide() {
        return triangleSide;
    }

    /**
     * 设置三角形边长
     *
     * @param triangleSide 三角形边长
     */
    public void setTriangleSide(float triangleSide) {
        this.triangleSide = triangleSide;
        invalidate();
    }

    /**
     * 获取圆角大小
     *
     * @return
     */
    public float getMarkRadius() {
        return markRadius;
    }

    /**
     * 设置圆角大小
     *
     * @param radius
     */
    public void setMarkRadius(float radius) {
        this.markRadius = radius;
        invalidate();
    }

    /**
     * @return 标识颜色
     */
    public int getMarkSolid() {
        return markSolid;
    }

    /**
     * 设置标识颜色
     *
     * @param markSolid
     */
    public void setMarkSolid(int markSolid) {
        this.markSolid = markSolid;
        invalidate();
    }
}

