package routing;
import core.Settings;
public class BestIndividual_60 extends ActiveRouter{ 
	public BestIndividual_60(Settings s) {
		super(s);
	}
	protected BestIndividual_60(BestIndividual_60 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if((((!isTransferring() || (isTransferring() != canStartTransfer())) || ((isTransferring() || canStartTransfer()) != (isTransferring() != canStartTransfer()))) || (((isTransferring() != canStartTransfer()) != (canStartTransfer() != canStartTransfer())) || (isTransferring() != !canStartTransfer())))){if((!canStartTransfer() || (isTransferring() != canStartTransfer()))){if(isTransferring()){exchangeDeliverableMessages();}
if(canStartTransfer()){exchangeDeliverableMessages();}
return;}
if((canStartTransfer() || canStartTransfer())){this.tryAllMessagesToAllConnections();
if(canStartTransfer()){exchangeDeliverableMessages();}}}	}


	@Override
	public BestIndividual_60 replicate() {
		return new BestIndividual_60(this);
	}
}