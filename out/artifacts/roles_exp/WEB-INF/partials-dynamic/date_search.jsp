<%
    String mes = (String) request.getAttribute(Const.FILTER_MONTH);
%>
<form class="container-range" method="post">
    <button href="#" name="previous" class="previous">
        &#8249;
        <span class="tooltiptext">Mes Anterior</span>
    </button>
    <strong><%=mes%></strong>
    <button href="#" name="next" class="next">
        &#8250;
        <span class="tooltiptext">Mes Siguiente</span>
    </button>
</form>
