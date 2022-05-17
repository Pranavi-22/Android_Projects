package course.examples.UI.GridLayout;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ImageViewActivity extends Activity {

	boolean isBack = false;
	boolean isFront = false;
	int animalPosition = 0;
	String[] names = {"rabbit" , "fox" , "eagle" ,  "koala" , "rabbit again" , "bear" , "dog" , "cat" , "elephant" , "sheep" , "tiger" , "turtle" };
	int[] images = {R.drawable.image1, R.drawable.image2,
			R.drawable.image3, R.drawable.image4, R.drawable.image5,
			R.drawable.image6, R.drawable.image7, R.drawable.image8,
			R.drawable.image9, R.drawable.image10, R.drawable.image11,
			R.drawable.image12};

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	
		// Get the Intent used to start this Activity
		Intent intent = getIntent();
		
		// Make a new ImageView
		// Example of programmatic layout definition
		ImageView imageView = new ImageView(getApplicationContext());
		
		// Get the ID of the image to display and set it as the image for this ImageView
		String str1 = GridLayoutActivity.EXTRA_RES_ID;
		Log.d("img1 ", intent.getIntExtra(str1 , 0) + "" )  ;
		animalPosition = intent.getIntExtra(str1 , 0) ;
		imageView.setImageResource(images[intent.getIntExtra(str1 , 0)]);
		setContentView(imageView);

		imageView.setOnClickListener(new View.OnClickListener() {
			//@Override
			public void onClick(View v) {
				Log.d("clicked ", " click");



				Intent intent = new Intent(ImageViewActivity.this, fact_activity.class);

			// Add the ID of the thumbnail to display as an Intent Extra
				intent.putExtra(GridLayoutActivity.EXTRA_RES_ID, (int) animalPosition);


			// Start the ImageViewActivity

				startActivity(intent);
			}
		});




	}




//	public void onUserInteraction() {
//		Log.d("anywhere", "Touch anywhere happened "  + isBack);
//		if(isBack == false) {
//
//			super.onUserInteraction();
//
//
//			Intent intent = new Intent(ImageViewActivity.this, fact_activity.class);
//
//			// Add the ID of the thumbnail to display as an Intent Extra
//			intent.putExtra(GridLayoutActivity.EXTRA_RES_ID, (int) animalPosition);
//
//
//			// Start the ImageViewActivity
//			isBack = true;
//			startActivity(intent);
//		}
//
//	}



}