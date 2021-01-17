package routing;
import core.Settings;
public class BestIndividual_68 extends ActiveRouter{ 
	public BestIndividual_68(Settings s) {
		super(s);
	}
	protected BestIndividual_68(BestIndividual_68 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if(!((isTransferring() != isTransferring()) != (canStartTransfer() != isTransferring()))){if(((canStartTransfer() != canStartTransfer()) || (isTransferring() || canStartTransfer()))){if((isTransferring() || (isTransferring() || isTransferring()))){if(isTransferring()){this.tryAllMessagesToAllConnections();}}}}
exchangeDeliverableMessages();
if(!(isTransferring() != canStartTransfer())){if(((canStartTransfer() || isTransferring()) != isTransferring())){this.tryAllMessagesToAllConnections();
super.update();}}	}


	@Override
	public BestIndividual_68 replicate() {
		return new BestIndividual_68(this);
	}
}