package it.ck.cyberdeck.presentation.activity;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardKey;
import it.ck.cyberdeck.model.CardLibrary;
import it.ck.cyberdeck.model.group.ElementGroup;
import it.ck.cyberdeck.presentation.BaseDeckActivity;
import it.ck.cyberdeck.presentation.CyberDeckApp;
import it.ck.cyberdeck.presentation.adapter.CardGridAdapter;
import it.ck.cyberdeck.presentation.presenter.DeckPresenter;

import java.util.List;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.ImageView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;

public class GroupedAddCardActivity extends BaseDeckActivity {

	public static class CardSectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_GROUP = "section_number";
		private ElementGroup<Card> cardGroup;
		
		public CardSectionFragment() {

		};

		public static CardSectionFragment newInstance(
				ElementGroup<Card> cardGroup, DeckPresenter presenter) {
			CardSectionFragment fragment = new CardSectionFragment();
			Bundle args = new Bundle();
			args.putSerializable(CardSectionFragment.ARG_SECTION_GROUP,
					cardGroup);
			fragment.setArguments(args);
			return fragment;
		}

		@SuppressWarnings("unchecked")
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.fragment_card_grid,
					container, false);
			cardGroup = (ElementGroup<Card>) getArguments().getSerializable(ARG_SECTION_GROUP);
			GridView gridView = (GridView) rootView.findViewById(R.id.gridview);
			CardGridAdapter adapter = new CardGridAdapter(this.getActivity().getApplicationContext(), cardGroup.getCards());
			gridView.setAdapter(adapter);
			OnItemClickListener listener = new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View v,
						int position, long id) {
					Card card = cardGroup.getCards().get(position);
					getSwipeActivity().addCard(card);
				}

				private GroupedAddCardActivity getSwipeActivity() {
					return (GroupedAddCardActivity) CardSectionFragment.this
							.getActivity();
				}
			};
			gridView.setOnItemClickListener(listener);

			OnItemLongClickListener onItemLongClickListener = new OnItemLongClickListener(){

				@Override
				public boolean onItemLongClick(AdapterView<?> parent, View view,
						int position, long id) {
					getSwipeActivity().zoomImageFromThumb(view, cardGroup.getCards().get(position).getKey());
					return true;
				}
				
				private GroupedAddCardActivity getSwipeActivity() {
					return (GroupedAddCardActivity) CardSectionFragment.this
							.getActivity();
				}
			};
			gridView.setOnItemLongClickListener(onItemLongClickListener);
			
			return rootView;
		}

	}

	public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

		private List<ElementGroup<Card>> cardGroups;

		public SectionsPagerAdapter(FragmentManager fm,
				List<ElementGroup<Card>> cardGroups) {
			super(fm);
			this.cardGroups = cardGroups;
		}

		@Override
		public Fragment getItem(int position) {
			return CardSectionFragment.newInstance(cardGroups.get(position),
					GroupedAddCardActivity.this.presenter);
		}

		@Override
		public int getCount() {
			return cardGroups.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return cardGroups.get(position).getType().name();
		}
	}

	private SectionsPagerAdapter sectionsPagerAdapter;
	private ViewPager viewPager;
	private int mShortAnimationDuration;
	private Animator mCurrentAnimator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.swipe_add_card_activity);

		CardLibrary cl = ((CyberDeckApp) getApplication()).getDeckService()
				.loadCardLibrary();
		List<ElementGroup<Card>> cardGroups = cl
				.getCardGroupsWithoutIdentities(getDeck().getIdentity());

		sectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager(), cardGroups);

		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(sectionsPagerAdapter);
		mShortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);

	}

	public void addCard(Card card) {
		presenter.addCard(card);
	}
	
	private void zoomImageFromThumb(final View thumbView, CardKey cardKey) {
	    // If there's an animation in progress, cancel it
	    // immediately and proceed with this one.
	    if (mCurrentAnimator != null) {
	        mCurrentAnimator.cancel();
	    }

	    // Load the high-resolution "zoomed-in" image.
	    final ImageView expandedImageView = (ImageView) findViewById(
	            R.id.expanded_image);

	    loadImage(cardKey, expandedImageView);

	    // Calculate the starting and ending bounds for the zoomed-in image.
	    // This step involves lots of math. Yay, math.
	    final Rect startBounds = new Rect();
	    final Rect finalBounds = new Rect();
	    final Point globalOffset = new Point();

	    // The start bounds are the global visible rectangle of the thumbnail,
	    // and the final bounds are the global visible rectangle of the container
	    // view. Also set the container view's offset as the origin for the
	    // bounds, since that's the origin for the positioning animation
	    // properties (X, Y).
	    thumbView.getGlobalVisibleRect(startBounds);
	    findViewById(R.id.container)
	            .getGlobalVisibleRect(finalBounds, globalOffset);
	    startBounds.offset(-globalOffset.x, -globalOffset.y);
	    finalBounds.offset(-globalOffset.x, -globalOffset.y);

	    // Adjust the start bounds to be the same aspect ratio as the final
	    // bounds using the "center crop" technique. This prevents undesirable
	    // stretching during the animation. Also calculate the start scaling
	    // factor (the end scaling factor is always 1.0).
	    float startScale;
	    if ((float) finalBounds.width() / finalBounds.height()
	            > (float) startBounds.width() / startBounds.height()) {
	        // Extend start bounds horizontally
	        startScale = (float) startBounds.height() / finalBounds.height();
	        float startWidth = startScale * finalBounds.width();
	        float deltaWidth = (startWidth - startBounds.width()) / 2;
	        startBounds.left -= deltaWidth;
	        startBounds.right += deltaWidth;
	    } else {
	        // Extend start bounds vertically
	        startScale = (float) startBounds.width() / finalBounds.width();
	        float startHeight = startScale * finalBounds.height();
	        float deltaHeight = (startHeight - startBounds.height()) / 2;
	        startBounds.top -= deltaHeight;
	        startBounds.bottom += deltaHeight;
	    }

	    // Hide the thumbnail and show the zoomed-in view. When the animation
	    // begins, it will position the zoomed-in view in the place of the
	    // thumbnail.
//	    ViewHelper.setAlpha(thumbView, 0f);
	    thumbView.setAlpha(0f);
	    expandedImageView.setVisibility(View.VISIBLE);
//	    ViewHelper.setAlpha(expandedImageView, 1f);
			expandedImageView.setAlpha(1f);
	    // Set the pivot point for SCALE_X and SCALE_Y transformations
	    // to the top-left corner of the zoomed-in view (the default
	    // is the center of the view).
//	    ViewHelper.setPivotX(expandedImageView, 0f);
//	    ViewHelper.setPivotY(expandedImageView, 0f);
	    expandedImageView.setPivotX(0f);
	    expandedImageView.setPivotY(0f);

	    // Construct and run the parallel animation of the four translation and
	    // scale properties (X, Y, SCALE_X, and SCALE_Y).
	    AnimatorSet set = new AnimatorSet();
	    set
	            .play(ObjectAnimator.ofFloat(expandedImageView, "x",
	                    startBounds.left, finalBounds.left))
	            .with(ObjectAnimator.ofFloat(expandedImageView,  "y",
	                    (float)startBounds.top, (float)finalBounds.top))
	            .with(ObjectAnimator.ofFloat(expandedImageView, "scaleX",
	            startScale, 1f)).with(ObjectAnimator.ofFloat(expandedImageView,
	            		 "scaleY", startScale, 1f));
	    set.setDuration(mShortAnimationDuration);
	    set.setInterpolator(new DecelerateInterpolator());
	    set.addListener(new AnimatorListenerAdapter() {
	        @Override
	        public void onAnimationEnd(Animator animation) {
	            mCurrentAnimator = null;
	        }

	        @Override
	        public void onAnimationCancel(Animator animation) {
	            mCurrentAnimator = null;
	        }
	    });
	    set.start();
	    mCurrentAnimator = set;

	    // Upon clicking the zoomed-in image, it should zoom back down
	    // to the original bounds and show the thumbnail instead of
	    // the expanded image.
	    final float startScaleFinal = startScale;
	    expandedImageView.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View view) {
	            if (mCurrentAnimator != null) {
	                mCurrentAnimator.cancel();
	            }

	            // Animate the four positioning/sizing properties in parallel,
	            // back to their original values.

	            AnimatorSet set = new AnimatorSet();
	            set.play(ObjectAnimator.ofFloat(expandedImageView,  "x", startBounds.left))
	                        .with(ObjectAnimator
	                                .ofFloat(expandedImageView, "y",startBounds.top))
	                        .with(ObjectAnimator
	                                .ofFloat(expandedImageView, "scaleX", startScaleFinal))
	                        .with(ObjectAnimator
	                                .ofFloat(expandedImageView, "scaleY", startScaleFinal))
	                                .with(ObjectAnimator.ofFloat(expandedImageView, "alpha", 0f)
	                                		);
	            set.setDuration(mShortAnimationDuration);
	            set.setInterpolator(new DecelerateInterpolator());

	            set.addListener(new AnimatorListenerAdapter() {
	                @Override
	                public void onAnimationEnd(Animator animation) {
	                	Log.i("animation", "onAnimationEnd");
	                	thumbView.setAlpha(1f);
//	                	ViewHelper.setAlpha(thumbView, 1f);
	                	expandedImageView.clearAnimation();
	                    expandedImageView.setVisibility(View.GONE);
	                    mCurrentAnimator = null;
	                }

	                @Override
	                public void onAnimationCancel(Animator animation) {
	                	Log.i("animation", "onAnimationCancel");
	                	thumbView.setAlpha(1f);
//	                	ViewHelper.setAlpha(thumbView, 1f);
	                	expandedImageView.clearAnimation();
	                    expandedImageView.setVisibility(View.GONE);
	                    mCurrentAnimator = null;
	                }
	            });
	            set.start();
	            mCurrentAnimator = set;
	        }
	    });
	}
	

	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		intent.putExtra(BaseDeckActivity.DECK_ARG_ID, getDeck());
		setResult(RESULT_OK, intent);
		super.onBackPressed();
	}
	

}
