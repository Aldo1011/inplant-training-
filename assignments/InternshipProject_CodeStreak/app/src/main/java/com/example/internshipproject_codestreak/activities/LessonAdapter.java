package com.example.internshipproject_codestreak.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.internshipproject_codestreak.R;
import com.example.internshipproject_codestreak.viewmodel.Lesson;

import java.util.List;

public class LessonAdapter
        extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {

    public interface OnLessonClickListener {
        void onLessonClick(Lesson lesson);
    }

    private final List<Lesson> lessons;
    private final OnLessonClickListener listener;


    public LessonAdapter(
            List<Lesson> lessons,
            OnLessonClickListener listener
    ) {
        this.lessons = lessons;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lesson, parent, false);

        return new LessonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull LessonViewHolder holder,
            int position
    ) {

        Lesson lesson = lessons.get(position);

        // Reset recycled view
        holder.container.setTranslationX(0f);
        holder.node.setScaleX(1.0f);
        holder.node.setScaleY(1.0f);

        // --------------------------------
        // ZIG-ZAG POSITION
        // --------------------------------

        if (position % 2 == 0) {
            holder.container.setTranslationX(-180f);

        } else {
            holder.container.setTranslationX(180f);

        }

        // --------------------------------
        // LESSON TITLE
        // --------------------------------

        holder.title.setText(lesson.getTitle());

        // --------------------------------
        // LESSON IMAGE
        // --------------------------------

        holder.node.setImageResource(
                getLessonImage(lesson.getId())
        );

        // --------------------------------
        // LOCKED / UNLOCKED
        // --------------------------------

        if (!lesson.isUnlocked()) {

            holder.node.setAlpha(0.4f);

        } else {

            holder.node.setAlpha(1.0f);
        }

        // --------------------------------
        // CLICK
        // --------------------------------

        holder.itemView.setOnClickListener(v -> {

            if (!lesson.isUnlocked()) {
                return;
            }

            holder.node.animate()
                    .scaleX(0.85f)
                    .scaleY(0.85f)
                    .setDuration(100)
                    .withEndAction(() -> {

                        holder.node.animate()
                                .scaleX(1.1f)
                                .scaleY(1.1f)
                                .setDuration(120)
                                .withEndAction(() -> {

                                    listener.onLessonClick(lesson);

                                })
                                .start();

                    })
                    .start();
        });
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    // --------------------------------
    // CHOOSE IMAGE FOR EACH LESSON
    // --------------------------------

    private int getLessonImage(int lessonId) {

        switch (lessonId) {

            case 1:
                return R.drawable.lesson_house;

            case 2:
                return R.drawable.building_cabin;

            case 3:
                return R.drawable.building_dock;

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

    // --------------------------------
    // VIEW HOLDER
    // --------------------------------

    static class LessonViewHolder
            extends RecyclerView.ViewHolder {

        ImageView node;
        TextView title;

        FrameLayout container;
        FrameLayout content;



        public LessonViewHolder(
                @NonNull View itemView
        ) {

            super(itemView);

            node = itemView.findViewById(
                    R.id.lessonNode
            );

            title = itemView.findViewById(
                    R.id.lessonTitle
            );

            container = itemView.findViewById(
                    R.id.lessonContainer
            );

            content = itemView.findViewById(
                    R.id.lessonContent
            );


        }
    }
}