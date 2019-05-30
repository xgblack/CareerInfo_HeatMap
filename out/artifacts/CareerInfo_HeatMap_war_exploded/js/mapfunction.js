// 百度地图API功能

// 创建Map实例
var map = new BMap.Map("mapid");

// 初始化地图,设置中心点坐标和地图级别
map.centerAndZoom(new BMap.Point(116.601144, 39.948574), 15);

//添加地图类型控件
map.addControl(new BMap.MapTypeControl({
    mapTypes:[
        BMAP_NORMAL_MAP,
        BMAP_HYBRID_MAP
    ]}));

// 设置地图显示的城市 此项是必须设置的
map.setCurrentCity("北京");

//开启鼠标滚轮缩放
map.enableScrollWheelZoom(true);




//这里面添加房价的经纬度
// var points =[
//
//     {"lon":116.418261,"lat":39.921984,"count":50},
//     {"lon":116.423332,"lat":39.916532,"count":51},
//     {"lon":116.419787,"lat":39.930658,"count":15},
//     {"lon":116.418455,"lat":39.920921,"count":40},
//     {"lon":116.418843,"lat":39.915516,"count":100},
//     {"lon":116.42546,"lat":39.918503,"count":6},
//     {"lon":116.423289,"lat":39.919989,"count":18},
//     {"lon":116.418162,"lat":39.915051,"count":80},
//     {"lon":116.422039,"lat":39.91782,"count":11},
//     {"lon":116.41387,"lat":39.917253,"count":7},
//     {"lon":116.41773,"lat":39.919426,"count":42},
//     {"lon":116.421107,"lat":39.916445,"count":4},
//     {"lon":116.417521,"lat":39.917943,"count":27},
//     {"lon":116.419812,"lat":39.920836,"count":23},
//     {"lon":116.420682,"lat":39.91463,"count":60},
//     {"lon":116.415424,"lat":39.924675,"count":8},
//     {"lon":116.419242,"lat":39.914509,"count":15},
//     {"lon":116.422766,"lat":39.921408,"count":25},
//     {"lon":116.421674,"lat":39.924396,"count":21},
//     {"lon":116.427268,"lat":39.92267,"count":1},
//     {"lon":116.417721,"lat":39.920034,"count":51},
//     {"lng":116.412456,"lat":39.92667,"count":7},
//     {"lon":116.420432,"lat":39.919114,"count":11},
//     {"lon":116.425013,"lat":39.921611,"count":35},
//     {"lon":116.418733,"lat":39.931037,"count":22},
//     {"lon":116.419336,"lat":39.931134,"count":4},
//     {"lon":116.413557,"lat":39.923254,"count":5},
//     {"lon":116.418367,"lat":39.92943,"count":3},
//     {"lon":116.424312,"lat":39.919621,"count":100},
//     {"lon":116.423874,"lat":39.919447,"count":87},
//     {"lon":116.424225,"lat":39.923091,"count":32},
//     {"lon":116.417801,"lat":39.921854,"count":44},
//     {"lon":116.417129,"lat":39.928227,"count":21},
//     {"lon":116.426426,"lat":39.922286,"count":80},
//     {"lon":116.421597,"lat":39.91948,"count":32},
//     {"lon":116.423895,"lat":39.920787,"count":26},
//     {"lon":116.423563,"lat":39.921197,"count":17},
//     {"lon":116.417982,"lat":39.922547,"count":17},
//     {"lon":116.426126,"lat":39.921938,"count":25},
//     {"lon":116.42326,"lat":39.915782,"count":100},
//     {"lon":116.419239,"lat":39.916759,"count":39},
//     {"lon":116.417185,"lat":39.929123,"count":11},
//     {"lon":116.417237,"lat":39.927518,"count":9},
//     {"lon":116.417784,"lat":39.915754,"count":47},
//     {"lon":116.420193,"lat":39.917061,"count":52},
//     {"lon":116.422735,"lat":39.915619,"count":100},
//     {"lon":116.418495,"lat":39.915958,"count":46},
//     {"lon":116.416292,"lat":39.931166,"count":9},
//     {"lon":116.419916,"lat":39.924055,"count":8},
//     {"lon":116.42189,"lat":39.921308,"count":11},
//     {"lon":116.413765,"lat":39.929376,"count":3},
//     {"lat":116.418232,"lat":39.920348,"count":50},//
//     {"lon":116.417554,"lat":39.930511,"count":15},
//     {"lon":116.418568,"lat":39.918161,"count":23},
//     {"lon":116.413461,"lat":39.926306,"count":3},
//     {"lon":116.42232,"lat":39.92161,"count":13},
//     {"lon":116.4174,"lat":39.928616,"count":6},
//     {"lon":116.424679,"lat":39.915499,"count":21},
//     {"lon":116.42171,"lat":39.915738,"count":29},
//     {"lon":116.417836,"lat":39.916998,"count":99},
//     {"lon":116.420755,"lat":39.928001,"count":10},
//     {"lon":116.414077,"lat":39.930655,"count":14},
//     {"lon":116.426092,"lat":39.922995,"count":16},
//     {"lon":116.41535,"lat":39.931054,"count":15},
//     {"lon":116.413022,"lat":39.921895,"count":13},
//     {"lon":116.415551,"lat":39.913373,"count":17},
//     {"lon":116.421191,"lat":39.926572,"count":1},
//     {"lon":116.419612,"lat":39.917119,"count":9},
//     {"lon":116.418237,"lat":39.921337,"count":54},
//     {"lon":116.423776,"lat":39.921919,"count":26},
//     {"lon":116.417694,"lat":39.92536,"count":17},
//     {"lon":116.415377,"lat":39.914137,"count":19},
//     {"lon":116.417434,"lat":39.914394,"count":43},
//     {"lon":116.42588,"lat":39.922622,"count":27},
//     {"lon":116.418345,"lat":39.919467,"count":8},
//     {"lon":116.426883,"lat":39.917171,"count":3},
//     {"lon":116.423877,"lat":39.916659,"count":34},
//     {"lon":116.415712,"lat":39.915613,"count":14},
//     {"lon":116.419869,"lat":39.931416,"count":12},
//     {"lon":116.416956,"lat":39.925377,"count":11},
//     {"lon":116.42066,"lat":39.925017,"count":38},
//     {"lon":116.416244,"lat":39.920215,"count":91},
//     {"lon":116.41929,"lat":39.915908,"count":54},
//     {"lon":116.422116,"lat":39.919658,"count":21},
//     {"lon":116.4183,"lat":39.925015,"count":15},
//     {"lon":116.421969,"lat":39.913527,"count":3},
//     {"lon":116.422936,"lat":39.921854,"count":24},
//     {"lon":116.41905,"lat":39.929217,"count":12},
//     {"lon":116.424579,"lat":39.914987,"count":57},
//     {"lon":116.42076,"lat":39.915251,"count":70},
//     {"lon":116.425867,"lat":39.918989,"count":8}
// ];
//
//
// var heatmapData = {
//     max : 100,
//     data: points
// };
//
// var cfg = {
//     "radius": 40,
//     "maxOpacity": 0.8,
//     "blur":0.7,
//     // "opacity":100,
//     // "scaleRadius": true,
//     // "useLocalExtrema": true,
//     // xField: 'lat',
//     // yField: 'lon',
//     // valueField: 'count'
// };

