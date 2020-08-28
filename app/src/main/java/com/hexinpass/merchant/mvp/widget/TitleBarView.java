package com.hexinpass.merchant.mvp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hexinpass.merchant.R;
import com.hexinpass.merchant.mvp.util.UiUtils;


/**
 * 功能描述: 标题栏
 */
public class TitleBarView extends FrameLayout {

    private int leftImgSrc;
    private String titleText;
    private String rightText;
    private String leftText;
    private int rightImgSrc;
    private int leftImgVisible;
    private int rightTextVisible;
    private int leftTextVisible;
    private int rightImgVisible;
    private View titleLayoutLeft;

    RelativeLayout rlCommonBar;

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
        view.setBackgroundColor(bgColor);
    }

    private int bgColor;
    private int titleTextColor;

    private ImageView titleLeftBtn;
    private View view;

    public ImageView getTitleRightBtn() {
        return titleRightBtn;
    }

    private ImageView titleRightBtn;
    private TextView titleLeftText;
    public TextView titleRightText;
    private TextView title;

    int statusBarHeight = -1;


    public TitleBarView(Context context) {
        super(context);
        initView();
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.TitleBarStyle);
    }

    public TitleBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBarView, defStyleAttr, R.style.DefaultTitleBarStyle);
        try {
            leftImgSrc = typedArray.getResourceId(R.styleable.TitleBarView_left_img_src, R.mipmap.ic_back);
            titleText = typedArray.getString(R.styleable.TitleBarView_title_text);
            rightText = typedArray.getString(R.styleable.TitleBarView_right_text);
            leftText = typedArray.getString(R.styleable.TitleBarView_left_text);
            rightImgSrc = typedArray.getResourceId(R.styleable.TitleBarView_right_img_src, R.mipmap.ic_back);
            leftImgVisible = typedArray.getInt(R.styleable.TitleBarView_left_img_visible, VISIBLE);
            rightTextVisible = typedArray.getInt(R.styleable.TitleBarView_right_text_visible, VISIBLE);
            leftTextVisible = typedArray.getInt(R.styleable.TitleBarView_left_text_visible, VISIBLE);
            rightImgVisible = typedArray.getInt(R.styleable.TitleBarView_right_img_visible, GONE);
            bgColor = typedArray.getColor(R.styleable.TitleBarView_bg_color, getResources().getColor(R.color.title_bar_bg_color));
            titleTextColor = typedArray.getColor(R.styleable.TitleBarView_title_text_color,getResources().getColor(R.color.text_title));
        } finally {
            typedArray.recycle();
        }
        initView();
    }

    private void initView() {
        setup();
        view = LayoutInflater.from(getContext()).inflate(R.layout.base_title_bar, null);
        view.setBackgroundColor(bgColor);
        title = (TextView) view.findViewById(R.id.title);
        title.setTextColor(titleTextColor);
        titleLeftText = (TextView) view.findViewById(R.id.title_left_txt);
        titleLeftBtn = (ImageView) view.findViewById(R.id.title_left_btn);
        titleRightBtn = (ImageView) view.findViewById(R.id.title_right_btn);
        titleRightText = (TextView) view.findViewById(R.id.title_right_txt);

        titleLayoutLeft = view.findViewById(R.id.rl_left);

        rlCommonBar = (RelativeLayout) view.findViewById(R.id.rl_common_bar);

        title.setText(titleText);
        titleLeftBtn.setVisibility(leftImgVisible);
        titleLeftBtn.setImageResource(leftImgSrc);
        titleRightBtn.setVisibility(rightImgVisible);
        titleRightBtn.setImageResource(rightImgSrc);
        titleRightText.setText(rightText);
        titleRightText.setVisibility(rightTextVisible);
        titleLeftText.setText(leftText);
        titleLeftText.setVisibility(leftTextVisible);


        this.addView(view);
    }

    public ImageView getRightImg(){
        return titleRightBtn;
    }

    public void setInfoBar() {
        this.rlCommonBar.setVisibility(View.GONE);
    }


    public void setup() {
        int compatPadingTop = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            compatPadingTop = getStatusBarHeight();
        }
        this.setPadding(getPaddingLeft(), getPaddingTop() + compatPadingTop, getPaddingRight(), getPaddingBottom());
    }

    public int getStatusBarHeight() {
        if (statusBarHeight == -1){
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusBarHeight = getResources().getDimensionPixelSize(resourceId);
            }
            Log.d("CompatToolbar", "状态栏高度：" + UiUtils.px2dip(statusBarHeight) + "dp");
        }
        return statusBarHeight;
    }


    public void setTitleText(CharSequence titleStr) {
        title.setText(titleStr);
    }

    public void setTitleText(@StringRes int titleRes) {
        title.setText(titleRes);
    }

    public String getTitleText() {
        return title.getText().toString();
    }

    public void setTitleColor(@ColorRes int colorRes) {
        title.setTextColor(getResources().getColor(colorRes));
    }

    public void setTextSize(int sizeSp) {
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, sizeSp);
    }

    public void setLeftBtnVisibility(int visibility) {
        titleLeftBtn.setVisibility(visibility);
    }

    public void setLeftBtnSrc(@DrawableRes int srcRes) {
        titleLeftBtn.setImageResource(srcRes);
    }

    public void setRightBtnVisibility(int visibility) {
        titleRightBtn.setVisibility(visibility);
    }

    public void setRightBtnSrc(@DrawableRes int srcRes) {
        titleRightBtn.setImageResource(srcRes);
    }

    public void setTitleRightText(Character titleStr) {
        titleRightText.setText(titleStr);
    }

    public void setTitleLeftText(Character titleStr) {
        titleLeftText.setText(titleStr);
    }

    public void setTitleRightText(@StringRes int titleRes) {
        titleRightText.setText(titleRes);
    }

    public void setTitleLeftText(@StringRes int titleRes) {
        titleLeftText.setText(titleRes);
    }

    public String getTitleRightText() {
        return titleRightText.getText().toString();
    }

    public String getTitleLeftText() {
        return titleLeftText.getText().toString();
    }

    public void setTitleRightColor(@ColorRes int colorRes) {
        titleRightText.setTextColor(getResources().getColor(colorRes));
    }

    public void setTitleLeftColor(@ColorRes int colorRes) {
        titleLeftText.setTextColor(getResources().getColor(colorRes));
    }

    public void setTextRightSize(int sizeSp) {
        titleRightText.setTextSize(TypedValue.COMPLEX_UNIT_SP, sizeSp);
    }

    public void setTextLeftSize(int sizeSp) {
        titleLeftText.setTextSize(TypedValue.COMPLEX_UNIT_SP, sizeSp);
    }

    public void setRightTextVisibility(int visibility) {
        titleRightText.setVisibility(visibility);
    }

    public void setLeftTextVisibility(int visibility) {
        titleLeftText.setVisibility(visibility);
    }

    public void setOnLeftBtnClickListener(@NonNull OnClickListener listener){
        titleLayoutLeft.setOnClickListener(listener);
    }

    public void setOnRightBtnClickListener(@NonNull OnClickListener listener){
        titleRightBtn.setOnClickListener(listener);
    }

    public void setOnRightTextClickListener(@NonNull OnClickListener listener){
        titleRightText.setOnClickListener(listener);
    }

    public void setOnLeftTextClickListener(@NonNull OnClickListener listener){
        titleLeftText.setOnClickListener(listener);
    }

    public void setTitleRightStr(String s) {
        titleRightText.setText(s);
    }


}
