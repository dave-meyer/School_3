package interfaces;

/**
 * Das Interface schreibt vor, dass jeder Mitarbeiter seinen Namen und seine
 * Monatsauszahlung bekannt geben kann und eine schöne String-Repräsentation
 * jedes Mitarbeiterobjektes existiert.
 * 
 * @author DI Martin Kampenhuber
 */
public interface IMitarbeiter {
	public String getName();
	public double getMonatsauszahlung();
	public String toString();
}
