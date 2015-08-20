package main;

import bean.Card;
import bean.CardColor;
import strategy.Rule;


public class DeckCard {

	public static final int DECK_LENGTH = 52;
	public static final int SUTI_LENGTH = 13;
	
	public CardColor[] suits = new CardColor[]{
		CardColor.SPADE, CardColor.HEART, CardColor.CLUB, CardColor.DIAMOND
	};
	
	public String[] names = new String[]{
		"A", "2", "3", "4", "5", "6", "7", "8", "9","10", "J", "Q", "K"};
	
	private Card[] cards = new Card[DECK_LENGTH];

	private DeckCard(){}

	public Card initCard(int index){
		if(index < 0 || index >= DECK_LENGTH)
			return null;
		Card card = new Card();
		card.setColor(suits[index / SUTI_LENGTH]);
		card.setName(names[index % SUTI_LENGTH]);
		card.setValue(index % SUTI_LENGTH + 1);
		card.setPoint(Rule.setPoint(card));
		return card;
	}
	
	public static DeckCard newInstance(){
		DeckCard deck = new DeckCard();
		deck.initCards();
		return deck;
	}

	private void initCards(){
		for(int i = 0; i < DECK_LENGTH; i++){
			cards[i] = initCard(i);
		}
	}
	
	public Card[] getCards() {
		return cards;
	}
	
	public void setCards(Card[] cards) {
		this.cards = cards;
	}
	
	public void print(){
		for(Card card : cards){
			System.out.println(card.toString());
		}
	}
	
	public static void main(String[] args) {
		DeckCard.newInstance().print();
	}
}
