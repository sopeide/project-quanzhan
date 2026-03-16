package com.example.sensordemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;

import org.java_websocket.client.WebSocketClient;

import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView orientationText;
    private TextView locationText;

    private AMapLocationClient locationClient;
    private AMapLocationClientOption locationOption;

    private MapView mapView;
    private AMap aMap;

    private SurfaceView cameraView;
    private Camera camera;

    private WebSocketClient webSocketClient;

    private static final int REQUEST_PERMISSIONS = 1001;
    private final String[] permissions = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CAMERA,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE
    };

    private float yaw, pitch, roll;
    private double longitude, latitude, altitude;
    private boolean hasOrientation = false;
    private boolean hasLocation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AMapLocationClient.updatePrivacyShow(this, true, true);
        AMapLocationClient.updatePrivacyAgree(this, true);

        setContentView(R.layout.activity_main);

        orientationText = findViewById(R.id.orientationText);
        locationText = findViewById(R.id.locationText);
        cameraView = findViewById(R.id.cameraView);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (!hasAllPermissions()) {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSIONS);
        } else {
            initAll();
        }
    }

    private boolean hasAllPermissions() {
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
                Log.d("PermissionCheck", "缺少权限：" + perm);
                return false;
            }
        }
        return true;
    }

    private void initAll() {
        initMap();
        initLocation();
        initCamera();
        initWebSocket();
    }

    private void initMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        myLocationStyle.interval(2000);
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationEnabled(true);
    }

    private void initLocation() {
        try {
            locationClient = new AMapLocationClient(getApplicationContext());
            locationOption = new AMapLocationClientOption();
            locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            locationOption.setInterval(2000);
            locationClient.setLocationOption(locationOption);
            locationClient.setLocationListener(new AMapLocationListener() {
                @Override
                public void onLocationChanged(AMapLocation location) {
                    if (location != null) {
                        if (location.getErrorCode() == 0) {
                            longitude = location.getLongitude();
                            latitude = location.getLatitude();
                            altitude = location.getAltitude();
                            hasLocation = true;

                            String locStr = String.format("经度：%.6f\n纬度：%.6f\n海拔：%.2f 米", longitude, latitude, altitude);
                            locationText.setText(locStr);

                            trySendCombinedData();
                        } else {
                            locationText.setText("定位失败: " + location.getErrorCode() +
                                    "\n错误信息: " + location.getErrorInfo());
                        }
                    } else {
                        locationText.setText("定位返回为空");
                    }
                }
            });
            locationClient.startLocation();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "初始化定位失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void initCamera() {
        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                try {
                    camera = Camera.open();
                    camera.setPreviewDisplay(holder);
                    camera.startPreview();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "无法启动相机", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {}

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                if (camera != null) {
                    camera.stopPreview();
                    camera.release();
                    camera = null;
                }
            }
        });
    }

    private void initWebSocket() {
        try {
            URI uri = new URI("ws://10.0.2.2:1111/ws/drone"); // 模拟器
//            URI uri = new URI("ws://10.139.130.20:1111/ws/drone"); // 真机
            webSocketClient = new UAVWebSocketClient(uri);
            webSocketClient.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
        Sensor rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        if (rotationVectorSensor != null) {
            sensorManager.registerListener(this, rotationVectorSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if (locationClient != null) {
            locationClient.stopLocation();
            locationClient.onDestroy();
        }
        if (webSocketClient != null) {
            webSocketClient.close();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            float[] rotationMatrix = new float[9];
            float[] remappedRotationMatrix = new float[9];
            float[] orientation = new float[3];

            SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values);
            // 关键修改：将设备坐标系映射为 Android Studio Virtual Sensors 显示一致的坐标系
            SensorManager.remapCoordinateSystem(rotationMatrix,
                    SensorManager.AXIS_X, SensorManager.AXIS_Z, remappedRotationMatrix);
            SensorManager.getOrientation(remappedRotationMatrix, orientation);

            yaw = (float) Math.toDegrees(orientation[0]);   // z 轴旋转（航向角）
            pitch = (float) Math.toDegrees(orientation[1]); // x 轴旋转（俯仰角）
            roll = (float) Math.toDegrees(orientation[2]);  // y 轴旋转（横滚角）
            hasOrientation = true;

            String orientationStr = String.format("Yaw: %.4f°\nPitch: %.4f°\nRoll: %.4f°", yaw, pitch, roll);
            orientationText.setText(orientationStr);

            trySendCombinedData();
        }
    }

    private void trySendCombinedData() {
        if (hasOrientation && hasLocation && webSocketClient != null && webSocketClient.isOpen()) {
            String data = String.format(
                    "{\"yaw\": %.4f, \"pitch\": %.4f, \"roll\": %.4f, \"longitude\": %.6f, \"latitude\": %.6f, \"altitude\": %.2f}",
                    yaw, pitch, roll, longitude, latitude, altitude
            );
            webSocketClient.send(data);
            Log.d("WebSocket", "发送数据：" + data);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }
            if (allGranted) {
                Toast.makeText(this, "权限已全部授予", Toast.LENGTH_SHORT).show();
                initAll();
            } else {
                Toast.makeText(this, "权限未全部授权，功能可能受限", Toast.LENGTH_LONG).show();
            }
        }
    }
}
