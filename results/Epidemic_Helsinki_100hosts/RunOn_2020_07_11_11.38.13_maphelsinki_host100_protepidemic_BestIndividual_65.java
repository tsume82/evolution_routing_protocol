package routing;
import core.Settings;
public class BestIndividual_65 extends ActiveRouter{ 
	public BestIndividual_65(Settings s) {
		super(s);
	}
	protected BestIndividual_65(BestIndividual_65 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if((((canStartTransfer() != isTransferring()) || (canStartTransfer() != canStartTransfer())) != (!canStartTransfer() || (isTransferring() || canStartTransfer())))){if(isTransferring()){this.tryAllMessagesToAllConnections();}
if((!isTransferring() || isTransferring())){if(canStartTransfer()){super.update();}}}
exchangeDeliverableMessages();	}


	@Override
	public BestIndividual_65 replicate() {
		return new BestIndividual_65(this);
	}
}