package vt.droidcon;

/**
 * Created by YAM on 1/26/2015.
 */


        import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
        import android.widget.Toast;

        import com.vt.droidcon.R;

public class Get_loc extends Activity {
    Button addressButton , skip;
    TextView addressTV;
    TextView latLongTV;
    public  String lat=null ,lon=null,llt=null,add="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getloc);


        addressTV = (TextView) findViewById(R.id.addressTV);
        latLongTV = (TextView) findViewById(R.id.latLongTV);

  /*      skip = (Button) findViewById(R.id.skipbtn);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Get_loc.this,
                        advice.class);
               startActivity(intent);
            }
        });
    */
        addressButton = (Button) findViewById(R.id.addressButton);
        addressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                EditText editText = (EditText) findViewById(R.id.addressET);
                String address = editText.getText().toString();
                add = address;
                if(add == null || add == ""){
                    Toast.makeText(getApplicationContext(),
                            "please Enter the location address",
                            Toast.LENGTH_LONG).show();
                }else {

                    GeocodingActivity locationAddress = new GeocodingActivity();
                    try {
                        locationAddress.getAddressFromLocation(address,
                                getApplicationContext(), new GeocoderHandler());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //Intent in1 = new Intent(Get_loc.this , Welcome)''
         /*       Intent intent = new Intent(
                        Get_loc.this,
                        Welcome.class);
                Log.e("lat and lot = " , " "+lat+" "+lon);
               startActivity(intent);
*/

            }
        });


    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;

            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    lat = bundle.getString("address1");
                    lon = bundle.getString("address2");
                    locationAddress = lat + lon;
                    if(lat!=null || lon!=null) {
                        Intent in = new Intent(Get_loc.this, Welcome.class);
                        in.putExtra("ll1", lat);
                        in.putExtra("ll2", lon);
                        in.putExtra("add", add);
                        startActivity(in);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),
                                "please Enter the location address",
                                Toast.LENGTH_LONG).show();
                    }
                    break;
                default:
                    locationAddress = null;
            }

            latLongTV.setText(locationAddress);
        }
    }
}
