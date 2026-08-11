package com.example.internshipproject_codestreak.viewmodel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.internshipproject_codestreak.R;
import com.example.internshipproject_codestreak.models.Lesson;

import java.util.ArrayList;
import java.util.List;

public class CodeStreakMapView extends FrameLayout {

    private final Paint pathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Path path = new Path();

    private final List<Lesson> lessons = new ArrayList<>();

    private OnLessonClickListener listener;

    private final int nodeSize;
    private final int verticalSpacing;

    // X positions as a percentage of screen width.
    // This gives us a less mechanical map.
    private final float[] xPositions = {
            0.50f,
            0.30f,
            0.65f,
            0.35f,
            0.70f,
            0.45f,
            0.65f
    };

    public interface OnLessonClickListener {
        void onLessonClick(Lesson lesson);
    }

    public CodeStreakMapView(
            @NonNull Context context,
            @Nullable AttributeSet attrs
    ) {
        super(context, attrs);

        setWillNotDraw(false);

        nodeSize = dp(165);
        verticalSpacing = dp(210);

        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(dp(10));
        pathPaint.setStrokeCap(Paint.Cap.ROUND);
        pathPaint.setStrokeJoin(Paint.Join.ROUND);

        // Temporary path color.
        // We'll style this later.
        pathPaint.setColor(Color.rgb(205, 205, 205));

        setClipChildren(false);
    }

    public void setLessons(
            List<Lesson> lessonList,
            OnLessonClickListener clickListener
    ) {

        lessons.clear();

        if (lessonList != null) {
            lessons.addAll(lessonList);
        }

        listener = clickListener;

        buildMap();

        requestLayout();
        invalidate();
    }

    private void buildMap() {

        removeAllViews();

        for (int i = 0; i < lessons.size(); i++) {

            Lesson lesson = lessons.get(i);

            FrameLayout lessonContainer =
                    new FrameLayout(getContext());

            lessonContainer.setTag(lesson);

            // -----------------------------
            // BUILDING
            // -----------------------------

            ImageView node =
                    new ImageView(getContext());

            node.setScaleType(
                    ImageView.ScaleType.CENTER_INSIDE
            );

            node.setImageResource(
                    getLessonImage(lesson.getId())
            );

            if (lesson.isUnlocked()) {
                node.setAlpha(1.0f);
            } else {
                node.setAlpha(0.35f);
            }

            FrameLayout.LayoutParams
                    nodeParams =
                    new FrameLayout.LayoutParams(
                            nodeSize,
                            nodeSize
                    );

            nodeParams.gravity =
                    Gravity.CENTER_HORIZONTAL;

            lessonContainer.addView(
                    node,
                    nodeParams
            );

            // -----------------------------
            // TITLE
            // -----------------------------

            TextView title =
                    new TextView(getContext());

            title.setText(
                    lesson.getTitle()
            );

            title.setTextSize(16);
            title.setTextColor(
                    Color.rgb(45, 45, 45)
            );

            title.setGravity(
                    Gravity.CENTER
            );

            title.setTypeface(
                    null,
                    android.graphics.Typeface.BOLD
            );

            FrameLayout.LayoutParams
                    titleParams =
                    new FrameLayout.LayoutParams(
                            LayoutParams.WRAP_CONTENT,
                            dp(40)
                    );

            titleParams.gravity =
                    Gravity.TOP | Gravity.CENTER_HORIZONTAL;

            titleParams.topMargin =
                    nodeSize - dp(5);

            lessonContainer.addView(
                    title,
                    titleParams
            );

            // -----------------------------
            // CLICK
            // -----------------------------

            lessonContainer.setClickable(true);

            lessonContainer.setOnClickListener(v -> {

                if (!lesson.isUnlocked()) {
                    return;
                }

                // Small bounce animation.
                lessonContainer.animate()
                        .scaleX(0.92f)
                        .scaleY(0.92f)
                        .setDuration(80)
                        .withEndAction(() -> {

                            lessonContainer.animate()
                                    .scaleX(1.0f)
                                    .scaleY(1.0f)
                                    .setDuration(100)
                                    .withEndAction(() -> {

                                        if (listener != null) {
                                            listener.onLessonClick(
                                                    lesson
                                            );
                                        }

                                    })
                                    .start();

                        })
                        .start();
            });

            addView(lessonContainer);
        }
    }

    private int getLessonImage(int lessonId) {

        switch (lessonId) {

            case 1:
                return R.drawable.lesson_house;

            case 2:
                return R.drawable.building_cabin;

            case 3:
                return R.drawable.building_port;

            case 4:
                return R.drawable.building_tower;

            case 5:
                return R.drawable.building_castle;

            case 6:
                return R.drawable.building_village;

            case 7:
                return R.drawable.building_wizard_tower;

            default:
                return R.drawable.lesson_house;
        }
    }

