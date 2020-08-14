<%--
  Created by IntelliJ IDEA.
  User: 程炯
  Date: 2020/7/20
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-1.11.3.js"></script>
</head>
<body>
<script type="text/javascript">
    $(function(){
        var btnEle = $("#btn");
        btnEle.click(function(){
            $.ajax({
                url:'/test',
                type:'post',
                dataType:'json',
                success:function(data){
                    //alert(data.name);
                    console.log(data)
                    var str = data.id+","+data.name+","+data.score;
                    $("#text").val(str)
                }
            });
        });
    });
</script>
<input type="text" id="text" />
<input type="button" id="btn" value="展现" />

</body>
</html>
