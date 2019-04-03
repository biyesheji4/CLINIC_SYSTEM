$.fn.datetimepicker.dates['zh-CN'] = {
			days: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
			daysShort: ["日", "一", "二", "三", "四", "五", "六", "七"],
			daysMin: ["日", "一", "二", "三", "四", "五", "六", "七"],
			months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
			monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
			today: "今天",
			suffix: [],
	        meridiem: ["上午", "下午"],
	        weekStart: 1,
};
/*day*/
$('.form_date').datetimepicker({
    language:  'zh-CN',
    weekStart: 1,
    todayBtn:  1,
	autoclose: 1,
	todayHighlight: 1,
	startView: 2,
	minView: 2,
	forceParse: 0,
	pickerPosition:"bottom-left",
	initialDate:new Date()
});
//$(".form_date input").val(moment().subtract(1, 'days').format("YYYY-MM-DD"));

/*months*/
$('.form_date_mon').datetimepicker({
    language:  'zh-CN',
    weekStart: 1,
	autoclose: 1,
	todayHighlight: 1,
	startView: 3,
	minView: 3,
	forceParse: 0,
	pickerPosition:"bottom-right",
	format:'yyyy-mm',
	initialDate:new Date()
});

$('.form_date_year').datetimepicker({
    language:  'zh-CN',
    weekStart: 1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 4,
    minView: 4,
    forceParse: 0,
    pickerPosition:"bottom-right",
    format:'yyyy',
    initialDate:new Date()
});

//$(".form_date_mon input").val(moment().subtract(0, 'months').format("YYYY-MM"));

$("#areaPicker a").click(function(){
   $("#chooseArea").text($(this).text());
});