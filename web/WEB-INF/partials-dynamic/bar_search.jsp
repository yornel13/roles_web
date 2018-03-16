<%
    String searchText = (String) request.getAttribute(Const.FILTER_DATA);
    if (searchText == null) searchText = "";
%>
<div class="row">
    <div class="col-sm-6 col-sm-offset-3">
        <div id="imaginary_container">
            <form class="input-group stylish-input-group input-append">
                <input value="<%=searchText%>" name="<%=Const.SEARCH_TEXT%>" class="form-control" placeholder="Buscar empleado" >
                <span class="input-group-addon">
                    <button class="searchButton" name="search">
                        <span class="glyphicon glyphicon-search"></span>
                    </button>
                </span>
            </form>
        </div>
    </div>
</div>