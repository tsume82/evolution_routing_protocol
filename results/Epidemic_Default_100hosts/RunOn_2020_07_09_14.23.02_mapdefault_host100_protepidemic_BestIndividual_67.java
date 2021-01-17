package routing;
import core.Settings;
public class BestIndividual_67 extends ActiveRouter{ 
	public BestIndividual_67(Settings s) {
		super(s);
	}
	protected BestIndividual_67(BestIndividual_67 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if(((isTransferring() || isTransferring()) != (canStartTransfer() || isTransferring()))){exchangeDeliverableMessages();
return;}
if(((canStartTransfer() || isTransferring()) || !canStartTransfer())){if(canStartTransfer()){exchangeDeliverableMessages();}
if(isTransferring()){exchangeDeliverableMessages();}}
if((((isTransferring() || isTransferring()) || (canStartTransfer() || canStartTransfer())) != ((isTransferring() != isTransferring()) || (canStartTransfer() != canStartTransfer())))){if(canStartTransfer()){this.tryAllMessagesToAllConnections();}
if(canStartTransfer()){this.tryAllMessagesToAllConnections();}
if(isTransferring()){exchangeDeliverableMessages();}
exchangeDeliverableMessages();}	}


	@Override
	public BestIndividual_67 replicate() {
		return new BestIndividual_67(this);
	}
}