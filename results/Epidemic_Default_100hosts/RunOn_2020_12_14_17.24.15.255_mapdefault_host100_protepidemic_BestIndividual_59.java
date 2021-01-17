package routing;
import core.Settings;
public class BestIndividual_59 extends ActiveRouter{ 
	public BestIndividual_59(Settings s) {
		super(s);
	}
	protected BestIndividual_59(BestIndividual_59 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if(((((isTransferring() != isTransferring()) != (isTransferring() || canStartTransfer())) || ((canStartTransfer() != canStartTransfer()) || (canStartTransfer() || canStartTransfer()))) || ((!isTransferring() || (canStartTransfer() || isTransferring())) != (!isTransferring() || (isTransferring() || canStartTransfer()))))){if(!!isTransferring()){if((canStartTransfer() || isTransferring())){super.update();
this.tryAllMessagesToAllConnections();}}
if(isTransferring()){this.tryAllMessagesToAllConnections();}
if(isTransferring()){super.update();}
if((isTransferring() || canStartTransfer())){exchangeDeliverableMessages();
return;}}	}


	@Override
	public BestIndividual_59 replicate() {
		return new BestIndividual_59(this);
	}
}