// if(!isSupportCanvas()){
//     alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~')
// }

//参数说明如下:
/* visible 热力图是否显示,默认为true
 * opacity 热力的透明度,1-100
 * radius 势力图的每个点的半径大小
 * gradient  {JSON} 热力图的渐变区间 . gradient如下所示
 *  {
        .2:'rgb(0, 255, 255)',
        .5:'rgb(0, 110, 255)',
        .8:'rgb(100, 0, 255)'
    }
    其中 key 表示插值的位置, 0~1.
        value 为颜色值.
 */
// heatmapOverlay = new BMapLib.HeatmapOverlay(cfg);
// map.addOverlay(heatmapOverlay);
// heatmapOverlay.setDataSet(heatmapData);

// openHeatmap();
// closeHeatmap();

//判断浏览区是否支持canvas
// function isSupportCanvas(){
//     var elem = document.createElement('canvas');
//     return !!(elem.getContext && elem.getContext('2d'));
// }

// function setGradient(){
//     /*格式如下所示:
//     {
//         0:'rgb(102, 255, 0)',
//         .5:'rgb(255, 170, 0)',
//         1:'rgb(255, 0, 0)'
//     }*/
//     var gradient = {};
//     var colors = document.querySelectorAll("input[type='color']");
//     colors = [].slice.call(colors,0);
//     colors.forEach(function(ele){
//         gradient[ele.getAttribute("data-key")] = ele.value;
//     });
//     heatmapOverlay.setOptions({"gradient":gradient});
// }
//
// function openHeatmap(){
//     heatmapOverlay.show();
// }
//
// function closeHeatmap(){
//     heatmapOverlay.hide();
// }