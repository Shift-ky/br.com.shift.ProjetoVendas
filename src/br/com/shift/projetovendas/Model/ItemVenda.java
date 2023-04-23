/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.shift.projetovendas.Model;

/**
 *
 * @author robsonPaiva
 */
public class ItemVenda {

    //Atributos
    private int id;
    private Vendas venda;
    private Produtos produto;
    private int Qtd;
    private double subotal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vendas getVenda() {
        return venda;
    }

    public void setVenda(Vendas venda) {
        this.venda = venda;
    }

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public int getQtd() {
        return Qtd;
    }

    public void setQtd(int Qtd) {
        this.Qtd = Qtd;
    }

    public double getSubotal() {
        return subotal;
    }

    public void setSubotal(double subotal) {
        this.subotal = subotal;
    }
    
    

}
