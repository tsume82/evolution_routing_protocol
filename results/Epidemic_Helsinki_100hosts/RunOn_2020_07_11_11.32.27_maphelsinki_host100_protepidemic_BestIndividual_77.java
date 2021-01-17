package routing;
import core.Settings;
public class BestIndividual_77 extends ActiveRouter{ 
	public BestIndividual_77(Settings s) {
		super(s);
	}
	protected BestIndividual_77(BestIndividual_77 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if(((isTransferring() || canStartTransfer()) || (isTransferring() || isTransferring()))){if((((isTransferring() || isTransferring()) || (isTransferring() != canStartTransfer())) || ((isTransferring() != canStartTransfer()) || !canStartTransfer()))){if((!canStartTransfer() != (isTransferring() || canStartTransfer()))){if(isTransferring()){this.tryAllMessagesToAllConnections();}
exchangeDeliverableMessages();
super.update();
return;}}}	}


	@Override
	public BestIndividual_77 replicate() {
		return new BestIndividual_77(this);
	}
}