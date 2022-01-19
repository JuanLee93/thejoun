<%@ page contentType="text/html; charset=utf-8" %>
<title>PROJECT</title>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<link href="<%=request.getContextPath()%>/css/admin/basic.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/admin/layout.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/admin/board.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/admin/manage.css" rel="stylesheet" type="text/css" />

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/smarteditor/js/HuskyEZCreator.js"></script>
<style>
#searchForm {
    position: relative;
    width: 100%;
    margin: 0px;
    padding: 9px 0;
    text-align: center;
    vertical-align: middle;
}

#stype {
    width: 115px;
    height: 20px;
    border: 1px solid #cccccc;
    font-size: 12px;
    vertical-align: middle;
    color: #706a5f;
    margin: 0 5px 0 0;
    display: inline;
}
#sval1{
	width : 160px;
	height : 20px;
	back-ground: #FFFFFF;
	
}
</style>