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
if((((!isTransferring() != (isTransferring() || canStartTransfer())) != ((isTransferring() != isTransferring()) != !canStartTransfer())) != !isTransferring())){if((canStartTransfer() || isTransferring())){if(canStartTransfer()){super.update();}}
if((isTransferring() != canStartTransfer())){exchangeDeliverableMessages();
if(isTransferring()){super.update();}
return;}
if((canStartTransfer() || canStartTransfer())){exchangeDeliverableMessages();
exchangeDeliverableMessages();}
exchangeDeliverableMessages();
super.update();
if(isTransferring()){this.tryAllMessagesToAllConnections();}}	}


	@Override
	public BestIndividual_60 replicate() {
		return new BestIndividual_60(this);
	}
}