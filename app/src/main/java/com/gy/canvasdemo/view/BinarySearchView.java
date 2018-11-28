package com.gy.canvasdemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.gy.canvasdemo.analyze.gold12.HelpDraw;
import com.gy.canvasdemo.analyze.gold12.JudgeMan;
import com.gy.canvasdemo.ds.tree.BinarySearchTree;
import com.gy.canvasdemo.model.TreeNode;
import com.gy.canvasdemo.view.other.OnBSTClickListener;

import java.util.ArrayList;

/**
 * 说明：数组实现表结构---测试视图
 */
public class BinarySearchView<E extends Comparable<E>> extends View {
    private Point mCoo = new Point(200, 150);//坐标系
    private Picture mCooPicture;//坐标系canvas元件
    private Picture mGridPicture;//网格canvas元件


    private Path mPath;//主路径
    private Paint mPaint;//主画笔
    private Paint mTxtPaint;//数字画笔
    private Paint mPathPaint;//路径画笔
    private Paint mCtrlPaint;//几个圆的画笔

    private BinarySearchTree<TreeNode<E>> mTreeBalls = new BinarySearchTree<>();

    private OnBSTClickListener mOnBSTClickListener;
    private int selectIndex = -1;//当前选中的索引
    private ValueAnimator mAnimator;


    private static final int OFFSET_X = 10;//X空隙
    private static final int OFFSET_Y = 60;//Y空隙
    private static final int OFFSET_OF_TXT_Y = 10;//文字的偏移


    private static final int NODE_RADIUS = 40;//文字的偏移

    private static final int VIEW_X = 0;//数组盒子的圆角
    private static final int VIEW_Y = 0;//数组盒子的圆角
    private static final int VIEW_H = 900;//数组盒子的圆角
    private static final int VIEW_W = 1700;//数组盒子的圆角


    private static final int ROOT_X = (VIEW_W - VIEW_X) / 2;//数组盒子的圆角
    private static final int ROOT_Y = 50;//数组盒子的圆角


    private static final Point[] CTRL_POS = new Point[]{//控制按钮的点位
            new Point(-100, 100),//插入
            new Point(-100, 300),//移除
            new Point(-100, 500),//遍历
            new Point(-100, 700),//包含

            new Point(700, -70),//删除最大
            new Point(700 + 300, -70),//删除最小
            new Point(700 + 300 * 2, -70),//获取最大
            new Point(700 + 300 * 3, -70),//获取最小
    };


    private static int[] CTRL_COLOR = new int[]{//控制按钮的颜色
            0xff1EF519,//插入
            0xff2992F2,//移除
            0xffFC1693,//遍历
            0xffF50C0C,//包含

            0xff1EF519,//删除最大
            0xffB946F4,//删除最小
            0xffF50C0C,//获取最大
            0xffF46410,//获取最小
    };

    private static final String[] CTRL_TXT = new String[]{//控制按钮的文字
            "插入",//插入
            "移除",//移除
            "遍历",//遍历
            "包含",//包含

            "删除max",//删除最大
            "删除min",//删除最小
            "获取max",//获取最大
            "获取min",//获取最小
    };

    private static final int CTRL_RADIUS = 50;//控制按钮的半径
    private Paint mHelpPaint;


    public void setOnBSTClickListener(OnBSTClickListener onBSTClickListener) {
        mOnBSTClickListener = onBSTClickListener;
    }

    public BinarySearchView(Context context) {
        this(context, null);
    }

