package com.example.PrimerProyectoTIC1;

import com.example.PrimerProyectoTIC1.CentroDeportivoP.Actividad;
import com.example.PrimerProyectoTIC1.EmpleadoP.VentanaActividadController;
import com.example.PrimerProyectoTIC1.EmpleadoP.VistaEmpleadoController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PrimerProyectoTic1ApplicationTests {

	@Test
	void contextLoads() {

	}
	@Test
	void obtenerNombreCentros(){
		VentanaActividadController vent=new VentanaActividadController();
		List<String> centros=vent.nombreDeCentrosDeportivos("futbol");
		assertEquals(2,centros.size());
	}
	@Test
	void obtenerHorariosDeAct(){
		VentanaActividadController vent=new VentanaActividadController();
		List<String> horarios=vent.horariosDeActividad("hockey");
		assertEquals(1,horarios.size());
	}
	@Test
	void obtenerHorariosActividadConNombreCentro(){
		VentanaActividadController vent=new VentanaActividadController();
		List<String> horarios=vent.obtenerHorarioConNombreCentroYNombreActividad("hockey","Nautico");
		assertEquals("10 horas",horarios.get(0));
	}
	@Test
	void obtenerCentroActividadConHorario(){
		VentanaActividadController vent= new VentanaActividadController();
		List<String> centro=vent.obtenerCentrosAPartirDeUnHorariodeActividad("hockey","8 horas");
		assertEquals(2,centro.size());
		assertEquals("Nautico",centro.get(1));
	}
	@Test
	void getData() throws IOException {
		VistaEmpleadoController vista=new VistaEmpleadoController();
		List<Actividad> actividades=vista.getData();
		assertEquals(actividades.get(0).getCentroDeportivo1().getNombre(),"casa teter");
	}

}
