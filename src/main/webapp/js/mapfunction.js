// 百度地图API功能

// 创建Map实例
var map = new BMap.Map("mapid");

//初始化地图,设置中心点坐标和地图级别
map.centerAndZoom(new BMap.Point(121.480198, 31.235314), 11);

//添加地图类型控件
/*map.addControl(new BMap.MapTypeControl({
    mapTypes:[
        BMAP_NORMAL_MAP,
        BMAP_SATELLITE_MAP,
        BMAP_HYBRID_MAP
    ]}));*/

//开启鼠标滚轮缩放
map.enableScrollWheelZoom(true);
//启用键盘操作
map.enableKeyboard(true);


var heatmapOverlay = new BMapLib.HeatmapOverlay(cfg);
map.addOverlay(heatmapOverlay);

// 设置地图显示的城市 此项是必须设置的
// map.setCurrentCity("北京");

//更换地体显示类型
function changeMapType(type){
    if(type=="BMAP_NORMAL_MAP"){
        map.setMapType(BMAP_NORMAL_MAP);
    }else if(type=="BMAP_SATELLITE_MAP"){
        map.setMapType(BMAP_SATELLITE_MAP);
    }else if(type=="BMAP_HYBRID_MAP"){
        map.setMapType(BMAP_HYBRID_MAP);
    }
}

//定位
function locationNow(){
    var geolocation = new BMap.Geolocation();
    geolocation.getCurrentPosition(function(r){
        if(this.getStatus() == BMAP_STATUS_SUCCESS){
            var mk = new BMap.Marker(r.point);
            map.addOverlay(mk);

            myMarker.push(mk);

            map.panTo(r.point);
        }
        else {
            alert('定位失败，请确认浏览器已允许网站获取定位权限');
        }
    });
}






//判断浏览区是否支持canvas
function isSupportCanvas(){
    var elem = document.createElement('canvas');
    return !!(elem.getContext && elem.getContext('2d'));
}

