package bean;

import main.DealerMachine;


public class Dealer extends Person{

	private DealerMachine machine;
	private Card openCard;

	public Dealer(String name) {
		super(name);
	}
	
	@Override
	public void addCard(Card card) {
		if(card == null) return;
		if(getHandCard().size() == 0) {
			openCard = card;
		}
		super.addCard(card);
	}
	
	public void setDealerMachine(DealerMachine machine){
		this.machine = machine;
	}
	
	public DealerMachine getDealerMachine(){
		return machine;
	}
	
	public void shuffle(){
		if(machine != null)
			machine.shuffle();
	}
	
	public Card dealCard(){
		return machine.deal();
	}

	public Card getOpenCard() {
		return openCard;
	}

}
