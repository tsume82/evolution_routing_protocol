package routing;
import core.Settings;
public class BestIndividual_59 extends ActiveRouter{ 
	public BestIndividual_59(Settings s) {
		super(s);
	}
	protected BestIndividual_59(BestIndividual_59 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if((((isTransferring() || canStartTransfer()) || (isTransferring() != canStartTransfer())) || ((isTransferring() != canStartTransfer()) || (isTransferring() || canStartTransfer())))){if((canStartTransfer() != isTransferring())){exchangeDeliverableMessages();}
if((canStartTransfer() != canStartTransfer())){if(canStartTransfer()){return;}}}
if((!canStartTransfer() || (isTransferring() != canStartTransfer()))){if((canStartTransfer() || canStartTransfer())){if(canStartTransfer()){exchangeDeliverableMessages();}}}
if((((isTransferring() != canStartTransfer()) || (isTransferring() != isTransferring())) || isTransferring())){if((canStartTransfer() || canStartTransfer())){this.tryAllMessagesToAllConnections();
super.update();}}	}


	@Override
	public BestIndividual_59 replicate() {
		return new BestIndividual_59(this);
	}
}