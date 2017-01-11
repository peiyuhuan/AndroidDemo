package com.example.administrator.a2048;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/12/3.
 */

public class ItemView extends FrameLayout {

    TextView item;

    public ItemView(Context context) {
        super(context);
        initItemView();
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initItemView();
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initItemView();
    }

    //44
    //2
    //84

    private void initItemView() {
        item = new TextView(getContext());

        item.setTextSize(40);
        item.setBackgroundResource(R.color.color2);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(10, 10, 0, 0);
        item.setGravity(Gravity.CENTER);

        addView(item, layoutParams);
    }

    int mNumber = 0;

    public void setNumber(int number) {
        mNumber = number;

        if (number <= 0) {
            item.setText("");
        } else {
            item.setText(mNumber + "");
        }

        switch (number){
            case 0:
                item.setBackgroundResource(R.color.color2);
                break;
            case 2:
                item.setBackgroundColor(0xffeee4da);
                break;
            case 4:
                item.setBackgroundColor(0xffede0c8);
                break;
            case 8:
                item.setBackgroundColor(0xfff2b179);
                break;
            case 16:
                item.setBackgroundColor(0xfff59563);
                break;
            case 32:
                item.setBackgroundColor(0xfff67c5f);
                break;
            case 64:
                item.setBackgroundColor(0xfff65e3b);
                break;
            case 128:
                item.setBackgroundColor(0xffedcf72);
                break;
            case 256:
                item.setBackgroundColor(0xffedcc61);
                break;
            case 512:
                item.setBackgroundColor(0xffedc850);
                break;
            case 1024:
                item.setBackgroundColor(0xffedc53f);
                break;
            case 2048:
                item.setBackgroundColor(0xffedc22e);
                break;
            default:
                item.setBackgroundResource(R.color.color2);
                break;
        }
    }

    public int getNumber() {
        return mNumber;
    }


}
