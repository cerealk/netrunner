package it.ck.cyberdeck.model;

import it.ck.cyberdeck.model.reputation.ReputationRule;
import it.ck.cyberdeck.model.reputation.ReputationRuleFactory;
import it.ck.cyberdeck.model.utils.CardKeyComparator;

import java.util.*;

public class CardLibrary {

	private Map<CardKey, Card> cards = new HashMap<CardKey, Card>();
	private ReputationRuleFactory reputationRuleFactory;

	public CardLibrary(ReputationRuleFactory reputationRuleFactory) {
		this.reputationRuleFactory = reputationRuleFactory;
	}

	public List<Card> getCardList() {
		List<Card> cardList = new ArrayList<Card>();
		cardList.addAll(cards.values());
		Collections.sort(cardList, new CardKeyComparator());
		return Collections.unmodifiableList(cardList);
	}

	public List<Identity> getIdentities() {

		List<Identity> identities = new ArrayList<Identity>();
		for (Card card : cards.values()) {
			if (card.isIdentity()){
				CardKey key = card.getKey();
				identities.add(new Identity(card, getReputationRule(key)));
			}
		}
		return Collections.unmodifiableList(identities);
	}

	private ReputationRule getReputationRule(CardKey key) {
		return reputationRuleFactory.createRule(key);
	}

	public List<Card> getCardList(Identity identity) {
		List<Card> result = new ArrayList<Card>();
		for (Card card : cards.values()) {
			if (identity.canUse(card)) {
				result.add(card);
			}
		}
		return result;
	}

	public void addAll(List<Card> cardList) {
		for (Card card : cardList) {
			cards.put(card.getKey(), card);
		}
	}

	public List<CardGroup> getCardGroups(Side side) {
		Map<CardType, CardGroup> cardGroups = new HashMap<CardType, CardGroup>();
		for (Card card : cards.values()) {
			if (card.getSide().equals(side)) {
				CardGroup cardGroup = cardGroups.get(card.getType());
				if (cardGroup == null) {
					cardGroup = new CardGroup(card.getType());
				}
				cardGroup.add(card);
				cardGroups.put(card.getType(), cardGroup);
			}
		}

		return Collections.unmodifiableList(new ArrayList<CardGroup>(cardGroups
				.values()));

	}

	public List<CardGroup> getCardGroups(Identity identity) {
		Map<CardType, CardGroup> cardGroups = populateCardGroup(identity);

		return Collections.unmodifiableList(new ArrayList<CardGroup>(cardGroups.values()));
	}
	
	public List<CardGroup> getCardGroupsWithoutIdentities(Identity identity){
		Map<CardType, CardGroup> cardGroups = populateCardGroup(identity);
		cardGroups.remove(CardType.IDENTITY);
		return Collections.unmodifiableList(new ArrayList<CardGroup>(cardGroups.values()));
	}

	private Map<CardType, CardGroup> populateCardGroup(Identity identity) {
		Map<CardType, CardGroup> cardGroups = new HashMap<CardType, CardGroup>();
		for (Card card : cards.values()) {
			if (card.getSide().equals(identity.side())) {
				CardGroup cardGroup = cardGroups.get(card.getType());
				if (cardGroup == null) {
					cardGroup = new CardGroup(card.getType());
				}
				if (identity.canUse(card)) {
					cardGroup.add(card);
					cardGroups.put(card.getType(), cardGroup);
				}
			}
		}
		return cardGroups;
	}

	public Card getCard(CardKey key) {
		return cards.get(key);
	}

	public Identity getItentity(CardKey key) {
		return new Identity(getCard(key), getReputationRule(key));
	}
}
