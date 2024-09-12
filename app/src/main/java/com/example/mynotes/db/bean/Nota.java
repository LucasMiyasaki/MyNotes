package com.example.mynotes.db.bean;

public class Nota {
   private int id;
   private String titulo;
   private String texto;
   private int prioridade;

   public Nota(int id, String titulo, String texto, int prioridade) {
      this.id = id;
      this.titulo = titulo;
      this.texto = texto;
      this.prioridade = prioridade;
   }

   public Nota(String titulo, String texto, int prioridade) {
      this.titulo = titulo;
      this.texto = texto;
      this.prioridade = prioridade;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getTitulo() {
      return titulo;
   }

   public void setTitulo(String titulo) {
      this.titulo = titulo;
   }

   public String getTexto() {
      return texto;
   }

   public void setTexto(String texto) {
      this.texto = texto;
   }

   public int getPrioridade() {
      return prioridade;
   }

   public void setPrioridade(int prioridade) {
      this.prioridade = prioridade;
   }
}
