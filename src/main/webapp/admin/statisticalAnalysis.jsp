<%--
  Created by IntelliJ IDEA.
  User: 16628
  Date: 2023-06-06
  Time: 8:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp"%>
<div id="main" style="width: 1200px;height:640px; margin: 0 auto;margin-top: 50px;"></div>
<div id="main1" style="width: 1200px;height:640px; margin: 0 auto;margin-top: 25px;"></div>
<div id="main2" style="width: 1200px;height:640px; margin: 0 auto;margin-top: 25px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    var myChart1 = echarts.init(document.getElementById('main1'));
    var myChart2 = echarts.init(document.getElementById('main2'));

    // 指定图表的配置项和数据
    option = {
        title: {
            text: '图书按分类统计',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['计算机系统概述', 'python路线', '计算机网络', 'java', 'c++', 'Web和APP开发', '信息安全', 'Linux入门', '数据库']
        },
        series: [
            {
                name: '图书按分类统计',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: [
                    { value: ${bookStatistics[0]}, name: '计算机系统概述' },
                    { value: ${bookStatistics[1]}, name: 'python路线' },
                    { value: ${bookStatistics[2]}, name: '计算机网络' },
                    { value: ${bookStatistics[3]}, name: 'java' },
                    { value: ${bookStatistics[4]}, name: 'c++' },
                    { value: ${bookStatistics[5]}, name: 'Web和APP开发' },
                    { value: ${bookStatistics[6]}, name: '信息安全' },
                    { value: ${bookStatistics[7]}, name: 'Linux入门' },
                    { value: ${bookStatistics[8]}, name: '数据库' }
                ],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    // 指定图表的配置项和数据
    option1 = {
        title: {
            text: '借阅按分类统计',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['计算机系统概述', 'python路线', '计算机网络', 'java', 'c++', 'Web和APP开发', '信息安全', 'Linux入门', '数据库']
        },
        series: [
            {
                name: '借阅按分类统计',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: [

                    <c:if test="${borrowStatistics[0] != 0}">{ value: ${borrowStatistics[0]}, name: '计算机系统概述' },</c:if>
                    <c:if test="${borrowStatistics[1] != 0}">{ value: ${borrowStatistics[1]}, name: 'python路线' },</c:if>
                    <c:if test="${borrowStatistics[2] != 0}">{ value: ${borrowStatistics[2]}, name: '计算机网络' },</c:if>
                    <c:if test="${borrowStatistics[3] != 0}">{ value: ${borrowStatistics[3]}, name: 'java' },</c:if>
                    <c:if test="${borrowStatistics[4] != 0}">{ value: ${borrowStatistics[4]}, name: 'c++' },</c:if>
                    <c:if test="${borrowStatistics[5] != 0}">{ value: ${borrowStatistics[5]}, name: 'Web和APP开发' },</c:if>
                    <c:if test="${borrowStatistics[6] != 0}">{ value: ${borrowStatistics[6]}, name: '信息安全' },</c:if>
                    <c:if test="${borrowStatistics[7] != 0}">{ value: ${borrowStatistics[7]}, name: 'Linux入门' },</c:if>
                    <c:if test="${borrowStatistics[8] != 0}">{ value: ${borrowStatistics[8]}, name: '数据库' }</c:if>
                ],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    // 指定图表的配置项和数据
    option2 = {
        title: {
            text: '罚款缴纳统计',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['未缴纳', '已缴纳']
        },
        series: [
            {
                name: '罚款缴纳统计',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: [
                    { value: ${fineStatistics[0]}, name: '未缴纳' },
                    { value: ${fineStatistics[1]}, name: '已缴纳' }
                ],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    let currentIndex = -1;
    setInterval(function() {
        var dataLen = option.series[0].data.length;
        // 取消之前高亮的图形
        myChart.dispatchAction({
            type: 'downplay',
            seriesIndex: 0,
            dataIndex: currentIndex
        });
        currentIndex = (currentIndex + 1) % dataLen;
        // 高亮当前图形
        myChart.dispatchAction({
            type: 'highlight',
            seriesIndex: 0,
            dataIndex: currentIndex
        });
        // 显示 tooltip
        myChart.dispatchAction({
            type: 'showTip',
            seriesIndex: 0,
            dataIndex: currentIndex
        });
    }, 1000);
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    myChart1.setOption(option1);
    myChart2.setOption(option2);
</script>

<%@ include file="footer.jsp"%>
