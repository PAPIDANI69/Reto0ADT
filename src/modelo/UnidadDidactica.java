/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import utilidades.Utilidades;

/**
 *
 * @author 2dam
 */
public class UnidadDidactica {
    private Integer id;
    private String acronimo = "";
    private String titulo = "";
    private String evaluacion = "";
    private String descripcion = "";

  
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDatos(){
        this.acronimo= Utilidades.introducirCadena("introduce el acronimo de la unidad ");
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
   
}