package routing;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import routing.util.RoutingInfo;
import util.Tuple;
import core.Connection;
import core.DTNHost;
import core.Message;
import core.Settings;
import core.SimClock;
public class BestIndividual_59 extends ActiveRouter{ 
public static final double P_INIT = 0.75;
	public static final double DEFAULT_BETA = 0.25;
	public static final double DEFAULT_GAMMA = 0.98;
	public static final String PROPHET_NS = "ProphetRouter";
	public static final String SECONDS_IN_UNIT_S ="secondsInTimeUnit";
	public static final String BETA_S = "beta";
	public static final String GAMMA_S = "gamma";
	private int secondsInTimeUnit;
	private double beta;
	private double gamma;
	private Map<DTNHost, Double> preds;
	private double lastAgeUpdate;	public BestIndividual_59(Settings s) {
		super(s);
		Settings prophetSettings = new Settings(PROPHET_NS);
		secondsInTimeUnit = prophetSettings.getInt(SECONDS_IN_UNIT_S);
		if (prophetSettings.contains(BETA_S)) {
			beta = prophetSettings.getDouble(BETA_S);
		}
		else {
			beta = DEFAULT_BETA;
		}

		if (prophetSettings.contains(GAMMA_S)) {
			gamma = prophetSettings.getDouble(GAMMA_S);
		}
		else {
			gamma = DEFAULT_GAMMA;
		}

		initPreds();
	}
	protected BestIndividual_59(BestIndividual_59 r) {
		super(r);
		this.secondsInTimeUnit = r.secondsInTimeUnit;
		this.beta = r.beta;
		this.gamma = r.gamma;
		initPreds();
	}
	private void initPreds() {
		this.preds = new HashMap<DTNHost, Double>();
	}

	@Override
	public void changedConnection(Connection con) {
		super.changedConnection(con);

		if (con.isUp()) {
			DTNHost otherHost = con.getOtherNode(getHost());
			updateDeliveryPredFor(otherHost);
			updateTransitivePreds(otherHost);
		}
	}
	private void updateDeliveryPredFor(DTNHost host) {
		double oldValue = getPredFor(host);
		double newValue = oldValue + (1 - oldValue) * P_INIT;
		preds.put(host, newValue);
	}
public double getPredFor(DTNHost host) {
		ageDeliveryPreds();
		if (preds.containsKey(host)) {
			return preds.get(host);
		}
		else {
			return 0;
		}
	}
	private void updateTransitivePreds(DTNHost host) {
		MessageRouter otherRouter = host.getRouter();
		assert otherRouter instanceof BestIndividual_59 : "PRoPHET only works " +
			" with other routers of same type";

		double pForHost = getPredFor(host); 
		Map<DTNHost, Double> othersPreds =
			((BestIndividual_59)otherRouter).getDeliveryPreds();

		for (Map.Entry<DTNHost, Double> e : othersPreds.entrySet()) {
			if (e.getKey() == getHost()) {
				continue; 
			}

			double pOld = getPredFor(e.getKey()); 
			double pNew = pOld + ( 1 - pOld) * pForHost * e.getValue() * beta;
			preds.put(e.getKey(), pNew);
		}
	}
	private void ageDeliveryPreds() {
		double timeDiff = (SimClock.getTime() - this.lastAgeUpdate) /
			secondsInTimeUnit;
		if (timeDiff == 0) {
			return;
		}
		double mult = Math.pow(gamma, timeDiff);
		for (Map.Entry<DTNHost, Double> e : preds.entrySet()) {
			e.setValue(e.getValue()*mult);
		}
		this.lastAgeUpdate = SimClock.getTime();
	}
	private Map<DTNHost, Double> getDeliveryPreds() {
		ageDeliveryPreds();
		return this.preds;
	}
	private Tuple<Message, Connection> tryOtherMessages() {
		List<Tuple<Message, Connection>> messages =
			new ArrayList<Tuple<Message, Connection>>();
		Collection<Message> msgCollection = getMessageCollection();
		for (Connection con : getConnections()) {
			DTNHost other = con.getOtherNode(getHost());
			BestIndividual_59 othRouter = (BestIndividual_59)other.getRouter();

			if (othRouter.isTransferring()) {
				continue; 
			}

			for (Message m : msgCollection) {
				if (othRouter.hasMessage(m.getId())) {
					continue;
				}
				if (othRouter.getPredFor(m.getTo()) > getPredFor(m.getTo())) {
					messages.add(new Tuple<Message, Connection>(m,con));
				}
			}
		}

		if (messages.size() == 0) {
			return null;
		}
		Collections.sort(messages, new TupleComparator());
		return tryMessagesForConnected(messages);
	}
	private class TupleComparator implements Comparator
		<Tuple<Message, Connection>> {
		public int compare(Tuple<Message, Connection> tuple1,
				Tuple<Message, Connection> tuple2) {
			double p1 = ((BestIndividual_59)tuple1.getValue().
					getOtherNode(getHost()).getRouter()).getPredFor(
					tuple1.getKey().getTo());
			double p2 = ((BestIndividual_59)tuple2.getValue().
					getOtherNode(getHost()).getRouter()).getPredFor(
					tuple2.getKey().getTo());
			if (p2-p1 == 0) {
				return compareByQueueMode(tuple1.getKey(), tuple2.getKey());
			}
			else if (p2-p1 < 0) {
				return -1;
			}
			else {
				return 1;
			}
		}
	}

	@Override
	public RoutingInfo getRoutingInfo() {
		ageDeliveryPreds();
		RoutingInfo top = super.getRoutingInfo();
		RoutingInfo ri = new RoutingInfo(preds.size() +
				" delivery prediction(s)");
		for (Map.Entry<DTNHost, Double> e : preds.entrySet()) {
			DTNHost host = e.getKey();
			Double value = e.getValue();
			ri.addMoreInfo(new RoutingInfo(String.format("%s : %.6f",
					host, value)));
		}
		top.addMoreInfo(ri);
		return top;
	}

	@Override
	public MessageRouter replicate() {
		BestIndividual_59 r = new BestIndividual_59(this);
		return r;
	}	@Override
	public void update() {
		super.update();
if((!!(canStartTransfer() != canStartTransfer()) != (((isTransferring() || isTransferring()) != (canStartTransfer() != isTransferring())) != (canStartTransfer() != (canStartTransfer() || isTransferring()))))){if((canStartTransfer() || (!isTransferring() || (isTransferring() != canStartTransfer())))){if((isTransferring() || ((!(isTransferring() != isTransferring()) || (!isTransferring() || (canStartTransfer() != canStartTransfer()))) != (((isTransferring() || canStartTransfer()) || (isTransferring() || isTransferring())) || (isTransferring() || isTransferring()))))){super.update();
this.tryAllMessagesToAllConnections();}
if((isTransferring() || canStartTransfer())){super.update();
exchangeDeliverableMessages();}}}	}
}