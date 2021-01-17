package routing;
import core.Settings;
public class BestIndividual_58 extends ActiveRouter{ 
	public BestIndividual_58(Settings s) {
		super(s);
	}
	protected BestIndividual_58(BestIndividual_58 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if(!(((!isTransferring() || (isTransferring() != canStartTransfer())) || ((canStartTransfer() || isTransferring()) || (canStartTransfer() != canStartTransfer()))) != ((canStartTransfer() || canStartTransfer()) != ((canStartTransfer() != isTransferring()) || (isTransferring() != (isTransferring() != canStartTransfer())))))){if(!isTransferring()){super.update();
exchangeDeliverableMessages();}
if(!isTransferring()){exchangeDeliverableMessages();
return;}
if((canStartTransfer() || isTransferring())){if(canStartTransfer()){exchangeDeliverableMessages();}}
if(canStartTransfer()){super.update();}}
if(!isTransferring()){exchangeDeliverableMessages();
return;}
if(canStartTransfer()){if(canStartTransfer()){exchangeDeliverableMessages();}}
if((canStartTransfer() != isTransferring())){this.tryAllMessagesToAllConnections();
return;}
if(canStartTransfer()){this.tryAllMessagesToAllConnections();}
if(isTransferring()){return;}	}


	@Override
	public BestIndividual_58 replicate() {
		return new BestIndividual_58(this);
	}
}