// 百度地图API功能

// 创建Map实例
var map = new BMap.Map("mapid");

// 初始化地图,设置中心点坐标和地图级别
// map.centerAndZoom(new BMap.Point(121.480198, 31.235314), 9);

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


//判断浏览区是否支持canvas
function isSupportCanvas(){
    var elem = document.createElement('canvas');
    return !!(elem.getContext && elem.getContext('2d'));
}

