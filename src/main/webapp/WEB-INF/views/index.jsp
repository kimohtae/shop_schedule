<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <Script src="http://code.jquery.com/jquery-3.4.1.min.js"></Script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" integrity="sha512-uto9mlQzrs59VwILcLiRYeLKPPbS/bT71da/OEBYEwcdNUk8jYIy+D176RYoop1Da+f9mvkYrmj5MCLZWEtQuA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css" integrity="sha512-aOG0c6nPNzGk+5zjwyJaoRUgCdOrfSDhmMID2u4+OIslr0GjpLKo7Xm0Ao3xmpM4T8AmIouRkqwj1nrdVsLKEQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.structure.min.css" integrity="sha512-oM24YOsgj1yCDHwW895ZtK7zoDQgscnwkCLXcPUNsTRwoW1T1nDIuwkZq/O6oLYjpuz4DfEDr02Pguu68r4/3w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.theme.min.css" integrity="sha512-9h7XRlUeUwcHUf9bNiWSTO9ovOWFELxTlViP801e5BbwNJ5ir9ua6L20tEroWZdm+HFBAWBLx2qH4l4QHHlRyg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script>
        $(function(){
            $("#begin, #end").datepicker({dateFormat: 'yy-mm-dd'});
            $("#today").click(function(){
                if($(this).prop("checked")){
                    $("#begin, #end").prop("disabled",true);
                    $("#begin, #end").val(makeDateSting(new Date));
                }else{
                    $("#begin, #end").prop("disabled",false);
                }
            })
            function makeDateSting(dt){
                return dt.getFullYear() + "-" +leadingZero(dt.getMonth()+1) + "-" + leadingZero(dt.getDate());
            }
            function leadingZero(n){
                return n<10 ? "0"+n : ""+n;
            }
            $("#page_conn").click(function(){
                let start = $("#begin").val();
                let end = $("#end").val();
                $.ajax({
                    url:"/api/conn?start="+start+"&end="+end,
                    type:"get",
                    success:function(msg){
                        alert(msg);
                    }
                })
            })
            $("#prod").click(function(){
                let start = $("#begin").val();
                let end = $("#end").val();
                $.ajax({
                    url:"/api/prod?start="+start+"&end="+end,
                    type:"get",
                    success:function(msg){
                        alert(msg);
                    }
                })
            })
            $("#shopping").click(function(){
                let start = $("#begin").val();
                let end = $("#end").val();
                $.ajax({
                    url:"/api/shopping?start="+start+"&end="+end,
                    type:"get",
                    success:function(msg){
                        alert(msg);
                    }
                })
            })
            $("#review").click(function(){
                let start = $("#begin").val();
                let end = $("#end").val();
                $.ajax({
                    url:"/api/review?start="+start+"&end="+end,
                    type:"get",
                    success:function(msg){
                        alert(msg);
                    }
                })
            })
        })
    </script>
</head>
<body>
    <div class="date">
        <input type="checkbox" id="today">
        <label for="today">오늘 데이터</label>
        <br>
        <label for="begin">시작일</label>
        <input type="text" id="begin">
        <label for="end">종료일</label>
        <input type="text" id="end">
        <br>
        <button id="page_conn">페이지 접속기록 로그파일 생성</button>
        <button id="prod">제품 조회 기록 로그파일 생성</button>
        <button id="review">리뷰 기록 로그파일 생성</button>
        <button id="shopping">쇼핑 기록 로그파일 생성</button>
    </div>
</body>
</html>