package routing;
import core.Settings;
public class BestIndividual_50 extends ActiveRouter{ 
	public BestIndividual_50(Settings s) {
		super(s);
	}
	protected BestIndividual_50(BestIndividual_50 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if(!(((canStartTransfer() || isTransferring()) || (canStartTransfer() != isTransferring())) != ((canStartTransfer() || isTransferring()) != !canStartTransfer()))){if(canStartTransfer()){if((canStartTransfer() != isTransferring())){if(isTransferring()){this.tryAllMessagesToAllConnections();}}
if((canStartTransfer() || isTransferring())){super.update();
exchangeDeliverableMessages();}}}	}


	@Override
	public BestIndividual_50 replicate() {
		return new BestIndividual_50(this);
	}
}