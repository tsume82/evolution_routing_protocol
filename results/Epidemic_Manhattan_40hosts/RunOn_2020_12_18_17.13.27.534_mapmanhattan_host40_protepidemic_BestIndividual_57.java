package routing;
import core.Settings;
public class BestIndividual_57 extends ActiveRouter{ 
	public BestIndividual_57(Settings s) {
		super(s);
	}
	protected BestIndividual_57(BestIndividual_57 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if((((isTransferring() != canStartTransfer()) != (isTransferring() != canStartTransfer())) != ((canStartTransfer() || (canStartTransfer() || isTransferring())) || (isTransferring() != isTransferring())))){if((((isTransferring() || canStartTransfer()) != (isTransferring() != canStartTransfer())) != canStartTransfer())){if(isTransferring()){return;}}
if((isTransferring() != isTransferring())){exchangeDeliverableMessages();
exchangeDeliverableMessages();}}
if((isTransferring() || canStartTransfer())){if(isTransferring()){this.tryAllMessagesToAllConnections();}}
if(canStartTransfer()){exchangeDeliverableMessages();}
this.tryAllMessagesToAllConnections();
exchangeDeliverableMessages();
if(canStartTransfer()){return;}
if(canStartTransfer()){this.tryAllMessagesToAllConnections();}
if((isTransferring() || canStartTransfer())){this.tryAllMessagesToAllConnections();
return;}	}


	@Override
	public BestIndividual_57 replicate() {
		return new BestIndividual_57(this);
	}
}