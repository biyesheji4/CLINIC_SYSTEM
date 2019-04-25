var myChart = echarts.init(document.getElementById('line'));
var myChart1 = echarts.init(document.getElementById('bills'));

/**
 * 挂号人数分析图
 * @type
 * */
var option = {
    char:{
      type:'column',
    },
    title : {
        text : '挂号人数分析图',
        textStyle : {
            color : '#1DB3AA',
            fontSize : '14',
        }
    },
    tooltip : {
        trigger : 'axis',
        axisPointer: {
            type: 'cross',
            crossStyle: {
                color: '#999'
            }
        }
    },
    legend : {
        data : [ '挂号人数' ],
        y : '25'
    },
    grid : {
        bottom : '30',
    },
    calculable : true,
    xAxis : [ {
        type : 'category',
        // boundaryGap : false,
        data : [],
        axisLabel : {
            type: 'shadow'
        }
    } ],
    yAxis : [ {
        type : 'value',
        name: '挂号人数',
        interval: 1,
        axisLabel: {
            formatter: '{value} 个'
        }
    } ],
    series : [ {
        name : '挂号人数',
        type : 'bar',//'line'
        data : [],
        barMaxWidth: '30',
        barGap:'10%',
        itemStyle : {
            normal : {
                color : '#ffa31b'
            }
        },  animationDelay: function (idx) {
            return idx * 500;
        }
    }]
};

/**
 * 收入支出统计图
 * */
var option1 = {
    char:{
        type:'column',
    },
    title : {
        text : '收入、支出统计图',
        textStyle : {
            color : '#1DB3AA',
            fontSize : '14',
        }
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            crossStyle: {
                color: '#999'
            }
        }
    },
    legend : {
        data : [ '收入','支出'],
    },
    grid : {
        bottom : '30',
    },
    calculable : true,
    xAxis : [ {
        type : 'category',
        // boundaryGap : false,
        data : [],
        axisLabel : {
            type: 'shadow'
        }
    } ],
    yAxis : [{
        type: 'value',
        name: '收入/支出',
        axisLabel: {
            formatter: '{value} 元'
        }
    }
    ],
    series : [ {
        name : '收入',
        type : 'bar',//'line'
        data : [],
        barMaxWidth: '30',
        barGap:'10%',
        itemStyle : {
            normal : {
                color : '#007bff'
            }
        },  animationDelay: function (idx) {
            return idx * 500;
        }
    },
        {
            name: '支出',
            type: 'bar',
            data: [],//700, 680, 710, 730, 720
            barMaxWidth: '30',
            barGap:'10%',
            itemStyle:{
                normal:{
                    color:'#ffe637'
                }
            },
            animationDelay: function (idx) {
                return idx * 100 + 100;
            }
        }
    ]
};
myChart.setOption(option);
myChart1.setOption(option1);

$("#search").click(function(){
    var  yeartime = $("#yeartime").val();
    var  startDate = $("#starttime").val();
    var  endDate = $("#endtime").val()*1+1;
    tuxing(yeartime,startDate,endDate);
});
$("#search").click();


function tuxing(yeartime,startDate,endDate) {

    $.ajax({
        type: "POST",
        url: "tuxing.do",//访问RegisteredController.java里面的tuxing.do
        dataType: "json",
        data: {"yeartime": yeartime,"startDate":startDate,"endDate":endDate},

        success: function (result) {
            querytop(result);
            var lw = result.lw;//挂号人数分析图
            var bi = result.bill;//收入、支出

            option.xAxis[0].data = [];
            option.series[0].data = [];

            option1.xAxis[0].data = [];
            option1.series[0].data = [];
            option1.series[1].data =[];
            // 挂号人数分析柱状图
            for (var i = 0; i < lw.length; i++) {
                option.xAxis[0].data.push(lw[i].registeredDate);
                option.series[0].data.push(lw[i].id);

            }

            //收入、支出
            for(var j=0;j<bi.length;j++){

                if(bi[j].billState == "0"){
                    option1.xAxis[0].data.push(bi[j].billDate);
                    option1.series[0].data.push(bi[j].billMon);
                }else if(bi[j].billState == "1"){
                    option1.series[1].data.push(bi[j].billMon);
                }
            }
            myChart.setOption(option);
            myChart1.setOption(option1);
        }

    });
}


function  querytop(result) {
    var tbody1="";
    var tbody2="";
            var tbody1="";
            var tbody2="";
            var pre="<tr>";
            var text="";
            var next ="</tr>";

            if(result.cas==null || result.cas.length==0){
                $("#topStockUser1").html(tbody1);
                $("#topStockUser2").html(tbody2);
                $("#topFeeTitle").css("display", "none");
                $("#topFeeRow").css("display", "none");
                return;
            }

            try {

                $("#topFeeTitle").css("display", "block");
                $("#topFeeRow").css("display", "block");
                for(var i=0;i<4;i++){
                    var classV = "two";
                    try {
                        var sort = i+1;
                        var planName = result.cas[i].employee_name;
                        var stockUser = result.cas[i].casesContent;
                        if(stockUser==null){
                            stockUser=0;
                        }
                        if(i>2){
                            classV = "three";
                        }
                        text="<td class='"+classV+"'>"+sort+"</td>" +
                            "<td>"+planName+"</td>" +
                            "<td>"+stockUser+"</td>" +
                            "<td><div class='box1' style='width: "+(20-i)*10+"px;'></div></td>" ;
                    } catch (e) {
                        // TODO: handle exception
                        text="<td></td>" +
                            "<td></td>" +
                            "<td></td>" +
                            "<td></td>" ;
                    }
                    if(i<2){
                        tbody1+=pre+text+next;
                    }else{
                        tbody2+=pre+text+next;
                    }

                }
            } catch (e) {
                // TODO: handle exception
            }

            $("#topStockUser1").html(tbody1);
            $("#topStockUser2").html(tbody2);

}