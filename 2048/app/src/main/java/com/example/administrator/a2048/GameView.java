package com.example.administrator.a2048;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/3.
 */

public class GameView extends GridLayout {
    private static final String TAG = "GameView";

    public GameView(Context context) {
        super(context);
        intiGameView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        intiGameView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intiGameView();
    }


    boolean isInit = true;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "onSizeChanged: ");
        int itemViewwidth = (w - 10) / 4;
        addItemView(itemViewwidth);

        startGame();


    }

    private void startGame() {
        MainActivity.getMainActivity().clearNumber();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                itemViews[x][y].setNumber(0);
            }

        }

        addRandomNum();
        addRandomNum();
    }


    private ItemView[][] itemViews = new ItemView[4][4];
    private List<Point> points = new ArrayList<>();


    private void addItemView(int width) {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {

//                LayoutParams layoutParams = new LayoutParams();
//                layoutParams.width = width - 5;
//                layoutParams.height = width - 5;
//                layoutParams.setMargins(5, 5, 5, 5);
                ItemView itemView = new ItemView(getContext());
                itemView.setNumber(0);
                itemViews[x][y] = itemView;
                addView(itemView, width, width);
            }
        }
    }

    private void addRandomNum() {
        Log.d(TAG, "addRandomNum: ");

//        if (null != points && !points.isEmpty()) {
        points.clear();
//        }
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (itemViews[x][y].getNumber() <= 0) {
                    points.add(new Point(x, y));
                }
            }
        }

        Point point = points.remove((int) (Math.random() * points.size()));
        itemViews[point.x][point.y].setNumber(Math.random() > 0.3 ? 2 : 4);
    }

    private void intiGameView() {
        setColumnCount(4);
        setBackgroundResource(R.color.color1);

        setOnTouchListener(new OnTouchListener() {
            private float startX, startY, endX, endY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                switch (action) {
                    case MotionEvent.ACTION_DOWN:
//                        Log.e(TAG, "按下");
                        startX = event.getX();
                        startY = event.getY();
//                        Log.e(TAG, "X = " + startX);
//                        Log.e(TAG, "Y = " + startY);

                        break;
                    case MotionEvent.ACTION_UP:
//                        Log.e(TAG, "抬起");
                        endX = event.getX() - startX;
                        endY = event.getY() - startY;
                        if (Math.abs(endX) > Math.abs(endY)) {
                            if (endX < -15) {
                                Log.e(TAG, "左滑");
                                left();
                            } else if (endX > 15) {
                                Log.e(TAG, "右滑");
                                right();
                            }
                        } else {
                            if (endY < -15) {
                                Log.e(TAG, "上滑");
                                up();
                            } else if (endY > 15) {
                                Log.e(TAG, "下滑");
                                down();
                            }
                        }
                        break;

                    case MotionEvent.ACTION_MOVE:
//                        Log.e(TAG, "拖动");
                        break;

                }
                //我消化了这个事件
                return true;
            }
        });
    }

    int number = 0;

    private void up() {
        boolean isChange = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                for (int y1 = y + 1; y1 < 4; y1++) {
                    if (itemViews[x][y1].getNumber() > 0) {
                        if (itemViews[x][y].getNumber() <= 0) {
                            itemViews[x][y].setNumber(itemViews[x][y1].getNumber());
                            itemViews[x][y1].setNumber(0);
                            isChange = true;
                            y--;
                        } else if (itemViews[x][y].getNumber() == itemViews[x][y1].getNumber()) {
                            itemViews[x][y].setNumber(itemViews[x][y].getNumber() * 2);

//                            number = number +(itemViews[x][y].getNumber());
//                            Log.e(TAG, "number = " + number);
                            MainActivity.getMainActivity().addNumber(itemViews[x][y].getNumber());

                            itemViews[x][y1].setNumber(0);
                            isChange = true;
                        }
                        break;
                    }
                }
            }
        }
        if (isChange) {
            addRandomNum();
            check();
        }
    }


    private void down() {
        boolean isChange = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 3; y >= 0; y--) {
                for (int y1 = y - 1; y1 >= 0; y1--) {
                    if (itemViews[x][y1].getNumber() > 0) {
                        if (itemViews[x][y].getNumber() <= 0) {
                            itemViews[x][y].setNumber(itemViews[x][y1].getNumber());
                            itemViews[x][y1].setNumber(0);
                            isChange = true;
                            y++;
                        } else if (itemViews[x][y].getNumber() == itemViews[x][y1].getNumber()) {
                            itemViews[x][y].setNumber(itemViews[x][y].getNumber() * 2);
                            itemViews[x][y1].setNumber(0);
                            isChange = true;
                            MainActivity.getMainActivity().addNumber(itemViews[x][y].getNumber());


//                            number = number +(itemViews[x][y].getNumber());
//                            Log.e(TAG, "number = " + number);
                        }
                        break;
                    }
                }
            }
        }
        if (isChange) {
            addRandomNum();
            check();
        }
    }

    private void left() {

        boolean isChange = false;

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                for (int x1 = x + 1; x1 < 4; x1++) {
                    if (itemViews[x1][y].getNumber() > 0) {
                        if (itemViews[x][y].getNumber() <= 0) {
                            itemViews[x][y].setNumber(itemViews[x1][y].getNumber());
                            itemViews[x1][y].setNumber(0);
                            isChange = true;
                            x--;
                        } else if (itemViews[x][y].getNumber() == itemViews[x1][y].getNumber()) {
                            itemViews[x][y].setNumber(itemViews[x][y].getNumber() * 2);
                            itemViews[x1][y].setNumber(0);
                            isChange = true;

                            MainActivity.getMainActivity().addNumber(itemViews[x][y].getNumber());

//                            number = number +(itemViews[x][y].getNumber());
//                            Log.e(TAG, "number = " + number);
                        }
                        break;
                    }
                }
            }
        }
        if (isChange) {
            addRandomNum();
            check();
        }
    }

    private void right() {
        boolean isChange = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 3; x >= 0; x--) {
                for (int x1 = x - 1; x1 >= 0; x1--) {
                    if (itemViews[x1][y].getNumber() > 0) {
                        if (itemViews[x][y].getNumber() <= 0) {
                            itemViews[x][y].setNumber(itemViews[x1][y].getNumber());
                            itemViews[x1][y].setNumber(0);
                            isChange = true;
                            x++;
                        } else if (itemViews[x][y].getNumber() == itemViews[x1][y].getNumber()) {
                            itemViews[x][y].setNumber(itemViews[x][y].getNumber() * 2);
                            itemViews[x1][y].setNumber(0);
                            isChange = true;

                            MainActivity.getMainActivity().addNumber(itemViews[x][y].getNumber());
//                            number = number +(itemViews[x][y].getNumber());
//                            Log.e(TAG, "number = " + number);
                        }
                        break;
                    }
                }
            }
        }

        if (isChange) {
            addRandomNum();
            check();
        }
    }

    private void check() {
        boolean complete = true;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (itemViews[x][y].getNumber() == 0 ||
                        (x > 0 && itemViews[x][y].getNumber() == itemViews[x - 1][y].getNumber()) ||
                        (x < 3 && itemViews[x][y].getNumber() == itemViews[x + 1][y].getNumber()) ||
                        (y > 0 && itemViews[x][y].getNumber() == itemViews[x][y - 1].getNumber()) ||
                        (y < 3 && itemViews[x][y].getNumber() == itemViews[x][y + 1].getNumber())) {
                    complete = false;
                }
            }
        }
        if (complete) {
            //Toast.makeText(getContext(), "结束", Toast.LENGTH_SHORT).show();
            new AlertDialog.Builder(getContext()).setTitle("提示").setMessage("游戏结束").setPositiveButton("重来", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    Toast.makeText(getContext(), "重来", Toast.LENGTH_SHORT).show();
                    startGame();
                }
            }).setCancelable(false).show();

        }
    }

}