    @Override
    protected void onMeasure(
            int widthMeasureSpec,
            int heightMeasureSpec
    ) {

        int width =
                MeasureSpec.getSize(widthMeasureSpec);

        int height =
                dp(100)
                        + lessons.size()
                        * verticalSpacing;

        int resolvedHeight =
                resolveSize(
                        height,
                        heightMeasureSpec
                );

        setMeasuredDimension(
                width,
                resolvedHeight
        );

        for (int i = 0; i < getChildCount(); i++) {

            View child = getChildAt(i);

            measureChild(
                    child,
                    widthMeasureSpec,
                    MeasureSpec.makeMeasureSpec(
                            verticalSpacing,
                            MeasureSpec.EXACTLY
                    )
            );
        }
    }

    @Override
    protected void onLayout(
            boolean changed,
            int left,
            int top,
            int right,
            int bottom
    ) {

        int width = getWidth();

        for (int i = 0; i < getChildCount(); i++) {

            View child = getChildAt(i);

            int childWidth =
                    child.getMeasuredWidth();

            int childHeight =
                    child.getMeasuredHeight();

            float position;

            if (i < xPositions.length) {
                position = xPositions[i];
            } else {
                // Repeat the pattern for future lessons.
                position =
                        (i % 2 == 0)
                                ? 0.65f
                                : 0.35f;
            }

            int centerX =
                    (int) (width * position);

            int childLeft =
                    centerX - childWidth / 2;

            int childTop =
                    dp(30) + i * verticalSpacing;

            child.layout(
                    childLeft,
                    childTop,
                    childLeft + childWidth,
                    childTop + childHeight
            );
        }
    }

    @Override
    protected void dispatchDraw(
            @NonNull Canvas canvas
    ) {

        drawLessonPath(canvas);

        super.dispatchDraw(canvas);
    }

    private void drawLessonPath(Canvas canvas) {

        if (lessons.size() < 2) {
            return;
        }

        int width = getWidth();

        path.reset();

        for (int i = 0; i < lessons.size() - 1; i++) {

            Lesson currentLesson = lessons.get(i);
            Lesson nextLesson = lessons.get(i + 1);

            // -----------------------------------------
            // ONLY CONNECT UNLOCKED LESSONS
            // -----------------------------------------

            if (!currentLesson.isUnlocked()
                    || !nextLesson.isUnlocked()) {

                continue;
            }

            // -----------------------------------------
            // X POSITIONS
            // -----------------------------------------

            float startX =
                    width * getXPosition(i);

            float endX =
                    width * getXPosition(i + 1);

            // -----------------------------------------
            // TOP OF EACH LESSON
            // -----------------------------------------

            float currentTop =
                    dp(30)
                            + i * verticalSpacing;

            float nextTop =
                    dp(30)
                            + (i + 1) * verticalSpacing;

            // -----------------------------------------
            // START AT BOTTOM OF CURRENT TEXT
            // -----------------------------------------
            //
            // Image occupies:
            //
            // 0 → nodeSize
            //
            // Text begins around:
            //
            // nodeSize - 2
            //
            // Text height ≈ 40dp
            //
            // Therefore the bottom of the text is
            // approximately nodeSize + 38dp.
            //

            float startY =
                    currentTop
                            + nodeSize
                            + dp(40);

            // -----------------------------------------
            // END AT TOP OF NEXT IMAGE
            // -----------------------------------------
            //
            // The next image starts at nextTop.
            //
            // We enter it slightly below its top edge
            // so the line visually touches the island.
            //

            float endY =
                    nextTop
                            + dp(20);

            // -----------------------------------------
            // CURVE
            // -----------------------------------------

            float distance =
                    endY - startY;

            float curveAmount =
                    distance * 0.45f;

            path.moveTo(
                    startX,
                    startY
            );

            path.cubicTo(

                    startX,
                    startY + curveAmount,

                    endX,
                    endY - curveAmount,

                    endX,
                    endY
            );
        }

        canvas.drawPath(
                path,
                pathPaint
        );
    }
    private float getXPosition(int index) {

        if (index < xPositions.length) {
            return xPositions[index];
        }

        return (index % 2 == 0)
                ? 0.65f
                : 0.35f;
    }

    private int dp(float value) {

        return (int) (
                value
                        * getResources()
                        .getDisplayMetrics()
                        .density
                        + 0.5f
        );
    }
}