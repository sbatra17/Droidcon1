package vt.droidcon;

/**
 * Created by YAM on 1/26/2015.
 */

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GeocodingActivity{

    private static final String TAG = "GeocodingLocation";

    public static void getAddressFromLocation(final String locationAddress,
                                              final Context context, final Handler handler)throws IOException{
        Thread thread = new Thread() {
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                String result = null;
                String result1 = null , result2= null ;
                try {
                    Log.e("location address " , locationAddress);
                    List<Address> addressList = geocoder.getFromLocationName(locationAddress, 1);
                    Log.e("hello " , "123");
                    if (addressList != null && addressList.size() > 0) {
                        Address address = addressList.get(0);
                        StringBuilder sb1 = new StringBuilder();
                        StringBuilder sb2 = new StringBuilder();
                        sb1.append(address.getLatitude()).append("\n");
                        sb2.append(address.getLongitude()).append("\n");
                        result1 = sb1.toString();
                        result2 = sb2.toString();
                        result = result1+" "+result2;
                        Log.e(TAG , "result = "+result1+"  "+result2);
                    }
                }
                //catch()
                catch (IOException e) {
                    Log.e(TAG, "Unable to connect to Geocoder", e);
                } finally {
                    Message message = Message.obtain();
                    message.setTarget(handler);
                    if (result != null) {
                        message.what = 1;
                        Bundle bundle1 = new Bundle();
                        Bundle bundle2 = new Bundle();
                        result = "Address: " + locationAddress +
                                "\n\nLatitude and Longitude :\n" + result;
                        bundle1.putString("address1", result1);
                        bundle1 .putString("address2", result2);
                        message.setData(bundle1);
            /*             Intent in = new Intent(GeocodingActivity.this , Welcome.class);
                        in.putExtra("ll1",result1);
                        in.putExtra("ll2",result2);
                        startActivity(in);
              */
                    } else {
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        result = "Address: " + locationAddress +
                                "\n Unable to get Latitude and Longitude for this address location.";
                        bundle.putString("address", result);
                        message.setData(bundle);
                    }
                    message.sendToTarget();

                }
            }
        };
        thread.start();

    }
}