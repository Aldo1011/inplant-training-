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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.internshipproject_codestreak.R;

import java.util.ArrayList;
import java.util.List;

public class CodeStreakMapView extends FrameLayout {

    private final Paint pathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private final Path path = new Path();

    private final Bitmap backgroundBitmap;

    private final Paint backgroundPaint =
            new Paint(Paint.ANTI_ALIAS_FLAG);

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

        backgroundBitmap =
                BitmapFactory.decodeResource(
                        getResources(),
                        R.drawable.home_page_image
                );

        backgroundPaint.setFilterBitmap(true);

        setWillNotDraw(false);

        nodeSize = dp(175);
        verticalSpacing = dp(350);

        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStyle(
                Paint.Style.STROKE
        );

        pathPaint.setStrokeWidth(
                dp(4)
        );

        pathPaint.setStrokeCap(
                Paint.Cap.ROUND
        );

        pathPaint.setStrokeJoin(
                Paint.Join.ROUND
        );

        pathPaint.setColor(
                getResources().getColor(
                        R.color.cs_accent
                )
        );

        pathPaint.setStrokeCap(Paint.Cap.ROUND);
        pathPaint.setStrokeJoin(Paint.Join.ROUND);

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

            // =========================================
            // ISLAND / BUILDING
            // =========================================

            ImageView node =
                    new ImageView(getContext());

            node.setScaleType(
                    ImageView.ScaleType.CENTER_INSIDE
            );

            node.setImageResource(
                    getLessonImage(lesson.getId())
            );

            if (lesson.isUnlocked()) {
                node.setAlpha(1f);
            } else {
                node.setAlpha(0.32f);
            }

            int currentNodeSize =
                    isBossLesson(lesson)
                            ? dp(190)
                            : nodeSize;

            FrameLayout.LayoutParams nodeParams =
                    new FrameLayout.LayoutParams(
                            currentNodeSize,
                            currentNodeSize
                    );

            nodeParams.gravity =
                    Gravity.TOP
                            | Gravity.CENTER_HORIZONTAL;

            lessonContainer.addView(
                    node,
                    nodeParams
            );

            // =========================================
            // CURRENT / AVAILABLE GLOW
            // =========================================

            if (lesson.isUnlocked()
                    && !lesson.isCompleted()) {

                node.setElevation(
                        dp(8)
                );
            }

            // =========================================
            // LESSON NUMBER
            // =========================================

            TextView number =
                    createLessonNumber(
                            lesson.getId(),
                            lesson.isUnlocked()
                    );

            FrameLayout.LayoutParams
                    numberParams =
                    new FrameLayout.LayoutParams(
                            dp(36),
                            dp(36)
                    );

            numberParams.gravity =
                    Gravity.TOP
                            | Gravity.CENTER_HORIZONTAL;

            numberParams.topMargin =
                    dp(4);

            lessonContainer.addView(
                    number,
                    numberParams
            );

            // =========================================
            // TITLE CARD
            // =========================================

            LinearLayout titleCard =
                    new LinearLayout(getContext());

            titleCard.setOrientation(
                    LinearLayout.VERTICAL
            );

            titleCard.setGravity(
                    Gravity.CENTER
            );

            titleCard.setPadding(
                    dp(16),
                    dp(7),
                    dp(16),
                    dp(7)
            );

            titleCard.setBackground(
                    getRoundedBackground(
                            Color.WHITE,
                            dp(18)
                    )
            );

            if (lesson.isUnlocked()) {

                titleCard.setElevation(
                        dp(5)
                );
            }

            TextView title =
                    new TextView(getContext());

            title.setText(
                    lesson.getTitle()
            );

            title.setTextSize(17);

            title.setTypeface(
                    null,
                    android.graphics.Typeface.BOLD
            );

            title.setTextColor(
                    getResources().getColor(
                            R.color.cs_text
                    )
            );

            title.setGravity(
                    Gravity.CENTER
            );

            titleCard.addView(title);

            // =========================================
            // SUBTITLE
            // =========================================

            TextView subtitle =
                    new TextView(getContext());

            subtitle.setText(
                    getLessonSubtitle(
                            lesson.getId()
                    )
            );

            subtitle.setTextSize(12);

            subtitle.setTextColor(
                    getResources().getColor(
                            R.color.cs_text_muted
                    )
            );

            subtitle.setGravity(
                    Gravity.CENTER
            );

            titleCard.addView(subtitle);

            FrameLayout.LayoutParams
                    titleParams =
                    new FrameLayout.LayoutParams(
                            dp(190),
                            dp(62)
                    );

