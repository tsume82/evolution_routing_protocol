package routing;
import core.Settings;
public class BestIndividual_84 extends ActiveRouter{ 
	public BestIndividual_84(Settings s) {
		super(s);
	}
	protected BestIndividual_84(BestIndividual_84 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if(((((isTransferring() != isTransferring()) || (isTransferring() || isTransferring())) != ((isTransferring() != isTransferring()) || (isTransferring() != isTransferring()))) != (!(isTransferring() || isTransferring()) != (!isTransferring() != (canStartTransfer() || canStartTransfer()))))){if(((canStartTransfer() || ((isTransferring() != isTransferring()) != (isTransferring() || canStartTransfer()))) || (isTransferring() != canStartTransfer()))){exchangeDeliverableMessages();
if(isTransferring()){this.tryAllMessagesToAllConnections();}}
if(((canStartTransfer() != isTransferring()) != (isTransferring() || isTransferring()))){if((isTransferring() || isTransferring())){exchangeDeliverableMessages();
super.update();}}}	}


	@Override
	public BestIndividual_84 replicate() {
		return new BestIndividual_84(this);
	}
}