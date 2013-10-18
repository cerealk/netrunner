package it.ck.cyberdeck.presentation.activity;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.CardLibrary;
import it.ck.cyberdeck.presentation.BaseCardLibraryActivity;
import it.ck.cyberdeck.presentation.MassDownloadView;
import it.ck.cyberdeck.presentation.service.AndroidFSImageService;
import it.ck.cyberdeck.presentation.service.ImageService;
import it.ck.cyberdeck.presentation.service.MassDownloadTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;

public class MassDownloadActivity extends BaseCardLibraryActivity implements MassDownloadView {

	
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
		
		trf = findOrCreateRetainFragment();
		trf.executeTask();
	}

	@Override
	public void onUpdateProgress(Integer perc) {
		advancement.setText(perc.toString() + "%");
	}
	
	@Override
	public void onTaskEnd() {
		onBackPressed();
	}
	
	
	private TaskRetainFragment findOrCreateRetainFragment() {
		FragmentManager fm = getSupportFragmentManager();
		TaskRetainFragment trf = (TaskRetainFragment) fm.findFragmentByTag(TAG);

        if (trf == null) {
            trf = new TaskRetainFragment();
            fm.beginTransaction().add(trf, TAG).commitAllowingStateLoss();
            ImageService is = new AndroidFSImageService(this);
            trf.createMassDownloadTask(is, cardLibrary);
        }
        trf.setUpdateView(this);
        return trf;
    }
	
    public static class TaskRetainFragment extends Fragment {
        private MassDownloadTask task;

        public void setUpdateView(MassDownloadView massDownloadActivity) {
			task.setProgressView(massDownloadActivity);
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

        public void createMassDownloadTask(ImageService is, CardLibrary cl) {
            if(task == null)
            	task = new MassDownloadTask(is, cl);
        }

    }



}
