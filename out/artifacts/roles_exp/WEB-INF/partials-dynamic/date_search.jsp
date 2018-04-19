<%
    String mes = (String) request.getAttribute(Const.FILTER_MONTH);
%>
<form class="container-range" method="post">
    <button href="#" name="previous" class="previous">
        &#8249;
        <span class="tooltiptext">Mes Anterior</span>
    </button>
    <div style="margin-top: 5px">
        <input style="max-width: 180px" id="month" type="month" name="month"
               value="<%=mes%>" required>
        <span class="validity"></span>
    </div>
    <button href="#" name="next" class="next">
        &#8250;
        <span class="tooltiptext">Mes Siguiente</span>
    </button>
</form>
<script>
    var monthControl = document.querySelector('input[type="month"]');
    monthControl.addEventListener('input', function (evt) {
        post('', {'month': 'month'});
    });
    function post(path, params, method) {
        method = method || "post"; // Set method to post by default if not specified.
        var monthElement = document.querySelector('input[type="month"]');
        var value = String(monthElement.value);
        // The rest of this code assumes you are not using a library.
        // It can be made less wordy if you use one.
        var form = document.createElement("form");
        form.setAttribute("method", method);
        form.setAttribute("action", path);

        for(var key in params) {
            if(params.hasOwnProperty(key)) {
                var hiddenField = document.createElement("input");
                hiddenField.setAttribute("type", "hidden");
                hiddenField.setAttribute("name", key);
                hiddenField.setAttribute("value", value);

                form.appendChild(hiddenField);
            }
        }

        document.body.appendChild(form);
        form.submit();
    }
</script>


