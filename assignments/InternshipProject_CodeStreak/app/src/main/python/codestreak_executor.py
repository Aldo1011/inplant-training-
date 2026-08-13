import io
import json
import contextlib
import builtins


ALLOWED_BUILTINS = {
    "print": print,
    "input": input,
    "int": int,
    "float": float,
    "str": str,
    "bool": bool,
    "range": range,
    "len": len,
    "abs": abs,
    "min": min,
    "max": max,
    "sum": sum,
}


def execute(code, input_data=""):
    output = io.StringIO()
    error = io.StringIO()

    input_lines = input_data.splitlines()
    input_index = 0

    def controlled_input(prompt=""):
        nonlocal input_index

        if input_index < len(input_lines):
            value = input_lines[input_index]
            input_index += 1
            return value

        return ""

    safe_builtins = dict(ALLOWED_BUILTINS)
    safe_builtins["input"] = controlled_input

    globals_dict = {
        "__builtins__": safe_builtins
    }

    try:
        compiled_code = compile(
            code,
            "<student_code>",
            "exec"
        )

        with contextlib.redirect_stdout(output):
            with contextlib.redirect_stderr(error):
                exec(compiled_code, globals_dict)

        return json.dumps({
            "success": True,
            "output": output.getvalue(),
            "error": ""
        })

    except Exception as e:

        return json.dumps({
            "success": False,
            "output": output.getvalue(),
            "error": f"{type(e).__name__}: {e}"
        })