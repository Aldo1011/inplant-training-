package com.example.internshipproject_codestreak.viewmodel;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

import org.json.JSONObject;

public class CodeExecutionEngine {

    private static final String MODULE_NAME =
            "codestreak_executor";

    private CodeExecutionEngine() {
        // Utility class
    }

    public static ExecutionResult execute(
            String code,
            String input
    ) {

        try {

            Python python =
                    Python.getInstance();

            PyObject module =
                    python.getModule(MODULE_NAME);

            PyObject result =
                    module.callAttr(
                            "execute",
                            code,
                            input == null ? "" : input
                    );

            String json =
                    result.toString();

            JSONObject object =
                    new JSONObject(json);

            return new ExecutionResult(
                    object.optBoolean(
                            "success",
                            false
                    ),
                    object.optString(
                            "output",
                            ""
                    ),
                    object.optString(
                            "error",
                            ""
                    )
            );

        } catch (Exception e) {

            return new ExecutionResult(
                    false,
                    "",
                    e.getClass().getSimpleName()
                            + ": "
                            + e.getMessage()
            );
        }
    }


    public static class ExecutionResult {

        private final boolean success;
        private final String output;
        private final String error;

        public ExecutionResult(
                boolean success,
                String output,
                String error
        ) {
            this.success = success;
            this.output = output;
            this.error = error;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getOutput() {
            return output;
        }

        public String getError() {
            return error;
        }
    }

}
