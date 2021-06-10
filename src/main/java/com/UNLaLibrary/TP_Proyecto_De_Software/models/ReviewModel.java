package com.UNLaLibrary.TP_Proyecto_De_Software.models;

public class ReviewModel implements Comparable<ReviewModel>{
    private long idReview;
    private int valoracion;
    private String titulo;
    private String comentario;
    private UserModel autor;
    private DocumentoModel documento;

    public ReviewModel() {
    }

    public ReviewModel(long idReview, int valoracion, String titulo, String comentario, UserModel autor, DocumentoModel documento) {
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

    public UserModel getAutor() {
        return this.autor;
    }

    public void setAutor(UserModel autor) {
        this.autor = autor;
    }

    public DocumentoModel getDocumento() {
        return this.documento;
    }

    public void setDocumento(DocumentoModel documento) {
        this.documento = documento;
    }

    public int compareTo(ReviewModel reviewModel){
        if(this.idReview > reviewModel.getIdReview()){
            return 1;
        }
        else{
            return -1;
        }
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
