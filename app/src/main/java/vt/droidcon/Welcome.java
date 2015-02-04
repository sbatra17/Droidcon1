package vt.droidcon;

/**
 * Created by YAM on 1/20/2015.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.vt.droidcon.R;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;



public class Welcome extends Activity {

    // Declare Variable
    Button logout;
    String s1 = "28.61";
    String s2 = "77.23";
    String ad= "";
    public static  final String TAG = Welcome.class.getSimpleName();
    private CurrentWeather mCurrentWeather ;



    @InjectView(R.id.timeLabel) TextView mTimeLabel;
    @InjectView(R.id.temperatureLabel) TextView mTemperatureLabel;
    @InjectView(R.id.humidityValue) TextView mHumidityValue;
    @InjectView(R.id.precipValue) TextView mPrecipValue;
    @InjectView(R.id.summaryLabel) TextView mSummaryLabel;
    @InjectView(R.id.iconImageView) ImageView mIconImageView;
    @InjectView(R.id.progressBar) ProgressBar prog ;
    @InjectView(R.id.locationLabel) TextView mlocationlabel;
    @InjectView(R.id.back) Button mback;
  //  @InjectView(R.id.addressET) EditText edittext;
//EditText edittext = (EditText)findViewById(R.id.addressET);

    // @InjectView(R.id)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        ButterKnife.inject(this);

        //   mTemperatureLabel = (TextView)findViewById(R.id.temperatureLabel);


     //   public void onCreate(Bundle savedInstanceState){
      //      super.onCreate(savedInstanceState);
            // Get the view from singleitemview.xml
        //    setContentView(R.layout.welcome);

            // Retrieve current user from Parse.com
            ParseUser currentUser = ParseUser.getCurrentUser();

            // Convert currentUser into String
            String struser = currentUser.getUsername().toString();

            // Locate TextView in welcome.xml


            //  TextView txtuser = (TextView) findViewById(R.id.txtuser);

            // Set the currentUser String into TextView
            // txtuser.setText("You are logged in as " + struser);

            // Locate Button in welcome.xml
            logout = (Button) findViewById(R.id.logout);

            // Logout Button Click Listener
            logout.setOnClickListener(new View.OnClickListener() {

                public void onClick(View arg0) {
                    // Logout current user
                    ParseUser.logOut();
                    Intent intent = new Intent(
                            Welcome.this,
                          WelcomeActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),
                            "Successfully Logged out",
                            Toast.LENGTH_LONG).show();
                    finish();
                }
            });
        mback = (Button)findViewById(R.id.back);
        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(Welcome.this , Get_loc.class);
                startActivity(inte);
            }
        });


            ButterKnife.inject(this);

            //   mTemperatureLabel = (TextView)findViewById(R.id.temperatureLabel);
/*
try {
    Get_loc gl = new Get_loc();
    Bundle bun = new Bundle();
     s1 = bun.getString("address1");
    s2 = bun.getString("address2");
    Log.e("s1  = "+s1, "s2  =  "+s2);
}catch(Exception e){
    e.printStackTrace();
}
*/
Intent in = getIntent();
      String  ss= in.getStringExtra("ll1");
       String  ss2 = in.getStringExtra("ll2");
       ad = in.getStringExtra("add");

        Log.e("Intent transfer final =  ", ss+"  "+ss2);
        if(ss == null || ss2 == null){

            new AlertDialog.Builder(this).setTitle("Wrong Place")
                    .setMessage("place cant be identify by the server ! .. please Enter correct spelling or nearest popular city ..")
                    .setPositiveButton("OK" , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Intent intent1 = new Intent(Welcome.this , Get_loc.class);
                            startActivity(intent1);
                        }
                    });

        }
            //String s3 = gl.ad//
            String apiKey = "8350ea53ca7f5e87e66201a87cbd1235";
        double latitude =1000.00;
        double longitude=1000.00 ;
        try {
             latitude = Double.parseDouble(ss);//28.6100;
             longitude = Double.parseDouble(ss2);//77.2300;
        }catch(Exception e){
            new AlertDialog.Builder(this).setTitle("Wrong Place")
                    .setMessage("place cant be identify by the server ! .. please Enter correct spelling or nearest popular city ..")
                    .setPositiveButton("OK" , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Intent intent1 = new Intent(Welcome.this , Get_loc.class);
                            startActivity(intent1);
                        }
                    });
            Log.e("TAG   = =  = ",""+e);
        }
        String url="";
        try {
            url = "https://api.forecast.io/forecast/" + apiKey + "/" + latitude + "," + longitude;
        }catch (Exception e){
            Log.e(TAG , ""+e);
            new AlertDialog.Builder(this).setTitle("Wrong Place")
                    .setMessage("place cant be identify by the server ! .. please Enter correct spelling or nearest popular city ..")
                    .setPositiveButton("OK" , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Intent intent1 = new Intent(Welcome.this , Get_loc.class);
                            startActivity(intent1);
                        }
                    });

        }
            if (isNetworkAvailable()) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();

                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        AlertDialog.Builder ok1 = new AlertDialog.Builder(Welcome.this).setTitle("Wrong Place")
                                .setMessage("place cant be identify by the server ! .. please Enter correct spelling or nearest popular city ..")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        Intent intent1 = new Intent(Welcome.this, Get_loc.class);
                                        startActivity(intent1);
                                    }
                                });


                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        try {
                            String jsonData = response.body().string();
                            Log.e(TAG, jsonData);
                            if (response.isSuccessful()) {
                                mCurrentWeather = getCurrentDetails(jsonData);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        updateDisplay();
                                    }
                                });

                            } else {
                                alertUserAboutError();
                            }
                        } catch (IOException io) {
                            Log.e(TAG, "Exception ", io);

                        } catch (JSONException js) {
                            Log.e(TAG, "Exception ", js);
                        }
                    }
                });

            } else {
                Toast.makeText(this, getString(R.string.netwok_isnot_available), Toast.LENGTH_LONG).show();
            }

            Log.d(TAG, "Main UI code is running!");
        }


    private void updateDisplay() {
        prog.setVisibility(View.INVISIBLE);
        mTemperatureLabel.setText(mCurrentWeather.getTemprature()+"");
        mTimeLabel.setText("At "+mCurrentWeather.getFormattedTime() +" it will be");
        mHumidityValue.setText(""+(int) mCurrentWeather.getHumidity());
        mPrecipValue.setText("" + (int) mCurrentWeather.getPrecipChance()+"%");
        mSummaryLabel.setText(""+mCurrentWeather.getSummary());
        Drawable drawble = getResources().getDrawable(mCurrentWeather.getIconId());
        mIconImageView.setImageDrawable(drawble);
        mlocationlabel.setText(ad);
    }

    private CurrentWeather getCurrentDetails(String jsonData) throws JSONException{
        JSONObject forecast = new JSONObject(jsonData);

        String timezone = forecast.getString("timezone");
        Log.i(TAG ,"From jason: " + timezone);
        JSONObject currently = forecast.getJSONObject("currently");
        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setHumidity(currently.getDouble("humidity"));
        currentWeather.setTime(currently.getLong("time"));
        currentWeather.setIcon(currently.getString("icon"));
        currentWeather.setPrecipChance(currently.getDouble("precipProbability"));
        currentWeather.setSummary(currently.getString("summary"));
        currentWeather.setTemprature(currently.getDouble("temperature"));
        currentWeather.setTimeZone(timezone);

        Log.d(TAG , currentWeather.getFormattedTime());
        return  currentWeather;


    }

    private boolean isNetworkAvailable() {

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false ;
        if(networkInfo != null && networkInfo.isConnected()){
            isAvailable =true;
        }
        return isAvailable ;

    }

    private void alertUserAboutError() {

        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager() , "error_dialog");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main , menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.logout:
			/*
			 * Log current user out using ParseUser.logOut()
			 */
                ParseUser.logOut();
                Intent intent = new Intent(this, WelcomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


        //return super.onOptionsItemSelected(item);
    }

    private class GeocoderHandler1 extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;

            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    s1 = bundle.getString("address1");
                    s2 = bundle.getString("address2");
                    Log.e(s1 , s2);
                    locationAddress = s1 + s2;
                    break;
                default:
                    locationAddress = null;
            }

            //latLongTV.setText(locationAddress);
        }
    }


}




