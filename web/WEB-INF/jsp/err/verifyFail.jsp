<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<TITLE>未获取到用户信息</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<STYLE type=text/css></STYLE>
<LINK type=text/css rel=stylesheet>
<STYLE type=text/css>
BODY {
	FONT-SIZE: 9pt;
	COLOR: #842b00;
	LINE-HEIGHT: 16pt;
	FONT-FAMILY: "Tahoma", "宋体";
	TEXT-DECORATION: none
}
TABLE {
	FONT-SIZE: 9pt;
	COLOR: #842b00;
	LINE-HEIGHT: 16pt;
	FONT-FAMILY: "Tahoma", "宋体";
	TEXT-DECORATION: none
}
TD {
	FONT-SIZE: 9pt;
	COLOR: #842b00;
	LINE-HEIGHT: 16pt;
	FONT-FAMILY: "Tahoma", "宋体";
	TEXT-DECORATION: none
}
BODY {
	SCROLLBAR-HIGHLIGHT-COLOR: buttonface;
	SCROLLBAR-SHADOW-COLOR: buttonface;
	SCROLLBAR-3DLIGHT-COLOR: buttonhighlight;
	SCROLLBAR-TRACK-COLOR: #eeeeee;
	BACKGROUND-COLOR: #ffffff
}
A {
	FONT-SIZE: 9pt;
	COLOR: #842b00;
	LINE-HEIGHT: 16pt;
	FONT-FAMILY: "Tahoma", "宋体";
	TEXT-DECORATION: none
}
A:hover {
	FONT-SIZE: 9pt;
	COLOR: #0188d2;
	LINE-HEIGHT: 16pt;
	FONT-FAMILY: "Tahoma", "宋体";
	TEXT-DECORATION: underline
}
H1 {
	FONT-SIZE: 9pt;
	FONT-FAMILY: "Tahoma", "宋体"
}
</STYLE>

</HEAD>
<BODY topMargin=20>
<TABLE cellSpacing=0 width=600 align=center border=0 cepadding="0">
  <TBODY>
    <TR colspan="2">
      <TD vAlign=top align=middle><IMG height=100 src="${pageContext.request.contextPath}/img/404.jpg"
      width=100 border=0>
      <TD>
      <TD><!--------System Return Begin------------>
        
        <H1>未获取到用户信息或者用户信息已失效</H1>
        <HR noShade SIZE=0>
        <P>请尝试以下操作：</P>
        <UL>
        	<li>重新从正确安全链接跳转登录。</li>
            <LI>如果通过单击链接而到达了该网页，请与网站管理员联系，通知他们该链接的格式不正确。</LI>
            <LI>单击<A href="javascript:history.back(1)"><FONT 
        color=#ff0000>后退</FONT></A>按钮尝试另一个链接。 </LI>
        </UL>
        
        <HR noShade SIZE=0>
        <P>如果您对本站有任何疑问、意见、建议、咨询，请联系管理员</A> <BR>
          &nbsp;&nbsp;&nbsp;<BR>
        </P>
        <!------------End this!---------------></TD>
    </TR>
  </TBODY>
</TABLE>
</BODY>
</HTML>
