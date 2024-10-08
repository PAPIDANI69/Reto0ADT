package vista;

import controlador.Controlador;

import java.util.ArrayList;

import java.util.List;
import modelo.Convocatoria;
import modelo.Dificultad;
import modelo.Enunciado;
import modelo.UnidadDidactica;
/**
 *
 * @author 2dam
*/

public class VConsola {
   
    public static void main(String[] args) {
        Integer opc = 0;
        Controlador con = new Controlador();


        do{
            
            System.out.println("1. Crear una unidad didáctica y convocatoria.\n"+
            "2. Crear enunciado de examen.\n"+
            "3. Consultar enunciado.\n"+
            "4. Consultar convocatorias.\n"+
            "5. Visualizar el documento de enunciado.\n"+
            "6. Asignar enunciado a convocatoria.\n"+
            "0. Salir\n"+
 opc = utilidades.Utilidades.leerInt(0, 6);
            switch (opc) {
                case 1:

                    //opcion1();
                crearUnidadDidacticaConvocatoria(con);
                    break;
                case 2:
                    //opcion2();
                crearEnunciado(con);
                    break;
                case 3:
                    //opcion3();
             //   consultarEnunciadosDeUnidadDidactica(con);
                    break;
                 
                case 4:

                    consultarConvocatorias(con);
                    break;
                case 5:
                    // Implementar opción 5
                    break;
                case 6:
                    // Implementar opción 6
                	asignarEnunciadoAConvocatoria(con);

                    break;
                case 0:
                    System.out.println("Hasta la vista.");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
                    break;

                }

        }while(opc!=0);
    }
        //Este metodo crea una Unidad didáctica (UnidadDidactica), lo guarda en la base de datos y luego redirige al metodo crearConvocatoria.

        private static void crearUnidadDidacticaConvocatoria(Controlador con) {
                
          UnidadDidactica nuevo= new UnidadDidactica();
          
          nuevo.setAcronimo(utilidades.Utilidades.introducirCadena("Introduce el acronimo de la Unidad Didactica"));
          nuevo.setTitulo(utilidades.Utilidades.introducirCadena("Introduce el titulo de la Unidad Didactica"));
          nuevo.setEvaluacion(utilidades.Utilidades.introducirCadena("Introduce la evaluacion de la Unidad Didactica"));
          nuevo.setDescripcion(utilidades.Utilidades.introducirCadena("Introduce la descripcion de la Unidad Didactica"));
          // 
          
          boolean existeUnidad= con.crearUnidadDidactica(nuevo);
          if (existeUnidad) {
            System.out.println("Unidad didáctica creada con éxito.");
        } else {
            System.out.println("Error al crear la unidad didáctica.");
        }
        
          crearConvocatoria(con);
    }
            //Este metodo crea objeto Convocatoria y lo guarda en la base de datos.
        private static void crearConvocatoria(Controlador con) {
          
          Convocatoria nuevo= new Convocatoria();
          
          System.out.println("Ahora introduce los datos de la convocatoria");
          nuevo.setDescripcion(utilidades.Utilidades.introducirCadena("Introduce la descripcion de la convocatoria"));
          nuevo.setFecha(utilidades.Utilidades.pidoFechaDMA("Introduce la fecha de la Convocatoria"));
          nuevo.setCurso(utilidades.Utilidades.introducirCadena("Introduce el curso de la convocatoria"));
          
        String descripcionEnunciado = utilidades.Utilidades.introducirCadena("Introduce la descripcion del enunciado que quieres asociar:");
        boolean existeConvocatoria = con.crearConvocatoria(nuevo, descripcionEnunciado); 
         if (existeConvocatoria) {
            System.out.println("Convocatoria creada con éxito.");
        } else {
            System.out.println("Error al crear la convocatoria.");
        }
    }
        
      private static void crearEnunciado(Controlador con) {
                
          Enunciado nuevo= new Enunciado();
          
          nuevo.setDescripcion(utilidades.Utilidades.introducirCadena("Introduce la descripcion del enunciado"));
          nuevo.setDificultad(Dificultad.validarDificultad("Introduce la dificultad del enunciado"));
          nuevo.setDisponible(utilidades.Utilidades.leerRespuesta("¿El enuciado esta disponible? si/no"));
          nuevo.setRuta(utilidades.Utilidades.introducirCadena("Introduce la ruta del enunciado"));
          // 
          String acronimoUnidad = utilidades.Utilidades.introducirCadena("Introduce el acronimo de la unidad didactica que quieres asociar");
        boolean existeEnunciado = con.crearEnunciado(nuevo, acronimoUnidad); 
        if (existeEnunciado) {
            System.out.println("enunciado creada con éxito.");
        } else {
            System.out.println("Error al crear la enunciado.");
        }
    }


       //Este metodo busca en lka base de datos las convocatorias segun un nombre de enunciado que da el usuario.
     private static void consultarConvocatorias(Controlador con) {

    String enunciado = utilidades.Utilidades.introducirCadena("Introduce el enunciado de la convocatoria a consultar:");
    

    List<Convocatoria> convocatorias = con.consultarConvocatoriasPorEnunciado(enunciado); 

    if (convocatorias.isEmpty()) {
        System.out.println("No se encontraron convocatorias para el enunciado especificado.");
    } else {
        System.out.println("Convocatorias encontradas:");
        for (Convocatoria convocatoria : convocatorias) {
            System.out.println("Descripción: " + convocatoria.getDescripcion());
            System.out.println("Fecha: " + convocatoria.getFecha());
            System.out.println("Curso: " + convocatoria.getCurso());
            System.out.println("-----------------------");
        }
    }
}
   //Este metodo muestra una convocatoria en base el nombre introducido, le muestra sus datos y si el usuario quiere, le permite cambiar el idEnunciado de la convocatoria para que se asigne a otro enunciado.
    private static void asignarEnunciadoAConvocatoria(Controlador con) {
     
        String nombreConvocatoria = utilidades.Utilidades.introducirCadena("Introduce el nombre de la convocatoria que deseas modificar:");
        
        Convocatoria convocatoria = con.buscarConvocatoriaPorNombre(nombreConvocatoria);
        
        if (convocatoria == null) {
            System.out.println("No se encontró una convocatoria con ese nombre.");
            return;
        }
        
        String nombreEnunciadoActual = con.obtenerNombreEnunciadoPorConvocatoria(nombreConvocatoria);
        System.out.println("El enunciado asociado actualmente es: " + nombreEnunciadoActual);
        
        boolean cambiarEnunciado = utilidades.Utilidades.leerRespuesta("¿Deseas cambiar el enunciado asociado a esta convocatoria? (si/no)");
        
        if (cambiarEnunciado) {
            String nuevoNombreEnunciado = utilidades.Utilidades.introducirCadena("Introduce el nombre del nuevo enunciado:");
            
            boolean exito = con.actualizarEnunciadoConvocatoria(nombreConvocatoria, nuevoNombreEnunciado);
            
            if (exito) {
                System.out.println("Enunciado actualizado correctamente.");
            } else {
                System.out.println("Hubo un problema al actualizar el enunciado.");
            }
        }
    }

}
