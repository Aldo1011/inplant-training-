package com.example.internshipproject_codestreak.viewmodel;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.TextureView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.filament.Engine;
import com.google.android.filament.utils.Float3;
import com.google.android.filament.utils.ModelViewer;
import com.google.android.filament.android.UiHelper;
import com.google.android.filament.utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class Lesson3DView extends TextureView {

    private static final String TAG = "Lesson3DView";

    private ModelViewer modelViewer;
    private Choreographer choreographer;

    public Lesson3DView(
            @NonNull Context context,
            @Nullable AttributeSet attrs
    ) {
        super(context, attrs);

        setOpaque(false);

        Utils.INSTANCE.init();

        choreographer = Choreographer.getInstance();

        // Create Filament engine
        Engine engine = Engine.create();

        // Create UI helper for TextureView
        UiHelper uiHelper = new UiHelper(
                UiHelper.ContextErrorPolicy.DONT_CHECK
        );

        uiHelper.setOpaque(false);

        /*
         * ModelViewer has a TextureView constructor:
         *
         * TextureView
         * Engine
         * UiHelper
         * Manipulator
         *
         * We don't need touch-camera controls yet,
         * so the manipulator is null.
         */
        modelViewer = new ModelViewer(
                this,
                engine,
                uiHelper,
                null
        );

        loadModel();

        choreographer.postFrameCallback(frameCallback);
    }

    private void loadModel() {

        try {

            InputStream inputStream =
                    getContext()
                            .getAssets()
                            .open("building-house.glb");

            int size = inputStream.available();

            byte[] bytes = new byte[size];

            int totalBytesRead = 0;

            while (totalBytesRead < size) {

                int bytesRead = inputStream.read(
                        bytes,
                        totalBytesRead,
                        size - totalBytesRead
                );

                if (bytesRead == -1) {
                    break;
                }

                totalBytesRead += bytesRead;
            }

            inputStream.close();

            Log.d(
                    TAG,
                    "GLB bytes loaded: " + totalBytesRead
            );

            ByteBuffer buffer =
                    ByteBuffer.wrap(
                            bytes,
                            0,
                            totalBytesRead
                    );

            buffer.rewind();

            // Load the Kenney GLB
            modelViewer.loadModelGlb(buffer);

            /*
             * Center and scale the model.
             *
             * The default position used by Filament
             * is approximately (0, 0, -4).
             */
            modelViewer.transformToUnitCube(
                    new Float3(
                            0.0f,
                            0.0f,
                            -4.0f
                    )
            );

            Log.d(
                    TAG,
                    "building-house.glb sent to Filament"
            );

        } catch (IOException e) {

            Log.e(
                    TAG,
                    "Could not load building-house.glb",
                    e
            );
        }
    }

    private final Choreographer.FrameCallback frameCallback =
            new Choreographer.FrameCallback() {

                @Override
                public void doFrame(long frameTimeNanos) {

                    if (modelViewer != null) {

                        /*
                         * We are not using the camera
                         * manipulator, so position the camera
                         * ourselves.
                         */
                        modelViewer.getCamera().lookAt(
                                0.0,
                                0.0,
                                1.5,

                                0.0,
                                0.0,
                                -4.0,

                                0.0,
                                1.0,
                                0.0
                        );

                        modelViewer.render(
                                frameTimeNanos
                        );
                    }

                    choreographer.postFrameCallback(this);
                }
            };

    @Override
    protected void onDetachedFromWindow() {

        if (choreographer != null) {

            choreographer.removeFrameCallback(
                    frameCallback
            );
        }

        if (modelViewer != null) {

            modelViewer.destroy();

            modelViewer = null;
        }

        super.onDetachedFromWindow();
    }
}