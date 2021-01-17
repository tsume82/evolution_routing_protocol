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
if(!!canStartTransfer()){exchangeDeliverableMessages();
if((canStartTransfer() || (!isTransferring() != (isTransferring() || isTransferring())))){if(isTransferring()){this.tryAllMessagesToAllConnections();}}
if(!isTransferring()){if(isTransferring()){super.update();}}}	}


	@Override
	public BestIndividual_77 replicate() {
		return new BestIndividual_77(this);
	}
}