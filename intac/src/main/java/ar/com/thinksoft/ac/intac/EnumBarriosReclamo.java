package ar.com.thinksoft.ac.intac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum EnumBarriosReclamo {
	
	Comuna1("Comuna 1"),
	Comuna2("Comuna 2"),
	Comuna3("Comuna 3"),
	Comuna4("Comuna 4"),
	Comuna5("Comuna 5"),
	Comuna6("Comuna 6"),
	Comuna7("Comuna 7"),
	Comuna8("Comuna 8"),
	Comuna9("Comuna 9"),
	Comuna10("Comuna 10"),
	Comuna11("Comuna 11"),
	Comuna12("Comuna 12"),
	Comuna13("Comuna 13"),
	Comuna14("Comuna 14"),
	Comuna15("Comuna 15"),
	
	
	
	Agronomia("Agronomía","Comuna 15"),
	Almagro("Almagro","Comuna 5"),
	Balvanera("Balvanera","Comuna 3"),
	Barracas("Barracas","Comuna 4"),
	Belgrano("Belgrano","Comuna 13"),
	Boedo("Boedo","Comuna 5"),
	Caballito("Caballito","Comuna 6"),
	Chacarita("Chacarita","Comuna 15"),
	Coghlan("Coghlan","Comuna 12"),
	Colegiales("Colegiales","Comuna 15"),
	Constitucion("Constitución","Comuna 1"),
	Flores("Flores","Comuna 7"),
	Floresta("Floresta","Comuna 10"),
	LaBoca("La Boca","Comuna 4"),
	LaPaternal("La Paternal","Comuna 15"),
	Liniers("Liniers","Comuna 9"),
	Mataderos("Mataderos","Comuna 9"),
	MonteCastro("Monte Castro","Comuna 10"),
	Montserrat("Montserrat","Comuna 1"),
	NuevaPompeya("Nueva Pompeya","Comuna 4"),
	Nuniez("Nuñez","Comuna 13"),
	Palermo("Palermo","Comuna 14"),
	ParqueAvellaneda("Parque Avellaneda","Comuna 9"),
	ParqueChacabuco("Parque Chacabuco","Comuna 7"),
	ParqueChas("Parque Chas","Comuna 15"),
	ParquePatricios("Parque Patricios","Comuna 4"),
	PuertoMadero("Puerto Madero","Comuna 1"),
	Recoleta("Recoleta","Comuna 2"),
	Retiro("Retiro","Comuna 1"),
	Saavedra("Saavedra","Comuna 12"),
	SanCristobal("San Cristóbal","Comuna 3"),
	SanNicolas("San Nicolás","Comuna 1"),
	SanTelmo("San Telmo","Comuna 1"),
	VelezSarsfield("Vélez Sarsfield","Comuna 10"),
	Versalles("Versalles","Comuna 10"),
	VillaCrespo("Villa Crespo","Comuna 15"),
	VilladelParque("Villa del Parque","Comuna 11"),
	VillaDevoto("Villa Devoto","Comuna 11"),
	VillaGeneralMitre("Villa General Mitre","Comuna 11"),
	VillaLugano("Villa Lugano","Comuna 8"),
	VillaLuro("Villa Luro","Comuna 10"),
	VillaOrtuzar("Villa Ortúzar","Comuna 15"),
	VillaPueyrredon("Villa Pueyrredón","Comuna 12"),
	VillaReal("Villa Real","Comuna 10"),
	VillaRiachuelo("Villa Riachuelo","Comuna 8"),
	VillaSantaRita("Villa Santa Rita","Comuna 11"),
	VillaSoldati("Villa Soldati","Comuna 8"),
	VillaUrquiza("Villa Urquiza","Comuna 12");
	
	
	
	private String barrio;
	private String comuna;
	private static List<String> listaBarriosReclamo = new ArrayList<String>();
	private static List<String> listaComunasPorBarrioReclamo = new ArrayList<String>();
	private static List<String> listaComunasReclamo = new ArrayList<String>();
	 
	
	private EnumBarriosReclamo(String comuna) {
    	this.comuna = comuna;
    }
	
    private EnumBarriosReclamo(String barrio,String comuna) {
    	this.barrio = barrio;
    	this.comuna = comuna;
    }
    
    public String getBarrio() {
    	return barrio;
    }
    
    public String getComuna(){
    	return comuna;
    }
    
    public static String getComunaDeBarrio(String barrio) throws Exception{
    	Map<String,String> lista = getLista();
    	if(lista.get(barrio)!= null)
    		return lista.get(barrio);
    	else
    		throw new Exception("no se encontro el barrio");
    }
    
    private static Map<String,String> getLista(){
    	Map<String,String> map = new HashMap<String,String>();
    	listaBarriosReclamo = getListaBarriosReclamo();
    	listaComunasPorBarrioReclamo = getListaComunasPorBarrioReclamo();
    	
    	for(int i=0;i<listaBarriosReclamo.size();i++){
    		map.put(listaBarriosReclamo.get(i), listaComunasPorBarrioReclamo.get(i));
    	}
    	
    	return map;
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
    
    public static List<String> getListaComunasPorBarrioReclamo(){
    	listaComunasPorBarrioReclamo.clear();
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.Agronomia.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.Almagro.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.Balvanera.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.Barracas.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.Belgrano.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.Boedo.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.Caballito.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.Chacarita.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.Coghlan.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.Colegiales.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.Constitucion.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.Flores.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.Floresta.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.LaBoca.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.LaPaternal.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.Liniers.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.Mataderos.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.MonteCastro.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.Montserrat.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.NuevaPompeya.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.Nuniez.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.Palermo.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.ParqueAvellaneda.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.ParqueChacabuco.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.ParqueChas.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.ParquePatricios.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.PuertoMadero.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.Recoleta.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.Retiro.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.Saavedra.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.SanCristobal.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.SanNicolas.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.SanTelmo.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.VelezSarsfield.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.Versalles.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.VillaCrespo.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.VilladelParque.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.VillaDevoto.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.VillaGeneralMitre.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.VillaLugano.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.VillaLuro.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.VillaOrtuzar.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.VillaPueyrredon.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.VillaReal.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.VillaRiachuelo.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.VillaSantaRita.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.VillaSoldati.getComuna());
    	listaComunasPorBarrioReclamo.add(EnumBarriosReclamo.VillaUrquiza.getComuna());
    	
    	return listaComunasPorBarrioReclamo;
    }

    public static List<String> getListaComunasReclamo(){
    	listaComunasReclamo.clear();
    	
    	listaComunasReclamo.add(EnumBarriosReclamo.Comuna1.getComuna());
    	listaComunasReclamo.add(EnumBarriosReclamo.Comuna2.getComuna());
    	listaComunasReclamo.add(EnumBarriosReclamo.Comuna3.getComuna());
    	listaComunasReclamo.add(EnumBarriosReclamo.Comuna4.getComuna());
    	listaComunasReclamo.add(EnumBarriosReclamo.Comuna5.getComuna());
    	listaComunasReclamo.add(EnumBarriosReclamo.Comuna6.getComuna());
    	listaComunasReclamo.add(EnumBarriosReclamo.Comuna7.getComuna());
    	listaComunasReclamo.add(EnumBarriosReclamo.Comuna8.getComuna());
    	listaComunasReclamo.add(EnumBarriosReclamo.Comuna9.getComuna());
    	listaComunasReclamo.add(EnumBarriosReclamo.Comuna10.getComuna());
    	listaComunasReclamo.add(EnumBarriosReclamo.Comuna11.getComuna());
    	listaComunasReclamo.add(EnumBarriosReclamo.Comuna12.getComuna());
    	listaComunasReclamo.add(EnumBarriosReclamo.Comuna13.getComuna());
    	listaComunasReclamo.add(EnumBarriosReclamo.Comuna14.getComuna());
    	listaComunasReclamo.add(EnumBarriosReclamo.Comuna15.getComuna());
    	
    	return listaComunasReclamo;
    }

}
