package kapitel_19;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class KontoImpl extends UnicastRemoteObject implements KontoI
{
	private int saldo = 100; // man starter med 100 kroner
	private ArrayList<String> bevægelser = new ArrayList<>();
	private String kontohavernavn;

	public KontoImpl() throws RemoteException
	{
	}

	public void overførsel(int kroner, String modtager)
	{
		int nySaldo = saldo + kroner; // her beregnes ny saldo
		String s = "Kontohaver: "+modtager+" Overførsel på "+kroner+" kr. Ny saldo er "+nySaldo+" kr.";

		System.out.println(s);
		try { Thread.sleep(500); } catch (InterruptedException ex) { } // transaktionen gemmes
		saldo = nySaldo; // her er transaktionen gemt
		kontohavernavn = modtager;
		System.out.println("Transaktionen indeholdende:" + kroner + " kr. er gemt.");
		bevægelser.add(s);
	}

	public int saldo()
	{
		System.out.println("Der spørges om saldoen. Den er "+saldo+" kr.");
		return saldo;
	}

	public ArrayList<String> bevægelser()
	{
		System.out.println("Der spørges på alle bevægelser.");
		return bevægelser;
	}

	@Override
	public void withdrawal(int amount) throws RemoteException {
		saldo = saldo-amount;

	}

	@Override
	public String getNavn() throws RemoteException {
		return kontohavernavn;
	}
}