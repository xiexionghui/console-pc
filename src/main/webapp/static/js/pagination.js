function submitWithoutNumber() {
	$("#qp_number").val(1);
	document.form1.submit();
}

function onPrev() {
	var number = $("#page_number").val();
	$("#qp_number").val(number - 1);
	document.form1.submit();
}
function goPage(number) {
	$("#qp_number").val(number);
	document.form1.submit();
}
function onNext() {
	var number = $("#page_number").val();
	$("#qp_number").val(Number(number) + 1);
	document.form1.submit();
}
function onSelectedSubmit(){
	$("#qp_number").val(1);
	document.form1.submit();
}

$(function(){
	$('.pager li a').click(function(){$(this).addClass('current').siblings().removeClass('current');});
	
});

function goTo(maxPageIndex){
	if($.trim($("#goTo").val()) == ''){
		alert("请输入要跳转的页数!");
		return;
	}
	var reg = new RegExp("^[0-9]*$");
	if(!reg.test($.trim($("#goTo").val()))){  
        alert("请输入数字!");
        $("#goTo").foucs();
        return;
    }
	var gotoPageInt=parseInt($.trim($("#goTo").val()));
	if(gotoPageInt<=0){
		gotoPageInt=1;
	}else if(gotoPageInt>maxPageIndex){
		gotoPageInt=maxPageIndex;
	}
	$("#qp_number").val(gotoPageInt);
	document.form1.submit();
}