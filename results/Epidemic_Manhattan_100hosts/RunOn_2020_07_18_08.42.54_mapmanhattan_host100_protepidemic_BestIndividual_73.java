package routing;
import core.Settings;
public class BestIndividual_73 extends ActiveRouter{ 
	public BestIndividual_73(Settings s) {
		super(s);
	}
	protected BestIndividual_73(BestIndividual_73 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if(((!(canStartTransfer() || isTransferring()) != (!canStartTransfer() || (isTransferring() != canStartTransfer()))) || (isTransferring() != ((isTransferring() != isTransferring()) || (canStartTransfer() != (((isTransferring() != isTransferring()) || ((isTransferring() != (isTransferring() || canStartTransfer())) || (canStartTransfer() != isTransferring()))) || ((((canStartTransfer() != canStartTransfer()) != (canStartTransfer() || canStartTransfer())) != isTransferring()) != (canStartTransfer() || isTransferring())))))))){if(((isTransferring() != isTransferring()) || (canStartTransfer() != isTransferring()))){super.update();
exchangeDeliverableMessages();
if(canStartTransfer()){return;}
exchangeDeliverableMessages();
exchangeDeliverableMessages();
super.update();}
if((!isTransferring() != (isTransferring() || canStartTransfer()))){this.tryAllMessagesToAllConnections();
if((!isTransferring() != (isTransferring() || canStartTransfer()))){this.tryAllMessagesToAllConnections();
this.tryAllMessagesToAllConnections();
super.update();
return;}
super.update();
return;}}	}


	@Override
	public BestIndividual_73 replicate() {
		return new BestIndividual_73(this);
	}
}