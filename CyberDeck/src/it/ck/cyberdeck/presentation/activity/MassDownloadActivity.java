package it.ck.cyberdeck.presentation.activity;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.CardLibrary;
import it.ck.cyberdeck.presentation.BaseCardLibraryActivity;
import it.ck.cyberdeck.presentation.service.AndroidFSImageService;
import it.ck.cyberdeck.presentation.service.ImageService;
import it.ck.cyberdeck.presentation.service.MassDownloadTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;

public class MassDownloadActivity extends BaseCardLibraryActivity {

	
	private static final String TAG = "trf";
	private TextView advancement;
	private CardLibrary cardLibrary;

	private TaskRetainFragment trf;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mass_download);
		advancement = (TextView) findViewById(R.id.textView1);
		cardLibrary = getCardLibrary();
		ImageService is = new AndroidFSImageService(this);
		trf = findOrCreateRetainFragment(getSupportFragmentManager());
		trf.createMassDownloadTask(is, cardLibrary, advancement);
		trf.executeTask();
		trf.setUpdateView(advancement);

	}

	public void updateProgress(Integer perc) {
		advancement.setText(perc.toString() + "%");
	}
	
	private static TaskRetainFragment findOrCreateRetainFragment(FragmentManager fm) {
        // Check to see if we have retained the worker fragment.
		TaskRetainFragment mRetainFragment = (TaskRetainFragment) fm.findFragmentByTag(TAG);

        // If not retained (or first time running), we need to create and add it.
        if (mRetainFragment == null) {
            mRetainFragment = new TaskRetainFragment();
            fm.beginTransaction().add(mRetainFragment, TAG).commitAllowingStateLoss();
        }

        return mRetainFragment;
    }
	
    public static class TaskRetainFragment extends Fragment {
        private MassDownloadTask task;

        /**
         * Empty constructor as per the Fragment documentation
         */
        public TaskRetainFragment() {}

        public void setUpdateView(TextView advancement) {
			task.setProgressView(advancement);
			
		}

		public void executeTask() {
        	Status status = task.getStatus();
        	if(status.equals(Status.PENDING)){
        		task.execute();
        	}
		}

		@Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }

        /**
         * Store a single object in this Fragment.
         *
         * @param object The object to store
         */
        public void createMassDownloadTask(ImageService is, CardLibrary cl, TextView advancement) {
            if(task == null)
            	task = new MassDownloadTask(is, cl, advancement);
        }

        /**
         * Get the stored object.
         *
         * @return The stored object
         */
        public MassDownloadTask getObject() {
            return task;
        }
    }

}
