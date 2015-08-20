package bean;

import java.util.ArrayList;
import java.util.List;

import strategy.Rule;

public class HandCard {

	public List<Card> handCards = new ArrayList<Card>();
	public int bet;
	
	public int getPoint(){
		return Rule.calPoints(this);
	}
	
	public void addCard(Card card){
		if(card != null)
			handCards.add(card);
	}
	
	public int size(){
		return handCards.size();
	}
	
	public Card get(int index) {
		return handCards.get(index);
	}

	public Card remove(int i) {
		return handCards.remove(i);
	}

	public void initClear() {
		handCards.clear();
//		bet = 0;
	}

	public void setBetChip(int chip) {
		this.bet = chip;
	}
	
	public int getBetChip(){
		return bet;
	}
	
}
