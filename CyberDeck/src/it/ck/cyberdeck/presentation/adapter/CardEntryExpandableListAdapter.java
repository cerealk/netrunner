package it.ck.cyberdeck.presentation.adapter;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.CardEntry;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.model.group.ElementGroup;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class CardEntryExpandableListAdapter extends BaseExpandableListAdapter {

	private List<ElementGroup<CardEntry>> groups;
	private Context context;
	private Typeface font;
	private ImageLoader imageLoader;
	private Deck deck;

	public CardEntryExpandableListAdapter(Context context, Deck deck){
		this.context = context;
		this.deck = deck;
		this.groups = deck.getGroupedEntries();
		this.font = Typeface.createFromAsset( context.getAssets(), "fontawesome-webfont.ttf" );
		imageLoader = ImageLoader.getInstance();
		
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return groups.get(groupPosition).getCards().get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
	    boolean isLastChild, View convertView, ViewGroup parent) {
		
		
		View childView;
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			childView = inflater.inflate(R.layout.composite_deck_entry_layout,null );
		} else 
			childView = convertView;
		
		ImageView icon = (ImageView) childView.findViewById(R.id.icon);
		TextView cardName = (TextView) childView.findViewById(R.id.cardName);
		TextView cardReputation = (TextView) childView.findViewById(R.id.reputation);
		cardReputation.setTypeface(font);
		TextView countText = (TextView) childView.findViewById(R.id.countText);
		CardEntry entry = getEntry(groupPosition,childPosition);
		cardName.setText(getCardNameText(entry));
		cardReputation.setText(getCardReputationText(entry));
		countText.setText(getCardCountText(entry));
		String urlString = "http://netrunnercards.info/web/bundles/netrunnerdbcards/images/cards/300x418/"+ entry.getKey().getCardCode() +".png";
		imageLoader.displayImage(urlString, icon);
		return childView;
	}

	public CardEntry getEntry(int groupPosition, int childPosition) {
		return (CardEntry) getChild(groupPosition, childPosition);
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return groups.get(groupPosition).getCards().size();
	}

	@Override
	public ElementGroup<CardEntry> getGroup(int groupPosition) {
		return groups.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return groups.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
	    View convertView, ViewGroup parent) {
		String gropuName = getGroup(groupPosition).getType().name();
    if (convertView == null) {
        LayoutInflater infalInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = infalInflater.inflate(R.layout.group_item,
                null);
    }
    TextView item = (TextView) convertView.findViewById(R.id.cardGroup);
    item.setText(gropuName);
    return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
	private CharSequence getCardReputationText(CardEntry entry) {
		Integer repoCost = deck.calculateReputationCost(entry);
		if (repoCost > 0){
			return StringUtils.repeat(context.getResources().getString(R.string.reputation_dot), repoCost);
		}
		return "";
	}

	private String getCardCountText(CardEntry entry) {
		return String.valueOf(entry.getCount());
	}

	private String getCardNameText(CardEntry entry) {
		return entry.getCard().getName();
	}

	public void updateGroups(Deck deck) {
		this.groups = deck.getGroupedEntries();
	}

}
