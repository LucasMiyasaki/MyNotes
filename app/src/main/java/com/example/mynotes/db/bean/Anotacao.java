package com.example.mynotes.db.bean;

public class Anotacao {
   private int id;
   private String titulo;
   private String texto;
   private String prioridade;

   public Anotacao(int id, String titulo, String texto, String prioridade) {
      this.id = id;
      this.titulo = titulo;
      this.texto = texto;
      this.prioridade = prioridade;
   }

   public Anotacao() {
      this(0, "", "", "");
   }

   public Anotacao(String titulo, String texto, String prioridade) {
      this(0, titulo, texto, prioridade);
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getTexto() {
      return texto;
   }

   public void setTexto(String texto) {
      this.texto = texto;
   }

   public String getPrioridade() {
      return prioridade;
   }

   public void setPrioridade(String prioridade) {
      this.prioridade = prioridade;
   }

   public String getTitulo() {
      return titulo;
   }

   public void setTitulo(String titulo) {
      this.titulo = titulo;
   }
}
