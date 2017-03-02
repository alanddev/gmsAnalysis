package com.alanddev.gmscall.adapter;

import com.alanddev.gmscall.R;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class NwInfoWindowAdapter  implements InfoWindowAdapter {
	    private final View nwMarkerView;
	    public NwInfoWindowAdapter(Context context) {
	    	//LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
	    	LayoutInflater inflater = LayoutInflater.from(context);
	    	
	    	nwMarkerView = inflater.inflate(R.layout.nw_info_window, null); 
	    }

	    public View getInfoWindow(Marker marker) {      
	        //render(marker, nwMarkerview);
	        //return nwMarkerview;
	    	return null;
	    }

	    public View getInfoContents(Marker marker) {
	    	TextView t = (TextView)nwMarkerView.findViewById(R.id.infoTitle);
	    	t.setText(marker.getTitle());

	    	TextView t2 = (TextView)nwMarkerView.findViewById(R.id.snippet);
	    	t2.setText(marker.getSnippet());
	    	return nwMarkerView;
	    }

	}


