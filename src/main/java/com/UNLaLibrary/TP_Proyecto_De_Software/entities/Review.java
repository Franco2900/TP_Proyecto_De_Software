package com.UNLaLibrary.TP_Proyecto_De_Software.entities;

import javax.persistence.*;

@Entity
public class Review{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReview;
    private int valoracion;
    private String titulo;
    private String comentario;
    @ManyToOne
    @JoinColumn(name = "autor", referencedColumnName = "id", nullable = false)
    private User autor;
    @ManyToOne
    @JoinColumn(name = "documento", referencedColumnName = "idDocumento", nullable = false)
    private Documento documento;

    public Review() {
    }

    public Review(long idReview, int valoracion, String titulo, String comentario, User autor, Documento documento) {
        this.idReview = idReview;
        this.valoracion = valoracion;
        this.titulo = titulo;
        this.comentario = comentario;
        this.autor = autor;
        this.documento = documento;
    }

    public long getIdReview() {
        return this.idReview;
    }

    public void setIdReview(long idReview) {
        this.idReview = idReview;
    }

    public int getValoracion() {
        return this.valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public User getAutor() {
        return this.autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public Documento getDocumento() {
        return this.documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    @Override
    public String toString() {
        return "{" +
            " idReview='" + getIdReview() + "'" +
            ", valoracion='" + getValoracion() + "'" +
            ", titulo='" + getTitulo() + "'" +
            ", comentario='" + getComentario() + "'" +
            ", autor='" + getAutor() + "'" +
            ", documento='" + getDocumento() + "'" +
            "}";
    }
}