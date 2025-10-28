import java.util.Random;

public class App {
    Random random = new Random();
    // Probabilidades climáticas expresadas en tanto por 1
    final float PROBABILIDAD_DE_LLUVIA_FUERTE = 0.1f;
    final float PROBABILIDAD_DE_LLUVIA_NORMAL = 0.3f;
    final float PROBABILIDAD_DE_SOL = 0.6f;

    // Penalización de velocidad a Marco por el clima
    final float REDUCCION_VELOCIDAD_MARCO_LLUVIA = 0.25f;

    // Penalización de velocidad a Marco por el clima
    final float REDUCCION_VELOCIDAD_CARRUAJE_POR_LLUVIA_FUERTE = 0.5f;
    final float REDUCCION_VELOCIDAD_CARRUAJE_POR_LLUVIA = 0.25f;

    // Probabilidades de que Amedio se canse o se escape expresadas en tanto por 1
    final float PROBABILIDAD_AMEDIO_SE_CANSE = 0.25f;
    final float PROBABILIDAD_AMEDIO_SE_ESCAPE = 0.15f;
    // Es posible que AMEDIO se canse y se escape
    // si Amedio de escapa, -2 horas de viaje ese dia

    // si Amedio se cansa, Marco va un 10% más lento
    final float REDUCCION_VELOCIDAD_MARCO_POR_AMEDIO_CANSADO = 0.1f;

    // Calcula si Amedio se cansa o no
    public boolean AmedioSeCansa() {
        double probabilidadAmedioSeCansa = random.nextDouble();

        if (probabilidadAmedioSeCansa <= PROBABILIDAD_AMEDIO_SE_CANSE) {
            System.out
                    .println("He tenido que cargar a Amedio porque se ha cansado!");
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

    /*
     * Calcula la probabilidad Climatica del dia en base a las
     * constantes definidas. Devuelve la reducción de velocidad del
     * carruaje, 50% por lluvia fuerte, 25% por lluvia normal y 0% por
     * buen tiempo
     */
    public float ProbabilidadClimaticaMadreMarco() {
        double probabilidadClimatica = random.nextDouble();

        if (probabilidadClimatica <= PROBABILIDAD_DE_LLUVIA_FUERTE) {
            System.out.println("A mamá le ha llovido muchísimo!");
            return REDUCCION_VELOCIDAD_CARRUAJE_POR_LLUVIA_FUERTE;
        } else if (probabilidadClimatica <= PROBABILIDAD_DE_LLUVIA_NORMAL + PROBABILIDAD_DE_LLUVIA_FUERTE) {
            System.out.println("A mamá le ha llovido un poco");
            return REDUCCION_VELOCIDAD_CARRUAJE_POR_LLUVIA;
        } else {
            System.out.println("A mamá le ha hecho un buen día");
            return 0f;
        }

    }

    // Calcula el avance de Marco en un dia teniendo en cuenta las variables
    // climáticas y el estado de Amedio. También muestra el progreso diario
    public float AvanzarUnDiaMarco() {
        float velocidadPromedioMarcoPorDia = (Math.round(random.nextFloat(9f, 16f) * 100f)) / 100f;
        float horasDeTrayectoRecorridoMarcoPorDia = Math.round(random.nextFloat(7f, 11f) * 100f) / 100f;       

        if (ProbabilidadClimatica()) {// Determina la condición climática y ajusta la velocidad dependiendo de esta.
            velocidadPromedioMarcoPorDia -= velocidadPromedioMarcoPorDia * REDUCCION_VELOCIDAD_MARCO_LLUVIA;
        }

        if (AmedioSeEscapa()) {// Determina si Amedio se escapa y ajusta las horas de viaje en consecuencia.
            horasDeTrayectoRecorridoMarcoPorDia -= 2f;
        }

        if (AmedioSeCansa()) {// Determina si Amedio se cansa y ajusta la velocidad en consecuencia.
            velocidadPromedioMarcoPorDia -= velocidadPromedioMarcoPorDia * REDUCCION_VELOCIDAD_MARCO_POR_AMEDIO_CANSADO;
        }

        float distanciaRecorrida = Math
                .round((velocidadPromedioMarcoPorDia * horasDeTrayectoRecorridoMarcoPorDia) * 100f)
                / 100f;
                
        System.out.println("Avance " + horasDeTrayectoRecorridoMarcoPorDia + " horas a " + velocidadPromedioMarcoPorDia
                + " km/h recorriendo " + distanciaRecorrida + " km");

        return distanciaRecorrida;
    }

    // Calcula el avance de la madre de Marco en un dia
    // teniendo en cuenta las variables climáticas. También muestra el progreso
    // diario
    public float AvanzarUnDiaMadreMarco() {

        float velocidadMadreMarco = (Math.round(random.nextFloat(5, 10) * 100f)) / 100f; // Promedio de velocidad en km/h
        float horasDeTrayectoRecorridoMadreMarcoPorDia = (Math.round(random.nextFloat(5, 10) * 100f)) / 100f; // horas de viaje

        velocidadMadreMarco -= velocidadMadreMarco * ProbabilidadClimaticaMadreMarco(); //Determina la condición climática y ajusta la velocidad dependiendo de esta.

        float distanciaRecorridaMadreMarco = Math
                .round(velocidadMadreMarco * horasDeTrayectoRecorridoMadreMarcoPorDia * 100f) / 100f;

        System.out.println("Mamá avanza " + horasDeTrayectoRecorridoMadreMarcoPorDia + " horas a " + velocidadMadreMarco
                + " km/h, recorriendo " + distanciaRecorridaMadreMarco + " km");
        return distanciaRecorridaMadreMarco;

    }

    // Inicia el viaje de Marco y su madre, mostrando el progreso diario hasta que
    // se reúnan
    public void IniciarViaje() {
        int dia = 1;
        float distanciaEntreMarcoYMadre = 350f;// km

        System.out.println("DIARIO DEL VIAJE DE MARCO");
        System.out.println("============================");
        while (true) {
            System.out.print("\n");
            System.out.println("- - - - - - - - - - - - - - - - -");

            System.out.println("Día " + dia);

            distanciaEntreMarcoYMadre -= AvanzarUnDiaMarco();// Marco se acerca a su madre

            System.out.print("\n");

            distanciaEntreMarcoYMadre += AvanzarUnDiaMadreMarco();// la madre se aleja de Marco

            distanciaEntreMarcoYMadre = Math.round(distanciaEntreMarcoYMadre * 100f) / 100f;// redondeo a 2 decimales

            if (distanciaEntreMarcoYMadre < 0f) // Evita distancias negativas
                distanciaEntreMarcoYMadre = 0f;

            System.out.println("\nAl final del día " + dia + " la distancia entre Marco y su madre es de "
                    + Math.round(distanciaEntreMarcoYMadre) + " km");

            if (distanciaEntreMarcoYMadre == 0f) {
                System.err.print("\n");
                System.out.println("* * * * * * * * * * * * * * * * * * * * * *");

                System.out.println("Marco ha logrado reunirse con su madre en el día " + dia + "!!!\n");
                break;
            }
            dia++;

        }

    }

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.IniciarViaje();
    }
}
