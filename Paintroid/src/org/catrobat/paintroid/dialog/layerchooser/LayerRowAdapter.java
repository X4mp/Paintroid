package org.catrobat.paintroid.dialog.layerchooser;

import java.util.ArrayList;

import org.catrobat.paintroid.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LayerRowAdapter extends ArrayAdapter<LayerRow> {
	Context context;
	int layoutResourceId;
	ArrayList<LayerRow> data = null;

	public LayerRowAdapter(Context context, int layoutResourceId,
			ArrayList<LayerRow> layer_data) {
		super(context, layoutResourceId, layer_data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = layer_data;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View row = convertView;
		LayerRowHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new LayerRowHolder();
			holder.thumbnail = (ImageView) row.findViewById(R.id.thumbnail);
			holder.layerTitle = (TextView) row.findViewById(R.id.layerTitle);
			holder.eyeIcon = (ImageView) row.findViewById(R.id.eyeIcon);

			row.setTag(holder);
		} else {
			holder = (LayerRowHolder) row.getTag();
		}

		LayerRow mLayerRow = data.get(position);
		holder.layerTitle.setText(mLayerRow.name);
		holder.thumbnail.setImageResource(mLayerRow.icon);

		// holder.eyeIcon.setClickable(true);
		holder.eyeIcon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				data.get(position).visible = !data.get(position).visible;
			}
		});

		if (data.get(position).visible) {
			holder.eyeIcon.setImageResource(R.drawable.ic_menu_view);
		} else {
			holder.eyeIcon.setImageResource(R.drawable.ic_menu_no_view);
		}

		if (data.get(position).selected) {
			row.setBackgroundResource(R.color.abs__holo_blue_light);
		} else {
			row.setBackgroundResource(R.color.dialog_background_pre_v14_color);
		}
		return row;
	}

	static class LayerRowHolder {
		ImageView thumbnail;
		TextView layerTitle;
		static ImageView eyeIcon;
	}
}