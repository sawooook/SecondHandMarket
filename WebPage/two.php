<HTML>
<HEAD>
<TITLE>팝업창</TITLE>
<SCRIPT language="JavaScript">
function setCookie(name, value, expiredays){
        var todayDate = new Date();
        todayDate.settime(todayDate.gettime()+1*1000 );
        document.cookie = name + "=" + escape(value) + "; path=/; expires=" + todayDate.toGMTString() + ";"
}

function closeWin(){
        if(document.checkClose.Notice.checked){
                setCookie( "notice1", "1" , 1);
        }
        self.close();
}
</SCRIPT>
</HEAD>
<body>
<form name="checkClose">
        <input type="checkbox" name="Notice">오늘 하루만 이창 띄우지 않기
        <a href=javascript:closeWin()>창닫기</a>
</form>
</body>
</HTML>
