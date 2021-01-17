package routing;
import core.Settings;
public class BestIndividual_72 extends ActiveRouter{ 
	public BestIndividual_72(Settings s) {
		super(s);
	}
	protected BestIndividual_72(BestIndividual_72 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if((isTransferring() || (canStartTransfer() != isTransferring()))){if((isTransferring() != canStartTransfer())){exchangeDeliverableMessages();
if(isTransferring()){this.tryAllMessagesToAllConnections();}}
if((canStartTransfer() || canStartTransfer())){if(isTransferring()){return;}}
if(((canStartTransfer() || isTransferring()) || (isTransferring() || canStartTransfer()))){if(((isTransferring() || isTransferring()) != (isTransferring() || isTransferring()))){this.tryAllMessagesToAllConnections();
return;}}}	}


	@Override
	public BestIndividual_72 replicate() {
		return new BestIndividual_72(this);
	}
}