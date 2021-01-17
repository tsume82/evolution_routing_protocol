package routing;
import core.Settings;
public class BestIndividual_100 extends ActiveRouter{ 
	public BestIndividual_100(Settings s) {
		super(s);
	}
	protected BestIndividual_100(BestIndividual_100 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if(isTransferring()){this.tryAllMessagesToAllConnections();}
exchangeDeliverableMessages();	}


	@Override
	public BestIndividual_100 replicate() {
		return new BestIndividual_100(this);
	}
}