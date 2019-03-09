<%@ include file="/common/taglibs.jsp"%>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>

<script type="text/javascript" src="<c:out value="${ctx}"/>/js/jquery/jquery1.3.2.js"></script>
<script type="text/javascript" src="<c:out value="${ctx}"/>/js/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="<c:out value="${ctx}"/>/js/jquery/jquery.metadata.js"></script>
<script type="text/javascript" src="<c:out value="${ctx}"/>/js/jquery/jquery.form.js"></script>

<script type="text/javascript">
$(function(){
	$("form").submit(function(){
	//trim all input text fied's value.
		$("input:text", this).each(function(){this.value = $.trim(this.value);});
	  }
	);
  }
);
</script>
