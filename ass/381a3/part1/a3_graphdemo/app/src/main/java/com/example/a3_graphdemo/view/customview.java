package com.example.a3_graphdemo.view;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint;

public class customview extends View {
    private static final int Square_size_def = 200;
    private Paint mPaintSquare;
    private int mSquareColor;
    private int mSquareSize;

    private Rect mRectSquare;
    private Paint mpaintcircle;
    private Paint mpaintcolor;
    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public customview(Context context) {
        super(context);
    }

    /**
     * Constructor that is called when inflating a view from XML. This is called
     * when a view is being constructed from an XML file, supplying attributes
     * that were specified in the XML file. This version uses a default style of
     * 0, so the only attribute values applied are those in the Context's Theme
     * and the given AttributeSet.
     *
     * <p>
     * The method onFinishInflate() will be called after all children have been
     * added.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     * @param attrs   The attributes of the XML tag that is inflating the view.
    // * @see #View(Context, AttributeSet, int)
     */
    //@androidx.annotation.Nullable
    public customview(Context context,  AttributeSet attrs) {
        super(context, attrs);

        init(null);

    }

    /**
     * Perform inflation from XML and apply a class-specific base style from a
     * theme attribute. This constructor of View allows subclasses to use their
     * own base style when they are inflating. For example, a Button class's
     * constructor would call this version of the super class constructor and
     * supply <code>R.attr.buttonStyle</code> for <var>defStyleAttr</var>; this
     * allows the theme's button style to modify all of the base view attributes
     * (in particular its background) as well as the Button class's attributes.
     *
     * @param context      The Context the view is running in, through which it can
     *                     access the current theme, resources, etc.
     * @param attrs        The attributes of the XML tag that is inflating the view.
     * @param defStyleAttr An attribute in the current theme that contains a
     *                     reference to a style resource that supplies default values for
     *                     the view. Can be 0 to not look for defaults.
   //  * @see #View(Context, AttributeSet)
     */
    //@androidx.annotation.Nullable
    public customview(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    /**
     * Perform inflation from XML and apply a class-specific base style from a
     * theme attribute or style resource. This constructor of View allows
     * subclasses to use their own base style when they are inflating.
     * <p>
     * When determining the final value of a particular attribute, there are
     * four inputs that come into play:
     * <ol>
     * <li>Any attribute values in the given AttributeSet.
     * <li>The style resource specified in the AttributeSet (named "style").
     * <li>The default style specified by <var>defStyleAttr</var>.
     * <li>The default style specified by <var>defStyleRes</var>.
     * <li>The base values in this theme.
     * </ol>
     * <p>
     * Each of these inputs is considered in-order, with the first listed taking
     * precedence over the following ones. In other words, if in the
     * AttributeSet you have supplied <code>&lt;Button * textColor="#ff000000"&gt;</code>
     * , then the button's text will <em>always</em> be black, regardless of
     * what is specified in any of the styles.
     *
     * @param context      The Context the view is running in, through which it can
     *                     access the current theme, resources, etc.
     * @param attrs        The attributes of the XML tag that is inflating the view.
     * @param defStyleAttr An attribute in the current theme that contains a
     *                     reference to a style resource that supplies default values for
     *                     the view. Can be 0 to not look for defaults.
     * @param defStyleRes  A resource identifier of a style resource that
     *                     supplies default values for the view, used only if
     *                     defStyleAttr is 0 or can not be found in the theme. Can be 0
     *                     to not look for defaults.
    // * @see #View(Context, AttributeSet, int)
     */
    //@androidx.annotation.Nullable
    public customview(Context context,  AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }
    private  void init(@Nullable AttributeSet set){


        //if(set == null){
        //    return;
        //    TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.customview);

           // int squareColor = ta.getColor(android.support.compat.R.styleable)
            //        ta.recycle();
        mpaintcolor = new Paint();
        mpaintcolor.setAntiAlias(true);
        mpaintcolor.setColor(Color.parseColor("#00ccff"));
        }

    protected void onDraw(Canvas canvas){
        mRectSquare.left = 50;
        mRectSquare.top = 50;
        mRectSquare.right = mRectSquare.left + mSquareSize;
        mRectSquare.bottom = mRectSquare.top + mSquareSize;
        //canvas.drawColor(Color.RED);
        float cx, cy;
        float radius = 100f;

        cx = getWidth() - radius - 50f;
        cy = mRectSquare.top + (mRectSquare.height()/2);

        canvas.drawCircle(60,cy,radius,mpaintcolor);
        canvas.drawCircle(140,cy,radius,mpaintcolor);
        canvas.drawCircle(200,cy,radius,mpaintcolor);
        canvas.drawCircle(440,cy,radius,mpaintcolor);
        canvas.drawCircle(310,cy,radius,mpaintcolor);
        canvas.drawCircle(710,cy,radius,mpaintcolor);
        canvas.drawCircle(800,cy,radius,mpaintcolor);
    }
}
