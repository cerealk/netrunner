package it.ck.cyberdeck.presentation.adapter;

import java.util.List;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.*;
import it.ck.cyberdeck.model.group.ElementGroup;
import it.ck.cyberdeck.presentation.util.ImageFactory;
import android.content.Context;
import android.view.*;
import android.widget.*;

public class CardLibraryExpandableListAdapter extends BaseExpandableListAdapter {

	private List<ElementGroup<Card>> groups;
	private Context context;

	public CardLibraryExpandableListAdapter(Context context, List<ElementGroup<Card>>values){
		this.context = context;
		this.groups = values;
		
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
		
		
		Card card = (Card) getChild(groupPosition, childPosition);
		
		View childView;
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			childView = inflater.inflate(R.layout.rowlayout,null );
		} else 
			childView = convertView;
		
		TextView label = childView.findViewById(R.id.cardName);
		label.setText(card.getName());
		
		ImageView icon = childView.findViewById(R.id.icon);
		icon.setImageBitmap(ImageFactory.getFactionImage(context, card.getSide(), card.getFaction()));
		return childView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return groups.get(groupPosition).getCards().size();
	}

	@Override
	public ElementGroup<Card> getGroup(int groupPosition) {
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
    TextView item = convertView.findViewById(R.id.cardGroup);
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

}
