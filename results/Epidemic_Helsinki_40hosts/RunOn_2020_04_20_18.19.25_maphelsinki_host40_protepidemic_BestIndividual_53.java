package routing;
import core.Settings;
public class BestIndividual_53 extends ActiveRouter{ 
	public BestIndividual_53(Settings s) {
		super(s);
	}
	protected BestIndividual_53(BestIndividual_53 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if(((((isTransferring() != isTransferring()) || (canStartTransfer() != canStartTransfer())) != (!isTransferring() != !isTransferring())) != !((canStartTransfer() != isTransferring()) != (isTransferring() != canStartTransfer())))){if((((isTransferring() != isTransferring()) || (canStartTransfer() != canStartTransfer())) || ((canStartTransfer() || canStartTransfer()) || !isTransferring()))){exchangeDeliverableMessages();}}	}


	@Override
	public BestIndividual_53 replicate() {
		return new BestIndividual_53(this);
	}
}