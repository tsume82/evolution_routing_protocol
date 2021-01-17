package routing;
import core.Settings;
public class BestIndividual_53 extends ActiveRouter{ 
	public BestIndividual_53(Settings s) {
		super(s);
	}
	protected BestIndividual_53(BestIndividual_53 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if(!(!canStartTransfer() != (canStartTransfer() != isTransferring()))){this.tryAllMessagesToAllConnections();}
if(((!isTransferring() || !canStartTransfer()) || ((!canStartTransfer() || (canStartTransfer() || isTransferring())) || (canStartTransfer() || ((!isTransferring() || !canStartTransfer()) || ((isTransferring() || canStartTransfer()) || (canStartTransfer() || isTransferring()))))))){if(!(canStartTransfer() != isTransferring())){this.tryAllMessagesToAllConnections();}
exchangeDeliverableMessages();
if((isTransferring() != (isTransferring() || canStartTransfer()))){this.tryAllMessagesToAllConnections();
super.update();}
if((isTransferring() != canStartTransfer())){this.tryAllMessagesToAllConnections();
super.update();}}	}


	@Override
	public BestIndividual_53 replicate() {
		return new BestIndividual_53(this);
	}
}