    public BinarySearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();//初始化
    }

    private void init() {
        //初始化主画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(5);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(50);
        //初始化主路径
        mPath = new Path();
        //初始化文字画笔
        mTxtPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTxtPaint.setColor(Color.WHITE);
        mTxtPaint.setTextAlign(Paint.Align.CENTER);
        mTxtPaint.setTextSize(40);
        //辅助视图画笔
        mHelpPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHelpPaint.setColor(0x442EC7F8);
        //初始化路径画笔
        mPathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPathPaint.setColor(Color.GRAY);
        mPathPaint.setStrokeWidth(4);

        mPathPaint.setStyle(Paint.Style.STROKE);
        mCooPicture = HelpDraw.getCoo(getContext(), mCoo,false);
        mGridPicture = HelpDraw.getGrid(getContext());
        //初始化圆球按钮画笔
        mCtrlPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCtrlPaint.setColor(Color.RED);
        mCtrlPaint.setTextAlign(Paint.Align.CENTER);
        mCtrlPaint.setTextSize(30);
        //初始化时间流ValueAnimator
        mAnimator = ValueAnimator.ofFloat(0, 1);
        mAnimator.setRepeatCount(-1);
        mAnimator.setDuration(2000);
        mAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mAnimator.setInterpolator(new LinearInterpolator());


        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                invalidate();
            }
        });

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        HelpDraw.draw(canvas, mGridPicture);
        canvas.save();
        canvas.translate(mCoo.x, mCoo.y);//画布移到坐标原点

        helpView(canvas);//辅助视图

        mTxtPaint.setColor(Color.WHITE);

        dataView(canvas);//核心操作展示
        ctrlView(canvas);//操作按钮
        canvas.restore();
        HelpDraw.draw(canvas, mCooPicture);
    }

    /**
     * 绘制表结构
     *
     * @param canvas
     */
    private void dataView(Canvas canvas) {
        if (!mTreeBalls.isEmpty()) {
            canvas.save();
            canvas.translate(ROOT_X, ROOT_Y);
            BinarySearchTree<TreeNode<E>>.Node root = mTreeBalls.getRoot();
            canvas.drawCircle(0, 0, NODE_RADIUS, mPaint);
            canvas.drawText(root.el.data.toString(), 0, 10, mTxtPaint);
            drawNode(canvas, root);
            canvas.restore();

        }
    }

    private void drawNode(Canvas canvas, BinarySearchTree<TreeNode<E>>.Node node) {
        float thta = (float) ((60 - node.deep * 10) * Math.PI / 180);//父节点与子节点竖直方向夹角
        int lineLen = (int) (150 / ((node.deep + .5)));//线长

        float offsetX = (float) (NODE_RADIUS * Math.sin(thta));//将起点偏移圆心X，到圆上
        float offsetY = (float) (NODE_RADIUS * Math.cos(thta));//将起点偏移圆心X，到圆上

        //画布移动的X
        float translateOffsetX = (float) ((lineLen + 2 * NODE_RADIUS) * Math.sin(thta));
        //画布移动的Y
        float translateOffsetY = (float) ((lineLen + 2 * NODE_RADIUS) * Math.cos(thta));

        float moveX = (float) (lineLen * Math.sin(thta));//线移动的X
        float moveY = (float) (lineLen * Math.cos(thta));//线移动的Y

        if (node.right != null) {
            canvas.save();
            canvas.translate(translateOffsetX, translateOffsetY);//每次将画布移到右子的圆心
            canvas.drawCircle(0, 0, NODE_RADIUS, mPaint);//画圆
            mPath.reset();//画线
            mPath.moveTo(-offsetX, -offsetY);
            mPath.lineTo(-offsetX, -offsetY);
            mPath.rLineTo(-moveX, -moveY);
            canvas.drawPath(mPath, mPathPaint);
            canvas.drawText(node.right.el.data.toString(), 0, 10, mTxtPaint);//画字
            drawNode(canvas, node.right);
            canvas.restore();
        }
        if (node.left != null) {//同理
            canvas.save();
            canvas.translate(-translateOffsetX, translateOffsetY);
            mPath.reset();
            mPath.moveTo(offsetX, -offsetY);
            mPath.rLineTo(moveX, -moveY);
            canvas.drawPath(mPath, mPathPaint);
            canvas.drawCircle(0, 0, NODE_RADIUS, mPaint);
            canvas.drawText(node.left.el.data.toString(), 0, 10, mTxtPaint);
            drawNode(canvas, node.left);
            canvas.restore();
        }
    }

    /**
     * 绘制数组的长度个空白矩形
     *
     * @param canvas
     */
    private void helpView(Canvas canvas) {
        canvas.drawRoundRect(VIEW_X, VIEW_Y, VIEW_X + VIEW_W, VIEW_X + VIEW_H, 20, 20, mHelpPaint);
    }

    /**
     * 控制面板--圆球
     *
     * @param canvas 画布
     */
    private void ctrlView(Canvas canvas) {
        for (int i = 0; i < CTRL_POS.length; i++) {
            mCtrlPaint.setColor(CTRL_COLOR[i]);
            canvas.drawRoundRect(CTRL_POS[i].x - 100, CTRL_POS[i].y - 40, CTRL_POS[i].x + 100, CTRL_POS[i].y + 40, 20, 20, mCtrlPaint);
            canvas.drawText(CTRL_TXT[i], CTRL_POS[i].x, CTRL_POS[i].y + OFFSET_OF_TXT_Y, mTxtPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float downX = event.getX() - mCoo.x;
                float downY = event.getY() - mCoo.y;

                for (int i = 0; i < CTRL_POS.length; i++) {
                    //区域判定
                    if (JudgeMan.judgeCircleArea(
                            CTRL_POS[i].x, CTRL_POS[i].y,
                            downX, downY, CTRL_RADIUS * 2f)) {
                        if (mOnBSTClickListener != null) {
                            switch (i) {
                                case 0://插入
                                    mOnBSTClickListener.onAdd(this);
                                    break;
                                case 1://移除
                                    mOnBSTClickListener.onRemove(this);
//                                    contactTest();
                                    break;
                                case 2://遍历
                                    mOnBSTClickListener.onOrder(this);
                                    break;
                                case 3://包含
                                    mOnBSTClickListener.onContains(this);
                                    break;
                                case 4://删除最大
                                    mOnBSTClickListener.onRemoveMax(this);
                                    break;
                                case 5://删除最小
                                    mOnBSTClickListener.onRemoveMin(this);
                                    break;
                                case 6://获取最大
                                    mOnBSTClickListener.onGetMax(this);
                                    break;
                                case 7://获取最小
                                    mOnBSTClickListener.onGetMin(this);
                                    break;
                            }
                            CTRL_COLOR[i] = 0xff54E1F8;//点击更换颜色
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP://还原颜色
                CTRL_COLOR[0] = 0xff1EF519;
                CTRL_COLOR[1] = 0xff2992F2;
                CTRL_COLOR[2] = 0xffB946F4;
                CTRL_COLOR[3] = 0xffF50C0C;
                CTRL_COLOR[4] = 0xff1EF519;
                CTRL_COLOR[5] = 0xffB946F4;
                CTRL_COLOR[6] = 0xffF50C0C;
                CTRL_COLOR[7] = 0xffF46410;
                break;
        }
        invalidate();
        return true;
    }


    public void addData(E data) {
        TreeNode<E> treeNode = new TreeNode<>(data);
        mTreeBalls.add(treeNode);
    }

    public void removeMin() {
        mTreeBalls.removeMin();
        invalidate();
    }

    public void removeMax() {
        mTreeBalls.removeMax();
        invalidate();
    }


    public void remove(E data) {
        mTreeBalls.remove(new TreeNode<E>(data));
        invalidate();
    }

    public boolean contains(E data) {
        return mTreeBalls.contains(new TreeNode<E>(data));
    }

    public ArrayList<E> order(OrderType type) {
        ArrayList<TreeNode<E>> nodes = new ArrayList<>();
        switch (type) {
            case PREV://前序遍历
                mTreeBalls.orderPer(nodes);
                break;
            case IN://中序遍历
                mTreeBalls.orderIn(nodes);
                break;
            case POST://后序遍历
                mTreeBalls.orderPost(nodes);
                break;
            case LEVEL://层序遍历
                mTreeBalls.orderLevel(nodes);
                break;
        }

        ArrayList<E> es = new ArrayList<>();
        for (TreeNode<E> node : nodes) {
            es.add(node.data);
        }
        return es;
    }


    public E getMax() {
        return mTreeBalls.getMax().data;
    }

    public E getMin() {
        return mTreeBalls.getMin().data;
    }

    public enum OrderType {
        LEVEL,//层序遍历
        PREV,//前序遍历
        IN,//中序遍历
        POST,//后序遍历
    }
}