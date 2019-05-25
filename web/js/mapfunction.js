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
provAndcitylayers = new L.FeatureGroup();
map.addLayer(provAndcitylayers);

L.marker([34.607181, 119.2140000]).addTo(map)
    .bindPopup("<b>我在淮海工学院!</b><br />我是一个点").openPopup();


L.circle([34.607181, 119.2140000], 500, {
    color: 'blue',
    fillColor: '#90D',
    fillOpacity: 0.5
}).addTo(map).bindPopup("I am a circle.");

var popup = L.popup();



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


var testData = {
    max: 8,
    data:

        [{
            "lat": "34.62176", "lng": "119.214736", "count": "10285"
        },

            { "lat": "34.61498", "lng": "119.210096", "count": "10204 " },

            { "lat": "34.59902", "lng": "119.222416", "count": "10508" },

            { "lat": "34.59898", "lng": "119.210984", "count": "9041" },

            { "lat": "34.591312", "lng": "119.221184", "count": "7442" },

            { "lat": "34.607992", "lng": "119.204112", "count": "12634" },

            { "lat": "34.601408", "lng": "119.20216", "count": "11269" },

            { "lat": "34.59884", "lng": "119.195928", "count": "12282" }]

};
//配置
var cfg = {
    "radius": 0.008,
    "maxOpacity": .8,
    "scaleRadius": true,
    "useLocalExtrema": true,
    latField: 'lat',
    lngField: 'lng',
    valueField: 'count'
};


L.marker([34.62176, 119.214736]).addTo(map).bindPopup("四季金辉");
L.marker([34.61498, 119.210096]).addTo(map).bindPopup("秀逸苏杭");
L.marker([34.59902, 119.222416]).addTo(map).bindPopup("水木华园");
L.marker([34.59898, 119.210984]).addTo(map).bindPopup("一品国际");
L.marker([34.591312, 119.221184]).addTo(map).bindPopup("水木华园");
L.marker([34.607992, 119.204112]).addTo(map).bindPopup("同科汇丰国际");
L.marker([34.601408, 119.20216]).addTo(map).bindPopup("君臣大厦");
L.marker([34.59884, 119.195928]).addTo(map).bindPopup("苍梧小区");



var heatmapLayer = new HeatmapOverlay(cfg);//图层
map.addLayer(heatmapLayer);
heatmapLayer.setData(testData);
