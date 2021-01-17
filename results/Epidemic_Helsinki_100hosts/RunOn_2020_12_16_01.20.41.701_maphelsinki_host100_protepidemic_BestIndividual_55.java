package routing;
import core.Settings;
public class BestIndividual_55 extends ActiveRouter{ 
	public BestIndividual_55(Settings s) {
		super(s);
	}
	protected BestIndividual_55(BestIndividual_55 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if(((((isTransferring() != canStartTransfer()) || (canStartTransfer() != canStartTransfer())) || !(canStartTransfer() || canStartTransfer())) || (((isTransferring() || canStartTransfer()) || (isTransferring() || isTransferring())) || (!canStartTransfer() || (((((isTransferring() != canStartTransfer()) || isTransferring()) != (canStartTransfer() != isTransferring())) != ((isTransferring() || isTransferring()) != (isTransferring() != isTransferring()))) != isTransferring()))))){if(((canStartTransfer() != canStartTransfer()) || (canStartTransfer() || canStartTransfer()))){if((canStartTransfer() != isTransferring())){super.update();
exchangeDeliverableMessages();
return;}}
exchangeDeliverableMessages();
super.update();
if(canStartTransfer()){exchangeDeliverableMessages();}
if(canStartTransfer()){this.tryAllMessagesToAllConnections();}
if(canStartTransfer()){super.update();}}	}


	@Override
	public BestIndividual_55 replicate() {
		return new BestIndividual_55(this);
	}
}