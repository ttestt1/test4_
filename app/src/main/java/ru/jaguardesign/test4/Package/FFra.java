package ru.jaguardesign.test4.Package;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import ru.jaguardesign.test4.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FFra.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FFra#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FFra extends Fragment implements FraAnwer1,FraAnwer2{ Marker prevm;
    Context ctx;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    JSONArray array;
    JSONArray array1;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<Meet> arr=new ArrayList<Meet>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FFra.
     */
    // TODO: Rename and change types and number of parameters
    public static FFra newInstance(String param1, String param2) {
        FFra fragment = new FFra();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FFra() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        ctx = getActivity().getApplicationContext();
    }
ListView lv;
    MapView mapView;
    GoogleMap map;
    String url2="http://kshp-company.ru/z_test/url2.php";
     String url1="http://kshp-company.ru/z_test/url1.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_f, container, false);
        lv=(ListView)v.findViewById(R.id.listView);
        Load mt=new Load(ctx,this);
        mt.execute(url1);
        mapView = (MapView) v.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
              /*  LatLng coordinates = new LatLng(45.6, 45.6);
                googleMap.addMarker(new MarkerOptions().position(coordinates).title("f"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 15));*/

                mapView.onResume();
            }
        });
        // Gets to GoogleMap from the MapView and does initialization stuff
        map = mapView.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        MapsInitializer.initialize(this.getActivity());
        locate(0,0);

        return v;
        // Inflate the layout for this fragment

    }
private void setlist()
{
    /*for(int i = 0; i < array.length(); i++)
    {
        JSONObject obj = array.getJSONObject(i);

        //now, get whatever value you need from the object:
        String placename = obj.getString("title");
        Log.d("m__l", placename);
        //or if on the MainUI thread you can set your TextView from here:
        //  yourTextView.setText(obj.getString("placename"));
    }*/

}
    @Override
    public void FraEdit1(JSONArray ar)
    {
        array=ar;
        Load0 mt0= new Load0(ctx,this);
        mt0.execute(url2);
    }
    @Override
    public void FraEdit2(JSONArray ar)
    {
        array1=ar;
        try {
        for(int i = 0; i < array.length(); i++)
        {
            JSONObject obj = array.getJSONObject(i);

            //now, get whatever value you need from the object:
            String id = obj.getString("organizationId");
            Log.d("m__l", id);
            for(int ii = 0; ii < array1.length(); ii++)
            {
                JSONObject obj1 = array1.getJSONObject(ii);
                if (id.equals(obj1.getString("organizationId")))
                {
                    double lat=0;double lon=0;
                    try {


                       lat = Double.parseDouble(obj1.getString("latitude"));
                        lon= Double.parseDouble(obj1.getString("longitude"));
                    } catch (NumberFormatException e) {
                        // p did not contain a valid double
                    }
                    Meet m=new Meet(lat,lon,obj.getString("title"),obj1.getString("title"));
                    arr.add(m);
                    LatLng coordinates = new LatLng(m.lat, m.lon);
                    map.addMarker(new MarkerOptions().position(coordinates).title("f"));
                }
            }
            //or if on the MainUI thread you can set your TextView from here:
            //  yourTextView.setText(obj.getString("placename"));
        }} catch (JSONException e) {
            e.printStackTrace();
        }
        locate(arr.get(0).lat,arr.get(0).lon);
        MySimpleArrayAdapter arrayAdapter = new MySimpleArrayAdapter(
                ctx,

                arr );

        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            View previousView = null;
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                //Here you can get the position and access your
                //TicketList Object
                locate(arr.get(pos).lat, arr.get(pos).lon);
                view.setBackgroundColor(Color.GREEN);
                if (previousView!=null) previousView.setBackgroundColor(Color.WHITE);
                previousView=view;
            }
        });
    }

    private void locate(double lat,double lon) {
        if (lat!=0&&lon!=0)
        {
            LatLng coordinates = new LatLng(lat, lon);
            if (prevm!=null)
            {
                LatLng pl=prevm.getPosition();
                prevm.remove();
                map.addMarker(new MarkerOptions().position(pl).title("f"));
            }
            //map.de
           prevm= map.addMarker(new MarkerOptions().position(coordinates).title("f").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(lat, lon))      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
        else {
        LocationManager locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
       // Criteria criteria = new Criteria();


        Location location = null;
        try {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);//locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    if (location != null)
    {
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                .zoom(17)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }}
}
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
     /*   try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
