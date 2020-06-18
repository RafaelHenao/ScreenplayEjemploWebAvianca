package com.bancolombia.screenplay.avianca.step_definitions;

import com.bancolombia.screenplay.avianca.events.EnUnFrame;
import com.bancolombia.screenplay.avianca.model.busqueda.BuscarVuelos;
import com.bancolombia.screenplay.avianca.model.busqueda.FechaDeSalida;
import com.bancolombia.screenplay.avianca.tasks.Navegar;
import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.util.List;

import static com.bancolombia.screenplay.avianca.questions.ClasesDeVueloDisponibles.verQueCadaClaseDeVueloEstaEn;
import static com.bancolombia.screenplay.avianca.user_interface.Opcion.SeleccionaTuViaje;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ConsultaDeTiquetesStepDefinitions {
    @Before
    public void set_the_stage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Dado("^que (.*) ha decidido consultar la disponibilidad de vuelos")
    public void haDecididoConsultarLaDisponibilidadDeVuelos(String nombrePersona) throws Throwable {
        theActorCalled(nombrePersona).attemptsTo(
                Navegar.a(SeleccionaTuViaje)
        );
    }

    @Cuando("^mira los vuelos de (.*) a (.*) partiendo desde (.*)")
    public void BuscaLosVuelos(String origen, String destino, String fechaDeSalida) throws Throwable {
        theActorInTheSpotlight().attemptsTo(
                BuscarVuelos.paraViajeDeSoloIda()
                        .de(origen)
                        .a(destino)
                        .partiendoDesde(FechaDeSalida.valueOf(fechaDeSalida))
        );
    }

    @Entonces("^deberia ver las siguientes opciones de clase de vuelo:$")
    public void DeberiaLasSiguientesOpcionesDeClaseDeVuelo(List<String> clasesDeVuelo) throws Throwable {
        EnUnFrame.llamado("FrameAmadeus")
                .entonces(theActorInTheSpotlight())
                .espera(verQueCadaClaseDeVueloEstaEn(clasesDeVuelo));
    }
}
