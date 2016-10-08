package com.yasin.baidumap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.model.LatLng;

public class MainActivity extends AppCompatActivity implements BDLocationListener {

    private MapView mapView;
    private BaiduMap baiduMap;
    private LocationClient locationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.map_view);
        baiduMap = mapView.getMap();
        initLocation();
        locationClient.registerLocationListener(this);
        locationClient.start();

    }
    public void initLocation() {
        locationClient = new LocationClient(this);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setScanSpan(5000);
        option.setCoorType("bd09ll");
        option.setOpenGps(true);
        option.setIsNeedAddress(true);
        option.setIsNeedLocationDescribe(true);
        option.setIsNeedLocationPoiList(true);
        locationClient.setLocOption(option);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
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
    }

    public void btnAddTraffic(View view) {
        boolean isTraffic = baiduMap.isTrafficEnabled();
        if (isTraffic) {
            baiduMap.setTrafficEnabled(false);
        }else {
            baiduMap.setTrafficEnabled(true);
        }
    }

    public void btnAddHeat(View view) {
        boolean isHeat = baiduMap.isBaiduHeatMapEnabled();
        if (isHeat) {
            baiduMap.setBaiduHeatMapEnabled(false);
        }else{
            baiduMap.setBaiduHeatMapEnabled(true);
        }
    }

    @Override
    public void onReceiveLocation(BDLocation location) {
        String describe = location.getLocationDescribe();
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng lng = new LatLng(latitude, longitude);
        Address address = location.getAddress();
        String address_1 = address.address;
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(lng);
        BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(R.drawable.pin);
        markerOptions.icon(descriptor);


    }
}
