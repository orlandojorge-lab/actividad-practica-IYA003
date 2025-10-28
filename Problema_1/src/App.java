import java.util.Random;

public class App {
    Random random = new Random();
    // Probabilidades climáticas expresadas en tanto por 1
    final float PROBABILIDAD_DE_LLUVIA_FUERTE = 0.1f;
    final float PROBABILIDAD_DE_LLUVIA_NORMAL = 0.3f;
    final float PROBABILIDAD_DE_SOL = 0.6f;

    // Penalización de velocidad a Marco por el clima
    final float REDUCCION_VELOCIDAD_MARCO_LLUVIA = 0.25f;

    // Probabilidades de que Amedio se canse o se escape expresadas en tanto por 1
    final float PROBABILIDAD_AMEDIO_SE_CANSE = 0.25f;
    final float PROBABILIDAD_AMEDIO_SE_ESCAPE = 0.15f;
    // Es posible que AMEDIO se canse y se escape
    // si Amedio de escapa, -2 horas de viaje ese dia

    // si Amedio se cansa, Marco va un 10% más lento
    final float REDUCCION_VELOCIDAD_MARCO_POR_AMEDIO_CANSADO = 0.1f;

    final int VELOCIDAD_MADRE_MARCO = 80;// km por dia
    //Utilizo 80 km porque con 100 km Marco nunca alcanza a su madre

    public static void main(String[] args) {
        App app = new App();
        app.IniciarViaje();
    }

    public void IniciarViaje() {
        int dia = 1;
        float distanciaEntreMarcoMadre = 350f;// km

        System.out.println("DIARIO DEL VIAJE DE MARCO");
        System.out.println("============================\n");
        while (true) {
            System.out.println("- - - - - - - - - - - - - - - - -");

            System.out.println("Día " + dia);

            distanciaEntreMarcoMadre -= AvanzarUnDiaMarco(); // Marco se acerca a su madre

            System.out.print("\n");

            distanciaEntreMarcoMadre += AvanzarUnDiaMadreMarco(); // La madre de Marco se aleja de Marco

            distanciaEntreMarcoMadre = Math.round(distanciaEntreMarcoMadre * 100f) / 100f; // Redondea a 2 decimales

            if (distanciaEntreMarcoMadre < 0)
                distanciaEntreMarcoMadre = 0;

            System.out.println("Al final del día " + dia + " la distancia entre Marco y su madre es de "
                    + distanciaEntreMarcoMadre + " km\n");

            if (distanciaEntreMarcoMadre == 0) { // Comprueba si Marco ha alcanzado a su madre
                System.out.println("* * * * * * * * * * * * * * * * * * * * * *");
                System.out.println("Marco ha logrado reunirse con su madre en el día " + dia + "!!!");
                break;
            }

            dia++;

        }

    }

    // Calcula si Amedio se cansa o no
    public boolean AmedioSeCansa() {
        double probabilidadAmedioSeCansa = random.nextDouble();

        if (probabilidadAmedioSeCansa <= PROBABILIDAD_AMEDIO_SE_CANSE) {
            System.out
                    .println("¡He tenido que cargar a Amedio porque se ha cansado!");
            return true;
        }
        return false;

    }

    // Calcula si Amedio se escapa o no
    public boolean AmedioSeEscapa() {
        double probabilidadAmedioSeEscapa = random.nextDouble();

        if (probabilidadAmedioSeEscapa <= PROBABILIDAD_AMEDIO_SE_ESCAPE) {
            System.out.println("¡He perdido tiempo buscando a Amedio!");
            return true;
        }
        return false;

    }

    // Calcula la probabilidad Climatica del dia en base a las constantes definidas
    public boolean ProbabilidadClimatica() {
        double probabilidadClimatica = random.nextDouble();

        if (probabilidadClimatica <= PROBABILIDAD_DE_LLUVIA_FUERTE) {
            System.out.println("¡Ha llovido muchísimo!");
            return true;
        } else if (probabilidadClimatica <= PROBABILIDAD_DE_LLUVIA_NORMAL + PROBABILIDAD_DE_LLUVIA_FUERTE) {
            System.out.println("Ha llovido un poco");
            return true;
        } else {
            System.out.println("Ha hecho buen día");
            return false;
        }

    }

    // Calcula el avance de Marco en un dia teniendo en cuenta las variables
    // climáticas y el estado de Amedio
    public float AvanzarUnDiaMarco() {
        float velocidadPromedioMarcoPorDia = (Math.round(random.nextFloat(9, 16) * 100f)) / 100f;

        float horasDeTrayectoRecorridoMarcoPorDia = Math.round(random.nextFloat(7, 11) * 100f) / 100f;

        if (ProbabilidadClimatica()) {
            velocidadPromedioMarcoPorDia -= velocidadPromedioMarcoPorDia * REDUCCION_VELOCIDAD_MARCO_LLUVIA;
        }

        if (AmedioSeEscapa()) {
            horasDeTrayectoRecorridoMarcoPorDia -= 2f;
        }

        if (AmedioSeCansa()) {
            velocidadPromedioMarcoPorDia -= velocidadPromedioMarcoPorDia * REDUCCION_VELOCIDAD_MARCO_POR_AMEDIO_CANSADO;
        }

        float distanciaRecorrida = Math.round(velocidadPromedioMarcoPorDia * horasDeTrayectoRecorridoMarcoPorDia * 100f)
                / 100f;

        System.out.println("Avancé " + horasDeTrayectoRecorridoMarcoPorDia + " horas a " + velocidadPromedioMarcoPorDia
                + " km/h recorriendo " + distanciaRecorrida + " km");

        return distanciaRecorrida;
    }

    // Calcula el avance de la madre de Marco en un dia
    public float AvanzarUnDiaMadreMarco() {
        System.out.println("Mamá pudo avanza 80 km hoy");
        return VELOCIDAD_MADRE_MARCO;
    }

    // Inicia el viaje de Marco y su madre,
    // mostrando el progreso diario hasta que se reúnan

}
