package routing;
import core.Settings;
public class BestIndividual_80 extends ActiveRouter{ 
	public BestIndividual_80(Settings s) {
		super(s);
	}
	protected BestIndividual_80(BestIndividual_80 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if((!(!isTransferring() || (canStartTransfer() != isTransferring())) || !((canStartTransfer() || canStartTransfer()) != (isTransferring() != canStartTransfer())))){if(isTransferring()){if(canStartTransfer()){this.tryAllMessagesToAllConnections();}
this.tryAllMessagesToAllConnections();
this.tryAllMessagesToAllConnections();}
exchangeDeliverableMessages();
super.update();
if((canStartTransfer() != isTransferring())){return;}
if((canStartTransfer() || isTransferring())){super.update();}}	}


	@Override
	public BestIndividual_80 replicate() {
		return new BestIndividual_80(this);
	}
}