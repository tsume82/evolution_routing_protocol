package routing;
import core.Settings;
public class BestIndividual_94 extends ActiveRouter{ 
	public BestIndividual_94(Settings s) {
		super(s);
	}
	protected BestIndividual_94(BestIndividual_94 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if(!!(canStartTransfer() != !(((canStartTransfer() || isTransferring()) || ((isTransferring() != canStartTransfer()) || isTransferring())) || canStartTransfer()))){if(canStartTransfer()){super.update();}
super.update();
if((isTransferring() || isTransferring())){super.update();
this.tryAllMessagesToAllConnections();}
if(((canStartTransfer() || canStartTransfer()) || isTransferring())){super.update();
exchangeDeliverableMessages();
this.tryAllMessagesToAllConnections();}}	}


	@Override
	public BestIndividual_94 replicate() {
		return new BestIndividual_94(this);
	}
}