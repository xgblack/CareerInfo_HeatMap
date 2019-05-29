var tileUrl = {
    "Sat": {
        "TIANDITU": "http://{s}.tianditu.gov.cn/img_w/wmts?service=wmts&request=GetTile&version=1.0.0&LAYER=img&tileMatrixSet=w&TileMatrix={z}&TileRow={y}&TileCol={x}&style=default&format=tiles&tk=a9fcde75424f2e3035fc50fc53ddb9a9",
        "TIANDITU_L": "http://{s}.tianditu.gov.cn/cia_w/wmts?service=wmts&request=GetTile&version=1.0.0&LAYER=cia&tileMatrixSet=w&TileMatrix={z}&TileRow={y}&TileCol={x}&style=default&format=tiles&tk=a9fcde75424f2e3035fc50fc53ddb9a9"
    },
    "Vect": {
        "TIANDITU": "http://{s}.tianditu.gov.cn/vec_w/wmts?service=wmts&request=GetTile&version=1.0.0&LAYER=vec&tileMatrixSet=w&TileMatrix={z}&TileRow={y}&TileCol={x}&style=default&format=tiles&tk=a9fcde75424f2e3035fc50fc53ddb9a9",
        "TIANDITU_L": "http://{s}.tianditu.gov.cn/cva_w/wmts?service=wmts&request=GetTile&version=1.0.0&LAYER=cva&tileMatrixSet=w&TileMatrix={z}&TileRow={y}&TileCol={x}&style=default&format=tiles&tk=a9fcde75424f2e3035fc50fc53ddb9a9"
    },
    "Terr": {
        "TIANDITU": "http://{s}.tianditu.gov.cn/vec_w/wmts?service=wmts&request=GetTile&version=1.0.0&LAYER=vec&tileMatrixSet=w&TileMatrix={z}&TileRow={y}&TileCol={x}&style=default&format=tiles&tk=a9fcde75424f2e3035fc50fc53ddb9a9",
        "TIANDITU_L": "http://{s}.tianditu.gov.cn/cva_w/wmts?service=wmts&request=GetTile&version=1.0.0&LAYER=cva&tileMatrixSet=w&TileMatrix={z}&TileRow={y}&TileCol={x}&style=default&format=tiles&tk=a9fcde75424f2e3035fc50fc53ddb9a9"
    }
};

map = L.map("mapid", {
    center: [34.607181, 119.2140000],
    zoom: 15,
    //minZoom:5,
    //maxZoom:18,
    layers: [
        L.tileLayer(tileUrl.Vect.TIANDITU, { subdomains: ["t0", "t1", "t2", "t3", "t4", "t5", "t6", "t7"] }),
        L.tileLayer(tileUrl.Vect.TIANDITU_L, { subdomains: ["t0", "t1", "t2", "t3", "t4", "t5", "t6", "t7"] })
    ],
    zoomControl: true,
    attributionControl: false
});
// provAndcitylayers = new L.FeatureGroup();
// map.addLayer(provAndcitylayers);
//
// L.marker([34.607181, 119.2140000]).addTo(map)
//     .bindPopup("<b>我在淮海工学院!</b><br />我是一个点").openPopup();


// L.circle([34.607181, 119.2140000], 500, {
//     color: 'blue',
//     fillColor: '#90D',
//     fillOpacity: 0.5
// }).addTo(map).bindPopup("I am a circle.");

// var popup = L.popup();



//WebGIS-实验一-2.矢量影像切换

//当前显示的地图类型，默认为矢量瓦片底图
var curMapModel = "Vec";

//底图
var baseLayer = L.tileLayer(tileUrl.Vect.TIANDITU, { subdomains: ["t0", "t1", "t2", "t3", "t4", "t5", "t6", "t7"] }).addTo(map);
//标注层
var labLayer = L.tileLayer(tileUrl.Vect.TIANDITU_L, { subdomains: ["t0", "t1", "t2", "t3", "t4", "t5", "t6", "t7"] }).addTo(map);
//切换图层
function switchlayer() {
    if (curMapModel == "Vec") {
        baseLayer.setUrl(tileUrl.Sat.TIANDITU);
        //添加标注层
        labLayer.setUrl(tileUrl.Sat.TIANDITU_L);
        curMapModel = "Sat";
        $(".mswitch span").text("地图");
        $(".mswitch").css("background", "url(css/images/maptype.png) 0px 0px")
    } else if (curMapModel == "Sat") {
        baseLayer.setUrl(tileUrl.Vect.TIANDITU);
        //添加标注层
        labLayer.setUrl(tileUrl.Vect.TIANDITU_L);
        curMapModel = "Vec";
        $(".mswitch span").text("卫星");
        $(".mswitch").css("background", "url(css/images/maptype.png) 0px -46px");
    }
}

function swover() {
    $(".mswitch span").css("background-color", "blue");
    $(".mswitch span").css("color", "white");
}
function swout() {
    $(".mswitch span").css("background-color", "");
    $(".mswitch span").css("color", "rgb(102, 102, 102)"); //color: rgb(102, 102, 102)
}
