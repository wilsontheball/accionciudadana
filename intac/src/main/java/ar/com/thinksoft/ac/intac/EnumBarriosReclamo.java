package ar.com.thinksoft.ac.intac;

import java.util.ArrayList;
import java.util.List;

public enum EnumBarriosReclamo {
	
	Agronomia("Agronomia"),
	Almagro("Almagro"),
	Balvanera("Balvanera"),
	Barracas("Barracas"),
	Belgrano("Belgrano"),
	Boedo("Boedo"),
	Caballito("Caballito"),
	Chacarita("Chacarita"),
	Coghlan("Coghlan"),
	Colegiales("Colegiales"),
	Constitucion("Constitución"),
	Flores("Flores"),
	Floresta("Floresta"),
	LaBoca("La Boca"),
	LaPaternal("La Paternal"),
	Liniers("Liniers"),
	Mataderos("Mataderos"),
	MonteCastro("Monte Castro"),
	Montserrat("Montserrat"),
	NuevaPompeya("Nueva Pompeya"),
	Nuniez("Nuñez"),
	Palermo("Palermo"),
	ParqueAvellaneda("Parque Avellaneda"),
	ParqueChacabuco("Parque Chacabuco"),
	ParqueChas("Parque Chas"),
	ParquePatricios("Parque Patricios"),
	PuertoMadero("Puerto Madero"),
	Recoleta("Recoleta"),
	Retiro("Retiro"),
	Saavedra("Saavedra"),
	SanCristobal("San Cristóbal"),
	SanNicolas("San Nicolás"),
	SanTelmo("San Telmo"),
	VelezSarsfield("Vélez Sarsfield"),
	Versalles("Versalles"),
	VillaCrespo("Villa Crespo"),
	VilladelParque("Villa del Parque"),
	VillaDevoto("Villa Devoto"),
	VillaGeneralMitre("Villa General Mitre"),
	VillaLugano("Villa Lugano"),
	VillaLuro("Villa Luro"),
	VillaOrtuzar("Villa Ortúzar"),
	VillaPueyrredon("Villa Pueyrredón"),
	VillaReal("Villa Real"),
	VillaRiachuelo("Villa Riachuelo"),
	VillaSantaRita("Villa Santa Rita"),
	VillaSoldati("Villa Soldati"),
	VillaUrquiza("Villa Urquiza");
	
	private String barrio;
	private static List<String> listaBarriosReclamo = new ArrayList<String>();
	 
    private EnumBarriosReclamo(String barrio) {
    	this.barrio = barrio;
    }
    
    public static List<String> getListaBarriosReclamo(){
    	listaBarriosReclamo.clear();
		listaBarriosReclamo.add(EnumBarriosReclamo.Agronomia.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.Almagro.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.Balvanera.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.Barracas.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.Belgrano.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.Boedo.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.Caballito.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.Chacarita.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.Coghlan.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.Colegiales.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.Constitucion.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.Flores.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.Floresta.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.LaBoca.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.LaPaternal.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.Liniers.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.Mataderos.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.MonteCastro.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.Montserrat.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.NuevaPompeya.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.Nuniez.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.Palermo.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.ParqueAvellaneda.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.ParqueChacabuco.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.ParqueChas.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.ParquePatricios.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.PuertoMadero.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.Recoleta.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.Retiro.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.Saavedra.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.SanCristobal.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.SanNicolas.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.SanTelmo.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.VelezSarsfield.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.Versalles.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.VillaCrespo.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.VilladelParque.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.VillaDevoto.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.VillaGeneralMitre.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.VillaLugano.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.VillaLuro.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.VillaOrtuzar.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.VillaPueyrredon.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.VillaReal.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.VillaRiachuelo.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.VillaSantaRita.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.VillaSoldati.getBarrio());
    	listaBarriosReclamo.add(EnumBarriosReclamo.VillaUrquiza.getBarrio());
    	
    	return listaBarriosReclamo;
    }

    public String getBarrio() {
    	return barrio;
    }

}
