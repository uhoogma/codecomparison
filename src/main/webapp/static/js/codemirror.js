$(window).load(
  function createCodeEditors() {
  
    if (CodeMirror === undefined)
        return;
    var options = {
        lineNumbers: true,
        matchBrackets: true,
        tabMode: 'shift',
        mode: "text/x-java",
        onCursorActivity: function (editor) {
            if ('hlLine' in editor) {
                editor.setLineClass(editor.hlLine, null);
            }
            editor.hlLine = editor.setLineClass(editor.getCursor().line, "activeline");
        }
    };

    var area =  document.getElementById('java-code');
    area.cm = CodeMirror.fromTextArea(area, options);
        area.cm.setSize("100%" , 500);

    var area2 =  document.getElementById('java-code2');
    area2.cm = CodeMirror.fromTextArea(area2, options);
        area2.cm.setSize("100%", 500);

}
);