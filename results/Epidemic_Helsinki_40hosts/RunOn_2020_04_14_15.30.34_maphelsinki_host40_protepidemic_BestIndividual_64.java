package routing;
import core.Settings;
public class BestIndividual_64 extends ActiveRouter{ 
	public BestIndividual_64(Settings s) {
		super(s);
	}
	protected BestIndividual_64(BestIndividual_64 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if((!((isTransferring() != isTransferring()) || (isTransferring() != canStartTransfer())) != (((canStartTransfer() || canStartTransfer()) || (isTransferring() || isTransferring())) != (!canStartTransfer() != !canStartTransfer())))){if((((canStartTransfer() || (((canStartTransfer() || canStartTransfer()) || (isTransferring() || isTransferring())) != (!canStartTransfer() != !canStartTransfer()))) != !isTransferring()) != !(isTransferring() || (isTransferring() != isTransferring())))){if(canStartTransfer()){exchangeDeliverableMessages();}
if(canStartTransfer()){exchangeDeliverableMessages();}
if((canStartTransfer() || canStartTransfer())){if(isTransferring()){this.tryAllMessagesToAllConnections();}}
super.update();
super.update();
if(!isTransferring()){if(isTransferring()){return;}}}}	}


	@Override
	public BestIndividual_64 replicate() {
		return new BestIndividual_64(this);
	}
}