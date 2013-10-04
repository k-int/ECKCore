
function JSONSyntaxHighlight(json) {
    if (typeof json != 'string') {
        json = JSON.stringify(json, undefined, 2);
    }
    json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
    return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function (match) {
        var cls = 'jsonNumber';
        if (/^"/.test(match)) {
            if (/:$/.test(match)) {
                cls = 'jsonKey';
            } else {
                cls = 'jsonString';
            }
        } else if (/true|false/.test(match)) {
            cls = 'jsonBoolean';
        } else if (/null/.test(match)) {
            cls = 'jsonNull';
        }
        return '<span class="' + cls + '">' + match + '</span>';
    });
}
