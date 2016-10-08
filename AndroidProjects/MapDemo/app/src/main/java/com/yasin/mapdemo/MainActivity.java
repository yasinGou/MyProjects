package com.yasin.mapdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BaiduMap.OnMarkerDragListener, BaiduMap.OnMarkerClickListener, BDLocationListener, OnGetPoiSearchResultListener {

    //显示地图，不能控制地图的操作
    private MapView mapView;
   //百度map的核心控制器
    private BaiduMap baiduMap;
    private LocationClient locationClient;
    //定位时，显示位置
    private Marker selfMarker;


    //POI 检索的步骤
    //1、创建POISearch实例
    //2、检索信息
    //3、设置检索结构接收接口
    //4、检索结果，显示为 Marker
    //5、Destroy()销毁POISearch

    private PoiSearch poiserach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化百度地图SDK
        //以后完整的应用，都应该在application中初始化
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        //获取MapView
        mapView = (MapView) findViewById(R.id.map_view);
        //管理MapView生命周期
        baiduMap=mapView.getMap();
      //  baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        initLocationClient();

        poiserach=PoiSearch.newInstance();
        poiserach.setOnGetPoiSearchResultListener(this);

    }

    private void initLocationClient(){
        //初始化定位
        locationClient = new LocationClient(this);
        //设置定位参数
        LocationClientOption option = new LocationClientOption();
        //设置定位的精度
        // 高精度   省电模式  GPS
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //设置定位的时间间隔 单位 <1000代表只定位一次
        option.setScanSpan(5000);
        //定位结果的类型 针对百度 高德地图、或者其他
        //bd09ll 代表百度地图，gcj02
        option.setCoorType("bd09ll");
        //打开Gps
        option.setOpenGps(true);
        //接收地址信息
        option.setIsNeedAddress(true);
        locationClient.setLocOption(option);
        //设置定位结果接口回调
        locationClient.registerLocationListener(this);
        locationClient.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
        //针对定位，自动进行定位的请求
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        locationClient.stop();
        poiserach.destroy();
        locationClient.unRegisterLocationListener(this);
    }

    public void btnSwitchTraffic(View view) {
        if (baiduMap.isBaiduHeatMapEnabled()) {
            baiduMap.setTrafficEnabled(false);
        }else {
            baiduMap.setTrafficEnabled(true);
        }
    }

    public void btnSwitchHeat(View view) {
        baiduMap.setBaiduHeatMapEnabled(!baiduMap.isBaiduHeatMapEnabled());

    }

    public void btnMoveMap(View view) {
        //移动地图显示的位置
        //LatLng 就是纬经度
        //Latitude 纬度
        //longitude 经度

        LatLng latLng = new LatLng(40.13, 116.65);
        MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(latLng);
       // baiduMap.setMapStatus(update);
        baiduMap.animateMapStatus(update);

    }

    public void btnAddMarker(View view) {
        //添加标记
        //1.创建xxxOptions
        //2.BaiduMap addOverlay
        MarkerOptions markerOptions = new MarkerOptions();
        //设置标记的图像，必须设置
        BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher);
        markerOptions.icon(descriptor);
        //是否可以拖拽
        markerOptions.draggable(true);
        //设置覆盖物的位置  纬经度
        LatLng lng = new LatLng(40.13, 116.65);
        markerOptions.position(lng);

        //设置Marker的拖拽的监听，选择地理位置坐标
        baiduMap.setOnMarkerDragListener(this);

        //设置Marker的点击监听事件。通常点击查看详情
        baiduMap.setOnMarkerClickListener(this);
        Marker marker = (Marker) baiduMap.addOverlay(markerOptions);
        //Marker 可以再次进行各种修改，能够直接在地图上刷新



    }

    //Marker拖拽的接口回调，需要设置到BaiduMap上
    @Override
    public void onMarkerDrag(Marker marker) {
     //正在拖拽的时候回调
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
    //拖拽完成，重新定位到地图上

        // 获取当前所在坐标，用于路径查询
        LatLng position = marker.getPosition();
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }


    // Marker 的点击事件
    @Override
    public boolean onMarkerClick(Marker marker) {

        return true;
    }


    public void btnAddPolygon(View view) {
        //多边形会自动闭合，第一个点和第二个点
    }

    public void btnAddLines(View view) {
    //添加线段
        PolylineOptions options=new PolylineOptions();
        //设置线段的点
        List<LatLng> points=new ArrayList<>();
        points.add(new LatLng(40.1392,116.6519));
        points.add(new LatLng(40.1439,116.6717));
        points.add(new LatLng(40.1317,116.6573));
        points.add(new LatLng(40.1507,116.6667));
        options.points(points);
        //设置线段颜色
        List<Integer> colors=new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        options.colorsValues(colors);
        //线段的宽度
        options.width(5);
        baiduMap.addOverlay(options);

    }

    public void btnAddText(View view) {
        LatLng lng = new LatLng(35.16363, 139.73673);
        TextOptions options = new TextOptions();
        options.position(lng);
        options.text("这是哪儿啊");
        options.fontSize(30);
        options.fontColor(Color.RED).bgColor(Color.YELLOW);
        baiduMap.addOverlay(options);
    }

    public void btnRequestLocation(View view) {
        // 请求定位
        int location = locationClient.requestLocation();


    }


    //定位接口回调
    @Override
    public void onReceiveLocation(BDLocation location) {
        //定位接口可能返回错误码，要根据结果错误码，来判断是否是正确的地址

        int locType = location.getLocType();
        switch (locType){
            case BDLocation.TypeGpsLocation:
            case BDLocation.TypeCacheLocation:
            case BDLocation.TypeNetWorkLocation:
            case BDLocation.TypeOffLineLocation:
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                LatLng lng = new LatLng(latitude, longitude);

                MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(lng);
                baiduMap.setMapStatus(update);


                // 添加/移动Marker对象
                if (selfMarker == null) {
                    MarkerOptions options = new MarkerOptions();
                    options.position(lng);
                    BitmapDescriptor descriptor = BitmapDescriptorFactory.fromAsset("Icon_start.png");
                    options.icon(descriptor);
                    //给Marker添加附加信息，在点击的时候，实现特定的参数获取
                    Bundle bundle = new Bundle();
                    bundle.putString("type","self");
                    bundle.putString("address",location.getAddrStr());
                    options.extraInfo(bundle);
                    selfMarker=((Marker) baiduMap.addOverlay(options));
                }
                //如果已经创建过Marker 直接更新位置
                selfMarker.setPosition(lng);
                break;
            default:
                String describe = location.getLocationDescribe();
                Log.d("BaiduLoc", "onReceiveLocation: "+describe);
                break;

        }

    }

    public void btnSearchFood(View view) {
        //搜索当前城市的美食信息
        PoiCitySearchOption option = new PoiCitySearchOption();
        option.city("北京").keyword("烤鸭").pageCapacity(30);
        poiserach.searchInCity(option);
    }

    @Override
    public void onGetPoiResult(PoiResult result) {
        //获取地址信息，可能为空
        List<PoiInfo> allPoi = result.getAllPoi();
        if (allPoi != null) {
            int index=1;
            for (PoiInfo poiInfo : allPoi) {
                LatLng location = poiInfo.location;
                MarkerOptions options = new MarkerOptions();
                options.position(location);
                if (index<=10) {
                    BitmapDescriptor descriptor = BitmapDescriptorFactory.fromAsset("Icon_mark" + index + ".png");
                    options.icon(descriptor);
                }else {
                    BitmapDescriptor descriptor = BitmapDescriptorFactory.fromAsset("Icon_end.png");
                    options.icon(descriptor);
                }

                index++;
                options.title(poiInfo.name);
                Bundle bundle = new Bundle();
                bundle.putString("type","poi");
                bundle.putParcelable("poiInfo",poiInfo);
                options.extraInfo(bundle);

                baiduMap.addOverlay(options);

            }
        }
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult result) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult result) {

    }
}