            titleParams.gravity =
                    Gravity.TOP
                            | Gravity.CENTER_HORIZONTAL;

            titleParams.topMargin =
                    currentNodeSize - dp(2);

            lessonContainer.addView(
                    titleCard,
                    titleParams
            );

            // =========================================
            // LOCK ICON
            // =========================================

            if (!lesson.isUnlocked()) {

                TextView lock =
                        new TextView(getContext());

                lock.setText("🔒");

                lock.setTextSize(16);

                lock.setGravity(
                        Gravity.CENTER
                );

                lock.setBackground(
                        getRoundedBackground(
                                getResources()
                                        .getColor(
                                                R.color.cs_locked
                                        ),
                                dp(18)
                        )
                );

                FrameLayout.LayoutParams
                        lockParams =
                        new FrameLayout.LayoutParams(
                                dp(36),
                                dp(36)
                        );

                lockParams.gravity =
                        Gravity.TOP
                                | Gravity.CENTER_HORIZONTAL;

                lockParams.topMargin =
                        currentNodeSize - dp(30);

                lessonContainer.addView(
                        lock,
                        lockParams
                );
            }

            // =========================================
            // CLICK
            // =========================================

            lessonContainer.setClickable(
                    lesson.isUnlocked()
            );

            lessonContainer.setOnClickListener(
                    v -> {

                        if (!lesson.isUnlocked()) {
                            return;
                        }

                        lessonContainer
                                .animate()
                                .scaleX(0.96f)
                                .scaleY(0.96f)
                                .setDuration(80)
                                .withEndAction(() -> {

                                    lessonContainer
                                            .animate()
                                            .scaleX(1f)
                                            .scaleY(1f)
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
                    }
            );

            addView(
                    lessonContainer
            );
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

    private void drawMapBackground(Canvas canvas) {

        if (backgroundBitmap == null) {
            canvas.drawColor(
                    getResources().getColor(
                            R.color.cs_secondary
                    )
            );

            return;
        }

        int viewWidth = getWidth();
        int viewHeight = getHeight();

        int bitmapWidth =
                backgroundBitmap.getWidth();

        int bitmapHeight =
                backgroundBitmap.getHeight();

        if (bitmapWidth <= 0 || bitmapHeight <= 0) {
            return;
        }


        float scale =
                (float) viewWidth
                        / bitmapWidth;

        int scaledHeight =
                (int) (
                        bitmapHeight * scale
                );



        for (
                int y = 0;
                y < viewHeight;
                y += scaledHeight
        ) {

            Rect destination =
                    new Rect(
                            0,
                            y,
                            viewWidth,
                            y + scaledHeight
                    );

            canvas.drawBitmap(
                    backgroundBitmap,
                    null,
                    destination,
                    backgroundPaint
            );
        }
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


    private TextView createLessonNumber(
            int lessonId,
            boolean unlocked
    ) {

        TextView number =
                new TextView(getContext());

        number.setText(
                String.valueOf(lessonId)
        );

        number.setTextSize(16);

        number.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        number.setGravity(
                Gravity.CENTER
        );

        number.setTextColor(
                Color.WHITE
        );

        number.setBackground(
                getRoundedBackground(
                        unlocked
                                ? getResources()
                                  .getColor(
                                          R.color.cs_primary
                                  )
                                : getResources()
                                  .getColor(
                                          R.color.cs_locked
                                  ),
                        dp(20)
                )
        );

        return number;
    }

    private android.graphics.drawable.GradientDrawable
    getRoundedBackground(
            int color,
            int radius
    ) {

        android.graphics.drawable.GradientDrawable
                background =
                new android.graphics.drawable.GradientDrawable();

        background.setColor(color);

        background.setCornerRadius(
                radius
        );

        return background;
    }

    private String getLessonSubtitle(
            int lessonId
    ) {

        switch (lessonId) {

            case 1:
                return "Print your first line";

            case 2:
                return "Variables & memory";

            case 3:
                return "Python's basic types";

            case 4:
                return "Take input from the world";

            case 5:
                return "Make Python calculate";

            case 6:
                return "Change data types";

            case 7:
                return "Make Python speak";

            case 8:
                return "World 1 Boss";

            case 9:
                return "Make your first decision";

            default:
                return "Continue your journey";
        }
    }
    private boolean isBossLesson(
            Lesson lesson
    ) {

        return lesson.getId() == 8
                || lesson.getId() == 17;
    }

}