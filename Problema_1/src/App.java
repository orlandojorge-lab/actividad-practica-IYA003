import java.util.Random;

public class App {
    Random random = new Random();

    final float PROBABILIDAD_DE_LLUVIA_FUERTE = 0.1f;
    final float PROBABILIDAD_DE_LLUVIA_NORMAL = 0.3f;
    final float PROBABILIDAD_DE_SOL = 0.6f;

    final float REDUCCION_VELOCIDAD_MARCO_LLUVIA = 0.25f;

    final float PROBABILIDAD_AMEDIO_SE_CANSE = 0.25f;
    final float PROBABILIDAD_AMEDIO_SE_ESCAPE = 0.15f;
    // Es posible que AMEDIO se canse y se escape
    // si Amedio de escapa, -2 horas de viaje ese dia

    // si Amedio se cansa, Marco va un 10% más lento
    final float REDUCCION_VELOCIDAD_MARCO_POR_AMEDIO_CANSADO = 0.1f;

    final int VELOCIDAD_MADRE_MARCO = 100;// km por dia

    public boolean AmedioSeCansa() {
        double probabilidadAmedioSeCansa = random.nextDouble();

        if (probabilidadAmedioSeCansa <= PROBABILIDAD_AMEDIO_SE_CANSE) {
            System.out
                    .println("He tenido que cargar a Amedio porque se ha cansado!");
            return true;
        }
        return false;

    }

    public boolean AmedioSeEscapa() {
        double probabilidadAmedioSeEscapa = random.nextDouble();

        if (probabilidadAmedioSeEscapa <= PROBABILIDAD_AMEDIO_SE_ESCAPE) {
            System.out.println("¡He perdido tiempo buscando a Amedio!");
            return true;
        }
        return false;

    }

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

    public float AvanzarUnDiaMarco(int dia) {
        float velocidadPromedioMarcoPorDia = (Math.round(random.nextFloat(9, 16) * 100f)) / 100f;
        float horasDeTrayectoRecorridoMarcoPorDia = Math.round(random.nextFloat(7, 11) * 100f) / 100f;

        boolean estaLloviendo = ProbabilidadClimatica();
        boolean amedioSeEscapo = AmedioSeEscapa();
        boolean amedioSeCanso = AmedioSeCansa();

        if (estaLloviendo) {
            velocidadPromedioMarcoPorDia -= velocidadPromedioMarcoPorDia * REDUCCION_VELOCIDAD_MARCO_LLUVIA;
        }

        if (amedioSeEscapo) {
            horasDeTrayectoRecorridoMarcoPorDia -= 2f;
        }

        if (amedioSeCanso) {
            velocidadPromedioMarcoPorDia -= velocidadPromedioMarcoPorDia * REDUCCION_VELOCIDAD_MARCO_POR_AMEDIO_CANSADO;
        }

        float distanciaRecorrida = Math.round(velocidadPromedioMarcoPorDia * horasDeTrayectoRecorridoMarcoPorDia * 100f)
                / 100f;
        System.out.println("Avance " + horasDeTrayectoRecorridoMarcoPorDia + " horas a " + velocidadPromedioMarcoPorDia
                + " km/h recorriendo " + distanciaRecorrida + " km");

        return distanciaRecorrida;
    }

    public float AvanzarUnDiaMadreMarco() {
        System.out.println("Mamá pudo avanza 100 km hoy");
        return VELOCIDAD_MADRE_MARCO;
    }

    public void IniciarViaje() {
        int dia = 1;
        float distanciaEntreMarcoMadre = 350f;// km

        System.out.println("DIARIO DEL VIAJE DE MARCO");
        System.out.println("============================\n");
        while (true) {
            System.out.println("- - - - - - - - - - - - - - - - -");

            System.out.println("Día " + dia);

            float distanciaRecorridaMarco = AvanzarUnDiaMarco(dia);

            float distanciaRecorridaMadreMarco = AvanzarUnDiaMadreMarco();

            distanciaEntreMarcoMadre -= (distanciaRecorridaMarco + distanciaRecorridaMadreMarco);

            if (distanciaEntreMarcoMadre < 0f) {
                distanciaEntreMarcoMadre = 0f;
            }

            System.out.println("Al final del día " + dia + " la distancia entre Marco y su madre es de "
                    + distanciaEntreMarcoMadre + " km\n");

            if (distanciaEntreMarcoMadre == 0) {
                System.out.println("* * * * * * * * * * * * * * * * * * * * * *");

                System.out.println("Marco ha logrado reunirse con su madre en el día " + dia + "!!!");
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
