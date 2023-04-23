/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.shift.projetovendas.Model;

/**
 *
 * @author robsonPaiva
 */
public class Fornecedores extends Clientes {
    
     //Atributos
    private String cnpj;
    
    //Getters e Setters

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
   @Override
    public String toString(){
        return this.getNome();
    }
   
    
    
    
    
}
