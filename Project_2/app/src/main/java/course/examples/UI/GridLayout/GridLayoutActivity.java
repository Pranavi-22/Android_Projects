package course.examples.UI.GridLayout;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;



import android.view.ContextMenu;
import android.view.MenuItem;

import android.widget.Toast;

//This application uses some deprecated methods. 
//See UIViewPager for a more modern version of this application

public class GridLayoutActivity extends Activity {

	protected static final String EXTRA_RES_ID = "POS";
	GridView gridView;
	int animalPosition = 0;
	String[] names = {"rabbit", "fox", "eagle", "koala", "fish", "bear", "dog", "cat", "elephant", "sheep", "tiger", "turtle"};

	String[] webLinks = { "https://en.wikipedia.org/wiki/Rabbit" , "https://en.wikipedia.org/wiki/Fox" , "https://en.wikipedia.org/wiki/Eagle"  ,
			"https://en.wikipedia.org/wiki/Koala" , "https://en.wikipedia.org/wiki/Fish" , "https://en.wikipedia.org/wiki/Bear" , "https://en.wikipedia.org/wiki/Dog" ,
			"https://en.wikipedia.org/wiki/Cat" , "https://en.wikipedia.org/wiki/Elephant" , "https://en.wikipedia.org/wiki/Sheep" , "https://en.wikipedia.org/wiki/Tiger" ,
			"https://en.wikipedia.org/wiki/Turtle"};

	int[] images = {R.drawable.image1, R.drawable.image2,
			R.drawable.image3, R.drawable.image4, R.drawable.image5,
			R.drawable.image6, R.drawable.image7, R.drawable.image8,
			R.drawable.image9, R.drawable.image10, R.drawable.image11,
			R.drawable.image12};

//	private ArrayList<Integer> mThumbIdsFlowers = new ArrayList<Integer>(
//			Arrays.asList(R.drawable.image1, R.drawable.image2,
//					R.drawable.image3, R.drawable.image4, R.drawable.image5,
//					R.drawable.image6, R.drawable.image7, R.drawable.image8,
//					R.drawable.image9, R.drawable.image10, R.drawable.image11,
//					R.drawable.image12));


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);


		gridView = findViewById(R.id.gridview);

		CustomAdapter customAdapter = new CustomAdapter(names, images, this);

		gridView.setAdapter(customAdapter);


//		GridView gridview = (GridView) findViewById(R.id.gridview);
//
//		// Create a new ImageAdapter and set it as the Adapter for this GridView
//		gridview.setAdapter(new ImageAdapter(this, mThumbIdsFlowers));

		// Set an setOnItemClickListener on the GridView

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
									int position, long id) {

				Log.d("ss", position + "");
				//Create an Intent to start the ImageViewActivity
				Intent intent = new Intent(GridLayoutActivity.this,
						ImageViewActivity.class);

				// Add the ID of the thumbnail to display as an Intent Extra
				intent.putExtra(EXTRA_RES_ID, (int) position);

				// Start the ImageViewActivity
				startActivity(intent);
			}
		});

		registerForContextMenu(gridView);

	}


	public class CustomAdapter extends BaseAdapter {

		private String[] imageName;
		private int[] imagePhoto;
		private Context context;
		private LayoutInflater layoutInflater;


		public CustomAdapter(String[] imageName, int[] imagePhoto, Context context) {
			this.imageName = imageName;
			this.imagePhoto = imagePhoto;
			this.context = context;
			this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
		}


		@Override
		public int getCount() {
			return imagePhoto.length;
		}

		@Override
		public Object getItem(int i) {
			return null;
		}

		@Override
		public long getItemId(int i) {
			return 0;
		}

		@Override
		public View getView(int i, View view, ViewGroup viewGroup) {

			if (view == null) {
				view = layoutInflater.inflate(R.layout.row_items, viewGroup, false);
			}

			TextView animalName = view.findViewById(R.id.animalName);
			ImageView imageView = view.findViewById(R.id.imageView);


			animalName.setText(imageName[i]);
			imageView.setImageResource(imagePhoto[i]);

			return view;
		}
	}


	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("you got 3 options");
		getMenuInflater().inflate(R.menu.example_menu, menu);
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
		animalPosition = info.position;

		//Toast.makeText(this, "pls" +" " + position   , Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onContextItemSelected(MenuItem item ) {

		switch (item.getItemId()) {
			case R.id.o1:
				//Toast.makeText(this, "showing image" + item.getItemId(), Toast.LENGTH_SHORT).show();
				Intent intent1 = new Intent(GridLayoutActivity.this,
						ImageViewActivity.class);

				// Add the ID of the thumbnail to display as an Intent Extra
				intent1.putExtra(EXTRA_RES_ID, (int) animalPosition);

				// Start the ImageViewActivity
				startActivity(intent1);
				return true;
			case R.id.o2:

				Intent intent2 = new Intent(GridLayoutActivity.this,
						fact_activity.class);
				//Toast.makeText(this, "showing facts", Toast.LENGTH_SHORT).show();
				// Add the ID of the thumbnail to display as an Intent Extra
				intent2.putExtra(GridLayoutActivity.EXTRA_RES_ID, (int) animalPosition);

				// Start the ImageViewActivity
				startActivity(intent2);

				return true;
			case R.id.o3:
				Intent viewIntent =
						new Intent("android.intent.action.VIEW",
								Uri.parse(webLinks[animalPosition]));
				startActivity(viewIntent);

				return true;
			default:
				return super.onContextItemSelected(item);
		}
	}


}
