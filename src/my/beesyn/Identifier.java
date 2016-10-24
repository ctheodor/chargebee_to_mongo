/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.beesyn;

/**
 *
 * @author Christos Theodorakis
 */
public class Identifier {
    private String fetched_string;
    private String category;
    private String _id;
    private String checksum;
    
    public Identifier()
    {
     fetched_string= new String();
     category = new String();
     _id = new String();
     checksum = new String();
    }
    
    public boolean isEmpty(){
        return(this.fetched_string.isEmpty());
    }
    
    public Identifier cclone(){
        Identifier new_id = new Identifier();
        new_id._id = this._id;
        new_id.category = this.category;
        new_id.checksum = this.checksum;
        new_id.fetched_string = this.fetched_string;
        return(new_id);
    }
    
    public void add(String dat){
        fetched_string = dat;
    }
    
    public String get_fetched(){
        return(this.fetched_string);
    }
    
    public void set_cat(String cat){
        this.category = cat;
    }
    public String get_cat(){
        return(this.category);
    }
    
    public void set_id(String id){
        this._id = id;
    }
    public String get_id(){
        return(this._id);
    }
    
    public void set_chk(String chk){
        this.checksum = chk;
    }
    
    public String get_chk(){
        return(this.checksum);
    }
}
